<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改书籍</title>
</head>
<body>
	<h1>修改书籍</h1>
	<hr>
	<form action="book?op=modify" method="post">
		<table width="60%" align="center">
			<tr>
				<!-- 这里需要将id传递过去 -->
				<td>书名: <input type="hidden" name="id" value="${book.id }" />
				</td>
				<td><input type="text" name="name" value="${book.name}" /></td>
			</tr>
			<tr>
				<td>分类:</td>
				<!-- 修改书籍的时候需要将书籍分类通过下拉框的形式显示出来 -->
				<td><select name="categoryId">
						<c:forEach items="${clist }" var="bean">
							<option value="${bean.id }"
								<c:if test="${bean.id == book.categoryId }">selected</c:if>>${bean.name }</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>价格:</td>
				<td><input type="text" name="price" value="${book.price}" />
				</td>
			</tr>

			<tr>
				<td>作者:</td>
				<td><input type="text" name="author" value="${book.author}" />
				</td>
			</tr>

			<tr>
				<td>出版日期:</td>
				<td><input type="text" name="pubDate" value="${book.pubDate}" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>