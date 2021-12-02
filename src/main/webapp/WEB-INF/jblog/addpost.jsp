<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>블로그 관리</title>
<Link rel="stylesheet" href="/css/theme.css">
</head>

<body background="/images/kubrickbgcolor.jpg">
<center>
<!-- 블로그 이름, 주소 -->
<table background="/images/kubrickheader.jpg" width="760" height="200" border="0" cellpadding="0" cellspacing="0">
<tr><td height="60">&nbsp;</td></tr>
<tr><td height="60" class="blogtitle"><a href="/blog/user?blogId=${blog.blogId}">${blog.title}</a></td></tr>
<tr><td height="20" class="blogtag">${blog.tag }</td></tr>
<tr><td height="60">&nbsp;</td></tr>
<tr>
<td align="right" height="60">
<a href="/user/logout">로그아웃</a>&nbsp;&nbsp;<a href="/blog/user?blogId=${blog.blogId}">내 블로그 메인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
</tr>
</table>

<!-- 블로그 관리 메뉴-->
<table background="/images/kubrickbg.jpg" width="760" height="40" border="0" cellpadding="0" cellspacing="0">
<tr><td height="10" colspan="10">&nbsp;</td></tr>
<tr>
<td height="10" width="20">&nbsp;</td>
<td width="530" valign="top" class="tdcontent">
<!-- 메뉴 시작 -->
<a class="title" href="/blog/updateView"><b>기본설정</b></a>&nbsp;&nbsp;
<a class="title" href="/category/categorysetting"><b>카테고리</b></a>&nbsp;&nbsp;
<b>글작성</b>
<!-- 메뉴 끝 -->
</td>
</tr>

<tr><td height="5">&nbsp;</td></tr>
<tr>
<td height="10">&nbsp;</td>
<td>

<form action="/post/create" method="post">
<input name="blogId" value="${blog.blogId}" type="hidden">
<table width="720"  border="0" cellpadding="0" cellspacing="0">
<tr>
<td width="50" class="inputlabel">제목 :</td>
<td width="390"><input class="inputtext" type="text" size="56" name="title" value="${post.title}"></td>
<td width="300">
<select class="inputtextarea" id="categoryName" name="categoryName">
<c:forEach var="category" items="${categories}">
<option value="${category.categoryName}">
${category.categoryName}
</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td width="50" class="inputlabel">내용 :</td>
<td colspan="10"><textarea name="content" class="inputtextarea" rows="10" cols="69"></textarea></td>
</tr>
<tr><td height="5">&nbsp;</td></tr>
<tr>
<td colspan="10" align="center">&nbsp;<input type="submit" value="등록"></td>
</tr>
</table>
</form>

</td>
</tr>
<tr><td height="10" colspan="10">&nbsp;</td></tr>
</table>

<table background="/images/kubrickfooter.jpg" width="760" height="63" border="0" cellpadding="0" cellspacing="0">
<tr>
<td class="blogfooter">SPRING Stories is powered by JBlog</td>
</tr>
</table>

</center>
</body>
</html>
