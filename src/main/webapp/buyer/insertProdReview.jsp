<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 작성</title>
    <link rel="stylesheet" href="${contextPath}/buyer/insertProdReview.css">
	<script>
	    document.addEventListener("DOMContentLoaded", function () {
	        const stars = document.querySelectorAll("#starRating span");
	        const hiddenInput = document.getElementById("pdRating");
	        let currentRating = 0;
	
	        stars.forEach((star, index) => {
	            const value = index + 1;
	
	
	            // 마우스 나가면 현재 선택값으로 돌아옴
	            star.addEventListener("mouseout", function () {
	                highlightStars(currentRating);
	            });
	
	            // 클릭하면 현재 선택값 저장
	            star.addEventListener("click", function () {
	                currentRating = value;
	                hiddenInput.value = currentRating;
	                highlightStars(currentRating);
	            });
	        });
	
	        function highlightStars(rating) {
	            stars.forEach((star, idx) => {
	                star.textContent = idx < rating ? '★' : '☆';
	            });
	        }
	    });
	</script>
</head>
<body>
<jsp:include page="/header/mainHeader.jsp"/>
    <div class="container">
    	<div class="wrapper">
			<div class="sideMenu">
				<jsp:include page="/header/buyerMenu.jsp" />
			</div>
			<div class="content">
			
					<div class="header">리뷰 작성</div>
		
				    <div class="productInfo">
				        <img src="${contextPath }${imgUrl }" alt="고구마" class="productImage">
				        <div class="productDetails">
				        	<div>${storeName }</div>
				            <div>${productName }</div>
				        </div>
				    </div>
		
			    <form action="${contextPath }/insertProdReview" method="post">
			        <div class="formGroup">
				        <input type="hidden" name="userNum" value="${userNum}">
						<input type="hidden" name="productNum" value="${productNum}">
						
					    <!-- 추가: storeName, productName, imgUrl -->
					    <input type="hidden" name="storeName" value="${storeName}">
					    <input type="hidden" name="productName" value="${productName}">
					    <input type="hidden" name="imgUrl" value="${imgUrl}">
			            <label>만족도</label>
			            <div id="starRating" class="ratingStars">
					        <span data-value="1">☆</span>
					        <span data-value="2">☆</span>
					        <span data-value="3">☆</span>
					        <span data-value="4">☆</span>
					        <span data-value="5">☆</span>
					    </div>
					    <input type="hidden" name="pdRating" id="pdRating" value="0">
			        </div>
			
			        <div class="formGroup">
			            <label>내용</label>
			            <textarea class="reviewTextarea" placeholder="리뷰를 작성해주세요." name="pdReviewContent"></textarea>
			        </div>
			
			        <div class="fileUploadBox">
			            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-camera" viewBox="0 0 16 16">
			                <path d="M15 12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V6a1 1 0 0 1 1-1h1.172a3 3 0 0 0 2.12-.879l.83-.828A1 1 0 0 1 6.827 3h2.344a1 1 0 0 1 .707.293l.828.828A3 3 0 0 0 12.828 5H14a1 1 0 0 1 1 1zM2 4a2 2 0 0 0-2 2v6a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V6a2 2 0 0 0-2-2h-1.172a2 2 0 0 1-1.414-.586l-.828-.828A2 2 0 0 0 9.172 2H6.828a2 2 0 0 0-1.414.586l-.828.828A2 2 0 0 1 3.172 4z"/>
			                <path d="M8 11a2.5 2.5 0 1 1 0-5 2.5 2.5 0 0 1 0 5m0 1a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M3 6.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0"/>
			              </svg>
			            사진/동영상 첨부
			        </div>
			
			        <div class="buttonGroup">
			            <button type="submit" class="btn btnSubmit">등록하기</button>
			            <button type="button" class="btn btnCancel">취소</button>
			        </div>
			    </form>
			    
			</div> <!-- end of content -->
		</div> <!-- end of wrapper -->
		
		
	</div><!-- end of container -->
</body>
</html>