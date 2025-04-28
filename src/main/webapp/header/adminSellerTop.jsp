<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
 <link rel="stylesheet" href="${contextPath}/header/adminSellerTop.css">
  
<div id="info">
    <p id="userId">${user.userId }</p>
    <c:choose>
    	<c:when test="${user.isSeller == true }">
	    	<p><a href="#" class="detailStoreInfo">내 정보</a></p>
	    </c:when>
	</c:choose>
	<p><a href="logout" class="logout">로그아웃</a></p>
</div>