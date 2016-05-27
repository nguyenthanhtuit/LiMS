package lims.utils;

import java.security.MessageDigest;
import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lims.beans.UserAccount;

public class MyUtils {
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";

	// set connection in request attribute
	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION, conn);
	}

	// get connection from request
	public static Connection getStoreConnection(ServletRequest request) {
		Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return conn;
	}

	// set session for user
	public static void storeLoginUser(HttpSession session, UserAccount loginedUser) {
		session.setAttribute(SessionConstant.LOGIN_USER, loginedUser);
	}

	// get user from session user
	public static UserAccount getUserLogined(HttpSession session) {
		UserAccount loginedUser = (UserAccount) session.getAttribute(SessionConstant.LOGIN_USER);
		return loginedUser;
	}

	// save user in cookie

	public static void storeUserCookie(HttpServletResponse response, UserAccount user) {
		Cookie cookieUserName = new Cookie(CookieConstant.NAME_USER, user.getUserName());
		cookieUserName.setMaxAge(24 * 60 * 60);
		response.addCookie(cookieUserName);
	}

	// get username in cookie
	public static String getUserNameinCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (CookieConstant.NAME_USER.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	// delete cookie of user
	public static void deleteUserCookie(HttpServletResponse response){
		Cookie cookieUserName = new Cookie(CookieConstant.NAME_USER, null);
		cookieUserName.setMaxAge(0);
		response.addCookie(cookieUserName);
	}
	
	
	public static String convertToMD5(String arg) {
		String hashed_key = "";

		try {
			byte[] intext = arg.getBytes();
			StringBuffer sb = new StringBuffer();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md5rslt = md5.digest(intext);
			for (int i = 0; i < md5rslt.length; i++) {
				sb.append(String.format("%02x", 0xff & md5rslt[i]));
			}
			hashed_key = sb.toString();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return hashed_key;
	}
}
