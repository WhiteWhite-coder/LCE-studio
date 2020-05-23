<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改商品信息</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改商品</h3>
    <form action="${pageContext.request.contextPath}/updateGoodsServlet" method="post">
        <!--  隐藏域 提交id-->
        <input type="hidden" name="id" value="${goods.id}">

        <div class="form-group">
            <label>名称:</label>
            <input type="text" name="name" class="form-control" value="${goods.name}" placeholder="请输入名称"/>
        </div>

        <div class="form-group">
            <label>分类:</label>
            <select name="typeid" class="form-control">
                <option value="0">------</option>
                <c:if test="${goods.typeid == '电脑'}">
                    <option value="1">电脑</option>
                    <option value="2">笔记本</option>
                    <option value="3">平板</option>
                </c:if>

                <c:if test="${goods.typeid == '笔记本'}">
                    <option value="1">电脑</option>
                    <option value="2">笔记本</option>
                    <option value="3">平板</option>
                </c:if>

                <c:if test="${goods.typeid == '平板'}">
                    <option value="1">电脑</option>
                    <option value="2">笔记本</option>
                    <option value="3">平板</option>
                </c:if>
            </select>
        </div>

        <div class="form-group">
            <label>价格:</label>
            <input type="text" name="price" class="form-control" value="${goods.price}" placeholder="请输入价格"/>
        </div>
        <div class="form-group">
            <label>评分:</label>
            <input type="text" name="star" class="form-control" value="${goods.star}" placeholder="请输入评分"/>
        </div>
        <div class="form-group">
            <label>商品图片</label>
            <input type="file" name="picture" value="${goods.picture}"/>
        </div>
        <div class="form-group ">
            <label>商品简介</label>
            <textarea  name="intro" class="form-control" value="${goods.intro}" rows="5"></textarea>
        </div>


        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>