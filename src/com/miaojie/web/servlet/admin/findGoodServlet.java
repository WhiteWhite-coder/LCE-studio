package com.miaojie.web.servlet.admin;
/*
 *  @author 吴淼杰
 *  老天保佑，佛祖保佑，别出bug！
 *
 */

import com.miaojie.domain.Goods;
import com.miaojie.service.GoodsService;
import com.miaojie.service.UserService;
import com.miaojie.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findGoodServlet")
public class findGoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id
        String id = request.getParameter("id");
        //2.调用Service查询
        GoodsService goodsService = new GoodsServiceImpl();
        Goods goods = goodsService.findById(Integer.parseInt(id));
        //3.将goods存入request
        request.setAttribute("goods",goods);
        //4.转发给update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
