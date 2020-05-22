package com.miaojie.web.servlet;
/*
 *  @author 吴淼杰
 *  老天保佑，佛祖保佑，别出bug！
 *
 */

import com.miaojie.domain.Cart;
import com.miaojie.domain.Goods;
import com.miaojie.domain.User;
import com.miaojie.service.CartService;
import com.miaojie.service.GoodsService;
import com.miaojie.service.impl.CartServiceImpl;
import com.miaojie.service.impl.GoodsServiceImpl;
import com.miaojie.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "CartServlet",value = "/cartservlet")
public class CartServlet extends BaseServlet {
    public String addCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //1.判断用户有没有登录
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/login.jsp";//没有登录则去登录界面
        }
        //2.获得参数
        String goodsId = request.getParameter("goodsId");//商品Id
        String number = request.getParameter("number");//数量
        //2.1.判空
        if(StringUtils.isEmpty(goodsId)){
            return "redirect:/index.jsp";
        }
        //3.查询购物车有没有商品(根据用户id和商品id)
        CartService cartService = new CartServiceImpl();
        Cart cart = cartService.findByUidAndPid(user.getId(),Integer.parseInt(goodsId));
        //3.1.判空(无则添加到购物车，有则更新购物车)
        GoodsService goodsService = new GoodsServiceImpl();
        Goods goods = goodsService.findById(Integer.parseInt(goodsId));
        int num = Integer.parseInt(number);
        try {
            if (cart == null) {
                //无则添加到购物车
                cart = new Cart(user.getId(), Integer.parseInt(goodsId), num, goods.getPrice().multiply(new BigDecimal(num)));//java又臭又长
                cartService.add(cart);
            } else {
                //有则更新购物车
                cart.setNum(cart.getNum() + num);//原来的数量加上想要添加的数量
                cart.setMoney(goods.getPrice().multiply(new BigDecimal(cart.getNum())));
                cartService.update(cart);
            }
            return "redirect:/cartSuccess.jsp";//没有问题去成功页面
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "redirect:/index.jsp";//有问题去首页
        }

    }

}
