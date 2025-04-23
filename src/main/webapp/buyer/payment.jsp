<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문/결제</title>
<link rel="stylesheet" href="${contextPath }/buyer/payment.css">
</head>
<body>
	<jsp:include page="/header/mainHeader.jsp" />
	<div class="container">
		<div class="sideMenu">
			<jsp:include page="/header/buyerMenu.jsp" />
		</div>
		<div id="content">
    <h1 class="title">주문/결제</h1>

    <c:if test="${empty paymentCartMap}">
        <p>선택된 상품이 없습니다.</p>
    </c:if>

    <c:if test="${not empty paymentCartMap}">
        <c:set var="totalSum" value="0" />
        <c:forEach var="storeEntry" items="${paymentCartMap}">
            <div class="store-group">
                <h2>${storeEntry.key}</h2>
                <hr class="hr">

                <c:forEach var="product" items="${storeEntry.value}">
                    <c:set var="productSum" value="0" />
                    <div class="cart-item">
                        <img src="${contextPath}${product.imgUrl}" />
                        <div class="cart-item-info">
                            <div class="info-1">
                                <p class="product-title">${product.productName}</p>
                                <p class="price">${product.basePrice}원</p>
                            </div>
                            <div class="info-2">
                                <div class="option-list">
                                    <c:forEach var="opt" items="${product.options}">
                                        <p>${opt.option} / ${opt.quantity}개 (+${opt.optionPrice}원)</p>
                                        <c:set var="productSum" value="${productSum + opt.totalPrice}" />
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="info-3">
                                <p class="total">${productSum}원</p>
                            </div>
                        </div>
                    </div>
                    <c:set var="totalSum" value="${totalSum + productSum}" />
                </c:forEach>
            </div>
        </c:forEach>

        <div class="all-total-div">
            <p class="all-total">총 결제 금액: ${totalSum}원</p>
            <form action="${contextPath}/completePayment" method="POST">
                <c:forEach var="storeEntry" items="${paymentCartMap}">
                    <c:forEach var="product" items="${storeEntry.value}">
                        <c:forEach var="opt" items="${product.options}">
                            <input type="hidden" name="cartNums" value="${opt.cartNum}" />
                        </c:forEach>
                    </c:forEach>
                </c:forEach>
                <button type="submit">결제하기</button>
            </form>
        </div>
    </c:if>
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

				<!-- 옵션 리스트 (기존 옵션들 수정/삭제) -->
				<div id="optionListArea"></div>

				<!-- 옵션 추가 -->
				<div style="margin-top: 20px;">
					<label>옵션 추가 </label> <select id="addOptionSelect"></select> <label>수량
					</label><input type="number" class="number-input" id="addOptionQuantity"
						min="1" value="1" />
					<button type="button" id="addOptionBtn">추가</button>
				</div>

				<div
					style="display: flex; gap: 10px; justify-content: flex-end; margin-top: 20px;">
					<div
						style="display: flex; gap: 10px; justify-content: flex-end; margin-top: 20px;">
						<input type="hidden" name="productNum" id="modalProductNum" /> <input
							type="hidden" id="hiddenCartNums" name="cartNums" value="">
						<input type="hidden" id="hiddenQuantities" name="hiddenQuantities"
							value=""> <input type="hidden" id="hiddenNewOptionNums"
							name="newOptionNums" value=""> <input type="hidden"
							id="hiddenNewQuantities" name="newQuantities" value=""> <input
							type="hidden" id="hiddenDeleteCartNums" name="deleteCartNums"
							value="">

						<button type="submit">저장</button>
						<button type="button" class="close" onclick="closeModal()">닫기</button>
					</div>

				</div>
			</form>
		</div>
	</div>

	<jsp:include page="/header/footer.jsp" />
	
</body>
</html>