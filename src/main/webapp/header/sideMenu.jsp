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

            links.forEach(link => {
                // 기존 active 제거
                link.classList.remove("active");

                // 현재 URL과 일치하는 메뉴에 active 추가
                if (link.getAttribute("href") === currentURL) {
                    link.classList.add("active");
                }

                // 클릭할 때 기존 active 제거 후 새로운 active 추가
                link.addEventListener("click", function () {
                    links.forEach(l => l.classList.remove("active")); // 모든 a 태그에서 active 제거
                    this.classList.add("active"); // 클릭한 a 태그에 active 추가
                });
            });
        });
    </script>
    <link rel="stylesheet" href="${contextPath}/header/sideMenu.css">
</head>
<body>

<!-- 왼쪽 사이드바 -->
<div class="sidebar">
    <div class="menu-title">
        <span>카테고리</span>
    </div>
    <ul class="menu-list">
        <li><a href="productList?cateNum=1" class="active">배추/무/대파/부추</a></li>
        <li><a href="productList?cateNum=2">오이/호박/가지</a></li>
        <li><a href="productList?cateNum=3">고추/피망/파프리카/열매채소</a></li>
        <li><a href="productList?cateNum=4">감자/고구마</a></li>
        <li><a href="productList?cateNum=5">양상추/양배추/새싹채소</a></li>
        <li><a href="productList?cateNum=6">당근/연근/뿌리채소</a></li>
        <li><a href="productList?cateNum=7">마늘/양파/생강/파</a></li>
    </ul>

    <div class="menu-title">
        <span>꾸러미</span>
    </div>
    <ul class="menu-list">
        <li><a href="productList?cateNum=8">혼밥 꾸러미</a></li>
        <li><a href="productList?cateNum=9">커플 꾸러미</a></li>
        <li><a href="productList?cateNum=10">트리오 꾸러미</a></li>
        <li><a href="productList?cateNum=11">패밀리 꾸러미</a></li>
    </ul>
</div>


</body>
</html>