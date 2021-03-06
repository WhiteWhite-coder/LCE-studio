package com.miaojie.web.servlet.admin;


import com.miaojie.domain.GoodsType;
import com.miaojie.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addGoodsType")
public class AddGoodsType extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String typename = req.getParameter("typename");
		String goodsParentId = req.getParameter("goodsParent");
		//1.先获取 所属分类的等级 level 1-2-3

		GoodsType gt = new GoodsType();
		if(goodsParentId!=null){
			gt.setParent(Integer.parseInt(goodsParentId));//父级别id
			//获取等级
			Integer level = new GoodsServiceImpl().getGoodsTypeLevel(Integer.parseInt(goodsParentId));
			System.out.println(goodsParentId+"的等级::::"+level);
			if(level!=null){
				gt.setLevel(level+1);
			}
		}else{
			gt.setLevel(1);
		}
		gt.setName(typename);
		//2.连接数据库 完成 新分类的添加
		boolean res = new GoodsServiceImpl().addGoodsType(gt);
		if(res){
			resp.sendRedirect("getGoodsType?flag=show");
		}else{
			System.out.println("error");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
