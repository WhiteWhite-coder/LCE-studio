package com.miaojie.web.servlet.admin;
/*
 *  @author 吴淼杰
 *  老天保佑，佛祖保佑，别出bug！
 *
 */

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

@WebServlet("/deleteGood")
public class DeleteGood extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        GoodsService goodsService = new GoodsServiceImpl();
        if(goodsService.deleteGood(Integer.parseInt(id))){
            response.sendRedirect("admin/showGoods.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
