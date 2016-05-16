package lims.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lims.beans.UserAccount;
import lims.utils.DBUtils;
import lims.utils.MyUtils;

/**
 * Servlet implementation class DoLoginServlet
 */
public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remenberMe = "Y".equals(rememberMeStr);
		
		UserAccount user = null;
		boolean hasErro = false;
		String erroString = null;

		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasErro = true;
			erroString = "Required username and password";
		} else {
			Connection conn = MyUtils.getStoreConnection(request);
			try {
				user = DBUtils.findUser(conn, userName, password);
				if (user == null) {
					hasErro = true;
					erroString = "username or password invalid";
					System.out.println(erroString+"  "+hasErro);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				hasErro=true;
				erroString=e.getMessage();
				System.out.println(erroString);
			}
		}
		
		if(hasErro){
			user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			
			request.setAttribute("erroString", erroString);
			request.setAttribute("user", user);
			
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/page/mainpages/loginPage.jsp");
			dispatcher.forward(request, response);
		}
		
		else{
			HttpSession session = request.getSession();
			MyUtils.storeLoginUser(session, user);
			System.out.println("is remwnber "+remenberMe);
			if(remenberMe==true){
				MyUtils.storeUserCookie(response, user);
			}else{
				MyUtils.deleteUserCookie(response);
			}	
			response.sendRedirect(request.getContextPath()+"/userInfo");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
