package com.miaojie.web.servlet.admin;

import com.google.gson.Gson;
import com.miaojie.domain.Goods;
import com.miaojie.domain.User;
import com.miaojie.service.GoodsService;
import com.miaojie.service.UserService;
import com.miaojie.service.impl.GoodsServiceImpl;
import com.miaojie.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getUserList")
public class GetUserList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService userService = new UserServiceImpl();
		List<User> list = userService.getUserList();
		//将list集合 转换成 json格式字符串
		String json = new Gson().toJson(list);
		resp.getWriter().print(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
