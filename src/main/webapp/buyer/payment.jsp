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
<script src="https://cdn.portone.io/v2/browser-sdk.js"></script>
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
            
                <c:forEach var="storeEntry" items="${paymentCartMap}">
                    <c:forEach var="product" items="${storeEntry.value}">
                        <c:forEach var="opt" items="${product.options}">
                            <input type="hidden" name="cartNums" value="${opt.cartNum}" />
                        </c:forEach>
                    </c:forEach>
                </c:forEach>
                <button type="button" id="payment">결제하기</button>
            
        </div>
    </c:if>
</div>
		
	</div>

	<jsp:include page="/header/footer.jsp" />
	<script src="${contextPath }/buyer/payment.js"></script>
	
</body>
</html>