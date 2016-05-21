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

import org.apache.log4j.Logger;

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
 
	Logger logger = Logger.getLogger(CookieFilter.class);
	
   public CookieFilter() {
   }
 
   @Override
   public void init(FilterConfig fConfig) throws ServletException {
 
   }
 
   @Override
   public void destroy() {
 
   }
 
   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
           throws IOException, ServletException {
       HttpServletRequest req = (HttpServletRequest) request;
       HttpSession session = req.getSession();
 
       String userName1 = MyUtils.getUserNameinCookie(req);
       //check cookie neu lúc m` lấy có cookie mà 0 có session thì sau lúc đóng browser mở lại 
       logger.info("test function get user name in cookie: "+userName1);
       UserAccount userInSession = MyUtils.getUserLogined(session);
       
       // Đang login.
       if (userInSession != null) {
    	   logger.info("hien tai ddang login");
           session.setAttribute("COOKIE_CHECKED", "CHECKED_LOGIN");
           chain.doFilter(request, response);
           return;
       }
 
       // Đã được tạo trong JDBCFilter.
       Connection conn = MyUtils.getStoreConnection(request);
       if(userInSession == null){
    	   logger.info("chua login va co chuoi kn"+conn);
       }
       // Có cần kiểm tra Cookie ko?
       String checked = (String) session.getAttribute("COOKIE_CHECKED");
       
       if (checked != "CHECKED" && conn != null) {
           String userName = MyUtils.getUserNameinCookie(req);
           logger.info("user name"+userName);
           try {
               UserAccount user = DBUtils.findUser(conn,userName);
               MyUtils.storeLoginUser(session, user);
           } catch (SQLException e) {
               e.printStackTrace();
           }
           // Đã kiểm tra cookie
           session.setAttribute("COOKIE_CHECKED", "CHECKED");
       }
       
       chain.doFilter(request, response);
   }
 
}