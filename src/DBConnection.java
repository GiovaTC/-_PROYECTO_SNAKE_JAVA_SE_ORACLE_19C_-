// =============================
// 3. CONEXION ORACLE
// =============================
import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws Exception{
        String url = "jdbc:oracle:thin:@//localhost:1521/orcl";
        String user = "system";
        String pass = "Tapiero123";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(url, user, pass);
    }
}   


