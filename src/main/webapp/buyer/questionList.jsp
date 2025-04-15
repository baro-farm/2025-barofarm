<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page import="dto.buyer.Address" %>
<%@page import="java.util.List"%>
<% 
	Address address = (Address)request.getAttribute("address");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 문의 내역</title>
    <link rel="stylesheet" href="${contextPath }/buyer/questionList.css">
</head>
<body>

<div class="container">
    <div class="header">상품 문의 내역</div>

    <!-- 필터 섹션 -->
    <div class="filterSection">
        <select>
            <option>6개월</option>
            <option>3개월</option>
            <option>1개월</option>
        </select>
        <select>
            <option>전체</option>
            <option>답변 완료</option>
            <option>답변 미완료</option>
        </select>
    </div>

    <!-- 문의 내역 1 -->
    <div class="post">
        <div class="imgBox">img</div>
        <div class="postContent">
            <div class="postTitle">[친환경 수정재배] 유럽 샐러드 채소 야채 1kg ...</div>
            <div class="postInfo">강화도 뒷밭 | 25.03.26 08:33</div>
            <button class="replyBtn" onclick="toggleReply(this)">답변 0 ></button>
            
        </div>
    </div>

    <!-- 문의 내역 2 -->
    <div class="post">
        <div class="imgBox">img</div>
        <div class="postContent">
            <div class="postTitle">[친환경 수정재배] 유럽 샐러드 채소 야채 1kg ...</div>
            <div class="postInfo">강화도 뒷밭 | 25.03.26 08:33</div>
            <button class="replyBtn" onclick="toggleReply(this)">답변 1 ></button>
            <div class="replyBox">
                <strong>답변</strong>
                <p>포장은 뽁뽁이로 둘러싸서 갑니다.</p>
            </div>
        </div>
    </div>
</div>

<script>
    function toggleReply(button) {
        let replyBox = button.nextElementSibling;
        if (replyBox.style.display === "block") {
            replyBox.style.display = "none";
            button.textContent = button.textContent.replace("▲", ">");
        } else {
            replyBox.style.display = "block";
            button.textContent = button.textContent.replace(">", "▲");
        }
    }
</script>

</body>
</html>