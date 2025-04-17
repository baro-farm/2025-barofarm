<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문 내역</title>
    <link rel="stylesheet" href="${contextPath}/buyer/productOrderList.css">

</head>

<body>

    <div class="container">
        <h2>주문 내역</h2>

        <div class="searchBox">
            <label for="searchStartDate">조회 기간:</label>
            <input type="date" id="searchStartDate" name="searchStartDate">
            <span>~</span>
            <input type="date" id="searchEndDate" name="searchEndDate">
            <button type="button">검색</button>
        </div>

        <div class="orderList">

			<!-- 주문 내역 반복 -->
			<c:forEach var="prodOrder" items="${prodOrderList }">
	            <div class="orderItem">
	                <div class="orderTop">
	                </div>
	            
	                <div class="orderCenter">
	                    <div class="orderLeft">
	                    	<div class="orderStatus orderReady">${prodOrder.deleveryStatus } </div>
	                    
	                        <img src="${prodOrder.imgUrl }" alt="상품 이미지">
	                    </div>
	                    <div class="orderRight">
	                        <div>
	                            <span class="orderDate">${prodOrder.orderDate } 주문</span>
	                        </div>
	                        <div class="productName">${prodOrder.productName }</div>
	                        <div class="productPrice">${prodOrder.price } 원</div>
	                        <div class="orderDetail"><a href="${contextPath}/detailOrderInfo?pdOrderNum=${prodOrder.pdOrderNum}">상세보기></a></div>
	
	                    </div>
	                </div>
	                
	                <div class="orderBottom">
	                	<c:choose>
	                		<c:when test="${prodOrder.deleveryStatus eq '준비중' }">
			                    <button class="btn btnGreen">장바구니 담기</button>
			                    <button class="btn btnGreen">바로 구매하기</button>  
			                    <button class="btn btnRed">취소 신청</button>	                		
	                		</c:when>
	                		
	                		<c:when test="${prodOrder.deleveryStatus eq '취소신청' }">
			                    <button class="btn btnGreen">장바구니 담기</button>
			                    <button class="btn btnGreen">바로 구매하기</button>  	                		
	                		</c:when>
	                		
	                		<c:when test="${prodOrder.deleveryStatus eq '취소완료' }">
			                    <button class="btn btnRed">취소 정보</button>	                		
			                    <button class="btn btnGreen">장바구니 담기</button>
			                    <button class="btn btnGreen">바로 구매하기</button>  	                		
	                		</c:when>
	                		
	                		<c:when test="${prodOrder.deleveryStatus eq '배송완료' }">
			                    <button class="btn btnGreen">구매 확정</button>	                		
			                    <button class="btn btnGreen">장바구니 담기</button>
			                    <button class="btn btnGreen">바로 구매하기</button>  			                    	                		

	                		</c:when>
	                		
	                		<c:when test="${prodOrder.deleveryStatus eq '구매확정' }">
			                    <button class="btn btnGreen">장바구니 담기</button>
			                    <button class="btn btnGreen">바로 구매하기</button>  			                    	                		
	                		</c:when>	                			                		
	                	</c:choose>

	                </div>
	            </div>			
			</c:forEach>
			
        </div>

        <div class="pagination">
            <a href="#">◀</a>
            <a href="#" class="active">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">▶</a>
        </div>
    </div>

</body>

</html>