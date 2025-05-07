<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>비밀번호 확인</title>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/seller/detailIntoSellerInfo.css" />
</head>
<body>
	<jsp:include page="/header/sellerHeader.jsp" />
    <header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
    </header>
    <div id="content">
        <h2 class="title">비밀번호 확인</h2>
        <form action="/checkPassword" method="post">
            <input type="password" name="password" placeholder="비밀번호를 입력하세요" required>
            <div class="error">
            </div>
            <button type="submit" class="btn">확인</button>
        </form>
    </div>
</body>
</html>