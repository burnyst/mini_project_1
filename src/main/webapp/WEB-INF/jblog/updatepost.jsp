<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정</title>
<Link rel="stylesheet" href="css/theme.css">
</head>

<table>
<a href="/blog/user?blogId=${blog.blogId}"><h2 class="blogtitle">${blog.title}</h2></a>
<h4 class="blogtag">${blog.tag}</h4>

<c:if test="${user != null }">
<a href="/user/logout"><b>로그아웃</b></a>
</c:if>
</table>

<h3>글 작성</h3>
<form action="/post/update" method="post">
    <input type="hidden" name="postId" value="${updatingPost.postId}">
    <input type="hidden" name="categoryId" value="${updatingPost.categoryId}">
<p>
<strong>제목:</strong>
<input type="text" name="title" value="${updatingPost.title}">

<select id="categoryName" name="categoryName" >
<c:forEach var="category" items="${categories}">
<option value="${category.categoryName}">
${category.categoryName}
</option>
</c:forEach>
</select>
</p>

<p>
<strong>내용:</strong>
<input type="text" name="content" value="${updatingPost.content}">
</p>

<input type="submit" value="수정하기">
</form>


</body>
</html>
