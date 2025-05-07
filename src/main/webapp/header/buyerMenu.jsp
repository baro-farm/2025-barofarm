<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
      
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    
    <title>바로팜 마이페이지</title>
        <link rel="stylesheet" href="${contextPath}/header/buyerMenu.css">

</head>
<body>

<!-- 왼쪽 사이드바 -->
<div class="sidebar">
    <!-- 회원 정보 박스 -->
    <div class="user-info">
        <span>회원정보</span>
        <hr>
        <c:if test="${not empty user}">
    		<p>${user.userName} 님</p>
		</c:if>
        <div class="user-buttons">
            <button onclick="location.href='${contextPath}/myPageMain'">마이페이지</button>
            <button onclick="location.href='${contextPath}/infoFoam'">정보 수정</button>
        </div>
    </div>

    <!-- 마이메뉴 -->
    <div class="menu-title">
        <span>마이페이지</span>
    </div>
    <ul class="menu-list">
        <li><a href="buyerOrderList" >주문내역</a></li>
        <li>
            <a style="text-decoration: none;">리뷰</a>
            <ul class="submenu">
                <li><a href="allWritableReviewList">&rsaquo; 작성 가능한 리뷰</a></li>
                <li><a href="allWrittenReviewList">&rsaquo; 내가 작성한 리뷰</a></li>
            </ul>
        </li>
        <li>
            <a style="text-decoration: none;">꾸러미</a>
            <ul class="submenu">
                <li><a href="packSubList">&rsaquo; 구독 관리</a></li>
            </ul>
        </li>
        <li>
            <a style="text-decoration: none;">콕팜</a>
            <ul class="submenu">
                <li><a href="kockFarmPostList">&rsaquo; 내 게시글</a></li>
                <li><a href="kockCommentList">&rsaquo; 내 댓글</a></li>
            </ul>
        </li>
        <li><a href="addressList">배송지 주소관리</a></li>
        <li><a href="${contextPath}/buyerAlarmList">알림 내역</a></li>
        <li><a href="adminQAList">문의하기</a></li>
    </ul>
</div>

<script>
let links = document.querySelectorAll(".sidebar a");
let currentURL = window.location.pathname;


links.forEach(link => {
    let linkHref = link.getAttribute("href");
    if (!linkHref || linkHref === "#") return;

    let fullHref = linkHref.startsWith("/") 
        ? `\${contextPath}\${linkHref}` 
        : `\${contextPath}/\${linkHref}`;

    if (currentURL === fullHref) {
        link.classList.add("active");
    }
});

</script>
</body>
</html>