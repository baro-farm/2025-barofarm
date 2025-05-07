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
	                <div id="storeName">${prodOrder.storeName }<svg width="15" height="15" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
<path d="M6.75 16.5V9H11.25V16.5M2.25 6.75L9 1.5L15.75 6.75V15C15.75 15.3978 15.592 15.7794 15.3107 16.0607C15.0294 16.342 14.6478 16.5 14.25 16.5H3.75C3.35218 16.5 2.97064 16.342 2.68934 16.0607C2.40804 15.7794 2.25 15.3978 2.25 15V6.75Z" stroke="#1E1E1E" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></path></svg></div>
	                <div id="productName">${prodOrder.productName }</div>
	                <div id="productAmount">수량: ${prodOrder.amount} 개</div>
	                <c:set var="total" value="${prodOrder.price }"/>
	                <div>${total } 원</div>
	            </div>

	        </div>

        </c:forEach>
    </div>

    <!-- 배송지 정보 -->
    <div class="sectionTitle">배송지</div>
    <div class="addressBox">
        <div id="receiverName"><b>수령인:&nbsp;</b>${prodOrderList[0].rname }</div>
        <div id="receiverAddress"><b>수령지:&nbsp;</b>${prodOrderList[0].address }</div>
        <div id="receiverPhone"><b>전화번호:&nbsp;</b>${prodOrderList[0].rphone }</div>
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