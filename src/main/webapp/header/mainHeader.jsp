<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- 로그인버튼 hover할떄 다른 디자인이 움직임 / 종버튼 알람올때 변경할지 / 종에 알림 수 입력하는거-->
 <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="./mainHeader.css">
    <title>Document</title>
 </head>
 <body>
        <div class="user-menu">
            <ul>
                <li class="userli"><a href="#" id="login" class="userBtn">로그인</a></li>
                <li class="userli"><a href="#" id="join" class="userBtn">회원가입</a></li>
                <li class="userli"><a href="#" id="basket" class="userBtn">장바구니</a></li>
                <li class="userli"><a href="#" id="alarm"><i class="bi bi-bell"></i></a></li>
            </ul>
        </div>
        <!-- 헤더 -->
        <header>
            <div>
                <a href="#">
                    <img src="https://i.ibb.co/zH5kPJNt/barologo1.png" alt="barologo1" border="0" class="logo">
                </a>
            </div>            
            <div class="nav-menu">
                <ul>
                    <li class="headerli"><a href="#" class="headerBtn" id="new">신제품</a></li>
                    <li class="headerli"><a href="#" class="headerBtn" id="best">베스트</a></li>
                    <li class="headerli"><a href="#" class="headerBtn" id="package">꾸러미</a></li>
                    <li class="headerli"><a href="#" class="headerBtn" id="kfarm">콕팜</a></li>
                    <li class="headerli"><a href="#" class="headerBtn" id="notice">공지사항</a></li>
                </ul>
            </div>
            <div class="search-box">
                <input type="text" placeholder="검색어 입력">
                <button>
                    <i class="bi bi-search"></i>
                </button>
            </div>
        </header>
        <hr>
        
 </body>
</html>