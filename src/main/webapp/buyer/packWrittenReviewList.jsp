<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
	const contextPath = ${contextPath};

</script>

    <title>내가 작성한 리뷰</title>
    <link rel="stylesheet" href="${contextPath}/buyer/packWrittenReviewList.css">

			<div class="content1">
				<!-- 필터 -->
			    <div class="reviewFilter">
			        <select id="reviewFilterPeriod" name="reviewFilterPeriod">
			            <option value="1개월">1개월</option>
			            <option value="3개월">3개월</option>
			            <option value="6개월">6개월</option>
			        </select>
			    </div>
			    
			    <c:if test="${empty packReviewList}">
			        <div class="emptyMessage">작성한 리뷰내역이 없습니다.</div>
			    </c:if>
			    			
				<c:forEach var="packReview" items="${packReviewList }">
			
				    <!-- 리뷰 리스트 -->
				    <div class="reviewBox">
				        <img src="${contextPath}/upload/${packReview.pkReviewImgUrl }" alt="${packReview.pkReviewImgUrl }" class="productImage">
				        <div class="reviewContent ">
				            <div class="storeName" style="margin-top:10px; margin-bottom:5px;"><a href="${contextPath }/storeProductList?sellerNum=${packReview.sellerNum}">[${packReview.storeName }]</a></div>
				            <div class="productName" style="margin-top:10px;"><a href="${contextPath }/detailPackage?packageNum=${packReview.packageNum}">${packReview.packageName }</a></div>
				            <div class="pdRating" style="margin-top:10px;">
					            <c:forEach var="i" begin="1" end="5">
								    <c:choose>
								      <c:when test="${i <= packReview.pkRating}">
								        ★
								      </c:when>
								      <c:otherwise>
								        ☆
								      </c:otherwise>
								    </c:choose>
								  </c:forEach>
								  ${packReview.pkRating }
				            </div>
				            <div class="pdReviewContent" style="min-height:100px;margin-top:10px;">
				                ${packReview.pkReviewContent }
				            </div>
				            <div class="reviewDate">${packReview.createdAt }</div>
				            <c:if test="${packReview.pkCommentStatus eq 'true'}">
						        <div class="answerSection">
						            <button type="button" class="toggleAnswerBtn">답변 보기</button>
						            <div class="answerContent" style="display:none;">
						                ${packReview.pkComment}
						            </div>
						        </div>
					        </c:if>
				        </div>
				    </div>
			    </c:forEach>
				
			
			</div>
		
<div class="pagination">
    <!-- << -->
    <c:choose>
        <c:when test="${currentPage > 1}">
            <a href="#" class="page-link" data-page="${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}">&laquo;</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">&laquo;</a>
        </c:otherwise>
    </c:choose>

    <!-- < -->
    <c:choose>
        <c:when test="${currentPage > 1}">
            <a href="#" class="page-link" data-page="${currentPage - 1}">&lsaquo;</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">&lsaquo;</a>
        </c:otherwise>
    </c:choose>

    <!-- 번호 -->
    <c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
        <a href="#" class="page-link ${currentPage == i ? 'active' : ''}" data-page="${i}">${i}</a>
    </c:forEach>

    <!-- > -->
    <c:choose>
        <c:when test="${currentPage < totalPages}">
            <a href="#" class="page-link" data-page="${currentPage + 1}">&rsaquo;</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">&rsaquo;</a>
        </c:otherwise>
    </c:choose>

    <!-- >> -->
    <c:choose>
        <c:when test="${currentPage < totalPages}">
            <a href="#" class="page-link" data-page="${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}">&raquo;</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">&raquo;</a>
        </c:otherwise>
    </c:choose>
</div>
