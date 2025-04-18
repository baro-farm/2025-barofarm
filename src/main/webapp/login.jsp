<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/login.css">
</head>
<body>
	<div class="container">
		<!-- 헤더 -->
		<jsp:include page="/header/mainHeader.jsp" />
		<!-- 컨텐츠  -->
		<div class="content">
		<div class="loginContainer">
			<h2 class="loginTitle">로그인</h2>
		    <!-- 로그인 폼 -->
		    <form action="login" method="post" class="loginForm">
		    	<input type="text" class="inputField" name="userId" value="${userId}" placeholder="아이디" required>
		        <input type="password" class="inputField" name="pwd" placeholder="비밀번호" required>  
		        <!-- 아이디 저장 & 자동 로그인 -->
		        <div class="loginOptions">
		        	<label><input type="checkbox" name="saveId" value="on" ${not empty saveId ? "checked" : ""}> 아이디 저장</label>
		        	<label><input type="checkbox" name="autoLogin" value="on" ${not empty autoLogin ? "checked": ""}> 자동 로그인</label>
		        </div>
		        <c:if test="${not empty err}">
				    <div class="errorMsg">${err}</div>
				</c:if>
		        <!-- 로그인 버튼 -->
		        <button type="submit" class="loginBtn">로그인</button>
		        <a href="" class="kakaoLoginBtn">
		        	<img src="${contextPath}/img/kakao_login_large_wide.png" alt="카카오 로그인">
		        </a>
		   	</form>
		    <hr>
		    <!-- 하단 메뉴 -->
		    <div class="bottomMenu">
		    	<a href="join">회원가입</a>
		        <a href="#">아이디 찾기</a>
		        <a href="#">비밀번호 변경</a>
		    </div>
		</div> <!-- 로그인 컨텐츠  -->
		</div>
	<jsp:include page="/header/footer.jsp" />
	</div> <!-- 전체 컨테이너 -->
</body>
</html>