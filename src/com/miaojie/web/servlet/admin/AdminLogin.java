package com.miaojie.web.servlet.admin;

import com.miaojie.domain.User;
import com.miaojie.service.UserService;
import com.miaojie.service.impl.UserServiceImpl;
import com.miaojie.utils.Md5Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLogin extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String url = "admin/login.jsp";
		if(username.length()>0 && password.length()>0){
			UserService userService = new UserServiceImpl();
			User user = userService.findAdmin(username);
			if(user!=null){
				if(user.getPassword().equals(Md5Utils.md5(password))){
					req.getSession().setAttribute("admin", username);
					url = "admin/admin.jsp";
				}
			}
		}
		resp.sendRedirect(url);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
