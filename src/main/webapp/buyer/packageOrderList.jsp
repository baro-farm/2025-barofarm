<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>꾸러미 내역</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/header/reset.css">
	<link rel="stylesheet" href="${contextPath}/buyer/packageOrderList.css">
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
	  let selectedPkOrderNum = null;
	
	  // 구매확정 버튼 클릭 시 모달 띄우기
	  $(document).on("click", ".confirmBtn", function () {
	    const orderItem = $(this).closest(".orderItem");
	    selectedPkOrderNum = orderItem.attr("data-pk-order-num");
	
	    if (!selectedPkOrderNum) {
	      alert("주문 번호를 찾을 수 없습니다.");
	      return;
	    }
	
	    console.log("🟢 구매확정 클릭:", selectedPkOrderNum);
	    $("#confirmModal").show();
	  });
	
	  // 모달 취소 버튼
	  $("#confirmNo").click(function () {
	    $("#confirmModal").hide();
	  });
	
	  // 모달 확인 버튼
	  $("#confirmYes").click(function () {
	    if (!selectedPkOrderNum) {
	      alert("주문 번호가 없습니다.");
	      return;
	    }
	
	    $.ajax({
	      url: "${contextPath}/updatePkDeliveryStatus",
	      method: "POST",
	      dataType: "json",
	      data: {
	        pkOrderNum: selectedPkOrderNum,
	        deleveryStatus: "구매확정"
	      },
	      success: function (res) {
	    	  console.log("✅ 응답:", res);
	    	  $(".orderItem").each(function () {
	    	    if ($(this).attr("data-pk-order-num") == res.pkOrderNum) {
	    	      const orderItem = $(this);
	
	    	      orderItem.find(".orderStatus").text(res.status);
	    	      orderItem.find(".orderButtons").html(`
	    	        <button class="btn btnGreen">리뷰작성</button>
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
		<!-- 헤더 -->
		
			<h2>꾸러미 내역</h2>

		<div class="searchBox">
			<label for="searchStartDate">조회 기간:</label> <input type="date"
				id="searchStartDate" name="searchStartDate"> <span>~</span>
			<input type="date" id="searchEndDate" name="searchEndDate">
			<button type="button">검색</button>
		</div>

		<div class="orderList">

			<!-- 주문 내역 반복 -->
			<c:forEach var="packOrder" items="${packOrderList }">
				<div class="orderItem" data-pk-order-num="${packOrder.pkOrderNum}">
					<div class="orderCenter">
						<div class="orderLeft">
							<div class="orderStatus">${packOrder.deleveryStatus }</div>
							<img src="${packOrder.imgUrl }" alt="상품 이미지">
						</div>
						<div class="orderRight">
							<div>
								<span class="orderDate">${packOrder.orderedAt } 주문</span>
							</div>
							<div class="productName">${packOrder.packageName }</div>
							<div class="productPrice">${packOrder.pkTotalPrice }원</div>
							<div class="orderDetail">
								<a
									href="${contextPath}/detailOrderInfo?pdOrderNum=${prodOrder.pdOrderNum}">상세보기></a>
							</div>

						</div>
						<div class="orderButtons">
						<c:choose>
							<c:when test="${packOrder.deleveryStatus eq '준비중' }">
								<button class="btn btnRed">취소 신청</button>
							</c:when>

							<c:when test="${packOrder.deleveryStatus eq '취소신청' }">
							</c:when>

							<c:when test="${packOrder.deleveryStatus eq '취소완료' }">
								<button class="btn btnRed">취소 정보</button>

							</c:when>

							<c:when test="${packOrder.deleveryStatus eq '배송중' }">
								<button class="btn btnGreen confirmBtn" >구매 확정</button>
								<button class="btn btnGreen">리뷰작성</button>

							</c:when>
							<c:when test="${packOrder.deleveryStatus eq '배송완료' }">
								<button class="btn btnGreen confirmBtn">구매 확정</button>
								<button class="btn btnGreen">리뷰작성</button>

							</c:when>
							<c:when test="${packOrder.deleveryStatus eq '구매확정' }">
								<button class="btn btnGreen">리뷰작성</button>
							</c:when>
						</c:choose>

					</div>
					</div>

					
				</div>
			</c:forEach>

		</div>

		<div class="pagination">
			<a href="#">◀</a> <a href="#" class="active">1</a> <a href="#">2</a>
			<a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">▶</a>
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