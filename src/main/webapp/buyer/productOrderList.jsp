<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


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
		  // 검색 버튼 클릭
		  $('#searchBtn').click(function () {
		    const startDate = $('#searchStartDate').val();
		    const endDate = $('#searchEndDate').val();
		    const deliveryStatus = $('#deliveryStatus').val();

		    $.ajax({
		      url: '${contextPath}/prodOrderList',
		      type: 'GET',
		      data: {
		        searchStartDate: startDate,
		        searchEndDate: endDate,
		        deliveryStatus: deliveryStatus,
		        page: 1
		      },
		      success: function (data) {
		        $('.orderList').html($(data).find('.orderList').html());
		        $('.pagination').html($(data).find('.pagination').html());
		      },
		      error: function () {
		        alert("검색 실패");
		      }
		    });
		  });
		  
		  // 페이징 클릭 처리
		  $(document).on('click', '.page-link', function (e) {
		    e.preventDefault(); // 링크 기본 동작 막기

		    const page = $(this).data("page");
		    const startDate = $('#searchStartDate').val();
		    const endDate = $('#searchEndDate').val();
		    const deliveryStatus = $('#deliveryStatus').val();

		    $.ajax({
		      url: '${contextPath}/prodOrderList',
		      type: 'GET',
		      data: {
		        searchStartDate: startDate,
		        searchEndDate: endDate,
		        deliveryStatus: deliveryStatus,
		        page: page
		      },
		      success: function (data) {
		        $('.orderList').html($(data).find('.orderList').html());
		        $('.pagination').html($(data).find('.pagination').html());
		      },
		      error: function () {
		        alert("페이지 이동 실패");
		      }
		    });
		  });
		  
		  
		});
	</script>





<div class="content1">

	<div class="FilterBox">
	  <label for="searchStartDate">조회 기간:</label>
	  <input type="date" id="searchStartDate" name="searchStartDate">
	  <span>~</span>
	  <input type="date" id="searchEndDate" name="searchEndDate">
	
	  <label for="deliveryStatus">배송 상태:</label>
	  <select id="deliveryStatus" name="deliveryStatus">
	    <option value="">전체</option>
	    <option value="준비중">준비중</option>
	    <option value="배송중">배송중</option>
	    <option value="배송완료">배송완료</option>
	    <option value="구매확정">구매확정</option>
	    <option value="취소완료">취소완료</option>
	  </select>
	
	  <button type="button" id="searchBtn">검색</button>
	</div>

	<div class="orderList">
		<!-- 주문 내역이 없을 때 -->
		<c:if test="${empty prodOrderList}">
			<div class="emptyMessage">주문 내역이 없습니다.</div>
		</c:if>

		<!-- 주문 내역 반복 -->
		<c:forEach var="prodOrder" items="${prodOrderList }">
			<div class="orderItem" data-pd-order-num="${prodOrder.pdOrderNum}">


				<div class="orderCenter">
					<div class="orderLeft">
						<div class="orderStatus orderReady">${prodOrder.deleveryStatus }
						</div>

						<img src="${contextPath}${prodOrder.imgUrl }" alt="">
					</div>
					<div class="orderRight">
						<div>
							<span class="orderDate">${prodOrder.orderDate } 주문</span>
						</div>
						<div class="productName">${prodOrder.productName }</div>
						<div class="productPrice">${prodOrder.price }원</div>
						<div class="orderDetail">
							<a
								href="${contextPath}/detailOrderInfo?pdOrderNum=${prodOrder.pdOrderNum}">상세보기></a>
						</div>

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

<c:set var="startPage" value="${page - 2}" />
<c:set var="endPage" value="${page + 2}" />

<c:if test="${startPage < 1}">
    <c:set var="endPage" value="${endPage + (1 - startPage)}" />
    <c:set var="startPage" value="1" />
</c:if>

<c:if test="${endPage > totalPages}">
    <c:set var="startPage" value="${startPage - (endPage - totalPages)}" />
    <c:set var="endPage" value="${totalPages}" />
</c:if>

<c:if test="${startPage < 1}">
    <c:set var="startPage" value="1" />
</c:if>

<div class="pagination">
    <!-- << -->
    <c:if test="${currentPage > 1}">
        <a href="#" class="page-link" data-page="${currentPage - pageGroupSize > 0 ? currentPage - pageGroupSize : 1}">&laquo;</a>
    </c:if>

    <!-- < -->
    <c:if test="${currentPage > 1}">
        <a href="#" class="page-link" data-page="${currentPage - 1}">&lsaquo;</a>
    </c:if>

    <!-- 번호 -->
    <c:forEach begin="${startPage}" end="${endPage}" var="i">
        <a href="#" class="page-link ${currentPage == i ? 'active' : ''}" data-page="${i}">${i}</a>
    </c:forEach>

    <!-- > -->
    <c:if test="${currentPage < totalPages}">
        <a href="#" class="page-link" data-page="${currentPage + 1}">&rsaquo;</a>
    </c:if>

    <!-- >> -->
    <c:if test="${currentPage < totalPages}">
        <a href="#" class="page-link" data-page="${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}">&raquo;</a>
    </c:if>
</div>

<!-- 모달 -->
<div id="confirmModal" class="modal" style="display: none;">
	<div class="modalContent">
		<p>구매를 확정하시겠습니까?</p>
		<button id="confirmYes" class="btn btnGreen">확인</button>
		<button id="confirmNo" class="btn btnRed">취소</button>
	</div>
</div>


