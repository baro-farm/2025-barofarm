<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 | 꾸러미 주문 목록</title>
	<link rel="stylesheet" href="${contextPath}/admin/adminProdOrderList.css">
</head>
<body>

<jsp:include page="/header/adminHeader.jsp" />
<header id="header">
	<jsp:include page="/header/adminSellerTop.jsp" />
</header>
<div id="content">
	<h1 class="title">꾸러미 주문 조회</h1>

	<form method="get" action="${contextPath}/adminPackOrderList" class="filter-form">
		<div class="filter-top">
			<label>기간:</label>
			<input type="date" name="startDate" value="${param.startDate}">
			~
			<input type="date" name="endDate" value="${param.endDate}">
		</div>

		<div class="filter-bottom">
			<select name="searchType">
				<option value="pkOrderNum" ${param.searchType == 'pkOrderNum' ? 'selected' : ''}>주문번호</option>
				<option value="userId" ${param.searchType == 'userId' ? 'selected' : ''}>구매자 ID</option>
				<option value="storeName" ${param.searchType == 'storeName' ? 'selected' : ''}>스토어명</option>
				<option value="trackingNum" ${param.searchType == 'trackingNum' ? 'selected' : ''}>송장번호</option>
			</select>

			<input type="text" name="searchKeyword" placeholder="검색어를 입력하세요" value="${param.searchKeyword}">
			<button type="submit">검색</button>
		</div>
	</form>

	<div class="tableWrapper">
		<table class="table">
			<thead>
				<tr>
					<th>주문번호</th>
					<th>꾸러미명</th>
					<th>총금액</th>
					<th>구매자</th>
					<th>수령인</th>
					<th>판매자</th>
					<th>스토어명</th>
					<th>배송요일</th>
					<th>판매기간</th>			
					<th>배송상태</th>
					<th>송장번호</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${packOrderList}">
					<tr>
						<td>${order.pkOrderNum}</td>
						<td>${order.packageName}</td>
						
						<td><fmt:formatNumber value="${order.pkTotalPrice}" type="number"/>원</td>
						
						<td>${order.userId}</td>
						<td>${order.rName}</td>
						<td>${order.sellerId}</td>   
						<td>${order.storeName}</td>
						<td>${order.deleveryDate}</td>
						<td><fmt:formatDate value="${order.subStartDate}" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${order.subEndDate}" pattern="yyyy-MM-dd" /> (${order.subRound}회차)</td>
						<td>${order.deleveryStatus}</td>
						<td>${order.trackingNum}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="pagination">
		<c:if test="${currentPage > 1}">
			<a href="?page=${currentPage - 1}">&laquo;</a>
		</c:if>
		<c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
			<a href="?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
		</c:forEach>
		<c:if test="${currentPage < totalPages}">
			<a href="?page=${currentPage + 1}">&raquo;</a>
		</c:if>
	</div>
</div>

</body>
</html>
