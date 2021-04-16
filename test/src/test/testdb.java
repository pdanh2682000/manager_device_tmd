package test;
import java.sql.*;

public class testdb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String host="192.168.1.10";
		String port="5432";
		String dbname="qlbv";
		String user="postgres";
		String pass="123";
		String dburl = "jdbc:postgresql://"+host+":"+port+"/"+dbname+"?loggerLevel=OFF";
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(dburl, user, pass);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        
        try {
            // crate statement
            stmt = conn.createStatement();
            // get data from table 'current.psdangky'
            rs = stmt.executeQuery("select mabn, makb, maba, sott, mathe, ngaybd, ngaykt, mabvdk, macc"
            		+ " from current.psdangky"
            		+ " where"
            		+ " mabn='2020042177'");
            // show data
            while (rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getString(2) 
                        + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5)
                        + "  " + rs.getString(6) + "  " + rs.getString(7) + "  " + rs.getString(8)
                        + "  " + rs.getString(9));
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

	}

}
