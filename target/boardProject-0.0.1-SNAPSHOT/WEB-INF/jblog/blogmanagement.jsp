<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>블로그 관리</title>
<Link rel="stylesheet" href="css/theme.css">
</head>

<table>
<a href="/blog/user?blogId=${blog.blogId}"><h2 class="blogtitle">${blog.title}</h2></a>
<h4 class="blogtag">${blog.tag}</h4>

<c:if test="${user != null }">
<a href="/user/logout"><b>로그아웃</b></a>
</c:if>

<c:if test="${user.userId == blog.blogId}">
<a href="/blog/user?blogId=${blog.blogId}">내 블로그 메인</a>
</c:if>
</table>

<table width="400" border="0" cellpadding="0" cellspacing="0">
<tr>
<ul>
<li><a href="/blog/basicsetting" target="iframe">기본설정</a></li>
<li><a href="/category/categorysetting" target="iframe">카테고리</a></li>
<li><a href="/post/createView" target="iframe">글 작성</a></li>
</ul>
</tr>
</table>


<iframe height="500" width="1000" frameborder="0">

</iframe>

</body>
</html>
