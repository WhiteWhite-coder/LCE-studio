package com.miaojie.web.servlet.admin;

import com.miaojie.service.UserService;
import com.miaojie.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取id
		String id = req.getParameter("id");
		//调用service删除
		UserService userService = new UserServiceImpl();
		if(userService.deleteUser(Integer.parseInt(id))){
			resp.sendRedirect("admin/userList.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
