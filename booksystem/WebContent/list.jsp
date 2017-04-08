<!-- 
	向数据库中添加书籍的JSP页面 ,其中涉及到了JSTL,EL表达式;
	所以需要自己导入jstl.jar和standard.jar包;
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- eclipse下使用JSTL需要导入java.sun.com/jsp/jstl/core，才能使用JSTL表达式 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书籍信息页面</title>
</head>
<body>
	<h1>书籍信息列表</h1>
	<hr>
	<table width="80%" align="center">
		<tr>
			<td>编号</td>
			<td>名称</td>
			<td>分类</td>
			<td>价格</td>
			<td>作者</td>
			<td>出版日期</td>
			<td>操作</td>
		</tr>
		<!-- 通过EL表达式获取传递过来的list,并且通过JSTL中的循环获取对应的属性值 -->
		<c:forEach items="${list }" var="bean" varStatus="status">
			<!-- 
				1.隔行变色
				2.分别取得对应的书籍属性值并且显示出来
				3.当书籍中的分类编号和分类表中的编号相同时，显示分类表中的分类名
			-->
			<tr <c:if test="${status.index%2==0 }">style="background:#0f0"</c:if>>
				<td>${bean.id }</td>
				<td>${bean.name }</td>
				<td><c:forEach items="${clist }" var="category">
						<c:if test="${category.id == bean.categoryId }">
					${category.name }
				</c:if>
					</c:forEach></td>
				<td>${bean.price }</td>
				<td>${bean.author }</td>
				<td>${bean.pubDate }</td>
				<!-- 
					1.这里以浏览器的URL方式直接将书籍的对应id传递过去,可以对书籍进行查询操作和删除操作
				 -->
				<td><a href="book?op=getById&id=${bean.id }">修改</a> <a
					href="book?op=delete&id=${bean.id }">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>