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
		<div>
			<h1>장바구니</h1>
			<c:forEach var="entry" items="${cartMap}">
				<div class="store-group">
					<h2>🛍️ ${entry.key}</h2>
					<!-- storeName -->

					<c:forEach var="item" items="${entry.value}">
						<div class="cart-item">
							<img src="${contextPath}${item.imgUrl}" />
							<div class="cart-item-info">
								<p>${item.productName}</p>
								<p>옵션: ${item.option} (${item.optionPrice}원)</p>
								<p>수량: ${item.quantity}</p>
								<p>가격: ${item.price }원</p>
								<p>총 가격: ${item.totalPrice}원</p>
								<button class="open-modal" data-cart="${item.cartNum}"
									data-product="${item.productNum}">옵션 변경</button>
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