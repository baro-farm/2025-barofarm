<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="reset.css">
     <link rel="stylesheet" href="login.css">
</head>
<body>
<div>
<%@ include file="./header/mainHeader.jsp" %>
 <div class="loginContainer">
        <h2 class="loginTitle">로그인</h2>

        <!-- 일반/판매자 선택 -->
        <div class="userType">
            <label><input type="radio" name="userType" value="general" checked> <span class="radioBtn">일반</span></label>
            <label><input type="radio" name="userType" value="seller"> <span class="radioBtn">판매자</span></label>
        </div>

        <!-- 로그인 폼 -->
        <form class="loginForm">
            <input type="text" class="inputField" placeholder="아이디">
            <input type="password" class="inputField" placeholder="비밀번호">
            
            <!-- 아이디 저장 & 자동 로그인 -->
            <div class="loginOptions">
                <label><input type="checkbox"> 아이디 저장</label>
                <label><input type="checkbox"> 자동 로그인</label>
            </div>
            
            <!-- 로그인 버튼 -->
            <a href="" class="loginBtn">로그인</a>
            <a href="" class="kakaoLoginBtn">
                <img src="./img/kakao_login_large_wide.png" alt="카카오 로그인">
            </a>
        </form>
        <hr>
        <!-- 하단 메뉴 -->
        <div class="bottomMenu">
            <a href="#">회원가입</a>
            <a href="#">아이디 찾기</a>
            <a href="#">비밀번호 변경</a>
        </div>
    </div>
    </div>
</body>
</html>