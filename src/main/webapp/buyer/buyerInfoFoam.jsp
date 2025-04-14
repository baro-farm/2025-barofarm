<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="dto.buyer.User" %>
<%	
	User user = (User)request.getAttribute("user");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 정보</title>
    <link rel="stylesheet" href="${contextPath}/buyer/buyerInfoFoam.css">

    <style>
     
    </style>
</head>
<body>

<div class="container">
    <div class="header">내 정보 수정</div>

    <form action="">
        <div class="formGroup">
            <label>아이디</label>
            <input type="text" value="${user.userID}" disabled  }>
        </div>

        <div class="formGroup">
            <label>비밀번호</label>
            <input type="password">
        </div>

        <div class="formGroup">
            <label>이름</label>
            <input type="text"value="${user.userName}">
        </div>

        <div class="formGroup">
            <label>전화번호</label>
            <input type="text" value="${user.phone}">
        </div>

        <div class="formGroup">
            <label>이메일</label>
            <input type="email" value="abc@email.com">
        </div>

        <div class="formGroup">
            <label>주소</label>
            <div class="address-container">
                <input type="text" placeholder="우편번호" disabled>
                <button type="button" class="address-btn">주소검색</button>
            </div>
            <input type="text" placeholder="주소" disabled>
            <input type="text" placeholder="상세주소">
        </div>

        <div class="btn-group">
            <button type="submit" class="btn btn-save">수정</button>
            <button type="button" class="btn btn-cancel">취소</button>
            <button type="button" class="btn btn-delete">회원탈퇴</button>
        </div>
    </form>
</div>

</body>
</html>