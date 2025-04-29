<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
  const contextPath = "${contextPath}";
</script>
<!DOCTYPE html>
<html lang="ko">
    <!-- 로그인버튼 hover할떄 다른 디자인이 움직임 / 종버튼 알람올때 변경할지 / 종에 알림 수 입력하는거-->
 <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${contextPath}/header/reset.css">
    <link rel="stylesheet" href="${contextPath}/header/mainHeader.css">
    <title>Document</title>
      <c:if test="${user != null}">
	    <script src="https://www.gstatic.com/firebasejs/10.8.1/firebase-app-compat.js"></script>
	    <script src="https://www.gstatic.com/firebasejs/10.8.1/firebase-messaging-compat.js"></script>
	  </c:if>
 </head>
 <script>
 window.onload = function () {
	  const input = document.getElementById("headerSearchInput");
	  const button = document.getElementById("headerSearchBtn");

	  button.addEventListener("click", () => {
	    search();
	  });

	  input.addEventListener("keydown", (e) => {
	    if (e.key === "Enter") {
	      search();
	    }
	  });

	  function search() {
	    const keyword = input.value.trim();
	    if (keyword === "" || keyword == null) {
	      alert("검색어를 입력해주세요!");
	      return;
	    }

	    const ekeyword = encodeURIComponent(keyword);
	    location.href = `\${contextPath}/searchProductList?keyword=\${ekeyword}&page=1&sort=salesVolume`;
	  }
	};
</script>
 <body>
 	<div class="mainHeader">
        <div class="userMenu">
            <ul class="userMenuUl">
            	<c:choose>
            		<c:when test="${user==null}">
	            		<li class="userli"><a href="login" id="login" class="userBtn">로그인</a></li>
		                <li class="userli"><a href="join" id="join" class="userBtn">회원가입</a></li>
		                <li class="userli"><a href="shoppingCart" id="shoppingCart" class="userBtn">장바구니</a></li>
	                </c:when>
	                <c:otherwise>
	                	<li class="userli"><p>${user.userId}</p></li>
	                	<li class="userli"><a href="logout" id="" class="userBtn">로그아웃</a></li>
	                	<c:choose>
					        <c:when test="${user.isSeller == true}">
					          <li class="userli"><a href="sellerProductList" class="userBtn">마이스토어</a></li>
					          <li class="userli"><a href="shoppingCart" id="shoppingCart" class="userBtn">장바구니</a></li>
	                		  <li class="userli"><a href="#" id="alarm"><i class="bi bi-bell"></i></a></li>
					        </c:when>
							<c:when test="${user.userId == 'admin'}">
							    <li class="userli"><a href="userList" class="userBtn">관리자페이지</a></li>
							</c:when>
							<c:otherwise>
							    <li class="userli"><a href="myPageMain" class="userBtn">마이페이지</a></li>
							    <li class="userli"><a href="shoppingCart" id="shoppingCart" class="userBtn">장바구니</a></li>
	                			<li class="userli"><a href="#" id="alarm"><i class="bi bi-bell"></i></a></li>
							</c:otherwise>
					      </c:choose>
	                </c:otherwise>
             	</c:choose>
            </ul>
        </div>
        <!-- 헤더 -->
        <header class="headerMenu">
            <div class="headerlogo">
                <a href="main">
                    <img src="${contextPath }/img/barologo1.png" alt="barologo1" border="0" class="logo">
                </a>
            </div>
            <div class="navMenu">
                <ul class="navMenuUl">
                    <li class="headerli"><a href="newProductList" class="headerBtn" id="new">신제품</a></li>
                    <li class="headerli"><a href="bestProductList" class="headerBtn" id="best">베스트</a></li>
                    <li class="headerli"><a href="#" class="headerBtn" id="package">꾸러미</a></li>
                    <li class="headerli"><a href="kockFarmList" class="headerBtn" id="kockFarm">콕팜</a></li>
                    <li class="headerli"><a href="adminQAList" class="headerBtn" id="kockFarm">문의하기</a></li>
                    <li class="headerli"><a href="userNoticeList" class="headerBtn" id="notice">공지사항</a></li>
                </ul>
            </div>
            <div class="searchBox">
                <input type="text" name="keyword" id="headerSearchInput" value="${keyword}" placeholder="검색어 입력">
                <button id="headerSearchBtn" aria-label="상품 검색바">
                    <i class="bi bi-search"></i>
                </button>
            </div>
        </header>
        <hr>
        </div>
<c:if test="${user != null}">
<script>
  const firebaseConfig = {
    apiKey: "AIzaSyAMTyM_wgDLIGSRbXOec448CKNRcnlggRY",
    authDomain: "kosta-1213b.firebaseapp.com",
    projectId: "kosta-1213b",
    messagingSenderId: "798921087749",
    appId: "1:798921087749:web:8e5e43ff0c566e08d1356c"
  };
  firebase.initializeApp(firebaseConfig);
  const messaging = firebase.messaging();

  messaging.getToken({ vapidKey: "BE3AHxHgnALTTVtwcKYkxQOqktkJQ3aDHKlG2x-N85cdNXX_NS6ePIHjZuwqvivLYPjMMYaw4ytzg4hjUeFYZWk" })
    .then((token) => {
      console.log("FCM 토큰:", token);

      fetch(contextPath + '/updateFcmToken', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ fcmToken: token })
      });
    })
    .catch(err => console.error('토큰 가져오기 실패:', err));
</script>
</c:if>
        
 </body>
</html>