<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
      
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>바로팜 마이페이지</title>
    <script>

        document.addEventListener("DOMContentLoaded", function () {
            let links = document.querySelectorAll(".menu-list li a");
            let currentURL = window.location.pathname;

            // 모든 링크에서 active 제거 후 현재 페이지와 일치하는 링크에 active 추가
            links.forEach(link => {
                link.classList.remove("active");

                if (link.getAttribute("href") === currentURL) {
                    link.classList.add("active");
                }

                // 클릭 이벤트 추가
                link.addEventListener("click", function () {
                    links.forEach(l => l.classList.remove("active")); // 모든 메뉴에서 active 제거
                    this.classList.add("active"); // 클릭한 메뉴에 active 추가
                });
            });
        });
    </script>
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
            <button onclick="location.href='${contextPath}/logout'">로그아웃</button>
        </div>
    </div>

    <!-- 마이메뉴 -->
    <div class="menu-title">
        <span>마이페이지</span>
    </div>
    <ul class="menu-list">
        <li><a href="prodOrderList" >주문내역 및 배송조회</a></li>
        <li><a href="infoFoam">회원 정보 수정</a></li>

        <li><a href="questionList">상품 문의</a></li>
        
        <li class="dropdown">
            <a style="text-decoration: none;">리뷰</a>
            <ul class="submenu">
                <li><a href="prodWritableReviewList">작성 가능한 리뷰</a></li>
                <li><a href="prodWrittenReviewList">내가 작성한 리뷰</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a style="text-decoration: none;">꾸러미</a>
            <ul class="submenu">
                <li><a href="packOrderList">주문내역 및 배송조회</a></li>
            	<li><a href="#">리뷰</a></li>
            	<li><a href="packQuestionList">상품 문의</a></li>
                <li><a href="#">구독 관리</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a style="text-decoration: none;">콕팜</a>
            <ul class="submenu">
                <li><a href="kockFarmPostList">내 게시글</a></li>
                <li><a href="kockCommentList">내 댓글</a></li>
            </ul>
        </li>

        <li><a href="addressList">배송지 주소관리</a></li>
        <li><a href="adminQAList">고객센터</a></li>
    </ul>
</div>


</body>
</html>