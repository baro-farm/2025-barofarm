<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
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
			<div class="all-select">
			<input type="checkbox" id="selectAll">
  <label>
     전체 선택
  </label>
</div>
			
			<c:forEach var="storeEntry" items="${cartMap}">
				<div class="store-group">
						<h2 class="store_name">
							<label> <input type="checkbox" class="store-checkbox"
								data-store="${storeEntry.key }" /></label>${storeEntry.key}
							<svg width="18" height="18" viewBox="0 0 18 18" fill="none"
								xmlns="http://www.w3.org/2000/svg">
<path
									d="M6.75 16.5V9H11.25V16.5M2.25 6.75L9 1.5L15.75 6.75V15C15.75 15.3978 15.592 15.7794 15.3107 16.0607C15.0294 16.342 14.6478 16.5 14.25 16.5H3.75C3.35218 16.5 2.97064 16.342 2.68934 16.0607C2.40804 15.7794 2.25 15.3978 2.25 15V6.75Z"
									stroke="#1E1E1E" stroke-width="2" stroke-linecap="round"
									stroke-linejoin="round" />
</svg>
						</h2>
					
				<div class="wrapper">
					<c:forEach var="product" items="${storeEntry.value}">
						<c:set var="sum" value="0" />
						<c:forEach var="opt" items="${product.options}">
							<c:set var="sum" value="${sum + opt.totalPrice}" />
						</c:forEach>
							
						<div class="cart-item">
							<input type="checkbox" class="product-checkbox"
								data-total-price="${sum}" data-cart-nums="<c:forEach var='opt' items='${product.options}' varStatus='status'>${opt.cartNum}<c:if test='${!status.last}'>,</c:if></c:forEach>">
							<img src="${contextPath}${product.imgUrl}" />
							<div class="cart-item-info">
								<div class="info-1">
									<p class="product-title">${product.productName}</p>
									<p class="price"><fmt:formatNumber value="${product.basePrice}" type="number" />원</p>
									
								</div>
								<div class="info-2">
									<div class="option-list">
										<c:forEach var="opt" items="${product.options}">
										    <p>
										        ${opt.option} / ${opt.quantity}개
										        <c:choose>
										            <c:when test="${opt.optionPrice > 0}">
										                (+<fmt:formatNumber value="${opt.optionPrice}" type="number" />원)
										            </c:when>
										            <c:when test="${opt.optionPrice < 0}">
										                (<fmt:formatNumber value="${opt.optionPrice}" type="number" />원)
										            </c:when>
										        </c:choose>
										    </p>
										</c:forEach>
									</div>
									<button class="open-modal" data-cart="${opt.cartNum}"
										data-product="${product.productNum}"
										data-quantity="${opt.quantity}">주문수정</button>
								</div>
								<div class="info-3">
									<p class="total"><fmt:formatNumber value="${sum}" type="number" />원</p>
								</div>
							</div>
						</div>
					</c:forEach>
					<div class="store-total-div">
						<p class="store-total">선택된 상품 0건 / 총합 0원</p>
						<button class="store-order-btn">${storeEntry.key } 주문하기</button>
					</div>
				</div>
			</div>

			</c:forEach>
			<div class="all-total-div">
				<p class="all-total">전체 선택된 상품 0건 / 총합 0원</p>
				<button id="all-order-btn">전체 주문하기</button>
			</div>

		</div>
	</div>
	<!-- 모달 배경 -->
	<div id="modalOverlay" class="modal-overlay" style="display: none;"></div>

	<!-- 모달 본체 -->
	<div id="optionModal" class="modal" style="display: none;">
		<div class="modal-content">
			<h3>옵션 / 수량 변경</h3>
			<form id="optionChangeForm" method="POST"
				action="${contextPath}/updateCartOptions">
				<input type="hidden" name="productNum" id="modalProductNum" />
				<table id="optionTable" class="option-table">
				  <thead>
				    <tr>
				      <th>옵션명</th>
				      <th>수량</th>
				      <th>삭제</th>
				    </tr>
				  </thead>
				  <tbody id="optionListArea">
				    <!-- 여기에 JS로 row 추가 -->
				  </tbody>
				</table>

				<!-- 옵션 추가 -->
				<div style="margin-top: 20px;" id="optionAdd">
					<label style="font-size: 16px;">옵션 추가</label>
					<table class="option-add-table">
					  <thead>
					    <tr>
					      <th>옵션명</th>
					      <th>수량</th>
					      <th>추가</th>
					    </tr>
					  </thead>
					  <tbody>
					    <tr>
					      <td><select id="addOptionSelect"></select></td>
					      <td><input type="number" class="number-input" id="addOptionQuantity" min="1" value="1" /></td>
					      <td><button type="button" id="addOptionBtn">추가</button></td>
					    </tr>
					  </tbody>
					</table>
				</div>
				<div style="width: 100%;display: flex; gap: 10px; justify-content: center; margin-top: 20px;">
					<div style="display: flex; gap: 10px; justify-content: flex-end; margin-top: 20px;">
						<input type="hidden" name="productNum" id="modalProductNum" />
						<input type="hidden" id="hiddenCartNums" name="cartNums" value="">
						<input type="hidden" id="hiddenQuantities" name="hiddenQuantities" value=""> 
						<input type="hidden" id="hiddenNewOptionNums"
							name="newOptionNums" value=""> 
						<input type="hidden" id="hiddenNewQuantities" name="newQuantities" value="">
						<input type="hidden" id="hiddenDeleteCartNums" name="deleteCartNums" value="">
						<button type="button" class="close modalBtn" onclick="closeModal()">닫기</button>
						<button type="submit" class="modalBtn">저장</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="/header/footer.jsp" />
	<script src="${contextPath }/buyer/shoppingCart.js"></script>
</body>
</html>