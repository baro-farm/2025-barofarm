<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<title>매출 조회</title>
</head>
<body>
	<jsp:include page="/header/sellerHeader.jsp" />
	<header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
	</header>
	<div id="content">
		<div class="pkHeader">
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
		
		<div class="tableWrapper">
			<!-- 리스트 내역  -->
			<table id="package_table" class="table">
				<thead>
					<tr>
						<th style="font-weight: bold;">품번</th>
						<th style="font-weight: bold;">유저 아이디</th>
						<th style="font-weight: bold;">충천 금액</th>
						<th style="font-weight: bold;">충전 일자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="">
						<tr>	
							<td>건</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
			<!-- << 현재 페이지 - 5 -->
			<c:choose>
				<c:when test="${currentPage > 1}">
					<a href="?page=${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}&sellStat=${param.sellStat}&sort=${param.sort}">&laquo;</a>
				</c:when>
				<c:otherwise>
					    <a class="disabled">&laquo;</a>
				</c:otherwise>
			</c:choose>
			<!-- < 이전 페이지 -->
			<c:choose>
				<c:when test="${currentPage > 1}">
					<a href="?page=${currentPage - 1}&sellStat=${param.sellStat}&sort=${param.sort}">&lsaquo;</a>
				</c:when>
				<c:otherwise>
					 <a class="disabled">&lsaquo;</a>
				</c:otherwise>
			</c:choose>
			<!-- 페이지 번호 -->
			<c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
				<a href="?page=${i}&sellStat=${param.sellStat}&sort=${param.sort}" class="${currentPage == i ? 'active' : ''}">${i}</a>
			</c:forEach>
			<!-- > 다음 페이지 -->
			<c:choose>
				<c:when test="${currentPage < totalPages}">
					<a href="?page=${currentPage + 1}&sellStat=${param.sellStat}&sort=${param.sort}">&rsaquo;</a>
				</c:when>
				<c:otherwise>
					    <a class="disabled">&rsaquo;</a>
				</c:otherwise>
			</c:choose>
			<!-- >> 현재 페이지 + 5 -->
			<c:choose>
				<c:when test="${currentPage < totalPages}">
					<a href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}&sellStat=${param.sellStat}&sort=${param.sort}">&raquo;</a>
				</c:when>
				<c:otherwise>
					    <a class="disabled">&raquo;</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>