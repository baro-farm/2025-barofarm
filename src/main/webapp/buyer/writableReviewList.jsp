<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>작성 가능한 리뷰</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/buyer/writableReviewList.css">
	

</head>
<body>
	<jsp:include page="/header/mainHeader.jsp"/>
	
	<div class="container" id="reviewContainer" name="reviewContainer">
	<div class="wrapper">
		<div class="sideMenu">
			<jsp:include page="/header/buyerMenu.jsp" />
		</div>
		<div class="content">
			<div class="title">작성 가능한 리뷰</div>
			
			<div class="reviewList">
				
				<!-- 작성 가능한 리뷰 반복 -->
				<c:forEach var="prodReview" items="${prodReviewList }">
				
					<div class="reviewBox">
						<img class="reviewImage" src="${prodReview.imgUrl }" alt="img">
						<div class="reviewContent">
							<div class="storeName">${prodReview.storeName }</div>
							<div class="productName">${prodReview.productName }</div>
							<div class="purchaseDate">${prodReview.orderdAt }</div>
							<div class="writeDeadline">작성 기한: ${prodReview.deadline }</div>
						</div>
						<button class="reviewButton">리뷰쓰기</button>
					</div>
					
				</c:forEach>
				
			</div>
			</div>
		</div>
		<div class="pagination">
			<span class="active">1</span> <span>2</span> <span>3</span> <span>4</span>
			<span>5</span>
		</div>
	</div>
</body>
</html>