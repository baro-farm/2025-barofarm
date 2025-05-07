<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<title>작성 가능한 리뷰</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/buyer/packWritableReviewList.css">
	
<script>
  const contextPath = '${contextPath}';
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
			    <c:if test="${empty packReviewList}">
			        <div class="emptyMessage">작성 가능한 리뷰내역이 없습니다.</div>
			    </c:if>				
				<!-- 작성 가능한 리뷰 반복 -->
				<c:forEach var="packReview" items="${packReviewList }">
				
					<div class="reviewBox">
						<img class="reviewImage" src="${contextPath}${packReview.imgUrl }" alt="img">
						<div class="reviewContent">
							<div class="storeName">[${packReview.storeName }]</div>
							<div class="packageName" style="margin-top:10px">${packReview.packageName }</div>
							<div class="purchaseDate" style="margin-top:10px">${packReview.orderdAt }</div>
							<div class="writeDeadline" style="margin-top:10px">작성 기한: ${packReview.deadline }</div>
						</div>
						<button class="reviewButton writeReviewBtn"
							data-store-name="${packReview.storeName }"
							data-package-name="${packReview.packageName }"
							data-package-num="${packReview.packageNum }"
							data-img-url="${packReview.imgUrl }"
							data-user-num="${packReview.userNum }"
							data-pk-order-num="${packReview.pkOrderNum }"> 리뷰쓰기</button>
							
					</div>
					
				</c:forEach>
				
			</div>
			</div>
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
	</div>

<script>
$(function () {
	  $(".writeReviewBtn").on("click", function () {
	    const $btn = $(this);
	    
	    const userNum = $.trim($btn.data("user-num"));
	    const packageNum = $.trim($btn.data("package-num"));
	    const storeName = $.trim($btn.data("store-name"));
	    const packageName = $.trim($btn.data("package-name"));
	    const imgUrl = $.trim($btn.data("img-url"));
	    const pkOrderNum = $.trim($btn.data("pk-order-num"));

	    const queryString = 
	      "userNum=" + encodeURIComponent(userNum) +
	      "&packageNum=" + encodeURIComponent(packageNum) +
	      "&storeName=" + encodeURIComponent(storeName) +
	      "&packageName=" + encodeURIComponent(packageName) +
	      "&imgUrl=" + encodeURIComponent(imgUrl)+
	      "&pkOrderNum="+ encodeURIComponent(pkOrderNum);

	    const destination = `\${contextPath}/insertPackReview?\${queryString}`;
	    console.log("이동할 주소:", destination);
	    location.href = destination;
	  });
	});
</script>
