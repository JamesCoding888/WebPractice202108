package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dao.memberDao;
import model.member;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		member m = new memberDao().queryUserName(username, password);
		
		if(m!=null) {
			resp.sendRedirect("loginSuccess.html");
		} else {
			resp.sendRedirect("loginError.html");
		}
		
	}
	


}
