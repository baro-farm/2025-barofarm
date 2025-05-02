<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문/결제</title>
<link rel="stylesheet" href="${contextPath }/buyer/packagePayment.css">
<script src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
</head>
<body>

<jsp:include page="/header/mainHeader.jsp" />
	<div class="container">
		<div class="sideMenu">
			<jsp:include page="/header/buyerMenu.jsp" />
		</div>
		<div id="content">
		    <h1 class="title">꾸러미 구독</h1>
		
			<!-- 배송지 섹션 -->
			<h2 class="sub-title">배송지</h2>
			<div class="address-section">
			    <div class="address-header">
			        <div class="address-info">
			            <p class="receiver-name" id="receiverName" data-name="${sessionScope.defaultAddress.name}">${sessionScope.defaultAddress.name} (${sessionScope.defaultAddress.nickname})</p>
			            <p class="receiver-phone" id="receiverPhone" data-phone="${sessionScope.defaultAddress.phone}">${sessionScope.defaultAddress.phone}</p>
			            <p class="receiver-address" id="receiverAddress" data-address="${sessionScope.defaultAddress.addr1} ${sessionScope.defaultAddress.addr2}">${sessionScope.defaultAddress.addr1} ${sessionScope.defaultAddress.addr2}</p>
			        </div>
			        <button type="button" class="change-address-btn" onclick="openAddressModal()">변경</button>
			    </div>
			</div>

		   
		<h2 class="sub-title">구독 상품</h2>


<div class="store-group">
    <h2>${storeName}</h2>
    <hr class="hr">
    <div class="cart-item">
        <img src="${contextPath}${pack.imgUrl}" />
        <div class="cart-item-info">
            <div class="info-1">
                <p class="product-title">${pack.packageName}</p>
                <p class="price">월 <fmt:formatNumber value="${pack.packagePrice}" type="number" groupingUsed="true" />원</p>
            </div>
            <div class="info-2">
                <p>${pack.packageUnit} 꾸러미</p>
                <input type="hidden" name="packageNum" value="${pack.packageNum}" />
            </div>
            <div class="info-3">
                <p class="total"><fmt:formatNumber value="${pack.packagePrice}" type="number" groupingUsed="true" />원</p>
            </div>
        </div>
    </div>
    <div class="subscription-info">
	    <!-- 시작일, 종료일을 가진 숨겨진 span 태그 -->
		<span data-start-date="${pack.startDate}" data-end-date="${pack.endDate}"></span>
    <!-- 단가 표시용 -->
<p id="unitPrice" data-price="${pack.packagePrice}" style="display:none;"></p>
	  <p>
	    구독 기간:
	    <strong>${pack.startDate} ~ ${pack.endDate}</strong><br/>
	    현재 날짜: <strong><span id="today"></span></strong>
	  </p>
	  <p id="subscriptionMsg" class="sub-msg"></p>
	  <p class="price-msg">월 <strong><fmt:formatNumber value="${pack.packagePrice}" type="number" groupingUsed="true" />원</strong></p>
	  <!-- <p class="final-price-msg">결제 금액: <span id="finalPriceText"></span></p> -->
	</div>
</div>

<div class="all-total-div">
    <p class="all-total">총 결제 금액: <fmt:formatNumber value="${totalPrice}" type="number" groupingUsed="true" />원</p>
    <button type="button" id="payment">결제하기</button>
</div>
		    

		</div>
	</div>
	
	<!-- 배송지 선택 모달 -->
	<div id="addressModal" class="modal-overlay" style="display:none;">
	    <div class="modal-content" style="width: 400px;">
	        <h3>배송지 선택</h3>
	        <ul id="addressList">
	            <c:forEach var="addr" items="${sessionScope.addressList}">
	                <li class="address-item" 
	                    data-name="${addr.name}" 
	                    data-nickname="${addr.nickname}" 
	                    data-phone="${addr.phone}" 
	                    data-addr1="${addr.addr1}" 
	                    data-addr2="${addr.addr2}">
	                    <p>${addr.name} (${addr.nickname})</p>
	                    <p>${addr.phone}</p>
	                    <p>${addr.addr1} ${addr.addr2}</p>
	                </li>
	            </c:forEach>
	        </ul>
	        <button type="button" onclick="closeAddressModal()">닫기</button>
	    </div>
	</div>
	
	<jsp:include page="/header/footer.jsp" />
	<script src="${contextPath }/buyer/packagePayment.js"></script>
	<script>
	document.addEventListener('DOMContentLoaded', () => {
		  const paymentBtn = document.getElementById('payment');
		  paymentBtn.addEventListener('click', async () => {
		    // 1. 결제할 금액, 주문정보 준비 (JSP에서 값 가져오기)
		    /* const amount = parseInt(
		      document.querySelector('.all-total').innerText.replace(/[^\d]/g, ''),
		      10
		    ); */
		    /* let amount = payMonths * unitPrice; */
		    let amount = '${totalPrice}';

		    const rName = document.getElementById('receiverName').dataset.name;
		    const rPhone = document.getElementById('receiverPhone').dataset.phone;
		    const rAddress = document.getElementById('receiverAddress').dataset.address;

            const packageNum = document.querySelector('input[name="packageNum"]').value;
		    // 2. 포트원 결제창 호출
		    
		    const IMP = window.IMP;
		    IMP.init(${impKey});

		    IMP.request_pay(
		      {
		        pg: 'kcp', // PG사 선택 (예: kakaopay, tosspayments 등)
		        pay_method: 'card', // 결제 수단 (card, trans, vbank 등)
		        merchant_uid: 'payment_' + new Date().getTime(), // 주문번호 (고유해야 함)
		        name: '바로팜 꾸러미 구독', // 결제 상품명
		        amount: amount, // 결제 금액
		        buyer_email: '', // 구매자 이메일
		        buyer_name: '${sessionScope.userName != null ? sessionScope.userName : ""}',
		        buyer_tel: '${sessionScope.userPhone != null ? sessionScope.userPhone : ""}',
		      },
		      function (rsp) {
		        if (rsp.success) {
		          // ✅ 결제 성공 시 서버에 결제 정보 전달 (imp_uid)
		          fetch(`${contextPath}/packageSubscribe`, {
		            method: 'POST',
		            headers: { 'Content-Type': 'application/json' },
		            body: JSON.stringify({
		              imp_uid: rsp.imp_uid, // 아임포트 거래 고유번호
		              merchant_uid: rsp.merchant_uid,
		              amount: amount,
		              packageNum: parseInt(packageNum, 10),
		              rname: rName,
		              rphone: rPhone,
		              raddress: rAddress,
		              type: '바로팜 꾸러미 구독',
		              payInfo: 'KCP-카드',
		            }),
		          })
		            .then((res) => res.json())
		            .then((data) => {
		              if (data.success) {
		                location.href = `/barofarm/orderComplete?transactionId=\${data.transactionId}`;
		                
		              } else {
		                alert('결제 검증에 실패했습니다.');
		              }
		            });
		        } else {
		          alert('결제에 실패했습니다.\n' + rsp.error_msg);
		        }
		      }
		    );
		  });
		});
	</script>
</body>
</html>