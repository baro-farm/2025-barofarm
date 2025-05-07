<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="pagingUrl">
  <c:choose>
    <c:when test="${listType == 'search'}">
      ${contextPath}/searchProductList?keyword=${keyword}&
    </c:when>
    <c:when test="${listType == 'best'}">
      ${contextPath}/bestProductList?
    </c:when>
    <c:when test="${listType == 'new'}">
      ${contextPath}/newProductList?
    </c:when>
    <c:when test="${listType == 'store'}">
      ${contextPath}/storeProductList?sellerNum=${sellerNum}&
    </c:when>
    <c:when test="${listType == 'package'}">
      ${contextPath}/packageListByAll?
    </c:when>
    <c:otherwise>
      ${contextPath}/productList?cateNum=${cateNum}&
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
				    <c:when test="${listType eq 'search'}">
				      검색 결과: <span class="keyword">'${keyword}'</span>
				    </c:when>
				    <c:when test="${listType eq 'best'}">베스트</c:when>
				    <c:when test="${listType eq 'new'}">신상품</c:when>
				    <c:when test="${listType eq 'store'}">${storeName}</c:when>
				    <c:otherwise>${cateName}</c:otherwise>
				  </c:choose>
				</h2>
        		<div class="searchSorting">
        		<c:if test="${listType != 'best' and listType != 'new'}">
				  <ul class="searchList">
						    <li><a href="${pagingUrl}page=1&sort=createdAt">신상품</a></li>
						    <li><a href="${pagingUrl}page=1&sort=priceAsc">낮은가격</a></li>
						    <li><a href="${pagingUrl}page=1&sort=priceDesc">높은가격</a></li>
				  </ul>
				  </c:if>
				</div>

        		<!-- 상품목록  -->
        		<div class="productList">
        			<c:forEach var="p" items="${productList}">
			            <div class="product">
			                <div class="productImgBox">
			                    <a href="${contextPath}/detailProduct?productNum=${p.productNum}">
				                    <c:if test="${empty p.imgUrl}">
						    			<img src="${contextPath}/img/fruits1.jpg" alt="">
					    			</c:if>
					    			<c:if test="${not empty p.imgUrl}">
			                    		<img src="${contextPath}${p.imgUrl}" alt="">
			                    	</c:if>
			                    </a>
			                </div>
			                <div class="nameBox">
				                <p><a href="${contextPath}/detailProduct?productNum=${p.productNum}" class="productName">${p.productName}</a></p>
				                <p><a href="${contextPath}/detailProduct?productNum=${p.productNum}" class="storeName">${p.storeName}</a></p>
				                <p class="price"><fmt:formatNumber value="${p.price}" type="number" />원</p>
				                <p class="reviewScore"><i class="bi bi-star-fill" style="color: #FFB534;"></i> ${p.avgRating} (${p.reviewCount})</p>
			                </div>
			            </div>
		            </c:forEach>
		            <c:forEach var="p" items="${packageList}">
			            <div class="product">
			                <div class="productImgBox">
			                    <a href="${contextPath}/detailPackage?packageNum=${p.packageNum}">
				                    <c:if test="${empty p.imgUrl}">
						    			<img src="${contextPath}/img/fruits1.jpg" alt="">
					    			</c:if>
					    			<c:if test="${not empty p.imgUrl}">
			                    		<img src="${contextPath}${p.imgUrl}" alt="">
			                    	</c:if>
			                    </a>
			                    <p class="dateStr" hidden>${p.startDate}</p>
			                    <span class="weekday"></span>
			                </div>
			                <div class="nameBox">
				                
				                <p class="name-row">
				                	<a href="${contextPath}/detailPackage?packageNum=${p.packageNum}" class="packageName">${p.packageName}</a>	
				                </p>
				                <p><a href="${contextPath}/detailPackage?packageNum=${p.packageNum}" class="storeName">${p.storeName}</a></p>
				                <p class="price"><fmt:formatNumber value="${p.packagePrice}" type="number" />원</p>
				                <p class="reviewScore"><i class="bi bi-star-fill" style="color: #FFB534;"></i> ${p.avgRating} (${p.reviewCount})</p>
			                </div>
			            </div>
		            </c:forEach>
		    	</div>

		    	<div id="paging">
					<!-- 이전 페이지 -->
					<c:choose>
					  <c:when test="${pageInfo.curPage > 10}">
					    <a href="${pagingUrl}page=${pageInfo.curPage - 10}&sort=${sort}">&laquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&laquo;</a>
					  </c:otherwise>
					</c:choose>
					<c:choose>
					  <c:when test="${pageInfo.curPage > 1}">
					    <a href="${pagingUrl}page=${pageInfo.curPage - 1}&sort=${sort}">&lsaquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&lsaquo;</a>
					  </c:otherwise>
					</c:choose>
					<!-- 페이지 번호 -->
					<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="page">
					  <c:choose>
					    <c:when test="${page == pageInfo.curPage}">
					      <a href="${pagingUrl}page=${page}&sort=${sort}" class="select">${page}</a>
					    </c:when>
					    <c:otherwise>
					      <a href="${pagingUrl}page=${page}&sort=${sort}" class="btn">${page}</a>
					    </c:otherwise>
					  </c:choose>
					</c:forEach>
					<!-- 다음 페이지 -->
					<c:choose>
					  <c:when test="${pageInfo.curPage < pageInfo.allPage}">
					    <a href="${pagingUrl}page=${pageInfo.curPage + 1}&sort=${sort}">&rsaquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&rsaquo;</a>
					  </c:otherwise>
					</c:choose>
					<c:choose>
					  <c:when test="${pageInfo.curPage < pageInfo.allPage}">
					    <a href="${pagingUrl}page=${pageInfo.curPage + 10}&sort=${sort}">&raquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&raquo;</a>
					  </c:otherwise>
					</c:choose>
				</div>
				
			</div> <!-- content -->
		</div> <!-- wrapper -->
		<jsp:include page="/header/footer.jsp" />
	</div> <!-- container -->
</body>
<script>
	function getDayOfWeek(dateStr) {
	    const days = ['일요팜', '월요팜', '화요팜', '수요팜', '목요팜', '금요팜', '토요팜'];
	    const date = new Date(dateStr);
	    return days[date.getDay()];
	}
	
	document.querySelectorAll('.dateStr').forEach(function(dateElem, idx) {
	    const dateText = dateElem.innerText.trim();
	    const weekElem = document.querySelectorAll('.weekday')[idx];
	    if (weekElem) {
	        weekElem.innerHTML = getDayOfWeek(dateText);
	    }
	});
</script>
</html>