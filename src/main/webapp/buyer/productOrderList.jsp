<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문 내역</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    
    <link rel="stylesheet" href="${contextPath}/buyer/productOrderList.css">
	<style>
		.modal {
			position: fixed;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			background-color: rgba(0, 0, 0, 0.5);
			display: flex;
			justify-content: center;
			align-items: center;
		}
		
		.modalContent {
			background: white;
			padding: 20px;
			border-radius: 10px;
			text-align: center;
		}
	</style>
	<script>
		$(function () {
		  let selectedPdOrderNum = null;
		
		  // 구매확정 버튼 클릭 시 모달 띄우기
		  $(document).on("click", ".confirmBtn", function () {
		    const orderItem = $(this).closest(".orderItem");
		    selectedPdOrderNum = orderItem.attr("data-pd-order-num");
		
		    if (!selectedPdOrderNum) {
		      alert("주문 번호를 찾을 수 없습니다.");
		      return;
		    }
		
		    console.log("🟢 구매확정 클릭:", selectedPdOrderNum);
		    $("#confirmModal").show();
		  });
		
		  // 모달 취소 버튼
		  $("#confirmNo").click(function () {
		    $("#confirmModal").hide();
		  });
		
		  // 모달 확인 버튼
		  $("#confirmYes").click(function () {
		    if (!selectedPdOrderNum) {
		      alert("주문 번호가 없습니다.");
		      return;
		    }
		
		    $.ajax({
		      url: "${contextPath}/updatePdDeliveryStatus",
		      method: "POST",
		      dataType: "json",
		      data: {
		        pdOrderNum: selectedPdOrderNum,
		        deleveryStatus: "구매확정"
		      },
		      success: function (res) {
		    	  console.log("✅ 응답:", res);
		    	  $(".orderItem").each(function () {
		    	    if ($(this).attr("data-pd-order-num") == res.pdOrderNum) {
		    	      const orderItem = $(this);
		
		    	      orderItem.find(".orderStatus").text(res.status);
		    	      orderItem.find(".orderButtons").html(`
		    	        <button class="btn btnGreen">리뷰작성</button>
	                    <button class="btn btnGreen">장바구니 담기</button>
	                    <button class="btn btnGreen">바로 구매하기</button> 
		    	      `);
		
		    	      $("#confirmModal").hide();
		    	    }
		    	  });
		    	},
		      error: function (err) {
		        console.error("❌ AJAX 오류:", err);
		        alert("서버 오류가 발생했습니다.");
		      }
		    });
		  });
		});
	</script>
</head>

<body>
<jsp:include page="/header/mainHeader.jsp"/>

    <div class="container">
    	<div class="wrapper">
			<div class="sideMenu">
				<jsp:include page="/header/buyerMenu.jsp" />
			</div>
			<div class="content">
		        <h2>주문 내역</h2>
		
		        <div class="searchBox">
		            <label for="searchStartDate">조회 기간:</label>
		            <input type="date" id="searchStartDate" name="searchStartDate">
		            <span>~</span>
		            <input type="date" id="searchEndDate" name="searchEndDate">
		            <button type="button">검색</button>
		        </div>
	
		        <div class="orderList">
		
					<!-- 주문 내역 반복 -->
					<c:forEach var="prodOrder" items="${prodOrderList }">
			            <div class="orderItem" data-pd-order-num="${prodOrder.pdOrderNum}">
			                
			            
			                <div class="orderCenter">
			                    <div class="orderLeft">
			                    	<div class="orderStatus orderReady">${prodOrder.deleveryStatus } </div>
			                    
			                        <img src="${prodOrder.imgUrl }" alt="상품 이미지">
			                    </div>
			                    <div class="orderRight">
			                        <div>
			                            <span class="orderDate">${prodOrder.orderDate } 주문</span>
			                        </div>
			                        <div class="productName">${prodOrder.productName }</div>
			                        <div class="productPrice">${prodOrder.price } 원</div>
			                        <div class="orderDetail"><a href="${contextPath}/detailOrderInfo?pdOrderNum=${prodOrder.pdOrderNum}">상세보기></a></div>
			
			                    </div>
			                    <div class="orderButtons">
				                	<c:choose>
				                		<c:when test="${prodOrder.deleveryStatus eq '준비중' }">
						                    <button class="btn btnGreen">장바구니 담기</button>
						                    <button class="btn btnGreen">바로 구매하기</button>  
						                    <button class="btn btnRed">취소 신청</button>	                		
				                		</c:when>
				                		
				                		<c:when test="${prodOrder.deleveryStatus eq '취소신청' }">
						                    <button class="btn btnGreen">장바구니 담기</button>
						                    <button class="btn btnGreen">바로 구매하기</button>  	                		
				                		</c:when>
				                		
				                		<c:when test="${prodOrder.deleveryStatus eq '취소완료' }">
						                    <button class="btn btnRed">취소 정보</button>	                		
						                    <button class="btn btnGreen">장바구니 담기</button>
						                    <button class="btn btnGreen">바로 구매하기</button>  	                		
				                		</c:when>
				                		
				                		<c:when test="${prodOrder.deleveryStatus eq '배송중' }">
						                    <button class="btn btnGreen confirmBtn">구매 확정</button>	                		
						                    <button class="btn btnGreen">장바구니 담기</button>
						                    <button class="btn btnGreen">바로 구매하기</button>  			                    	                		
			
				                		</c:when>
				                		
				                		<c:when test="${prodOrder.deleveryStatus eq '배송완료' }">
						                    <button class="btn btnGreen confirmBtn">구매 확정</button>	                		
						                    <button class="btn btnGreen">장바구니 담기</button>
						                    <button class="btn btnGreen">바로 구매하기</button>  			                    	                		
			
				                		</c:when>
				                		
				                		<c:when test="${prodOrder.deleveryStatus eq '구매확정' }">
						                    <button class="btn btnGreen">장바구니 담기</button>
						                    <button class="btn btnGreen">바로 구매하기</button>  			                    	                		
				                		</c:when>	                			                		
				                	</c:choose>
			                	</div>
			                </div>
			                
			                
			            </div>			
					</c:forEach>
				</div>
			</div>
        </div> <!-- end of warrper -->

        <div class="pagination">
            <a href="#">◀</a>
            <a href="#" class="active">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">▶</a>
        </div>
        
        		<!-- 모달 -->
		<div id="confirmModal" class="modal" style="display: none;">
			<div class="modalContent">
				<p>구매를 확정하시겠습니까?</p>
				<button id="confirmYes" class="btn btnGreen">확인</button>
				<button id="confirmNo" class="btn btnRed">취소</button>
			</div>
		</div>
    </div>

</body>

</html>