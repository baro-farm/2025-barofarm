<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script defer src="join.js"></script>
	<link rel="stylesheet" href="${contextPath}/join.css">
	<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<!-- 헤더 -->
		<jsp:include page="/header/mainHeader.jsp"/>
		<!-- 컨텐츠  -->
		<div class="join">
       		<h2 class="title">회원가입</h2>
        	<!-- 일반/판매자 가입 선택 -->
        	<div class="userTypeContainer">
            	<label><input type="radio" name="userType" value="buyer" checked> 구매자 가입</label>
            	<label><input type="radio" name="userType" value="seller"> 판매자 가입</label>
        	</div>
	        <!-- 회원가입 폼 -->
	        <form class="joinForm">
	            <div class="inputWithButton">
	                <input type="text" class="inputField" placeholder="아이디">
	                <a href="#" class="checkBtn">중복확인</a>
	            </div>
	            <input type="password" class="inputField" placeholder="비밀번호">
	            <p class="passwordMsg">(영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 10자~16자)</p>
	            <!-- 판매자 가입 시 추가 입력란 -->
	            <div id="sellerFields">
	            	<div class="inputWithButton">
	                	<input type="text" class="inputField" placeholder="스토어명">
	                    <a href="#" class="checkBtn">중복확인</a>
	                </div>
	                <input type="text" class="inputField" placeholder="사업자번호">
	            </div>
	            <input type="text" class="inputField" placeholder="이름">
	            <input type="text" class="inputField" placeholder="핸드폰 번호 (숫자만)">
	            <input type="email" class="inputField" placeholder="이메일">
	            <input type="text" class="inputField" placeholder="생년월일 (6자리)">
	            <br>
	             <!-- 주소 입력란 -->
	            <div class="zipcodeContainer">
	            <input type="text" class="inputField zipcodeField" id="postcode" placeholder="우편번호">
	            <input type="button" onclick="execDaumPostcode()" class="searchAddressBtn" value="주소 검색"><br>
	            </div>
	            <input type="text" class="inputField"  id="address" placeholder="주소">
	            <input type="text" class="inputField" id="detailAddress" placeholder="상세주소">
	            <input type="text" class="inputField" id="extraAddress" placeholder="참고항목">
	
	            <button type="submit" class="submitBtn">가입하기</button>
	        </form>
	    </div>
    </div> 
</body>
</html>