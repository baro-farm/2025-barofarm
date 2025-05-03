<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<link rel="stylesheet" href="${contextPath}/header/reset.css">
<link rel="stylesheet" href="${contextPath}/admin/totalSalesPoint.css">
<title>관리자 | 매출 조회</title>
</head>
<body>
	<jsp:include page="/header/adminHeader.jsp" />
	<header id="header">
			<jsp:include page="/header/adminSellerTop.jsp" />
	</header>
	<div id="content">
	<div id="wrapper">
		<div class="point_header">
			<span id="title">전체 매출</span>
		</div>
		<div class="selectBox">
			<!-- 필터링 옵션 -->
				<form id="sortForm" method="get" action="">
					<select name="sellStat" id="sellStat">
						<option value="all">전체</option>
						<option value="sales" >충전 내역</option>
						<option value="salesStop" >사용 내역</option>
					</select>
				</form>
		</div>
		
			<!-- 리스트 내역  -->
			<table id="point_table" class="table">
				<thead>
					<tr>
						<th style="font-weight: bold;">유형</th>
						<th style="font-weight: bold;">유저 ID</th>
						<th style="font-weight: bold;">판매자 명</th>
						<th style="font-weight: bold;">상점 명</th>
						<th style="font-weight: bold;">사용처</th>
						<th style="font-weight: bold;">거래 금액</th>
						<th style="font-weight: bold;">잔여 금액</th>
						<th style="font-weight: bold;">충전 일자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${pointList}">
						<tr>	
							<td>${p.pType}</td>
							<td>${p.userId }</td>
							<td>${p.userName }</td>
							<td>${p.storeName }</td>
							<td>${p.type }</td>
							<td>${p.usedPoint }</td>
							<td>${p.currPoint }</td>
							<td>${p.createdAt }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		<div id="paging">
					<!-- 이전 페이지 -->
					<c:choose>
					  <c:when test="${pageInfo.curPage > 10}">
					    <a href="${pagingUrl}&page=${pageInfo.curPage - 10}&sort=${sort}">&laquo;</a>
					  </c:when>
					</c:choose>
					<c:choose>
					  <c:when test="${pageInfo.curPage > 1}">
					    <a href="${pagingUrl}&page=${pageInfo.curPage - 1}&sort=${sort}">&lsaquo;</a>
					  </c:when>
					</c:choose>
					<!-- 페이지 번호 -->
					<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="page">
					  <c:choose>
					    <c:when test="${page == pageInfo.curPage}">
					      <a href="${pagingUrl}&page=${page}&sort=${sort}" class="select">${page}</a>
					    </c:when>
					    <c:otherwise>
					      <a href="${pagingUrl}&page=${page}&sort=${sort}" class="btn">${page}</a>
					    </c:otherwise>
					  </c:choose>
					</c:forEach>
					<!-- 다음 페이지 -->
					<c:choose>
					  <c:when test="${pageInfo.curPage < pageInfo.allPage}">
					    <a href="${pagingUrl}&page=${pageInfo.curPage + 1}&sort=${sort}">&rsaquo;</a>
					  </c:when>
					</c:choose>
					<c:choose>
					  <c:when test="${pageInfo.curPage < pageInfo.allPage}">
					    <a href="${pagingUrl}&page=${pageInfo.curPage + 10}&sort=${sort}">&raquo;</a>
					  </c:when>
					</c:choose>
				</div>
		</div>
	</div>
</body>
</html>