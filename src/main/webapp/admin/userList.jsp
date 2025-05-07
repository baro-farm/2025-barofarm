<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 | 회원 정보 검색</title>
<link rel="stylesheet" href="${contextPath}/header/reset.css">
<link rel="stylesheet" href="${contextPath}/admin/userList.css" />
</head>
<body>
	<jsp:include page="/header/adminHeader.jsp" />
	<header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
	</header>
	
	<div id="content" >
			<div class="user_header">
				<span id="title">회원 정보 검색</span>
			</div>
			
			<!-- 검색 및 필터 -->
			<form method="get" action="${contextPath}/userList" class="filter-form">
				<div style="display: flex; gap: 12px; justify-content: flex-end;">
					<!-- 구매자/판매자 선택 시 바로 전송 -->
					<select name="isSeller" onchange="submitFormWithInput(this.form)">
						<option value="">전체</option>
						<option value="0" ${param.isSeller == '0' ? 'selected' : ''}>구매자</option>
						<option value="1" ${param.isSeller == '1' ? 'selected' : ''}>판매자</option>
					</select>
					<!-- 검색 조건 -->
					<select name="searchType">
						<option value="userId" ${param.searchType == 'userId' ? 'selected' : ''}>아이디</option>
						<option value="userName" ${param.searchType == 'userName' ? 'selected' : ''}>이름</option>
					</select>
					 <input type="text" name="keyword" placeholder="검색어 입력" value="${param.keyword}" />
					<button type="submit">검색</button>
				</div>
			</form>
			
			<table id="user_table" class="table display nowrap">
				<thead>
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>전화번호</th>
						<th>이메일</th>
						<th>회원분류</th>
						<th>탈퇴여부</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="row" items="${requestScope.userList}">
						<tr>
							<td>${row.userNum}</td>
							<td>${row.userId }</td>
							<td>${row.userName }</td>
							<td>${row.phone }</td>
							<td>${row.email }</td>
							<td>
								<c:if test="${row.isSeller eq true}">판매자</c:if> 
								<c:if test="${row.isSeller eq false}">구매자</c:if>
							</td>
							<td>
								<c:if test="${row.isDeleted eq true}">O</c:if> 
								<c:if test="${row.isDeleted eq false}">X</c:if>
							</td>
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
						href="?page=${jumpPrevPage}&searchType=${param.searchType}&keyword=${param.keyword}&isSeller=${param.isSeller}">&laquo;</a>
				</c:if>

				<!-- ＜ 이전 페이지 -->
				<c:if test="${currentPage > 1}">
					<a
						href="?page=${currentPage - 1}&searchType=${param.searchType}&keyword=${param.keyword}&isSeller=${param.isSeller}">&lsaquo;</a>
				</c:if>

				<!-- 페이지 번호 -->
				<c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
					<a
						href="?page=${i}&searchType=${param.searchType}&keyword=${param.keyword}&isSeller=${param.isSeller}"
						class="${currentPage == i ? 'active' : ''}">${i}</a>
				</c:forEach>

				<!-- ＞ 다음 페이지 -->
				<c:if test="${currentPage < totalPages}">
					<a
						href="?page=${currentPage + 1}&searchType=${param.searchType}&keyword=${param.keyword}&isSeller=${param.isSeller}">&rsaquo;</a>
				</c:if>

				<!-- ≫ 다음 그룹 -->
				<c:if test="${currentPage < totalPages}">
					<a
						href="?page=${jumpNextPage}&searchType=${param.searchType}&keyword=${param.keyword}&isSeller=${param.isSeller}">&raquo;</a>
				</c:if>
			</div>
	</div>
	<script>
		function submitFormWithInput(form) {
			const keywordInput = form.querySelector('input[name="keyword"]');
			if (keywordInput) {
				keywordInput.value = keywordInput.value.trim(); // 입력 값 반영
			}
			form.submit();
		}
	</script>
</body>
</html>