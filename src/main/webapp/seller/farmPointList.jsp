<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>팜포인트 조회</title>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <link rel="stylesheet" href="${contextPath}/seller/farmPointList.css" />
</head>
<body>
	<div class="container">
		<div class="wrapper">
			<div class="inner_body">
			        <div class="sidebar">
						<jsp:include page="/header/sellerHeader.jsp" />
			        </div>
		    </div>
		    <header id="header">
		        <div id="info">
		            <span id="email">kosta@kosta.com</span>
		            <span>내 정보</span>
		            <span>로그아웃</span>
		        </div>
		    </header>
    			<div class="content">
		        <div class="point-header">
		            <h1 class="title">팜포인트</h1>
		            <c:if test="${isAlarm eq false }">
			            <div class="subscribe-box">
			                <span class="question-icon">❓</span>
			                <button class="subscribe-btn">
			                    콕팜링 구독하기
			                </button>
			            </div>
		            </c:if>
		            <c:if test="${isAlarm eq true }">
			            <div class="subscribe-box">
			                <button class="subscribed-btn">
			                    ✔ 콕팜링 구독취소
			                </button>
			            </div>
					</c:if>
		        </div>
		        <div class="point-box">
		            <span class="point-label">보유 포인트</span>
		            <div>
		            <span class="point-value">💰 ${point }P</span>
		            <button class="charge-btn">충전하기</button>
		            </div>
		        </div>

		        <div class="history-section">
		        	<div>
   			            <h3>포인트 내역</h3>
		        		<form method="get" action="${contextPath}/farmPointList" >
						  <select name="searchType">
						    <option value="usedPoint" ${param.searchType == 'usedPoint' ? 'selected' : ''}>사용/충전</option>
						    <option value="type" ${param.searchType == 'type' ? 'selected' : ''}>상세내역</option>
						  </select>
						  <input type="text" name="keyword" value="${param.keyword}" placeholder="검색어 입력">
						  <input type="date" name="startDateFrom" value="${param.startDateFrom}" />
						  ~
						  <input type="date" name="startDateTo" value="${param.startDateTo}" />
						  <button type="submit">검색</button>
						</form>
		        	</div>
		            <hr>

		            <table id="point-table" class="display nowrap" >
		                <thead>
		                    <tr>
		                        <th></th>
		                        <th>날짜</th>
		                        <th>상세내역</th>
		                        <th>포인트</th>
		                        <th>잔여 포인트</th>
		                    </tr>
		                </thead>
		                <tbody>
			              <c:forEach var="up" items="${usePointList}" varStatus="status">
			              	<tr>
			              		<c:if test="${up.usedPoint < 0 }">
			              		<td class="point-date"><span class="status use">사용</span></td>
			              		</c:if>
			              		<c:if test="${up.usedPoint > 0 && up.type eq '광고반려' }">
			              		<td class="point-date"><span class="status rere">반환</span></td>
			              		</c:if>
			              		<c:if test="${up.usedPoint > 0 && up.type ne '광고반려' }">
			              		<td class="point-date"><span class="status charge">충전</span></td>
			              		</c:if>
		                        <td>${up.createdAt.toLocalDate()}</td>
		                        <td>${up.type }</td>
			              		<c:if test="${up.usedPoint < 0 }">				                        
		                        <td class="minus">${up.usedPoint }P</td>
		                        </c:if>
		                        <c:if test="${up.usedPoint > 0 }">				                        
		                        <td class="plus">+${up.usedPoint }P</td>
		                        </c:if>
		                        <td>${up.currPoint }P</td>
		                    </tr>
			              </c:forEach>				                    
		                </tbody>
		            </table>
		        </div>
				<div class="paging" id="pagingArea" style="text-align: center; margin-top: 20px;">
				  <c:if test="${pi.startPage > 1}">
				    <a href="?page=${pi.startPage - 1}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}">&laquo;</a>
				  </c:if>

				  <c:forEach begin="${pi.startPage}" end="${pi.endPage}" var="p">
				    <a href="?page=${p}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}"
				       class="${p == pi.currentPage ? 'active' : ''}">${p}</a>
				  </c:forEach>

				  <c:if test="${pi.endPage < pi.maxPage}">
				    <a href="?page=${pi.endPage + 1}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}">&raquo;</a>
				  </c:if>
				</div>
		    </div>
		</div>
	</div>
	<!-- 구독 모달 -->
	<div id="subscribeModal" class="modal-wrapper" style="display:none;">
		<div class="modal">
			<p>팜링을 구독하시겠습니까?<br>언제든 취소가 가능합니다.</p>
		    <ul>
		        <li>알림 1건당 500원 차감</li>
		        <li>잔액 부족 시 알림 미발송</li>
		    </ul>
		    <div class="buttons">
		        <button class="btn btn-subscribe">구독하기</button>
		        <button class="btn btn-cancel">취소</button>
		    </div>
        </div>
	</div>

	<!-- 구독 취소 모달 -->
	<div id="unsubscribeModal" class="modal-wrapper" style="display:none;">
		<div class="modal">
			<p>팜링 구독을 정말 취소하시겠습니까?<br>언제든 재구독이 가능합니다.</p>
		    <ul>
		        <li>취소 즉시 서비스 중간</li>
		    </ul>
		    <div class="buttons">
		        <button class="btn btn-subscribe">구독 지속</button>
		        <button class="btn btn-cancel">구독 취소</button>
		    </div>
	    </div>
	</div>
	<script>
		document.addEventListener('DOMContentLoaded', () => {
		    const subBtn = document.querySelector('.subscribe-btn, .subscribed-btn');
		    const subscribeModal = document.getElementById('subscribeModal');
		    const unsubscribeModal = document.getElementById('unsubscribeModal');
		    
		    if (subBtn) {
		        subBtn.addEventListener('click', () => {
		        	console.log("ddd");
		            const isSubscribed = subBtn.classList.contains('subscribed-btn');
		            if (isSubscribed) {
		                unsubscribeModal.style.display = 'flex'; // 구독취소 모달 띄우기
		            } else {
		                subscribeModal.style.display = 'flex'; // 구독 모달 띄우기
		            }
		        });
		    }
		 	// 구독 모달 내 버튼들
		    document.querySelector('#subscribeModal .btn-subscribe').addEventListener('click', () => {
		        sendSubscriptionRequest('subscribe');
		        subscribeModal.style.display = 'none';
		    });
		    document.querySelector('#subscribeModal .btn-cancel').addEventListener('click', () => {
		        subscribeModal.style.display = 'none';
		    });
		    // 구독취소 모달 내 버튼들
		    document.querySelector('#unsubscribeModal .btn-cancel').addEventListener('click', () => {
		        sendSubscriptionRequest('unsubscribe');
		        unsubscribeModal.style.display = 'none';
		    });
		    document.querySelector('#unsubscribeModal .btn-subscribe').addEventListener('click', () => {
		        unsubscribeModal.style.display = 'none';
		    });
			 // 서버 요청
			    async function sendSubscriptionRequest(action) {
			        try {
			            const res = await fetch(`${contextPath}/updateSubscription`, {
			                method: 'POST',
			                headers: { 'Content-Type': 'application/json' },
			                body: JSON.stringify({ action })
			            });
			            const result = await res.json();
			            if (result.success) {
			                // 버튼 상태 토글 (구독 ↔ 구독취소)
			                if (action === 'subscribe') {
			                    subBtn.classList.remove('subscribe-btn');
			                    subBtn.classList.add('subscribed-btn');
			                    subBtn.innerHTML = '✔ 팜링 구독취소';
			                } else {
			                    subBtn.classList.remove('subscribed-btn');
			                    subBtn.classList.add('subscribe-btn');
			                    subBtn.innerHTML = '팜링 구독하기';
			                }
			            }
			        } catch (e) {
			            console.error('에러:', e);
			        }
			    }
		});
	</script>			

	<div id="chargeModal" class="modal-wrapper" style="display:none;">
	    <div class="modal">
	        <div class="modal-header">
	            <span>포인트 충전</span>
	            <span class="close-btn" onclick="closeModal()">✖</span>
	        </div>

	        <div class="point-box">
	            <span class="point-icon">💲</span>
	            <span>보유 포인트</span>
	            <span>${point }P</span>
	        </div>

	        <div class="select-title">충전 금액을 선택하세요.</div>

            <div class="amount-container">
            	<label class="amount">
                    <input type="radio" name="charge_amount" value="0"> 0원
                </label>
                <label class="amount">
                    <input type="radio" name="charge_amount" value="200"> 200원
                </label>
                <label class="amount">
                    <input type="radio" name="charge_amount" value="5000"> 5,000원
                </label>
                <label class="amount">
                    <input type="radio" name="charge_amount" value="10000"> 10,000원
                </label>
                <label class="amount">
                    <input type="radio" name="charge_amount" value="15000"> 15,000원
                </label>
                <label class="amount">
                    <input type="radio" name="charge_amount" value="20000"> 20,000원
                </label>
                <label class="amount">
                    <input type="radio" name="charge_amount" value="30000"> 30,000원
                </label>
                <label class="amount">
                    <input type="radio" name="charge_amount" value="40000"> 50,000원
                </label>
                <label class="amount">
                    <input type="radio" name="charge_amount" value="30000"> 70,000원
                </label>
                <label class="amount">
                    <input type="radio" name="charge_amount" value="40000"> 100,000원
                </label>
            </div>

            <div class="buttons">
                <button type="submit" class="btn pay">결제</button>
                <button type="button" class="btn cancel" onclick="closeModal()">취소</button>
            </div>
	    </div>
	</div>
	<script> /* 포인트 결제 */
	document.querySelector('.charge-btn').addEventListener('click', () => {
	    document.getElementById('chargeModal').style.display = 'flex';
	});
	function closeModal() {
	    document.getElementById('chargeModal').style.display = 'none';
	}
	
	document.querySelector('#chargeModal .pay').addEventListener('click', () => {
	    const amount = document.querySelector('input[name="charge_amount"]:checked')?.value;
	    if (!amount) {
	        alert('충전 금액을 선택하세요!');
	        return;
	    }
	    const IMP = window.IMP; // 아임포트 객체 가져오기
	    IMP.init(''); // 여기에 본인 가맹점 코드 넣어!
	    IMP.request_pay({
	        pg: 'kcp', // PG사 선택 (예: kakaopay, tosspayments 등)
	        pay_method: 'card', // 결제 수단 (card, trans, vbank 등)
	        merchant_uid: 'order_' + new Date().getTime(), // 주문번호 (고유해야 함)
	        name: '포인트 충전', // 결제 상품명
	        amount: amount, // 결제 금액
	        buyer_email: '@gmail.com', // 구매자 이메일
	        buyer_name: '홍길동', // 구매자 이름
	        buyer_tel: '010-1234-5678' // 구매자 전화번호
	    }, function (rsp) {
	        if (rsp.success) {
	            // ✅ 결제 성공 시 서버에 결제 정보 전달 (imp_uid)
	            fetch(`${contextPath}/chargePoint`, {
	                method: 'POST',
	                headers: { 'Content-Type': 'application/json' },
	                body: JSON.stringify({
	                    imp_uid: rsp.imp_uid, // 아임포트 거래 고유번호
	                    merchant_uid: rsp.merchant_uid,
	                    amount: amount
	                })
	            })
	            .then(res => res.json())
	            .then(data => {
	                if (data.success) {
	                    alert('충전이 완료되었습니다!');
	                    location.reload(); // 포인트 새로고침
	                } else {
	                    alert('충전 처리에 실패했습니다.');
	                }
	            });
	        } else {
	            alert('결제에 실패했습니다.\n' + rsp.error_msg);
	        }
	    });
	});
	</script>
</body>
</html>
