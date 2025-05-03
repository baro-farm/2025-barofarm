<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 | 상품 주문 목록</title>
	<link rel="stylesheet" href="${contextPath}/admin/adminProdOrderList.css">
</head>
<body>

<jsp:include page="/header/adminHeader.jsp" />
<header id="header">
	<jsp:include page="/header/adminSellerTop.jsp" />
</header>
<div id="content">
	<h1 class="title">일반 상품 주문 조회</h1>

	<!-- 검색 필터 -->
	<form method="get" action="${contextPath}/adminProdOrderList" class="filter-form">
		<div class="filter-top">
			<label for="dateType">날짜 기준:</label>
			<select name="dateType" id="dateType">
				<option value="orderDate" ${param.dateType == 'orderDate' ? 'selected' : ''}>결제일</option>
				<option value="paymentDate" ${param.dateType == 'paymentDate' ? 'selected' : ''}>주문일</option>
			</select>

			<label>기간:</label>
			<input type="date" name="startDate" value="${param.startDate}">
			~
			<input type="date" name="endDate" value="${param.endDate}">
		</div>

		<div class="filter-bottom">
			<select name="searchType">
				<option value="buyerId" ${param.searchType == 'buyerId' ? 'selected' : ''}>구매자ID</option>
				<option value="sellerId" ${param.searchType == 'sellerId' ? 'selected' : ''}>판매자ID</option>
				<option value="storeName" ${param.searchType == 'storeName' ? 'selected' : ''}>스토어명</option>
				<option value="pdOrderNum" ${param.searchType == 'pdOrderNum' ? 'selected' : ''}>주문번호</option>
			</select>
			<input type="text" name="searchKeyword" placeholder="검색어를 입력하세요" value="${param.searchKeyword}">
			<button type="submit">검색</button>
		</div>
	</form>

	<!-- 주문 리스트 테이블 -->
	<div class="tableWrapper">
		<table class="table">
			<thead>
				<tr>
					<th>주문번호</th>
					<th>상품명</th>
					<th>옵션</th>
					<th>수량</th>
					<th>기본가격</th>
					<th>옵션가격</th>
					<th>총금액</th>
					<th>구매자</th>
					<th>판매자</th>
					<th>스토어명</th>
					<th>주문일</th>
					<th>주문상태</th>
					<th>배송상태</th>
					<th>송장번호</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${orderList}">
					<tr>
						<td>${order.pdOrderNum}</td>
						<td>${order.productName}</td>
						<td>${order.option}</td>
						<td>${order.amount}</td>
						<td>${order.productPrice}원</td>
						<td>${order.optionPrice}원</td>
						<td>${order.totalPrice}원</td>
						<td>${order.buyerId} (${order.buyerName})</td>
						<td>${order.sellerId}</td>
						<td>${order.storeName}</td>
						<td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd" /></td>
						<td>${order.orderStatus}</td>
						<td>${order.deleveryStatus}</td>
						<td>${order.trackingNum}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<%-- <div class="pagination">
		<c:if test="${currentPage > 1}">
			<a href="?page=${currentPage - 1}">이전</a>
		</c:if>

		<c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
			<a href="?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
		</c:forEach>

		<c:if test="${currentPage < totalPages}">
			<a href="?page=${currentPage + 1}">다음</a>
		</c:if>
	</div> --%>
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
		    <!-- << 현재 페이지 - 5 -->
			<c:if test="${currentPage > 1}">
				<a href="?page=${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}&sellStat=${param.sellStat}&sort=${param.sort}">&laquo;</a>
			</c:if>
			
			<!-- < 이전 페이지 -->
			<c:if test="${currentPage > 1}">
				<a href="?page=${currentPage - 1}&sellStat=${param.sellStat}&sort=${param.sort}">&lsaquo;</a>
			</c:if>
			
			<!-- 페이지 번호 -->
			<c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
				<a href="?page=${i}&sellStat=${param.sellStat}&sort=${param.sort}" class="${currentPage == i ? 'active' : ''}">${i}</a>
			</c:forEach>
			
			<!-- > 다음 페이지 -->
			<c:if test="${currentPage < totalPages}">
			    <a href="?page=${currentPage + 1}&sellStat=${param.sellStat}&sort=${param.sort}">&rsaquo;</a>

			</c:if>
			
			<!-- >> 현재 페이지 + 5 -->
			<c:if test="${currentPage < totalPages}">
			    <a href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}&sellStat=${param.sellStat}&sort=${param.sort}">&raquo;</a>
			</c:if>
</div>

</body>
</html>
