<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세페이지 - 리뷰</title>
</head>
<body>
	<table class="reviewTable">
		<c:forEach var="r" items="${review}">
			<tr>
				<td class="reviewContent">
					<div class="reviewId">
						<strong>${r.userId }</strong> <span class="stars"> <c:forEach
								var="i" begin="1" end="5">
								<c:choose>
									<c:when test="${i <= r.pdRating}">
										<i class="bi bi-star-fill" style="color: #FFB534;"></i>
									</c:when>
									<c:otherwise>
										<i class="bi bi-star" style="color: #FFB534;"></i>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</span>
					</div>
					<div class="prodName">${r.productName}</div>
					<div class="reviewText">${r.pdContent}</div> <span
					class="reviewDate">${r.createdAt }</span>
				</td>
				<c:if test="${not empty r.imgUrl}">
					<td class="reviewImg"><img alt="리뷰이미지"
						src="${contextPath}${r.imgUrl}"></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	<div id="paging">
		<!-- 이전 페이지 -->
		<c:choose>
			<c:when test="${pageInfo.curPage > 1}">
				<a
					href="${contextPath}/detailProduct?productNum=${product.productNum}&reviewPage=${pageInfo.curPage - 1}"
					class="reviewPageLink">&lt;</a>
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
						href="${contextPath}/detailProduct?productNum=${product.productNum}&reviewPage=${page}"
						class="reviewPageLink select">${page}</a>
				</c:when>
				<c:otherwise>
					<a
						href="${contextPath}/detailProduct?productNum=${product.productNum}&reviewPage=${page}"
						class="reviewPageLink btn">${page}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<!-- 다음 페이지 -->
		<c:choose>
			<c:when test="${pageInfo.curPage < pageInfo.allPage}">
				<a
					href="${contextPath}/detailProduct?productNum=${product.productNum}&reviewPage=${pageInfo.curPage + 1}"
					class="reviewPageLink">&gt;</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">&gt;</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>