<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="${contextPath}/buyer/mypageMain.css">

</head>

<body>
	<jsp:include page="/header/mainHeader.jsp"/>

    <div class="container">
       	<div class="wrapper">
			<div class="sideMenu">
				<jsp:include page="/header/buyerMenu.jsp" />
			</div>
			<div class="content">
		        <!-- 제목 -->
		        <div class="header">마이페이지</div>
			
			
				<c:set var="summary" value="${myPageVo}" />
		
		        <!-- 요약 정보 -->
		        <div class="box summary">
		            <div><a href="prodOrderList">> 주문내역 <br><span>${summary.totalOrders }건</a></span></div>
		            <div><a href="packOrderList">> 꾸러미 <br><span>${summary.totalPackages }건</a></span></div>
		            <div>> 취소 내역 <br><span>${summary.totalCancels }건</span></div>
		            <div><a href="prodWrittenReviewList">> 리뷰내역 <br><span>${summary.totalReviews }건</span></a></div>
		            <div><a href="questionList">> 상품문의 <br><span>${summary.totalQuestions }건</span></a></div>
		        </div>
		        <!-- 배송 진행 상태 -->
		        <div class="box">
		            <div class="deliveryStatus">
		                <div>
		                    <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor"
		                        class="bi bi-check2-square" viewBox="0 0 16 16">
		                        <path
		                            d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5z" />
		                        <path
		                            d="m8.354 10.354 7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0" />
		                    </svg>
		                    <div>결제완료</div>
		                    <span>${summary.paymentComplete }</span>
		                </div>
		                <div>
		                    <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor"
		                        class="bi bi-box" viewBox="0 0 16 16">
		                        <path
		                            d="M8.186 1.113a.5.5 0 0 0-.372 0L1.846 3.5 8 5.961 14.154 3.5zM15 4.239l-6.5 2.6v7.922l6.5-2.6V4.24zM7.5 14.762V6.838L1 4.239v7.923zM7.443.184a1.5 1.5 0 0 1 1.114 0l7.129 2.852A.5.5 0 0 1 16 3.5v8.662a1 1 0 0 1-.629.928l-7.185 2.874a.5.5 0 0 1-.372 0L.63 13.09a1 1 0 0 1-.63-.928V3.5a.5.5 0 0 1 .314-.464z" />
		                    </svg>
		                    <div>배송준비</div>
		                    <span>${summary.deliveryReady }</span>
		                </div>
		                <div>
		                    <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor"
		                        class="bi bi-truck" viewBox="0 0 16 16">
		                        <path
		                            d="M0 3.5A1.5 1.5 0 0 1 1.5 2h9A1.5 1.5 0 0 1 12 3.5V5h1.02a1.5 1.5 0 0 1 1.17.563l1.481 1.85a1.5 1.5 0 0 1 .329.938V10.5a1.5 1.5 0 0 1-1.5 1.5H14a2 2 0 1 1-4 0H5a2 2 0 1 1-3.998-.085A1.5 1.5 0 0 1 0 10.5zm1.294 7.456A2 2 0 0 1 4.732 11h5.536a2 2 0 0 1 .732-.732V3.5a.5.5 0 0 0-.5-.5h-9a.5.5 0 0 0-.5.5v7a.5.5 0 0 0 .294.456M12 10a2 2 0 0 1 1.732 1h.768a.5.5 0 0 0 .5-.5V8.35a.5.5 0 0 0-.11-.312l-1.48-1.85A.5.5 0 0 0 13.02 6H12zm-9 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2m9 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2" />
		                    </svg>
		                    <div>배송중</div>
		                    <span>${summary.deliveryProgress }</span>
		                </div>
		                <div>
		                    <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor"
		                        class="bi bi-gift" viewBox="0 0 16 16">
		                        <path
		                            d="M3 2.5a2.5 2.5 0 0 1 5 0 2.5 2.5 0 0 1 5 0v.006c0 .07 0 .27-.038.494H15a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1v7.5a1.5 1.5 0 0 1-1.5 1.5h-11A1.5 1.5 0 0 1 1 14.5V7a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h2.038A3 3 0 0 1 3 2.506zm1.068.5H7v-.5a1.5 1.5 0 1 0-3 0c0 .085.002.274.045.43zM9 3h2.932l.023-.07c.043-.156.045-.345.045-.43a1.5 1.5 0 0 0-3 0zM1 4v2h6V4zm8 0v2h6V4zm5 3H9v8h4.5a.5.5 0 0 0 .5-.5zm-7 8V7H2v7.5a.5.5 0 0 0 .5.5z" />
		                    </svg>
		                    <div>배송완료</div>
		                    <span>${summary.deliveryComplete }</span>
		                </div>
		            </div>
		        </div>
		
		        <!-- 문의 및 리뷰 -->
		        <div class="contentSection">
		            <div class="contentBox">
		                <div class="title">
		                    내 상품 문의 내역
		                    <span class="add-btn"><a href="questionList">+</a></span>
		                </div>
		                <ul>
		                	<c:forEach var="pdQuestion" items="${prodQuestions }">
		                    <li><a href="#">${pdQuestion.title }</a></li>
							</c:forEach>
		                </ul>
		            </div>
		
		            <div class="contentBox">
		                <div class="title">
		                    내 리뷰 내역
		                    <span class="add-btn"><a href="prodWrittenReviewList">+</a></span>
		                </div>
		                <ul>
		                	<c:forEach var="pdQuestion" items="${prodReviews }">
		                    <li><a href="#">${pdQuestion.pdReviewContent}</a></li>
							</c:forEach>
		                </ul>
		            </div>
		        </div>
	        </div> <!-- end of content -->
		</div> <!-- end of wrapper -->
    </div><!-- end of container -->

</body>
</html>