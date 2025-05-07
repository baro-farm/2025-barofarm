<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>비밀번호 변경</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/reset.css">
    <link rel="stylesheet" href="${contextPath}/find.css">
    <script defer src="find.js"></script>
</head>
<body>
<div class="container">
		<jsp:include page="/header/mainHeader.jsp" />
		<div class="content">
		<div class="findContainer">
			<h2 class="findTitle">비밀번호 찾기</h2>
		    <div class="findForm">
		    	<input type="email" id="emailInput" class="inputField" placeholder="이메일">
		        <input type="text" id="idInput" class="inputField" placeholder="아이디">
		        <div id="resultBox" class="resultBox hidden"></div>
		        <button type="button" id="findPwdBtn" class="findBtn">비밀번호 찾기</button>
		    </div>
		    <hr>
		    <!-- 하단 메뉴 -->
		    <div class="bottomMenu">
		    	<a href="${contextPath}/login">로그인</a>
		        <a href="${contextPath}/findId">아이디 찾기</a>
		    </div>
	    </div>
	    </div>
    	<jsp:include page="/header/footer.jsp" />
	</div>
</body>
</html>