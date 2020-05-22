package com.miaojie.web.servlet;
/*
 *  作者：吴淼杰
 *  注释：老天保佑，佛祖保佑，别出bug！
 *
 */

import com.alibaba.fastjson.JSON;
import com.miaojie.domain.GoodsType;
import com.miaojie.service.GoodsTypeService;
import com.miaojie.service.impl.GoodsTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GoodsTypeServlet",value = "/goodstypeservlet")
public class GoodsTypeServlet extends BaseServlet {
    public String goodstypelist(HttpServletRequest request,HttpServletResponse response) throws Exception{
        response.setContentType("application/json;charset=utf-8");
        //获取级别为1的商品的类型
        GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
        //调用方法
        List<GoodsType> goodsTypeList = goodsTypeService.getTypeByLevel(1);
        //使用fastjson转成json字符串
        String json = JSON.toJSONString(goodsTypeList);
        //返回给浏览器
        response.getWriter().write(json);

        return null;
    }
}
