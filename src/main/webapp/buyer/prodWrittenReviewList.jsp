<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
	const contextPath = ${contextPath};
</script>

    <title>내가 작성한 리뷰</title>
    <link rel="stylesheet" href="${contextPath}/buyer/prodWrittenReviewList.css">


	


			
			<div class="content1">
				<!-- 필터 -->
			    <div class="reviewFilter">
			        <select id="reviewFilterPeriod" name="reviewFilterPeriod">
			            <option value="6개월">6개월</option>
			            <option value="1년">1년</option>
			            <option value="전체">전체</option>
			        </select>
			    </div>
			    
			    <c:if test="${empty prodReviewList}">
			        <div class="emptyMessage">작성한 리뷰내역이 없습니다.</div>
			    </c:if>
			    			
				<c:forEach var="prodReview" items="${prodReviewList }">
			
				    <!-- 리뷰 리스트 -->
				    <div class="reviewBox">
				        <img src="${contextPath}/upload/${prodReview.pdReviewImgUrl }" alt="${prodReview.pdReviewImgUrl }" class="productImage">
				        <div class="reviewContent">
				            <div class="storeName"><a href="#">[${prodReview.storeName }]</a></div>
				            <div class="productName"><a href="#">${prodReview.productName }</a></div>
				            <div class="pdRating">
					            <c:forEach var="i" begin="1" end="5">
								    <c:choose>
								      <c:when test="${i <= prodReview.pdRating}">
								        ★
								      </c:when>
								      <c:otherwise>
								        ☆
								      </c:otherwise>
								    </c:choose>
								  </c:forEach>
								  ${prodReview.pdRating }
				            </div>
				            <div class="pdReviewContent">
				                ${prodReview.pdReviewContent }
				            </div>
				            <div class="reviewDate">${prodReview.createdAt }</div>
				        </div>
				    </div>
			    </c:forEach>
				
			
			</div>
		
		    <!-- 페이지네이션 -->
	    <div class="pagination">
	        <span class="active">1</span>
	        <span>2</span>
	        <span>3</span>
	        <span>4</span>
	        <span>5</span>
	    </div>
