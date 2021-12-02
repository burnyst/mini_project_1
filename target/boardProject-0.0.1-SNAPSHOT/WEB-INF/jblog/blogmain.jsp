<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${blog.title}</title>
<Link rel="stylesheet" href="css/theme.css">
</head>

<body>
<form>
<!-- 블로그 이름, 주소 -->
<a href="/blog/user?blogId=${blog.blogId}"><h2 class="blogtitle">${blog.title}</h2></a>
<h4 class="blogtag">${blog.tag}</h4>

<!-- 게시글 -->
<table>
<c:if test="${posts != null}">
<c:forEach var="post" items="${posts}">
<table>
<tr>
<br>
<td class="posttitle">${post.title}<br></td>
<td class="postdate"><fmt:formatDate value="${post.createdDate}" pattern="yyyy-MM-dd"/><br></td>
<td class="postcontent">${post.content}<br></td>
<c:if test="${user.userId == blog.blogId}">
    <td><a href="/post/updateView?postId=${post.postId}">EDIT</a></td>
    <td><a href="/post/delete?postId=${post.postId}">DEL</a></td>
</c:if>
<br>
</tr>
</table>
</c:forEach>
</c:if>
</table>
<h3>글 작성</h3>
<!-- 카테고리 -->
<form action="/blog/user" method="post">
<table>
<p>
    <strong class="categorytitle">카테고리</strong>
</p>
<c:forEach var="category" items="${categories }">
<td class="categoryitem"><a href="/post/category?categoryId=${category.categoryId}">${category.categoryName }</a></td>
</c:forEach>
</table>
</form>


<!-- 로그인 -->
<c:if test="${user == null }">
<a href="/loginView.do"><b>로그인</b></a>&nbsp;&nbsp;
</c:if>

<c:if test="${user != null }">
<a href="/user/logout"><b>로그아웃</b></a>
</c:if>

<c:if test="${user.userId == blog.blogId}">
    <a href="/blog/updateView?blogId=${blog.blogId}">블로그 관리</a>
</c:if>


</body>