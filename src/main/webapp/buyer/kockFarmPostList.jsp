<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>내가 작성한 원해요</title>
<link rel="stylesheet" href="${contextPath}/buyer/kockFarmPostList.css">

<style>
</style>
</head>
<body>
	<jsp:include page="/header/mainHeader.jsp" />

	<div class="container">
		<div class="wrapper">
			<div class="sideMenu">
				<jsp:include page="/header/buyerMenu.jsp" />
			</div>
			<div class="content">
				<div class="header">내가 작성한 콕팜</div>

				<!-- 필터 섹션 -->
				<form method="get" action="${contextPath }/kockFarmPostList"
					id="filterForm" class="filter-section">
					<select name="period" id="periodSelect">
						<option value="6" ${param.period == '6' ? 'selected' : ''}>6개월</option>
						<option value="3" ${param.period == '3' ? 'selected' : ''}>3개월</option>
						<option value="1" ${param.period == '1' ? 'selected' : ''}>1개월</option>
					</select> <select name="matched" id="matchedSelect">
						<option value="all" ${param.matched == 'all' ? 'selected' : ''}>전체</option>
						<option value="1" ${param.matched == '1' ? 'selected' : ''}>체결
							완료</option>
						<option value="0" ${param.matched == '0' ? 'selected' : ''}>체결
							미완료</option>
					</select>
				</form>
				<script>
			  document.addEventListener("DOMContentLoaded", function () {
			    const form = document.getElementById("filterForm");
			    const periodSelect = document.getElementById("periodSelect");
			    const matchedSelect = document.getElementById("matchedSelect");
			
			    periodSelect.addEventListener("change", () => form.submit());
			    matchedSelect.addEventListener("change", () => form.submit());
			  });
			</script>
				<c:if test="${empty kockPostList}">
					<div class="emptyMessage">작성한 콕팜이 없습니다.</div>
				</c:if>

				<!-- 게시글 개수만큼 반복 -->
				<c:forEach var="kcPost" items="${kockPostList }">
					<div class="post">
						<div class="post-title" onclick="toggleContent(this)">
							<span> ${kcPost.title } </span> <span class="toggle-btn">▼</span>
						</div>
						<div class="category">${kcPost.name }</div>
						<div class="answer">
							<div class="tag apply">
								<a href="detailKockFarm?kockNum=${kcPost.kockNum }"><span>제안></span><span
									class="answerNum">${kcPost.kcCommentCount }</span></a>
							</div>
							<c:choose>
								<c:when test="${kcPost.isMatched }">
									<span class="tag complete">체결</span> ${kcPost.storeName }
			            		
			            	</c:when>
								<c:otherwise>
									<span class="tag" style="display: none;">체결</span>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="kockContent">
							${kcPost.content}
							<div class="date">등록일: ${kcPost.createdAt }</div>
						</div>
					</div>
				</c:forEach>

			</div>
			<!-- end of content -->

			
			</div>
			
					<c:set var="groupStartPage"
				value="${(currentPage - 1) / pageGroupSize * pageGroupSize + 1}" />
			<c:set var="groupEndPage"
				value="${groupStartPage + pageGroupSize - 1}" />

			<c:if test="${groupEndPage > totalPages}">
				<c:set var="groupEndPage" value="${totalPages}" />
			</c:if>

			<div class="pagination">
				<!-- << -->
				<c:choose>
					<c:when test="${currentPage > 1}">
						<a
							href="?page=${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}&period=${param.period}&matched=${param.matched}">&laquo;</a>
					</c:when>
					<c:otherwise>
						<a class="disabled">&laquo;</a>
					</c:otherwise>
				</c:choose>

				<!-- < -->
				<c:choose>
					<c:when test="${currentPage > 1}">
						<a
							href="?page=${currentPage - 1}&period=${param.period}&matched=${param.matched}">&lsaquo;</a>
					</c:when>
					<c:otherwise>
						<a class="disabled">&lsaquo;</a>
					</c:otherwise>
				</c:choose>

				<!-- 페이지 번호 -->
				<c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
					<a
						href="?page=${i}&period=${param.period}&matched=${param.matched}"
						class="${currentPage == i ? 'active' : ''}">${i}</a>
				</c:forEach>

				<!-- > -->
				<c:choose>
					<c:when test="${currentPage < totalPages}">
						<a
							href="?page=${currentPage + 1}&period=${param.period}&matched=${param.matched}">&rsaquo;</a>
					</c:when>
					<c:otherwise>
						<a class="disabled">&rsaquo;</a>
					</c:otherwise>
				</c:choose>

				<!-- >> -->
				<c:choose>
					<c:when test="${currentPage < totalPages}">
						<a
							href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}&period=${param.period}&matched=${param.matched}">&raquo;</a>
					</c:when>
					<c:otherwise>
						<a class="disabled">&raquo;</a>
					</c:otherwise>
				</c:choose>
		</div>
		

		<!-- end of wrapper -->
	</div>
	<!-- end of container -->

	<script>
   function toggleContent(element) {
    let post = element.closest('.post'); // 현재 클릭한 요소가 속한 .post 찾기
    let content = post.querySelector('.kockContent'); // 해당 .post 안에서 .content 찾기
    let icon = element.querySelector('.toggle-btn');

    if (content.style.display === "block") {
        content.style.display = "none";
        icon.textContent = "▼";
    } else {
        content.style.display = "block";
        icon.textContent = "▲";
    }
}
</script>

</body>
</html>