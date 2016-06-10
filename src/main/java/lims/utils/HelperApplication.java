package lims.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lims.beans.UserAccount;
import lims.conn.ConnectionUtils;

public class HelperApplication {
	public  static String getPathRestPage(HttpServletRequest request, HttpServletResponse  response){
		String path= request.getServletPath();
		System.out.println(path);
		path=path.substring(1);
		switch(path){
			case "doLogin":
				path = "/";
			break;
		}
		return ((path.length()==1) ? path : "/"+path);
	}
	public static boolean checkUserSession(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		UserAccount user = new UserAccount();
		user = MyUtils.getUserLogined(session);
		if(user==null)
			return false;
		return true;
	}
	public static boolean checkUserNameExist(HttpServletRequest request, HttpServletResponse response, String userName) throws SQLException{
		Connection conn = MyUtils.getStoreConnection(request);
		UserAccount user = DBUtils.findUser(conn, userName);
		if (user!=null) {
			return true;
		}
		return false;
	}
	public static boolean checkEmailExist(HttpServletRequest request, HttpServletResponse response, String mail){
		Connection conn = MyUtils.getStoreConnection(request);
		return DBUtils.checkMailExist(conn, mail);
	}
	public static boolean newAccount(HttpServletRequest request, HttpServletResponse response, UserAccount user){
		Connection conn = null;
		try {
			conn = ConnectionUtils.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DBUtils.insertAccount(conn, user);
	}
}
