package com.miaojie.web.servlet.admin;

import com.google.gson.Gson;
import com.miaojie.domain.User;
import com.miaojie.service.UserService;
import com.miaojie.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchUser")
public class SearchUser extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String gender = req.getParameter("gender");

		UserService userService = new UserServiceImpl();
		List<User> list = userService.searchUser(username, gender);
		if(list!=null){
			String json = new Gson().toJson(list);
			resp.getWriter().print(json);
		}else{
			resp.getWriter().print(0);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
