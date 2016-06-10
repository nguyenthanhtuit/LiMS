package system;

import java.sql.Connection;
import java.sql.SQLException;

import lims.beans.UserAccount;
import lims.conn.ConnectionUtils;
import lims.utils.DBUtils;

public class TestSignUp {
	public static void main(String[] args) {
		Connection conn = null;
		UserAccount user = new UserAccount("htmz", "htmz", "htmz", "htmz", "htmz");
		try {
			conn = ConnectionUtils.getConnection();
			System.out.println(user);
			System.out.println(conn);
			boolean result = DBUtils.insertAccount(conn, user);

			if (result) {
				System.out.println("Sigup success!");
			} else {
				System.out.println("Sigup faild");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.closeQuietly(conn);
		}

	}

}
