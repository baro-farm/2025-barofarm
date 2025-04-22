<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="pagingUrl">
  <c:choose>
    <c:when test="${listType == 'search'}">
      ${contextPath}/searchProductList?keyword=${keyword}&page=
    </c:when>
    <c:when test="${listType == 'best'}">
      ${contextPath}/bestProductList?page=
    </c:when>
    <c:when test="${listType == 'new'}">
      ${contextPath}/newProductList?page=
    </c:when>
    <c:otherwise>
      ${contextPath}/productList?cateNum=${cateNum}&page=
    </c:otherwise>
  </c:choose>
</c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품 목록</title>
	<link rel="stylesheet" href="${contextPath}/reset.css" />
    <link rel="stylesheet" href="${contextPath}/productList.css" /> 
</head>
<body>
	<div class="container">
		<jsp:include page="/header/mainHeader.jsp" />
		<div class="wrapper">
			<jsp:include page="/header/sideMenu.jsp" />
			<div class="content">
				<h2 class="title">
				  <c:choose>
				    <c:when test="${listType == 'search'}">
				      검색 결과: <span class="keyword">'${keyword}'</span>
				    </c:when>
				    <c:when test="${listType == 'best'}">베스트</c:when>
				    <c:when test="${listType == 'new'}">신상품</c:when>
				    <c:otherwise>${cateName}</c:otherwise>
				  </c:choose>
				</h2>
        		<div class="searchSorting">
				  <ul class="searchList">
				    <li><a href="${pagingUrl}&sort=createdAt">신상품</a></li>
				    <li><a href="${pagingUrl}&sort=priceAsc">낮은가격</a></li>
				    <li><a href="${pagingUrl}&sort=priceDesc">높은가격</a></li>
				  </ul>
				</div>

        		<!-- 상품목록  -->
        		<div class="productList">
        			<c:forEach var="p" items="${productList}">
			            <div class="product">
			                <div class="productImgBox">
			                    <a href=""><img src="${contextPath}/img/fruits1.jpg" alt=""></a>
			                </div>
			                <p><a href="" class="productName">${p.productName}</a></p>
			                <p><a href="" class="storeName">${p.storeName}</a></p>
			                <p class="price"><fmt:formatNumber value="${p.price}" type="number" />원</p>
			                <p class="reviewScore">⭐ ${p.avgRating} (${p.reviewCount})</p>
			            </div>
		            </c:forEach>
		    	</div>

		    	
		    	<div id="paging">
					<!-- 이전 페이지 -->
					<c:choose>
					  <c:when test="${pageInfo.curPage > 1}">
					    <a href="${pagingUrl}${pageInfo.curPage - 1}">&lt;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&lt;</a>
					  </c:otherwise>
					</c:choose>
					<!-- 페이지 번호 -->
					<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="page">
					  <c:choose>
					    <c:when test="${page == pageInfo.curPage}">
					      <a href="${pagingUrl}${page}" class="select">${page}</a>
					    </c:when>
					    <c:otherwise>
					      <a href="${pagingUrl}${page}" class="btn">${page}</a>
					    </c:otherwise>
					  </c:choose>
					</c:forEach>
					<!-- 다음 페이지 -->
					<c:choose>
					  <c:when test="${pageInfo.curPage < pageInfo.allPage}">
					    <a href="${pagingUrl}${pageInfo.curPage + 1}">&gt;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&gt;</a>
					  </c:otherwise>
					</c:choose>

				</div>
				
			</div> <!-- content -->
		</div> <!-- wrapper -->
		<jsp:include page="/header/footer.jsp" />
	</div> <!-- container -->
			
</body>
</html>