package system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lims.conn.MySQLConnUtils;

public class TestConnection {
	MySQLConnUtils mySQLConnUtils = new MySQLConnUtils();
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn = MySQLConnUtils.getMySQLConnection();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM limsdb.PRODUCT");
		while(rs.next()){
			System.out.println("dung");
		}
		rs.close();
		stm.close();
		conn.close();
	}
}
