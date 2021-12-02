<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
    <a href="/blog/user?blogId=${blog.blogId}"><h2 class="blogtitle">${blog.title}</h2></a>
    <h4 class="blogtag">${blog.tag}</h4>

    <c:if test="${user != null }">
    <a href="/user/logout"><b>로그아웃</b></a>
    </c:if>
</table>


<form action="/blog/update" method="post">
    <input type="hidden" name="blogId" value="${blog.blogId}">
    <p>
        <strong>블로그 제목:</strong>
        <input type="text" name="title" value="${blog.title}">
    </p>
    <p>
        <strong>블로그 태그:</strong>
        <input type="text" name="tag" value="${blog.tag}">
    </p>
    <p>
        <strong>메인페이지 포스트:</strong>
        <input type="text" name="cntDisplayPost" value="${blog.cntDisplayPost}">
    </p>
    <p>
        <strong>로고이미지:</strong>
        <img src="/images/j2eelogo.jpg" border="0">
    </p>
    <p>
        <input type="submit" value="확인">
    </p>
</form>