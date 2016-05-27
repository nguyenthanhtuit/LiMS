package system;

import java.sql.Connection;
import java.sql.SQLException;

import lims.beans.UserAccount;
import lims.conn.ConnectionUtils;
import lims.utils.DBUtils;
import lims.utils.HelperApplication;

public class TestSignUp {
	public static void main(String[] args) {
		Connection conn = null;
		UserAccount user = new UserAccount("hello", "hello", "mail", "name", "name");
		try {
			conn = ConnectionUtils.getConnection();
			
			boolean result = DBUtils.insertAccount(conn, user);
			
			if (result) {
				System.out.println("Sigup success!");
			}
			else {
				System.out.println("Sigup faild");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeQuietly(conn);
		}
		
	}
}
