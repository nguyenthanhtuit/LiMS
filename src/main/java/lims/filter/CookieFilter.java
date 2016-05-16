package lims.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lims.beans.UserAccount;
import lims.utils.DBUtils;
import lims.utils.MyUtils;

/**
 * Servlet Filter implementation class CookieFilter Fillter này có tác dụng
 * người dùng đã login thì nó lưu vào cookie và tự động login 
 * (set session form cookie)
 */
@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public CookieFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter cookie");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		UserAccount userInSession = MyUtils.getUserLogined(session);

		// check dang login
		if (userInSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		}

		// Đã được tạo trong JDBCFilter
		Connection conn = MyUtils.getStoreConnection(request);

		// Có cần kiểm tra cokkie không?
		String checked = (String) session.getAttribute("COOKIE_CHECKED");
		if (checked == null && conn != null) {
			String userName = MyUtils.getUserNameinCookie(req);
			try {
				UserAccount user = DBUtils.findUser(conn, userName);
				MyUtils.storeLoginUser(session, user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// Đã kiểm tra cookie
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
