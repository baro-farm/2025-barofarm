<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<title>상품 상세페이지 - 상품 문의</title>
</head>
<body>
<div id="prodQASection">
	<table class="prodQA">
		<thead class="">
			<tr>
				<th style="font-weight: bold;">답변상태</th>
				<th style="font-weight: bold;">제목</th>
				<th style="font-weight: bold;">작성자</th>
				<th style="font-weight: bold;">작성일</th>
			</tr>
		</thead>
		<c:choose>
			<c:when test="${not empty qaList}">
				<c:forEach var="q" items="${qaList}">
					<tbody>
						<tr>
							<td>답변대기</td>
							<td>${q.title }</td>
							<td>${q.userId }</td>
							<td>${q.createdAt }</td>
						</tr>
				</c:forEach>
				<!-- <tr class="answerRow">
								<td colspan="4" class="answer"><span class="answerTitle">답변</span>
									안녕하세요. 고객님~ 저희 유럽 샐러드 채소를 주문해주시면...
									<div class="answerInfo">판매자 | 2025-02-21</div></td>
							</tr> -->
				</tbody>
			</c:when>
			<c:otherwise>
				<tr>
					<td class="qa-none" >등록된 문의가 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>


	<div id="paging">
		<!-- 이전 페이지 -->
		<c:choose>
			<c:when test="${pageInfo.curPage > 1}">
				<a
					href="${contextPath}/detailProduct?productNum=${product.productNum}&prodQAPage=${pageInfo.curPage - 1}"
					class="prodQAPageLink">&lt;</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">&lt;</a>
			</c:otherwise>
		</c:choose>
		<!-- 페이지 번호 -->
		<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}"
			var="page">
			<c:choose>
				<c:when test="${page == pageInfo.curPage}">
					<a
						href="${contextPath}/detailProduct?productNum=${product.productNum}&prodQAPage=${page}"
						class="prodQAPageLink select">${page}</a>
				</c:when>
				<c:otherwise>
					<a
						href="${contextPath}/detailProduct?productNum=${product.productNum}&prodQAPage=${page}"
						class="prodQAPageLink btn">${page}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<!-- 다음 페이지 -->
		<c:choose>
			<c:when test="${pageInfo.curPage < pageInfo.allPage}">
				<a
					href="${contextPath}/detailProduct?productNum=${product.productNum}&prodQAPage=${pageInfo.curPage + 1}"
					class="prodQAPageLink">&gt;</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">&gt;</a>
			</c:otherwise>
		</c:choose>
	</div>

	<button id="writeQAButton">문의 작성</button>
	<!-- 문의 작성 모달 -->
	<div id="qaModal" class="modal" style="display: none;">
		<textarea id="qaContent" placeholder="문의 내용을 입력하세요"></textarea>
		<button id="submitQAButton">작성 완료</button>
	</div>
	</div>
</body>
</html>