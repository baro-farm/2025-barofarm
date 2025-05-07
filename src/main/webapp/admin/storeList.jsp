<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 | 스토어 목록</title>
<link rel="stylesheet" href="${contextPath}/header/reset.css">
<link rel="stylesheet" href="${contextPath}/admin/storeList.css" />
</head>
<body>
	<jsp:include page="/header/adminHeader.jsp" />
	<header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
	</header>

	<div id="content">
			<div class="store_header">
				<span id="title">스토어 정보 검색</span>
			</div>
			
			<!-- 🔍 검색 및 필터 -->
			<form method="get" action="${contextPath}/storeList" class="filter-form">
			<div style="display: flex; gap: 12px; justify-content: flex-end;">
				<select name="isAlarm" onchange="submitFormWithParams(this.form)">
					<option value="">전체</option>
					<option value="1" ${isAlarm == '1' ? 'selected' : ''}>팜링 구독</option>
					<option value="0" ${isAlarm == '0' ? 'selected' : ''}>미구독</option>
				</select>
				<select name="searchType">
					<option value="userId" ${searchType == 'userId' ? 'selected' : ''}>아이디</option>
					<option value="storeName" 	${searchType == 'storeName' ? 'selected' : ''}>스토어명</option>
				</select> 
				<input type="text" name="keyword" placeholder="검색어 입력" value="${keyword}" />
				<button type="submit">검색</button>
			</div>
		</form>
		
			<table id="store_table" class="table display nowrap">
				<thead>
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>전화번호</th>
						<th>이메일</th>
						<th>스토어명</th>
						<th>사업자등록번호</th>
						<th>팜링 구독</th>
						<th>매출액</th>
						<th>탈퇴여부</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="row" items="${storeList}">
						<tr>
							<td>${row.sellerNum}</td>
							<td>${row.userId}</td>
							<td>${row.userName}</td>
							<td>${row.phone}</td>
							<td>${row.email}</td>
							<td>${row.storeName}</td>
							<td>${row.businessNum}</td>
							<td><c:out value="${row.isAlarm == 1 ? 'O' : 'X'}" /></td>
							<td>${row.amount}</td>
							<td><c:out value="${row.isDeleted == 1 ? 'O' : 'X'}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- 페이징 -->
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
				<!-- ≪ 현재 그룹 이전 -->
				<c:if test="${currentPage > 1}">
					<a
						href="?page=${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}">&laquo;</a>
				</c:if>

				<!-- ＜ 이전 페이지 -->
				<c:if test="${currentPage > 1}">
					<a href="?page=${currentPage - 1}">&lsaquo;</a>
				</c:if>

				<!-- 페이지 번호 -->
				<c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
					<a href="?page=${i}" class="${currentPage == i ? 'active' : ''}">${i}</a>
				</c:forEach>

				<!-- ＞ 다음 페이지 -->
				<c:if test="${currentPage < totalPages}">
					<a href="?page=${currentPage + 1}">&rsaquo;</a>
				</c:if>

				<!-- ≫ 다음 그룹 -->
				<c:if test="${currentPage < totalPages}">
					<a
						href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}">&raquo;</a>
				</c:if>
			</div>
	</div>
	<script>
		function submitFormWithParams(form) {
			const searchType = form.searchType.value;
			const keyword = form.keyword.value.trim();
			const isAlarm = form.isAlarm.value;

			const query = new URLSearchParams({
				searchType : searchType,
				keyword : keyword,
				isAlarm : isAlarm
			}).toString();

			window.location.href = form.action + "?" + query;
		}
	</script>
	<script src="${contextPath}/admin/storeList.js"></script>
</body>
</html>
