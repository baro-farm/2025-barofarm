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
	<jsp:include page="/header/sellerHeader.jsp" />
    <header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
    </header>
		<div id="content">
			<div class="pkHeader">
					<h1 class="title">팜포인트</h1>
			</div>
		<div class="header-a">
			<div class="point-box">
				<span class="point-label">보유 포인트</span>
				<div>
					<span class="point-value">💰 <fmt:formatNumber value="${point}" type="number" />P
					</span>
					<button class="charge-btn">충전하기</button>
				</div>
			</div>
			<c:if test="${isAlarm eq false }">
				<div class="subscribe-box">
					<!-- <span class="question-icon">❓</span>	 -->
					<button class="subscribe-btn">콕팜링 구독하기</button>
				</div>
			</c:if>
			<c:if test="${isAlarm eq true }">
				<div class="subscribe-box">
					<button class="subscribed-btn">✔ 콕팜링 구독취소</button>
				</div>
			</c:if>
		</div>

		<div class="selectBox">
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
	            
	            
			<div class="tableWrapper">
	            <table id="point-table" class="table" >
	                <thead >
	                    <tr>
	                        <th>유형</th>
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
	                        <td class="minus"><fmt:formatNumber value="${up.usedPoint}" type="number" />P</td>
	                        </c:if>
	                        <c:if test="${up.usedPoint > 0 && up.type eq '광고반려' }">				                        
	                        <td class="replus">+<fmt:formatNumber value="${up.usedPoint}" type="number" />P</td>
	                        </c:if>
	                        <c:if test="${up.usedPoint > 0 && up.type ne '광고반려'  }">				                        
	                        <td class="plus">+<fmt:formatNumber value="${up.usedPoint}" type="number" />P</td>
	                        </c:if>
	                        <td><fmt:formatNumber value="${up.currPoint}" type="number" />P</td>
	                    </tr>
		              </c:forEach>				                    
	                </tbody>
	            </table>
	        </div>
	        
			<div class="pagination" id="pagingArea" style="text-align: center; margin-top: 20px;">
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
	<!-- 구독 모달 -->
	<div id="subscribeModal" class="modal-wrapper" style="display:none;">
		<div class="modal">
			<p>콕팜링을 구독하시겠습니까?<br>언제든 취소가 가능합니다.</p>
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
			<p>콕팜링 구독을 정말 취소하시겠습니까?<br>언제든 재구독이 가능합니다.</p>
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
			                    subBtn.innerHTML = '✔ 콕팜링 구독취소';
			                } else {
			                    subBtn.classList.remove('subscribed-btn');
			                    subBtn.classList.add('subscribe-btn');
			                    subBtn.innerHTML = '콕팜링 구독하기';
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
	            <span><fmt:formatNumber value="${point}" type="number" />P</span>
	        </div>

	        <div class="select-title">충전 금액을 선택하세요.</div>

            <div class="amount-container">
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
                    <input type="radio" name="charge_amount" value="50000"> 50,000원
                </label>
                <label class="amount">
                    <input type="radio" name="charge_amount" value="70000"> 70,000원
                </label>
                <label class="amount">
                    <input type="radio" name="charge_amount" value="100000"> 100,000원
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
	    const impKey= ${impKey};
	    IMP.init(${impKey});
	    IMP.request_pay({
	        pg: 'kcp', 
	        pay_method: 'card', 
	        merchant_uid: 'chargePoint_' + new Date().getTime(), // 주문번호 (고유해야 함)
	        name: '팜포인트 충전', // 결제 상품명
	        amount: amount, // 결제 금액
	        buyer_email: '@naver.com', // 구매자 이메일
	        buyer_name: '${userName}', // 구매자 이름
	        buyer_tel: '${phone}' // 구매자 전화번호
	    }, function (rsp) {
	        if (rsp.success) {
	            //결제 성공 시 서버에 결제 정보 전달 (imp_uid)
	            fetch(`${contextPath}/chargePoint`, {
	                method: 'POST',
	                headers: { 'Content-Type': 'application/json' },
	                body: JSON.stringify({
	                    imp_uid: rsp.imp_uid, // 아임포트 거래 고유번호
	                    merchant_uid: rsp.merchant_uid,
	                    usedPoint: amount,
	                    type: "팜포인트 충전",
	                    payInfo: 'KCP-카드'
	                })
	            })
	            .then(res => res.json())
	            .then(data => {
	                if (data.success) {
	                    alert('충전이 완료되었습니다!');
	                    location.reload();
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
