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
    <link rel="stylesheet" href="${contextPath}/header/mainAlarm.css">
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
        <!-- 헤더 -->
        <header class="headerMenu">
            <div class="headerlogo">
                <a href="main">
                    <img src="${contextPath }/img/barologo1.png" alt="barologo1" border="0" class="logo">
                </a>
            </div>
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
            <div class="navMenu">
                <ul class="navMenuUl">
                    <li class="headerli"><a href="newProductList" class="headerBtn" id="new">신제품</a></li>
                    <li class="headerli"><a href="bestProductList" class="headerBtn" id="best">베스트</a></li>
                    <li class="headerli"><a href="packageListByAll" class="headerBtn" id="package">꾸러미</a></li>
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
const contextPath2 = "${contextPath}";

const firebaseConfig = {
  apiKey: "AIzaSyAMTyM_wgDLIGSRbXOec448CKNRcnlggRY",
  authDomain: "kosta-1213b.firebaseapp.com",
  projectId: "kosta-1213b",
  messagingSenderId: "798921087749",
  appId: "1:798921087749:web:8e5e43ff0c566e08d1356c"
};

firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();

navigator.serviceWorker.register(contextPath2 + '/firebase-messaging-sw.js')
  .then((registration) => {
    //console.log('Service Worker 등록 성공:', registration.scope);

    return messaging.getToken({
      vapidKey: "BE3AHxHgnALTTVtwcKYkxQOqktkJQ3aDHKlG2x-N85cdNXX_NS6ePIHjZuwqvivLYPjMMYaw4ytzg4hjUeFYZWk",
      serviceWorkerRegistration: registration
    });
  })
  .then((token) => {
    //console.log("FCM 토큰:", token);

    return fetch(contextPath2 + '/updateFcmToken', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ fcmToken: token })
    });
  })
  .catch((err) => {
    console.error("❌ FCM 토큰 가져오기 실패:", err);
  });
</script>

<!-- 헤더 알림 -->
<div id="headerAlarm" class="modal-wrapper" style="display:none;">

<div class="modal">
	<div class="modal-header">
	    <div class="date-header">최근 알림</div>
	    <button class="close-btn" onclick="closeModal()">&times;</button>
	</div>
</div>
</div>
<script>
const userNum = ${user.userNum};

document.querySelector('#alarm').addEventListener('click', async () => {
    const response = await fetch(`getAlarmList?seNum=\${userNum}`);
    if (response.ok) {
        const alarms = await response.json();
        renderAlarms(alarms);
        document.getElementById('headerAlarm').style.display = 'flex';
    }
});

function closeModal() {
    document.getElementById('headerAlarm').style.display = 'none';
}

function renderAlarms(alarms) {
    const container = document.querySelector('#headerAlarm .modal');

    const oldNotifications = container.querySelectorAll('.notification');
    oldNotifications.forEach(el => el.remove());

    if (alarms.length === 0) {
        const noAlarm = document.createElement('div');
        noAlarm.className = 'notification';
        noAlarm.innerHTML = `
            <div class="notification-icon icon-yellow">📩</div>
            <div class="notification-text">
                <h3>알림 없음</h3>
                <p>최근 알림이 없습니다.</p>
            </div>
            <span class="time">-</span>
        `;
        container.appendChild(noAlarm);
        return;
    }

    alarms.forEach(alarm => {
        const alarmEl = document.createElement('div');
        alarmEl.className = 'notification';

        const icon = getAlarmIcon(alarm.type);
        const time = formatAlarmTime(alarm.createdAt);

        alarmEl.innerHTML = `
            <div class="notification-icon \${icon.color}">\${icon.symbol}</div>
            <div class="notification-text">
            	<a href="\${contextPath2}/detailKockFarm?kockNum=\${alarm.targetNum}" class="alarm-link">    
            	<h3>새로운 \${alarm.type}</h3>
                <p>[\${alarm.content2}]에 등록되었습니다.</p>
                </a>
            </div>
            <span class="time">\${time}</span>
            <button class="close-notif" onclick="markAsRead(\${alarm.alarmNum}, this)">&times;</button>
        `;

        container.appendChild(alarmEl);
    });
}

function getAlarmIcon(type) {
    if (type.includes("콕팜")) return { symbol: "📩", color: "icon-yellow" };
    if (type.includes("답변")) return { symbol: "🔔", color: "icon-red" };
    if (type.includes("주문")) return { symbol: "📢", color: "icon-green" };
    return { symbol: "🔔", color: "icon-yellow" };
}

function formatAlarmTime(datetime) {
    const date = new Date(datetime);
    const now = new Date();
    const diffMin = Math.floor((now - date) / 60000);

    if (diffMin < 1) return "방금 전";
    if (diffMin < 60) return `\${diffMin}분 전`;

    const isSameDay =
        date.getFullYear() === now.getFullYear() &&
        date.getMonth() === now.getMonth() &&
        date.getDate() === now.getDate();

      const hours = date.getHours().toString().padStart(2, "0");
      const mins = date.getMinutes().toString().padStart(2, "0");

      if (isSameDay) {
        return `오늘 \${hours}:\${mins}`; // 오늘이라면 시간만
      } else {
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, "0");
        const day = date.getDate().toString().padStart(2, "0");
        return `\${year}.\${month}.\${day} \${hours}:\${mins}`;
      }
}

async function markAsRead(alarmNum, btnElement) {
    const res = await fetch('checkAlarm', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ alarmNum })
    });

    if (res.ok) {
        btnElement.parentElement.remove(); // ✅ 알림 DOM 삭제
    } else {
        alert('알림 확인 처리 실패');
    }
}
</script>

</c:if>
        
 </body>
</html>