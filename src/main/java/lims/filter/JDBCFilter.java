package lims.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import lims.conn.ConnectionUtils;
import lims.utils.MyUtils;

@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {

	public JDBCFilter() {

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	// Check the target of the request is a servlet?
	public boolean needJDBCFilter(HttpServletRequest request) {
		System.out.println("JDBC filter");
		//
		// Servlet Url-pattern: /spath/*
		//
		// => /spath
		String servletPath = request.getServletPath();

		String pathInfo = request.getPathInfo();

		String urlPattern = servletPath;

		if (pathInfo != null) {
			urlPattern = servletPath + "/*";
		}
		// key: servletName
		// value: ServletRegistration
		Map<String, ? extends ServletRegistration> servletRegistration = request.getServletContext()
				.getServletRegistrations();

		// Tập hợp tất cả các Servlet trong WebApp của bạn.
		Collection<? extends ServletRegistration> value = servletRegistration.values();
		for (ServletRegistration sr : value) {
			Collection<String> mappings = sr.getMappings();
			if (mappings.contains(urlPattern)) {
				return true;
			}

		}
		return false;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//
		// Chỉ mở kết nối đối với các request có đường dẫn đặc biệt cần
		// connection. (Chẳng hạn đường dẫn tới các servlet, jsp, ..)
		//
		// Tránh tình trạng mở connection với các yêu cầu thông thường
		// (chẳng hạn image, css, javascript,... )
		//
		HttpServletRequest req = (HttpServletRequest) request;
		if (needJDBCFilter(req)) {
			System.out.println("open connection for " + req.getServletPath());
			Connection conn = null;
			try {
				// Tạo đối tượng Connection kết nối database.
				conn = ConnectionUtils.getConnection();
				// Sét tự động commit false, để chủ động điều khiển.
				conn.setAutoCommit(false);
				// Lưu trữ vào attribute của request.
				MyUtils.storeConnection(request, conn);
				// cho phep request di tiep
				chain.doFilter(request, response);

				// Gọi commit() để commit giao dịch với DB.
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ServletException();
			} finally {
				ConnectionUtils.closeQuietly(conn);
			}
		}
		// Với các request thông thường (image,css,html,..)
		// không cần mở connection, cho tiếp tục.
		else {
			// cho phép request đi tiếp.
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
