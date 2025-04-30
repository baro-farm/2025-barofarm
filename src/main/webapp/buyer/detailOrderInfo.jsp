<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문 상세 정보</title>
    <link rel="stylesheet" href="${contextPath}/buyer/detailOrderInfo.css">


</head>
<body>

<div class="orderDetailContainer">
    <!-- 주문 정보 -->
    <div class="orderBox">
        <div class="orderHeader">
            <span id="orderDate">${prodOrderList[0].orderDate } 주문</span>
            <button class="btnGreen">영수증</button>
        </div>
        <div>
            <span class="orderNumber">주문 번호</span> 
            <span id="orderNumber">${prodOrderList[0].pdOrderNum}</span>
        </div>
    </div>

    <!-- 주문 상품 -->
    <div class="sectionTitle">주문 상품</div>
    <!-- 여러개일때 반복 -->
    <div class="orderBox">
    	<c:forEach var="prodOrder" items="${prodOrderList }">
    	
	        <div class="orderItem">
	            <img src="${contextPath}${prodOrder.imgUrl }" alt="상품 이미지" class="productImage">
	            <div class="productInfo">
	                <div id="productName">${prodOrder.productName }</div>
	                <div id="productAmount">수량: ${prodOrder.amount} 개</div>
	                <c:set var="total" value="${prodOrder.price }"/>
	                <div>${total } 원</div>
	            </div>
	            <button class="btnSmall">문의하기</button>
	        </div>
	        <button class="btnSmall">배송 조회</button>
	        <button class="btnSmall">재구매</button>
        </c:forEach>
    </div>

    <!-- 배송지 정보 -->
    <div class="sectionTitle">배송지</div>
    <div class="addressBox">
        <div id="receiverName">${prodOrder.rname }</div>
        <div id="receiverPhone">${prodOrder.rphone }</div>
        <div id="receiverAddress">${prodOrder.address }</div>
    </div>

    <!-- 결제 정보 -->
    <div class="sectionTitle">결제정보</div>
    <div class="paymentBox">
        <div>
            <span class="totalPrice">주문금액</span>
            <span class="totalPrice">총 <span id="totalPrice">${prodOrderList[0].pdTotalPrice }</span> 원</span>
        </div>
        <div>상품금액: <span id="productPrice">${prodOrderList[0].pdTotalPrice }</span> 원</div>
    </div>
</div>

</body>
</html>