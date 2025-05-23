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
	<title>Join</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body>
	<div class="container">
		<!-- 헤더 -->
		<jsp:include page="/header/mainHeader.jsp"/>
		<!-- 컨텐츠  -->
		<div class="content">
		<div class="join">
       		<h2 class="title">회원가입</h2>
	        <!-- 회원가입 폼 -->
	        <form action="join" method="post" class="joinForm">
		        <!-- 일반/판매자 가입 선택 -->
	        	<div class="userTypeContainer">
	            	<label><input type="radio" name="isSeller" value="false" checked> 구매자 가입</label>
	            	<label><input type="radio" name="isSeller" value="true"> 판매자 가입</label>
	        	</div>
	            <div class="inputWithButton">
	                <input type="text" class="inputField" name="userId" id="userId" placeholder="아이디" 
	                	value="${userId != null ? userId : ''}" required>
	                <button type="button" id="doubleId" class="checkBtn">중복확인</button>
	            </div>
	            <p id="idCheckResult" class="doubleCheck"></p>
	            <input type="password" class="inputField" name="pwd" placeholder="비밀번호" 
	            	value="${pwd != null ? pwd : ''}" required>
	            <p class="passwordMsg">(영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 10자~16자)</p>
	            <!-- 판매자 가입 시 추가 입력란 -->
	            <div id="sellerFields" >
	            	<div class="inputWithButton">
	                	<input type="text" class="inputField" name="storeName" id="storeName" placeholder="스토어명"
	                		value="${storeName != null ? storeName : ''}">
	                    <button type="button" id="doubleStoreName" class="checkBtn">중복확인</button>
	                </div>
	                <p id="storeNameCheckResult" class="doubleCheck"></p>
	                <input type="text" class="inputField" name="businessNum" placeholder="사업자번호"
	                	value="${businessNum != null ? businessNum : ''}">
	            </div>
	            <input type="text" class="inputField" name="userName" placeholder="이름" 
	            	value="${userName != null ? userName : ''}" required>
	            <input type="text" class="inputField" name="phone" placeholder="핸드폰 번호 (숫자만)" 
	            	value="${phone != null ? phone : ''}" required>
	            <input type="email" class="inputField" name="email" placeholder="이메일" 
	            	value="${email != null ? email : ''}" required>
	            <input type="date" class="inputField" name="birthDate" placeholder="생년월일 (6자리)" 
	            	value="${birthDate != null ? birthDate : ''}" required>
	            <br>
	             <!-- 주소 입력란 -->
	            <div class="zipcodeContainer">
	            <input type="text" class="inputField zipcodeField" name="postCode" id="postcode" placeholder="우편번호"
	            	value="${postCode != null ? postCode : ''}">
	            <input type="button" onclick="execDaumPostcode()" class="searchAddressBtn" value="주소 검색"><br>
	            </div>
	            <input type="text" class="inputField" name="addr1"  id="address" placeholder="주소"
	            	value="${addr1 != null ? addr1 : ''}">
	            <input type="text" class="inputField" name="addr2" id="detailAddress" placeholder="상세주소"
	            	value="${addr2 != null ? addr2 : ''}">
	            <input type="text" class="inputField" name="extraAddr" id="extraAddress" placeholder="참고항목"
	            	value="${extraAddr != null ? extraAddr : ''}">
	
	            <button type="submit" class="submitBtn">가입하기</button>
	           
	        </form>
	        <c:if test="${not empty error}">
				<div class="errorMsg">${err}</div>
			</c:if>
	    </div>
	    </div> <!-- .content -->
	<jsp:include page="/header/footer.jsp" />
    </div>
</body>
</html>