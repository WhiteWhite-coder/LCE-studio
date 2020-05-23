package com.miaojie.web.servlet.admin;
/*
 *  @author 吴淼杰
 *  老天保佑，佛祖保佑，别出bug！
 *
 */

import com.miaojie.domain.Goods;
import com.miaojie.service.GoodsService;
import com.miaojie.service.impl.GoodsServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateGoodsServlet")
public class UpdateGoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取map
        Map<String,String[]> map = request.getParameterMap();
        //2.封装对象
        Goods goods = new Goods();
        try {
            BeanUtils.populate(goods,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用Service修改
        GoodsService goodsService = new GoodsServiceImpl();
        goodsService.updateGoods(goods);

        //4.跳转到查询所有Service
        response.sendRedirect(request.getContextPath()+"/getGoodsList");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
