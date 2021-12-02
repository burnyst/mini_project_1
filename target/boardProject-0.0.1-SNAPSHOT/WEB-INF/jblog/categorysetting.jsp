<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

<table width="720" height="50" border="1" cellpadding="0" cellspacing="0">
<tr>
<td width="100" class="tablelabel">카테고리명</td>
<td width="100" class="tablelabel">보이기 유형</td>
<td width="100" class="tablelabel">포스트 수</td>
<td width="100" class="tablelabel">설명</td>
<td width="100" class="tablelabel">삭제</td>
</tr>

<form action="/category/delete" method="post">

<c:forEach var="category" items="${categories }">
<tr>
    <input type="hidden" name="blogId" value="${blog.blogId}">
<td class="tablecontent" align="center">
<c:if test="${category.categoryName != '미분류'}">
<a href="/category/updateView?categoryId=${category.categoryId}">
</c:if>
${category.categoryName }
</a>
</td>

<td class="tablecontent" align="center"> ${category.displayType } </td>
<td class="tablecontent" align="center"> ${category.cntDisplayPost } </td>
<td class="tablecontent" align="center"> ${category.description } </td>
<td class="tablecontent" align="center">
<c:if test="${category.categoryName.equals('미분류')}">
삭제불가
</c:if>
<c:if test="${!category.categoryName.equals('미분류')}">
<a href="/category/delete?categoryId=${category.categoryId}"><img src="/images/delete.jpg"/></a>
</c:if>
</td>
</tr>
</c:forEach>
</table>
</form>

<br>
<br>
<br>
<h3>카테고리 추가</h3>
<form action="/category/add" method="post">
<input type="hidden" name="blogId" value="${blog.blogId}">
<p>
<strong>카테고리명:</strong>
<input type="text" name="categoryName" value="${category.categoryName}">
</p>
<p>
<strong>보이기 유형</strong>
<input type="radio" name="displayType" value="제목">제목
<input type="radio" name="displayType" value="제목+내용">제목+내용
</p>
<p>
<strong>포스트 수:</strong>
<input type="text" name="cntDisplayPost" value="${category.cntDisplayPost}">개(1~20)
</p>
<p>
<strong>설명:</strong>
<input type="text" name="description" value="${category.description}">
</p>
<p>
<input type="submit" value="확인">
</p>
</form>
