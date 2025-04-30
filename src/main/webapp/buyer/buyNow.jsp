<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문/결제</title>
<link rel="stylesheet" href="${contextPath }/buyer/buyNow.css">
<script src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
</head>
<body>
	<jsp:include page="/header/mainHeader.jsp" />
	<div class="container">
		<div class="sideMenu">
			<jsp:include page="/header/buyerMenu.jsp" />
		</div>
		<div id="content">
		    <h1 class="title">주문/결제</h1>
		
			<!-- 배송지 섹션 -->
			<h2 class="sub-title">배송지</h2>
			<div class="address-section">
			    <div class="address-header">
			        <div class="address-info">
			            <p class="receiver-name" id="receiverName">${sessionScope.defaultAddress.name} (${sessionScope.defaultAddress.nickname})</p>
			            <p class="receiver-phone" id="receiverPhone">${sessionScope.defaultAddress.phone}</p>
			            <p class="receiver-address" id="receiverAddress">${sessionScope.defaultAddress.addr1} ${sessionScope.defaultAddress.addr2}</p>
			        </div>
			        <button type="button" class="change-address-btn" onclick="openAddressModal()">변경</button>
			    </div>
			</div>

		    <c:if test="${empty sessionScope.paymentCartMap}">
		        <p>선택된 상품이 없습니다.</p>
		    </c:if>

		<h2 class="sub-title">주문상품</h2>

		<c:if test="${not empty sessionScope.paymentCartMap}">
		    <c:set var="totalSum" value="0" />
		    
		    <c:forEach var="storeEntry" items="${sessionScope.paymentCartMap}">
		        <div class="store-group">
		            <h2>${storeEntry.key}</h2> <!-- ✅ 가게 이름 -->
		            <hr class="hr">
		            
		            <c:forEach var="product" items="${storeEntry.value}">
		                <c:set var="productSum" value="0" />
		                <div class="cart-item">
		                    <img src="${contextPath}${product.imgUrl}" />
		                    <div class="cart-item-info">
		                        <div class="info-1">
		                            <p class="product-title">${product.productName}</p>
		                            <p class="price">${product.basePrice}원</p>
		                        </div>
		                        <div class="info-2">
		                            <div class="option-list">
		                                <c:forEach var="opt" items="${product.options}">
		                                    <p>${opt.option} / ${opt.quantity}개 (+${opt.optionPrice}원)</p>
		                                    <c:set var="productSum" value="${productSum + opt.totalPrice}" />
		                                    <input type="hidden" name="optionNums" value="${opt.optionNum}" />
		                                    <input type="hidden" name="quantities" value="${opt.quantity}" />
		                                </c:forEach>
		                            </div>
		                        </div>
		                        <div class="info-3">
		                            <p class="total">${productSum}원</p>
		                        </div>
		                    </div>
		                </div>
		                <c:set var="totalSum" value="${totalSum + productSum}" />
		            </c:forEach>
		        </div>
		    </c:forEach>
		
		    <div class="all-total-div">
		        <p class="all-total">총 결제 금액: ${totalSum}원</p>
		        <button type="button" id="payment">결제하기</button>
		    </div>
		</c:if>
		

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
	<script src="${contextPath }/buyer/buyNow.js"></script>
	<script>
	document.addEventListener('DOMContentLoaded', () => {
		  const paymentBtn = document.getElementById('payment');
		  paymentBtn.addEventListener('click', async () => {
		    // 1. 결제할 금액, 주문정보 준비 (JSP에서 값 가져오기)
		    const amount = parseInt(
		      document.querySelector('.all-total').innerText.replace(/[^\d]/g, ''),
		      10
		    );
		    const optionNums = Array.from(
    		  document.querySelectorAll('input[name="optionNums"]')
    		).map((input) => parseInt(input.value));

    		const quantities = Array.from(
    		  document.querySelectorAll('input[name="quantities"]')
    		).map((input) => parseInt(input.value));


		    const rName = document.getElementById('receiverName').innerText.trim();
            const rPhone =  document.getElementById('receiverPhone').innerText.trim();
            const rAddress = document.getElementById('receiverAddress').innerText.trim();
            
		    // 2. 포트원 결제창 호출
		    
		    const IMP = window.IMP;
		    IMP.init(${impKey});

		    IMP.request_pay(
		      {
		        pg: 'kcp', // PG사 선택 (예: kakaopay, tosspayments 등)
		        pay_method: 'card', // 결제 수단 (card, trans, vbank 등)
		        merchant_uid: 'payment_' + new Date().getTime(), // 주문번호 (고유해야 함)
		        name: '바로팜 상품 결제', // 결제 상품명
		        amount: amount, // 결제 금액
		        buyer_email: '', // 구매자 이메일
		        buyer_name: '${sessionScope.userName != null ? sessionScope.userName : ""}',
		        buyer_tel: '${sessionScope.userPhone != null ? sessionScope.userPhone : ""}',
		      },
		      function (rsp) {
		        if (rsp.success) {
		          // ✅ 결제 성공 시 서버에 결제 정보 전달 (imp_uid)
		          fetch(`${contextPath}/buyNowComplete`, {
		            method: 'POST',
		            headers: { 'Content-Type': 'application/json' },
		            body: JSON.stringify({
		              imp_uid: rsp.imp_uid, // 아임포트 거래 고유번호
		              merchant_uid: rsp.merchant_uid,
		              amount: amount,
		              optionNums: optionNums,
		              quantities: quantities,
		              rName: rName,
		              rPhone: rPhone,
		              rAddress: rAddress,
		              type: '바로팜 상품 결제',
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
