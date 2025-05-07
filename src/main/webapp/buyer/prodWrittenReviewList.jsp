<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
	const contextPath = ${contextPath};
</script>
<script>
$(document).on('click', '.pagination a', function (e) {
    e.preventDefault();
    const page = $(this).data('page');
    const reviewFilterPeriod = $('#reviewFilterPeriod').val();


    $.ajax({
      url: '${contextPath}/prodWrittenReviewList',
      type: 'GET',
      data: {
    	reviewFilterPeriod:reviewFilterPeriod,
        page: page
      },
      success: function (data) {
        const $response = $('<div>').html(data);
        $('.reviewList').html($response.find('.reviewList').html());
        $('.pagination').html($response.find('.pagination').html());
      },
      error: function () {
        alert("페이지 요청 실패");
      }
    });
  });
  
$(document).on('click', '.toggleAnswerBtn', function () {
	const $answer = $(this).next('.answerContent');
	$answer.slideToggle(200);
});
</script>
<title>내가 작성한 리뷰</title>
<link rel="stylesheet" href="${contextPath}/buyer/prodWrittenReviewList.css">

			<div class="content1">
				<!-- 필터 -->
			    <div class="reviewFilter">
			        <select id="reviewFilterPeriod" name="reviewFilterPeriod">
			            <option value="1개월">1개월</option>
			            <option value="3개월">3개월</option>
			            <option value="6개월">6개월</option>
			        </select>
			    </div>
			    
			    <c:if test="${empty prodReviewList}">
			        <div class="emptyMessage">작성한 리뷰내역이 없습니다.</div>
			    </c:if>
			    			
				<c:forEach var="prodReview" items="${prodReviewList }">
			
				    <!-- 리뷰 리스트 -->
				    <div class="reviewBox">
				        <img src="${contextPath}/upload/${prodReview.imgUrl }" alt="${prodReview.imgUrl }" class="productImage">
				        <div class="reviewContent">
				            <div class="storeName" style="margin-top:10px; margin-bottom:5px;"><a href="${contextPath }/storeProductList?sellerNum=${prodReview.sellerNum}">[${prodReview.storeName }]</a></div>
				            <div class="productName" style="margin-top:10px;"><a href="${contextPath }/detailProduct?productNum=${prodReview.productNum}">${prodReview.productName }</a></div>
				            <div class="pdRating" style="margin-top:10px;">
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
				            <div class="pdContent" style="min-height:100px;margin-top:10px;">
				                ${prodReview.pdContent }
				            </div>
				            <div class="reviewDate">${prodReview.createdAt }</div>
				                <!-- 답변 보기 버튼 + 내용 -->
					        
			        
					        <c:if test="${prodReview.pdCommentStatus eq 'true'}">
						        <div class="answerSection">
						            <button type="button" class="toggleAnswerBtn">답변 보기</button>
						            <div class="answerContent" style="display:none;">
						                ${prodReview.pdComment}
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