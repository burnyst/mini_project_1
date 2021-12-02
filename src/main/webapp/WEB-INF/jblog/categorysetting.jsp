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
<b>카테고리</b>
<a class="title" href="/post/createView"><b>글작성</b></a>&nbsp;&nbsp;
<!-- 메뉴 끝 -->
</td>
</tr>

<tr><td height="5">&nbsp;</td></tr>
<tr>
<td height="10">&nbsp;</td>
<td>
<!-- 작업 화면  시작 -->
<c:if test="${!empty categories}">
<table width="720"  border="1" cellpadding="0" cellspacing="0">
<tr>
<td width="150" class="tablelabel">카테고리명</td>
<td width="80" class="tablelabel">보이기 유형</td>
<td width="70" class="tablelabel">포스트 수</td>
<td width="280" class="tablelabel">설명</td>
<td width="70" class="tablelabel">삭제</td>
</tr>


<c:forEach var="category" items="${categories }">
<tr>

<td class="tablecontent" align="center">
<c:if test="${category.categoryName != '미분류'}">
<a href="/category/updateView?categoryId=${category.categoryId}">
</c:if>
${category.categoryName }
</a>
</td>
<td class="tablecontent" align="center"> ${category.displayType } </td>
<td class="tablecontent" align="center">${category.cntDisplayPost }</td>
<td class="tablecontent">${category.description }</td>

<td class="tablecontent" align="center">&nbsp;
<c:if test="${category.categoryName == '미분류' }">
삭제불가
</c:if>

<c:if test="${category.categoryName != '미분류' }">
<a href="/category/delete?categoryId=${category.categoryId}"><img height="9" src="/images/delete.jpg" border="0"></a>
</c:if>

</c:forEach>
</c:if>


</table>
<form action="/category/add" method="post">
<input name="blogId" value="${blog.blogId}" type="hidden"/>
<table width="720"  border="0" cellpadding="0" cellspacing="0">
<tr><td height="5">&nbsp;</td></tr>
<tr><td height="5">&nbsp;</td></tr>
<tr><td class="tdcontent" height="5"><b>카테고리 추가</b></td></tr>
<tr><td height="5">&nbsp;</td></tr>
<tr>
<td width="150" class="inputlabel">카테고리명 :</td>
<td><input class="inputtext" type="text" size="40" name="categoryName"></td>
</tr>
<tr>
<td width="150" class="inputlabel">보이기 유형 :</td>
<td class="tdcontent">
<input type="radio" name="displayType" value="제목">제목
<input type="radio" name="displayType" value="제목+내용">제목+내용
</td>
</tr>
<tr>
<td width="150" class="inputlabel">포스트 수 :</td>
<td class="tdcontent"><input class="inputtext" type="text" size="4" name="cntDisplayPost">개(1~20)</td>
</tr>
<tr>
<td width="150" class="inputlabel">설명 :</td>
<td><input class="inputtext" type="text" size="50" name="description"></td>
</tr>
<tr><td height="5">&nbsp;</td></tr>
<tr>
<td colspan="10" align="center">&nbsp;<input type="submit" value="카테고리 추가"></td>
</tr>
</table>
</form>
</td>
</tr>



</table>
</center>
</body>
</html>