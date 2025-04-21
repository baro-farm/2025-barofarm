<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath }/buyer/shoppingCart.css">
</head>
<body>
	<jsp:include page="/header/mainHeader.jsp" />
	<div class="container">
		<div class="sideMenu">
			<jsp:include page="/header/buyerMenu.jsp" />
		</div>
		<div id="content">
			<h1 class="title">장바구니</h1>
			<c:forEach var="entry" items="${cartMap}">
				<div class="store-group">
					<h2 class="store_name">${entry.key} <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
<path d="M6.75 16.5V9H11.25V16.5M2.25 6.75L9 1.5L15.75 6.75V15C15.75 15.3978 15.592 15.7794 15.3107 16.0607C15.0294 16.342 14.6478 16.5 14.25 16.5H3.75C3.35218 16.5 2.97064 16.342 2.68934 16.0607C2.40804 15.7794 2.25 15.3978 2.25 15V6.75Z" stroke="#1E1E1E" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
</svg></h2>
<hr class="hr">
					<!-- storeName -->

					<c:forEach var="item" items="${entry.value}">
						<div class="cart-item">
							<img src="${contextPath}${item.imgUrl}" />
							<div class="cart-item-info">
							<div>
								<p class="product-title">${item.productName}</p>
								<p class="price">${item.price }원</p>
							</div>
							<div>
								<p>${item.option} / ${item.quantity }개  (+${item.optionPrice}원)</p>
								<button class="open-modal" data-cart="${item.cartNum}"
									data-product="${item.productNum}">옵션 변경</button>
							</div>
								
								<p>총 가격: ${item.totalPrice}원</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:forEach>

		</div>
	</div>
	<div id="optionModal" class="modal" style="display: none;">
		<div class="modal-content">
			<h3>옵션 변경</h3>
			<form id="optionChangeForm" method="post"
				action="${contextPath}/updateCartOption">
				<input type="hidden" name="cartNum" id="modalCartNum" /> <label>옵션
					선택 <select name="newOptionNum" id="modalOptionSelect"></select>
				</label>
				<button type="submit">변경</button>
				<button type="button" onclick="closeModal()">닫기</button>
			</form>
		</div>
	</div>

	<script src="${contextPath }/buyer/shoppingCart.js"></script>
</body>
</html>