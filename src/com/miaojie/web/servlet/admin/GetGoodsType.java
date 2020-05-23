package com.miaojie.web.servlet.admin;

import com.miaojie.domain.GoodsType;
import com.miaojie.service.GoodsService;
import com.miaojie.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getGoodsType")
public class GetGoodsType extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String flag = req.getParameter("flag");
		GoodsService goodsService = new GoodsServiceImpl();
		List<GoodsType> list = goodsService.getGoodsTypeList();
		req.getSession().setAttribute("goodsTypeList", list);
		if("show".equals(flag)){
			resp.sendRedirect("admin/showGoodsType.jsp");
		}
		if("add".equals(flag)){
			resp.sendRedirect("admin/addGoodsType.jsp");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
