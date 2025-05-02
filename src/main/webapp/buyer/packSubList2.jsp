<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<title>주문 내역</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<link rel="stylesheet" href="${contextPath}/buyer/packSubList.css">
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
		      url: '${contextPath}/packSubList',
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
		      url: '${contextPath}/packSubList',
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
	
	  <label for="deliveryStatus">구독 상태:</label>
	  <select id="deliveryStatus" name="deliveryStatus">
	    <option value="">구독전체</option>
	    <option value="구독중">구독중</option>
	    <option value="구독종료">구독종료</option>
	    <option value="구독취소">구독취소</option>
	  </select>
	
	  <button type="button" id="searchBtn">검색</button>
	</div>

	<div class="orderList">
		<!-- 주문 내역이 없을 때 -->
		<c:if test="${empty packSubList}">
			<div class="emptyMessage">구독 내역이 없습니다.</div>
		</c:if>

		<!-- 주문 내역 반복 -->
		<c:forEach var="packSub" items="${packSubList }">
			<div class="orderItem" data-sub-num="${packSub.subNum}">


				<div class="orderCenter">
					<div class="orderLeft">
						<div class="orderStatus orderReady">${packSub.isSub }
						</div>

						<img src="${contextPath}${packSub.imgUrl }" alt="">
					</div>
					<div class="orderRight">

						<div class="productName">${packSub.packageName }</div>
						<div class="productPrice">${packSub.packagePrice }원</div>
						<div>
							<span class="subStartDate">구독 시작일: ${packSub.subStartDate}</span><br>
							<span class="subEndDate">구독 종료 예상일: ${packSub.subEndDate}</span>
						</div>

					</div>
					<div class="orderButtons">
						<c:choose>
							<c:when test="${packSub.isSub eq true }">
								<button class="btn btnGreen">구독 배송지 변경</button>
								<button class="btn btnGreen">구독 취소</button>
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


