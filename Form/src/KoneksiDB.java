import java.sql.*;

public class KoneksiDB {
    public Connection getKoneksi() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost/mahasiswa?serverTimezone=UTC";
        Connection con = DriverManager.getConnection(url, "root", "");

        return con;
    }
}
