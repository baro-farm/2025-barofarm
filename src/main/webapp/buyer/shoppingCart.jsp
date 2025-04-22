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
			<c:forEach var="storeEntry" items="${cartMap}">
				<div class="store-group">
					<label> <input type="checkbox" class="store-checkbox"
						data-store="${storeEntry.key }" />
						<h2 class="store_name">${storeEntry.key}
							<svg width="18" height="18" viewBox="0 0 18 18" fill="none"
								xmlns="http://www.w3.org/2000/svg">
<path
									d="M6.75 16.5V9H11.25V16.5M2.25 6.75L9 1.5L15.75 6.75V15C15.75 15.3978 15.592 15.7794 15.3107 16.0607C15.0294 16.342 14.6478 16.5 14.25 16.5H3.75C3.35218 16.5 2.97064 16.342 2.68934 16.0607C2.40804 15.7794 2.25 15.3978 2.25 15V6.75Z"
									stroke="#1E1E1E" stroke-width="2" stroke-linecap="round"
									stroke-linejoin="round" />
</svg>
						</h2>
					</label>
					<hr class="hr">
					<!-- storeName -->

					<c:forEach var="product" items="${storeEntry.value}">
						<div class="cart-item">
							<img src="${contextPath}${product.imgUrl}" />
							<div class="cart-item-info">
								<div>
									<p class="product-title">${product.productName}</p>
									<p class="price">${product.basePrice}원</p>
								</div>

								<div>
									<c:forEach var="opt" items="${product.options}">
										<p>${opt.option}/ ${opt.quantity}개 (+${opt.optionPrice}원)</p>
										<button class="open-modal" data-cart="${opt.cartNum}"
											data-product="${product.productNum}">옵션 변경</button>
									</c:forEach>
								</div>

								<div>
									<p class="total">
										총 가격:
										<c:forEach var="opt" items="${product.options}"
											varStatus="loop">
                ${opt.totalPrice}<c:if test="${!loop.last}"> + </c:if>
										</c:forEach>
										원
									</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:forEach>

		</div>
	</div>
	<!-- 모달 배경 -->
	<div id="modalOverlay" class="modal-overlay" style="display: none;"></div>

	<!-- 모달 본체 -->
	<div id="optionModal" class="modal" style="display: none;">
		<div class="modal-content">
			<h3>옵션 변경</h3>
			<form id="optionChangeForm" method="post"
				action="${contextPath}/updateCartOption">
				<input type="hidden" name="cartNum" id="modalCartNum" /> <label>옵션
					선택 <select name="newOptionNum" id="modalOptionSelect"></select>
				</label>
				<div style="display: flex; gap: 10px; justify-content: flex-end;">
					<button type="submit">변경</button>
					<button type="button" onclick="closeModal()">닫기</button>
				</div>
			</form>
		</div>
	</div>

	<script src="${contextPath }/buyer/shoppingCart.js"></script>
</body>
</html>