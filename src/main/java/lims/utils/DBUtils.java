package lims.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lims.beans.UserAccount;

public class DBUtils {

	public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException {
		String sql = "select * from user_account a where a.USER_NAME= ? and a.PASSWORD = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.setString(2, password);

		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			String mail = rs.getString("MAIL");
			String firstName = rs.getString("FIRSTNAME");
			String lastName = rs.getString("LASTNAME");
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setMail(mail);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			return user;
		}
		return null;
	}

	public static UserAccount findUser(Connection conn, String userName) throws SQLException {
		String sql = "select * from user_account a where a.USER_NAME= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			String password = rs.getString("Password");
			String mail = rs.getString("MAIL");
			String firstName = rs.getString("FIRSTNAME");
			String lastName = rs.getString("LASTNAME");
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setMail(mail);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			return user;
		}
		return null;
	}

	public static boolean insertAccount(Connection conn, UserAccount user) {
		String sql = "INSERT INTO `limsdb`.`USER_ACCOUNT` (`USER_NAME`, `PASSWORD`, `MAIL`, `FIRSTNAME`, `LASTNAME`) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUserName());
			pstm.setString(2, user.getPassword());
			pstm.setString(3, user.getMail());
			pstm.setString(4, user.getFirstName());
			pstm.setString(5, user.getLastName());
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean checkMailExist(Connection conn, String email) {
		String sql = "SELECT MAIL FROM USER_ACCOUNT WHERE MAIL = ?";
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			pstm.executeQuery();
			ResultSet rs = pstm.executeQuery();
			if(rs.getString("MAIL")!=null&&rs.getString("MAIL")!="")
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
