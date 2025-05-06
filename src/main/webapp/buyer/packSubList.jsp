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
								<!-- 
								<div class="orderButtons">
									<c:choose>
										<c:when test="${packSub.isSub eq true }">
											<button class="btn btnGreen">구독 배송지 변경</button>
										</c:when>
									</c:choose>
								</div>
								 -->
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
				    <c:choose>
					    <c:when test="${currentPage > 1}">
					        <a href="?page=${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}&searchStartDate=${param.searchStartDate}&searchEndDate=${param.searchEndDate}&deliveryStatus=${param.deliveryStatus}">&laquo;</a>
					    </c:when>
					    <c:otherwise>
						    <a class="disabled">&laquo;</a>
						</c:otherwise>
					</c:choose>
				
				    <!-- < -->
				    <c:choose>
					    <c:when test="${currentPage > 1}">
					        <a href="?page=${currentPage - 1}&searchStartDate=${param.searchStartDate}&searchEndDate=${param.searchEndDate}&deliveryStatus=${param.deliveryStatus}">&lsaquo;</a>
					    </c:when>
						<c:otherwise>
							 <a class="disabled">&lsaquo;</a>
						</c:otherwise>
					</c:choose>
				    <!-- 번호 -->
				    <c:forEach begin="${startPage}" end="${endPage}" var="i">
				        <a href="?page=${i}&searchStartDate=${param.searchStartDate}&searchEndDate=${param.searchEndDate}&deliveryStatus=${param.deliveryStatus}" class="${currentPage == i ? 'active' : ''}">${i}</a>
				    </c:forEach>
				
				    <!-- > -->
				    <c:choose>
				    
				    <c:when test="${currentPage < totalPages}">
				        <a href="?page=${currentPage + 1}&searchStartDate=${param.searchStartDate}&searchEndDate=${param.searchEndDate}&deliveryStatus=${param.deliveryStatus}">&rsaquo;</a>
				    </c:when>
					<c:otherwise>
						    <a class="disabled">&rsaquo;</a>
					</c:otherwise>
					</c:choose>
				
				    <!-- >> -->
				    <c:choose>
				    
				    <c:when test="${currentPage < totalPages}">
				        <a href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}&searchStartDate=${param.searchStartDate}&searchEndDate=${param.searchEndDate}&deliveryStatus=${param.deliveryStatus}">&raquo;</a>
				    </c:when>
					<c:otherwise>
						    <a class="disabled">&raquo;</a>
					</c:otherwise>				    
				    </c:choose>
				    
				</div>
			</div>
			<!-- end of content -->
		</div>
		<!-- end of warraper -->
	</div>

</body>
</html>