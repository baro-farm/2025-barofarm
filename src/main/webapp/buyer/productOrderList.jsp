<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<title>주문 내역</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<link rel="stylesheet" href="${contextPath}/buyer/productOrderList.css">
<style>
.prodModal {
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
		    	        <button class="btn btnGreen">리뷰작성</button>`);
		
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
		  
		  
		  let selectedCancelOrderNum = null;
		  $(document).on("click", ".orderCancle", function () {
		    selectedCancelOrderNum = $(this).closest(".orderItem").data("pd-order-num");
		    $("#cancelModal").show();
		  });

		  $("#cancelConfirmNo").click(() => {
		    $("#cancelModal").hide();
		  });

		  $("#cancelConfirmYes").click(() => {
		    $.ajax({
		      url: "${contextPath}/cancelProdOrder",
		      type: "POST",
		      contentType: "application/json",
		      data: JSON.stringify({
		        pdOrderNum: selectedCancelOrderNum,
		        cancelStatus: "취소완료",
		        cancelReason: "단순변심",
		        cancelReasonDetail: "상품이 마음에 들지 않음"
		      }),
		      success: function (res) {
		        if (res.result === "success") {
		          alert("취소가 완료되었습니다.");
		          location.reload();
		        } else {
		          alert("취소 중 오류 발생");
		        }
		      },
		      error: function () {
		        alert("서버 오류");
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
						<c:choose>
							<c:when test="${prodOrder.deleveryStatus eq '취소완료' }">
								<div class="orderStatus orderCancel">${prodOrder.deleveryStatus }</div>
							</c:when>
							<c:otherwise>
							<div class="orderStatus orderReady">${prodOrder.deleveryStatus }</div>
							</c:otherwise>
						</c:choose>

						<img src="${contextPath}${prodOrder.imgUrl }" alt="">
					</div>
					<div class="orderRight">
						<div class="storeName"><a href="${contextPath }/storeProductList?sellerNum=${prodOrder.sellerNum}">
							${prodOrder.storeName }
							<svg width="15" height="15" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
							<path d="M6.75 16.5V9H11.25V16.5M2.25 6.75L9 1.5L15.75 6.75V15C15.75 15.3978 15.592 15.7794 15.3107 16.0607C15.0294 16.342 14.6478 16.5 14.25 16.5H3.75C3.35218 16.5 2.97064 16.342 2.68934 16.0607C2.40804 15.7794 2.25 15.3978 2.25 15V6.75Z" stroke="#1E1E1E" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></path></svg>
						</a></div>
						<div class="productName"><a href="${contextPath }/detailProduct?productNum=${prodOrder.productNum}">${prodOrder.productName }</a></div>
						<div class="productPrice" data-price=${prodOrder.price }>${prodOrder.price }원</div>
						<div class="orderDetail">
							<a href="#" onclick="return openDetailPopup('${contextPath}/detailOrderInfo?pdOrderNum=${prodOrder.pdOrderNum}')">상세보기></a>
						</div>
						<div>
							<span class="orderDate">${prodOrder.orderDate } 주문</span>
						</div>

					</div>
					<div class="orderButtons">
						<c:choose>
							<c:when test="${prodOrder.deleveryStatus eq '준비중' }">
								<button class="btn btnRed orderCancle">취소 신청</button>
							</c:when>


							<c:when test="${prodOrder.deleveryStatus eq '배송중' }">
								<button class="btn btnGreen confirmBtn">구매 확정</button>
							</c:when>

							<c:when test="${prodOrder.deleveryStatus eq '배송완료' }">
								<button class="btn btnGreen confirmBtn">구매 확정</button>
							</c:when>

							<c:when test="${prodOrder.deleveryStatus eq '구매확정' }">
							
							</c:when>
						</c:choose>
					</div>
				</div>


			</div>
		</c:forEach>
	</div>
</div>

<div class="pagination">
    <!-- << -->
    <c:choose>
        <c:when test="${currentPage > 1}">
            <a href="#" class="page-link" data-page="${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}">&laquo;</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">&laquo;</a>
        </c:otherwise>
    </c:choose>

    <!-- < -->
    <c:choose>
        <c:when test="${currentPage > 1}">
            <a href="#" class="page-link" data-page="${currentPage - 1}">&lsaquo;</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">&lsaquo;</a>
        </c:otherwise>
    </c:choose>

    <!-- 번호 -->
    <c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
        <a href="#" class="page-link ${currentPage == i ? 'active' : ''}" data-page="${i}">${i}</a>
    </c:forEach>

    <!-- > -->
    <c:choose>
        <c:when test="${currentPage < totalPages}">
            <a href="#" class="page-link" data-page="${currentPage + 1}">&rsaquo;</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">&rsaquo;</a>
        </c:otherwise>
    </c:choose>

    <!-- >> -->
    <c:choose>
        <c:when test="${currentPage < totalPages}">
            <a href="#" class="page-link" data-page="${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}">&raquo;</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">&raquo;</a>
        </c:otherwise>
    </c:choose>
</div>
<script>
$(document).on('click', '.pagination a', function (e) {
    e.preventDefault();
    const page = $(this).data('page');
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
        const $response = $('<div>').html(data);
        $('.orderList').html($response.find('.orderList').html());
        $('.pagination').html($response.find('.pagination').html());
      },
      error: function () {
        alert("페이지 요청 실패");
      }
    });
  });
</script>
<script>
  function openDetailPopup(url) {
    const popupWidth = 600;
    const popupHeight = 700;
    const left = (screen.width - popupWidth) / 2;
    const top = (screen.height - popupHeight) / 2;

    window.open(
      url,
      '상세보기',
      `width=\${popupWidth},height=\${popupHeight},left=\${left},top=\${top},scrollbars=yes,resizable=no`
    );
    return false;
  }
</script>
<!-- 모달 -->
<div id="confirmModal" class="prodModal" style="display: none;">
	<div class="modalContent">
		<p style="margin-top:10px; margin-bottom:10px;">구매를 확정하시겠습니까?</p>
		<button id="confirmYes" class="btn btnGreen">확인</button>
		<button id="confirmNo" class="btn btnRed">취소</button>
	</div>
</div>
<!-- 모달 추가 -->
<div id="cancelModal" class="prodModal" style="display:none;">
  <div class="modalContent">
    <p>정말로 해당 주문을 취소하시겠습니까?</p>
    <button id="cancelConfirmYes" class="btn btnRed">확인</button>
    <button id="cancelConfirmNo" class="btn btnGreen">취소</button>
  </div>
</div>



