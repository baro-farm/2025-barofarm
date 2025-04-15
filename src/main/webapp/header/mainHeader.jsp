<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
    <!-- 로그인버튼 hover할떄 다른 디자인이 움직임 / 종버튼 알람올때 변경할지 / 종에 알림 수 입력하는거-->
 <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${contextPath}/header/reset.css">
    <link rel="stylesheet" href="${contextPath}/header/mainHeader.css">
    <title>Document</title>
 </head>
 <body>
 	<div class="mainHeader">
        <div class="userMenu">
            <ul class="userMenuUl">
                <li class="userli"><a href="#" id="login" class="userBtn">로그인</a></li>
                <li class="userli"><a href="#" id="join" class="userBtn">회원가입</a></li>
                <li class="userli"><a href="#" id="shoppingCart" class="userBtn">장바구니</a></li>
                <li class="userli"><a href="#" id="alarm"><i class="bi bi-bell"></i></a></li>
            </ul>
        </div>
        <!-- 헤더 -->
        <header class="headerMenu">
            <div class="headerlogo">
                <a href="#">
                    <img src="https://i.ibb.co/zH5kPJNt/barologo1.png" alt="barologo1" border="0" class="logo">
                </a>
            </div>
            <div class="navMenu">
                <ul class="navMenuUl">
                    <li class="headerli"><a href="#" class="headerBtn" id="new">신제품</a></li>
                    <li class="headerli"><a href="#" class="headerBtn" id="best">베스트</a></li>
                    <li class="headerli"><a href="#" class="headerBtn" id="package">꾸러미</a></li>
                    <li class="headerli"><a href="#" class="headerBtn" id="kockFarm">콕팜</a></li>
                    <li class="headerli"><a href="#" class="headerBtn" id="notice">공지사항</a></li>
                </ul>
            </div>
            <div class="searchBox">
                <input type="text" placeholder="검색어 입력">
                <button>
                    <i class="bi bi-search"></i>
                </button>
            </div>
        </header>
        <hr>
        </div>
 </body>
</html>