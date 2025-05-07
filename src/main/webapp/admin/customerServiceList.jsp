<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 | 고객센터</title>
<link rel="stylesheet" href="${contextPath}/header/reset.css">
<link rel="stylesheet" 	href="${contextPath}/admin/customerServiceList.css" />
</head>
<body>
	<jsp:include page="/header/adminHeader.jsp" />
	<header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
	</header>
	<div id="content" class="table display nowrap">
			<div class="notice-header">
				<span id="title">고객센터</span>
			</div>
			<!-- 검색 및 필터 -->
			<form method="get" action="${contextPath}/customerServiceList" id="answerFilterForm" class="filter-form">
				<select id="answerFilter" name="answerStatus" onchange="submitAnswerFilter(this.form)">
					<option value="" ${empty answerStatus ? 'selected' : ''}>전체</option>
					<option value="1" ${answerStatus == '1' ? 'selected' : ''}>답변완료</option>
					<option value="0" ${answerStatus == '0' ? 'selected' : ''}>미답변</option>
				</select>
			</form>

			<table id="service_table" class="table display nowrap" width="100%">
				<thead>
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>제목</th>
						<th>작성일자</th>
						<th>수정일자</th>
						<th>카테고리</th>
						<th>답변여부</th>
						<th>답변등록</th>
						<th>답변수정</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="row" items="${requestScope.customerServiceList}">
						<tr>
							<td>${row.questionNum}</td>
							<td>${row.userId }</td>
							<td>${row.title }</td>
							<td>${row.questionCreatedAt }</td>
							<td>${row.questionUpdatedAt }</td>
							<td>${row.type }</td>
							<td><c:if test="${row.answerStatus eq 1}">답변완료</c:if> <c:if
									test="${row.answerStatus eq 0}">미답변</c:if></td>
							<td><button type="button" class="insertBtn" data-num="${row.questionNum }">등록</button></td>
							<td><button type="button" class="updateBtn" data-num="${row.questionNum }">수정</button></td>
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
					<a href="?page=${currentPage - 1}&answerStatus=${answerStatus}">&lsaquo;</a>
				</c:if>

				<!-- 페이지 번호 -->
				<c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
					<a href="?page=${i}&answerStatus=${answerStatus}" class="${currentPage == i ? 'active' : ''}">${i}</a>
				</c:forEach>

				<!-- ＞ 다음 페이지 -->
				<c:if test="${currentPage < totalPages}">
					<a href="?page=${currentPage + 1}&answerStatus=${answerStatus}">&rsaquo;</a>
				</c:if>

				<!-- ≫ 다음 그룹 -->
				<c:if test="${currentPage < totalPages}">
					<a
						href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}">&raquo;</a>
				</c:if>
			</div>
		</div>
		<div id="insertModal" class="modal">
			<div class="modal_content">
				<div class="modal_wrapper">
					<p class="customer_title"></p>
					<p class="customer_content"></p>
				</div>
				<textarea class="answer_content"></textarea>
				<button id="insertAnswerBtn">등록하기</button>
			</div>
		</div>
		<div id="updateModal" class="modal">
			<div class="modal_content">
				<div class="modal_wrapper">
					<p class="customer_title"></p>
					<p class="customer_content"></p>
				</div>
				<textarea class="answer_content"></textarea>
				<button id="updateAnswerBtn">수정하기</button>
			</div>
		</div>
		<script>
			document.addEventListener('DOMContentLoaded', function() {
				const answerFilter = document.getElementById('answerFilter');

				// 커스텀 필터 로직
				answerFilter.addEventListener('change', function() {
					const value = this.value;

					dataTable.columns(7).search(value).draw(); // 8번째 컬럼 (index 7) 기준 필터
				});
			});
			function submitAnswerFilter(form) {
			    form.submit();
			  }
		</script>
		<script src="${contextPath}/admin/customerServiceList.js"></script>
		<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
		
</body>
</html>