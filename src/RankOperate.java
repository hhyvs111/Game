import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RankOperate {
 static int i = 1;
 static int x = 0;

 public static List<Rank> queryTen() {
  String sql = "select * from rank  order by score desc limit 10";
  List<Rank> list = query(sql);
  return list;
 }
 
 public static List<Rank> queryMax(){
	 String sql = "select * from rank where id=(select MAX(id) from rank ) ";
	 List<Rank> list = query(sql);
	 return list;
 }
 public static List<Rank> queryUserMax(String name){
	 String sql = "select * from rank where name = '" + name + "'" +
		 	"and score = (select max(score) from rank where name = '" + name + "')";
	 List<Rank> list = query(sql);
	 return list;
 }
 
 public static List<Rank> query(String sql) {
	  Connection conn = DBConnection.getConnection();
	  List<Rank> list = new ArrayList<Rank>();
	  Statement stmt = null;
	  ResultSet rs = null;
	  try {
	   stmt = conn.createStatement();
	   rs = stmt.executeQuery(sql);
	   while (rs.next()) {
	    Rank rank = new Rank();
	    rank.setId(rs.getInt(1));
	    rank.setName(rs.getString(2));
	    rank.setScore(rs.getInt(3));
	    rank.setTime(rs.getDate(4));
	    list.add(rank);
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

 public static boolean add(Rank rank) {
	  Connection conn = DBConnection.getConnection();
	  PreparedStatement pstmt = null;
	  String sql = "insert into rank(name,score,time) values(?,?,?)";
	  int x = 0;
	  try {
	   pstmt = conn.prepareStatement(sql);
	   pstmt.setString(1, rank.getName());
	   pstmt.setInt(2, rank.getScore());
	   pstmt.setDate(3, rank.getTime());
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
