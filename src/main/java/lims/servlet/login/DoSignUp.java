package lims.servlet.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import lims.beans.UserAccount;
import lims.utils.HelperApplication;

/**
 * Servlet implementation class DoSignUp
 */
@WebServlet("/doSignUp")
public class DoSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger=Logger.getLogger(this.getClass());
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoSignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		String confirmPassword = request.getParameter("confirmPassword").trim();
		String email = request.getParameter("email").trim();
		UserAccount user = new UserAccount(userName, password, email, firstName, lastName);
//		UserAccount user = new UserAccount("htmz", "htmz", "htmz", "htmz", "htmz");
		logger.info(user.toString());
		StringBuffer mess = new StringBuffer();
		boolean hasEror = false;

		if (user.getUserName() == "" || user.getPassword() == "" || user.getMail() == "") {
			mess.append("Fill full the information please! </br>");
			String messS = mess.toString();
			request.setAttribute("mess", messS);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/page/navigates/loginPage.jsp");
			dispatcher.forward(request, response);

		} else {
			try {
				if (HelperApplication.checkUserNameExist(request, response, userName)) {
					hasEror = true;
					mess.append("User Name existed! </br>");
				}
			} catch (SQLException e) {
				hasEror = true;
				e.printStackTrace();
			}
			if (HelperApplication.checkEmailExist(request, response, user.getMail())) {
				hasEror = true;
				mess.append("Email existed! </br>");
			}
			if (password.equals(confirmPassword) == false) {
				hasEror = true;
				mess.append("Passwords confirm don't match");
			}

			if (hasEror) {
				request.setAttribute("user", user);
				String messS = mess.toString();
				request.setAttribute("mess", messS);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/page/navigates/loginPage.jsp");
				dispatcher.forward(request, response);
			} else {
				hasEror = HelperApplication.newAccount(request, response, user);
				if (hasEror) {
					mess.append("Signup success !");
					String messS = mess.toString();
					request.setAttribute("mess", messS);
					request.setAttribute("user", user);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("/WEB-INF/page/navigates/loginPage.jsp");
					dispatcher.forward(request, response);
				} else {
					 mess.append("Signup failed!");
					 String messS = mess.toString();
					 request.setAttribute("mess", messS);
					 RequestDispatcher dispatcher =
					 request.getRequestDispatcher("/WEB-INF/page/navigates/loginPage.jsp");
					 dispatcher.forward(request, response);
				}
			}
		}
	}

}
