<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 | 공지사항</title>
<link rel="stylesheet" href="${contextPath}/header/reset.css">
<link rel="stylesheet" href="${contextPath}/admin/noticeList.css" />
<link
	href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css"
	rel="stylesheet"
	integrity="sha384-jFvlDSY24z+oXMByOoX2Z1gM+M5NMd0uG7sDa4skv2mHXPuS0/RYXwPGLK0+Mgdc"
	crossorigin="anonymous" />
</head>
<body>
	<jsp:include page="/header/adminHeader.jsp" />
	<header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
	</header>
	<div id="content">
		<div id="wrapper">
			<div class="notice_header">
				<span id="title">공지사항</span>
				<button id="new_notice"
					onclick="location.href='${contextPath}/insertNotice'">공지
					작성</button>
			</div>
			<table id="notice_table" class="table display nowrap">
				<thead>
					<tr class="tr">
						<th class="th">번호</th>
						<th class="th">제목</th>
						<th class="th">내용</th>
						<th class="th">작성일자</th>
						<th class="th">수정일자</th>
						<th class="th">고정</th>
						<th class="th">수정</th>
						<th class="th">삭제</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="notice" items="${requestScope.noticeList}"
						varStatus="status">
						<tr class="tr">
							<td class="td">${notice.noticeNum}</td>
							<td class="td"><div class="ellipsis">${notice.title}</div></td>
							<td class="td"><div class="ellipsis">${notice.previewContent}</div></td>
							<td class="td">${notice.createdAt}</td>
							<td class="td">${notice.updatedAt}</td>
							<td class="td">${notice.fixed}</td>
							<td class="td"><button class="updateBtn"
									value="${notice.noticeNum}"
									onclick="location.href='updateNotice?noticeNum=${notice.noticeNum}'">수정</button></td>
							<td class="td"><button class="deleteBtn"
									value="${notice.noticeNum}" data-num="${notice.noticeNum}">삭제</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
	</div>
	<script src="${contextPath}/admin/noticeList.js"></script>
</body>
</html>