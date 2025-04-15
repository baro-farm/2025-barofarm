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

            <div class="orderItem">

                <div class="orderTop">
                    <div class="orderStatus orderReady">배송 준비중</div>
                </div>
            
                <div class="orderCenter">
                    <div class="orderLeft">
                        <img src="#" alt="상품 이미지">
                    </div>
                    <div class="orderRight">
                        <div>
                            <span class="orderDate">3.08 06:33 주문</span>
                        </div>
                        <div class="productName">상품 이름</div>
                        <div class="productPrice">10,000 원</div>
                        <div class="orderDetail"><a href="#">상세보기></a></div>

                    </div>
                </div>
                
                <div class="orderBottom">
                    <button class="btn btnGreen">배송 상세</button>
                    <button class="btn btnRed">취소 신청</button>
                </div>
            
            </div>



            <div class="orderItem">

                <div class="orderTop">
                    <div class="orderStatus orderCancel">취소 완료</div>                </div>
            
                <div class="orderCenter">
                    <div class="orderLeft">
                        <img src="#" alt="상품 이미지">
                    </div>
                    <div class="orderRight">
                        <div>
                            <span class="orderDate">3.08 06:33 주문</span>
                        </div>
                        <div class="productName">상품 이름</div>
                        <div class="productPrice">10,000 원</div>
                        <div class="orderDetail"><a href="#">상세보기></a></div>

                    </div>
                </div>
                
                <div class="orderBottom">
                    <button class="btn btnRed">취소 정보</button>
                    <button class="btn">장바구니 담기</button>
                    <button class="btn">바로 구매하기</button>
                </div>
            
            </div>
            <div class="orderItem">

                <div class="orderTop">
                    <div class="orderStatus deliveryComplete">배송 완료</div>
                </div>
                <div class="orderCenter">
                    <div class="orderLeft">
                        <img src="#" alt="상품 이미지">
                    </div>
                    <div class="orderRight">
                        <div>
                            <span class="orderDate">3.08 06:33 주문</span>
                        </div>
                        <div class="productName">상품 이름</div>
                        <div class="productPrice">10,000 원</div>
                        <div class="orderDetail"><a href="#">상세보기></a></div>

                    </div>
                </div>
                
                <div class="orderBottom">
                    <button class="btn btnGreen">구매 확정</button>
                    <button class="btn btnRed">반품 신청</button>               
                </div>
            
            </div>
            <div class="orderItem">

                <div class="orderTop">
                    <div class="orderStatus orderConfirm">구매확정</div>
                </div>
                <div class="orderCenter">
                    <div class="orderLeft">
                        <img src="#" alt="상품 이미지">
                    </div>
                    <div class="orderRight">
                        <div>
                            <span class="orderDate">3.08 06:33 주문</span>
                        </div>
                        <div class="productName">상품 이름</div>
                        <div class="productPrice">10,000 원</div>
                        <div class="orderDetail"><a href="#">상세보기></a></div>
                    </div>
                </div>
                
                <div class="orderBottom">
                    <button class="btn">정보 보기</button>
                    <button class="btn">장바구니 담기</button>
                    <button class="btn">바로 구매하기</button>                </div>
            
            </div>
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