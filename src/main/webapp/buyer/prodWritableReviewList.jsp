<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<title>작성 가능한 리뷰</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/buyer/prodWritableReviewList.css">
	
<script>
  const contextPath = '${contextPath}';
</script>
<script>
$(document).on('click', '.pagination a', function (e) {
    e.preventDefault();
    const page = $(this).data('page');
    const reviewFilterPeriod = $('#reviewFilterPeriod').val();


    $.ajax({
      url: '${contextPath}/prodWritableReviewList',
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
</script>
	
	<div class="container" id="reviewContainer" name="reviewContainer">
	<div class="wrapper">

		<div class="content1">
			<!-- 필터 -->
		    <div class="reviewFilter">
		        <select id="reviewFilterPeriod" name="reviewFilterPeriod">
		            <option value="1개월">1개월</option>
		            <option value="3개월">3개월</option>
		        </select>
		    </div>			
			<div class="reviewList">
	           <!-- 주문 내역이 없을 때 -->
			    <c:if test="${empty prodReviewList}">
			        <div class="emptyMessage">작성 가능한 리뷰내역이 없습니다.</div>
			    </c:if>				
				<!-- 작성 가능한 리뷰 반복 -->
				<c:forEach var="prodReview" items="${prodReviewList }">
				
					<div class="reviewBox">
						<img class="reviewImage" src="${contextPath}${prodReview.imgUrl }" alt="img">
						<div class="reviewContent">
							<div class="storeName">[${prodReview.storeName }]</div>
							<div class="productName" style="margin-top:10px">${prodReview.productName }</div>
							<div class="purchaseDate" style="margin-top:10px">${prodReview.orderdAt }</div>
							<div class="writeDeadline" style="margin-top:10px">작성 기한: ${prodReview.deadline }</div>
						</div>
						<button class="reviewButton writeReviewBtn"
							data-store-name="${prodReview.storeName }"
							data-product-name="${prodReview.productName }"
							data-product-num="${prodReview.productNum }"
							data-img-url="${prodReview.imgUrl }"
							data-user-num="${prodReview.userNum }"
							data-pd-order-num="${prodReview.pdOrderNum }"> 리뷰쓰기</button>
							
					</div>
					
				</c:forEach>
				
			</div>
			</div>
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
	</div>

<script>
$(function () {
	  $(".writeReviewBtn").on("click", function () {
	    const $btn = $(this);
	    
	    const userNum = $.trim($btn.data("user-num"));
	    const productNum = $.trim($btn.data("product-num"));
	    const storeName = $.trim($btn.data("store-name"));
	    const productName = $.trim($btn.data("product-name"));
	    const imgUrl = $.trim($btn.data("img-url"));
	    const pdOrderNum = $.trim($btn.data("pd-order-num"));

	    const queryString = 
	      "userNum=" + encodeURIComponent(userNum) +
	      "&productNum=" + encodeURIComponent(productNum) +
	      "&storeName=" + encodeURIComponent(storeName) +
	      "&productName=" + encodeURIComponent(productName) +
	      "&imgUrl=" + encodeURIComponent(imgUrl)+
	      "&pdOrderNum="+ encodeURIComponent(pdOrderNum);

	    const destination = `\${contextPath}/insertProdReview?` + queryString;
	    console.log("이동할 주소:", destination);
	    location.href = destination;
	  });
	});
</script>
