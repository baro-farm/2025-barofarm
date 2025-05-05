<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>내 정보</title>
<link rel="stylesheet" href="${contextPath}/buyer/packSubList.css">

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

	<jsp:include page="/header/mainHeader.jsp" />

	<div class="container">
	    
	
		<div class="wrapper">
		
			<div class="sideMenu">
				<jsp:include page="/header/buyerMenu.jsp" />
			</div>
			
			<div class="content">
			<div class="header">꾸러미 구독 내역</div>
				<div class="FilterBox">
					<form method="get" action="${contextPath}/packSubList" id="searchForm">
					
						<label for="searchStartDate">조회 기간:</label> <input type="date"
							id="searchStartDate" name="searchStartDate"> <span>~</span>
						<input type="date" id="searchEndDate" name="searchEndDate">
	
						<label for="deliveryStatus">구독 상태:</label> <select
							id="deliveryStatus" name="deliveryStatus">
							<option value="">구독전체</option>
							<option value="구독중">구독중</option>
							<option value="구독종료">구독종료</option>
						</select>
	
						<button type="submit" id="searchBtn">검색</button>
					</form>
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
									<c:choose>
										<c:when test="${packSub.isSub eq true }">
											<div class="orderStatus orderReady">구독중</div>
										</c:when>
										<c:otherwise>
											<div class="orderStatus orderReady">구독종료</div>
										</c:otherwise>
									</c:choose>
									<img src="${contextPath}${packSub.imgUrl }" alt="">
								</div>
								<div class="orderRight">

									<div class="productName">${packSub.packageName }</div>
									<div class="productPrice">${packSub.packagePrice }원</div>
									<div>
										<span class="subStartDate">구독 시작일:
											${packSub.subStartDate}</span><br> <span class="subEndDate">구독
											종료 예상일: ${packSub.subEndDate}</span>
									</div>

								</div>
								<div class="orderButtons">
									<c:choose>
										<c:when test="${packSub.isSub eq true }">
											<button class="btn btnGreen">구독 배송지 변경</button>
										</c:when>
									</c:choose>
								</div>
							</div>


						</div>
					</c:forEach>
				</div>
				
				<c:set var="startPage" value="${currentPage - 2}" />
				<c:set var="endPage" value="${currentPage + 2}" />
				
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
				        <a href="?page=${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}&searchStartDate=${param.searchStartDate}&searchEndDate=${param.searchEndDate}&deliveryStatus=${param.deliveryStatus}">&laquo;</a>
				    </c:if>
				
				    <!-- < -->
				    <c:if test="${currentPage > 1}">
				        <a href="?page=${currentPage - 1}&searchStartDate=${param.searchStartDate}&searchEndDate=${param.searchEndDate}&deliveryStatus=${param.deliveryStatus}">&lsaquo;</a>
				    </c:if>
				
				    <!-- 번호 -->
				    <c:forEach begin="${startPage}" end="${endPage}" var="i">
				        <a href="?page=${i}&searchStartDate=${param.searchStartDate}&searchEndDate=${param.searchEndDate}&deliveryStatus=${param.deliveryStatus}" class="${currentPage == i ? 'active' : ''}">${i}</a>
				    </c:forEach>
				
				    <!-- > -->
				    <c:if test="${currentPage < totalPages}">
				        <a href="?page=${currentPage + 1}&searchStartDate=${param.searchStartDate}&searchEndDate=${param.searchEndDate}&deliveryStatus=${param.deliveryStatus}">&rsaquo;</a>
				    </c:if>
				
				    <!-- >> -->
				    <c:if test="${currentPage < totalPages}">
				        <a href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}&searchStartDate=${param.searchStartDate}&searchEndDate=${param.searchEndDate}&deliveryStatus=${param.deliveryStatus}">&raquo;</a>
				    </c:if>
				</div>
			</div>
			<!-- end of content -->
		</div>
		<!-- end of warraper -->
	</div>

</body>
</html>