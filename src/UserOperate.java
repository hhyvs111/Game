import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserOperate {
 static int i = 1;
 static int x = 0;

 public static List<User> queryUser(String name) {

  String sql = "select * from user where name = '" + name + "'";
  List<User> list = query(sql);
  return list;
 }
 public static List<User> queryUuserMax(String name) {

	  String sql = "select max(score) from user where name = '" + name + "'";
	  List<User> list = query(sql);
	  return list;
	 }
// public static List<User> queryMax(){
//	 String sql = "select * from rank where id=(select MAX(id) from rank ) ";
//	 List<Rank> list = query(sql);
//	 return list;
// }
 
 public static List<User> query(String sql) {
	  Connection conn = DBConnection.getConnection();
	  List<User> list = new ArrayList<User>();
	  Statement stmt = null;
	  ResultSet rs = null;
	  try {
	   stmt = conn.createStatement();
	   rs = stmt.executeQuery(sql);
	   while (rs.next()) {
	    User user = new User();
	    user.setId(rs.getInt(1));
	    user.setName(rs.getString(2));
	    user.setPassword(rs.getString(3));
	    user.setPhone(rs.getString(4));
	    list.add(user);
	   }
	  } catch (SQLException e) {
	   e.printStackTrace();
	  } finally {
	   try {
	    if (rs != null)
	     rs.close();
	    if (stmt != null)
	     stmt.close();
	    if (conn != null)
	     conn.close();
	   } catch (SQLException e) {
	    e.printStackTrace();
	   }
	  }
	  return list;
	 }

 public static boolean add(User user) {
	  Connection conn = DBConnection.getConnection();
	  PreparedStatement pstmt = null;
	  String sql = "insert into user(name,password,phone) values(?,?,?)";
	  int x = 0;
	  try {
	   pstmt = conn.prepareStatement(sql);
	   pstmt.setString(1, user.getName());
	   pstmt.setString(2, user.getPassword());
	   pstmt.setString(3, user.getPhone());
	   x = pstmt.executeUpdate();
	  } catch (SQLException e) {
	   e.printStackTrace();
	  } finally {
	   try {
	    if (pstmt != null)
	     pstmt.close();
	    if (conn != null) {
	     conn.close();
	    }
	   } catch (SQLException e) {
	    e.printStackTrace();
	   }
	  }
	  if (x == 1) {
	   return true;
	  } else {
	   return false;
	  }
	 }
}
