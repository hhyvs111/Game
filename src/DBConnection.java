import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
 public static Connection getConnection(){
  Connection conn = null;
  String driver = "com.mysql.jdbc.Driver";
  String url = "jdbc:mysql://localhost:3306/game";
  String username = "root";
  String password = "123456";
  try {
   Class.forName(driver);
   conn = DriverManager.getConnection(url,username,password);
  } catch (ClassNotFoundException e) {
   e.printStackTrace();
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return conn;
 }
}
