<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<title>작성 가능한 리뷰</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet"
	href="${contextPath}/buyer/packWritableReviewList.css">




<div class="container" id="reviewContainer" name="reviewContainer">


		<div class="content1">
			<!-- 필터 -->
		    <div class="reviewFilter">
		        <select id="reviewFilterPeriod" name="reviewFilterPeriod">
		            <option value="6개월">6개월</option>
		            <option value="1년">1년</option>
		            <option value="전체">전체</option>
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
						<img class="reviewImage" src="${contextPath}${prodReview.imgUrl }"
							alt="img">
						<div class="reviewContent">
							<div class="storeName">[${prodReview.storeName }]</div>
							<div class="productName" style="margin-top: 10px">${prodReview.productName }</div>
							<div class="purchaseDate" style="margin-top: 10px">${prodReview.orderdAt }</div>
							<div class="writeDeadline" style="margin-top: 10px">작성 기한:
								${prodReview.deadline }</div>
						</div>
						<button class="reviewButton writeReviewBtn"
							data-store-name="${prodReview.storeName }"
							data-product-name="${prodReview.productName }"
							data-product-num="${prodReview.productNum }"
							data-img-url="${prodReview.imgUrl }"
							data-user-num="${prodReview.userNum }">리뷰쓰기</button>

					</div>

				</c:forEach>

			</div>
		</div>
	</div>
	<div class="pagination">
		<span class="active">1</span> <span>2</span> <span>3</span> <span>4</span>
		<span>5</span>
	</div>
<script>
document.addEventListener("DOMContentLoaded", function () {
	  const buttons = document.querySelectorAll(".writeReviewBtn");

	  buttons.forEach(button => {
	    button.addEventListener("click", () => {
	      const data = {
	        userNum: button.dataset.userNum?.trim(),
	        productNum: button.dataset.productNum?.trim(),
	        storeName: button.dataset.storeName?.trim(),
	        productName: button.dataset.productName?.trim(),
	        imgUrl: button.dataset.imgUrl?.trim()
	      };

	      const queryString = 
	        "userNum=" + encodeURIComponent(data.userNum)+
	        "&productNum=" + encodeURIComponent(data.productNum) +
	        "&storeName=" + encodeURIComponent(data.storeName) +
	        "&productName=" + encodeURIComponent(data.productName) +
	        "&imgUrl=" + encodeURIComponent(data.imgUrl);

	      const destination = `/barofarm/insertProdReview?`+ queryString;
	      console.log("이동할 주소:", destination);

	      window.location.href = destination;
	    });
	  });
	});
</script>
