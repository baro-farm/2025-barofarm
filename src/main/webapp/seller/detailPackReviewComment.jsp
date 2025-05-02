<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <title>리뷰 상세보기</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="${contextPath }/seller/detailReviewComment.css">
    <script>
    	$(document).ready(function() {
    	
    		$("#sellerReply").on("input", function () {
				  const currentLength = $(this).val().length;
				  $("#replyCounter").text(currentLength + " / 1000");
			});
    	});
        				
    </script>

            <span class="close">&times;</span>
            <div class="title"><h3>꾸러미 리뷰 상세보기</h3></div>
            <p><strong>리뷰 글번호:</strong> <span id="reviewNum" name="reviewNum">${review.pkReviewNum}</span></p>

            <div class="reviewComment">
                <div class="reviewLeft">
					<c:if test="${not empty review.pkReviewImgUrl}">
					        <img id="reviewImage" name="reviewImage" src="${contextPath}${review.pkReviewImgUrl}" alt="상품 이미지">
					</c:if>                </div>
                <div class="reviewRight">
                    <p>
                        <strong>상품주문번호: </strong> 
                        <a id="orderNum" name="orderNum" href="#">${review.pkOrderNum }</a>
                    </p>
                    <p>
                        <strong>상품명:</strong> 
                        <a id="productName" name="productName" href="#">${review.packageName }</a>
                    </p>
                    <p id="reviewContent" name="reviewContent">
                       <strong>리뷰 내용:</strong>
                        ${review.pkReviewContent }
                    </p>
                    <p>
                        		<c:forEach var="i" begin="1" end="5">
									<c:choose>
										<c:when test="${i <= review.pkRating}">★</c:when>
										<c:otherwise>☆</c:otherwise>
									</c:choose>
								</c:forEach> 
                        <span id="reviewRating" name="reviewRating">${review.pkRating }</span> | 
                        <span id="reviewUser" name="reviewUser">${review.userId }</span> | 
                        <span id="reviewDate" name="reviewDate">${review.createdAt }</span>
                    </p>
                </div>
            </div>

            <div class="sellerReply">
                <h3>판매자 답글</h3>
                <textarea id="sellerReply" name="sellerReply" placeholder="정성스러운 답글을 남겨주세요.">${review.pkComment}</textarea>
                <p id="replyCounter">0 / 1000 (최소 5자)</p>
                <button id="replySubmit" name="replySubmit">답글 등록</button>
            </div>
            <!-- 
            <div class="reviewNavigation">
                <button id="prevReview">⬅ 이전 리뷰</button>
                <span id="reviewPage" name="reviewPage">1 / 24</span>
                <button id="nextReview">다음 리뷰 ➡</button>
            </div>
			 -->

            <p class="notice">
                상품 구매 후 작성한 리뷰가 정상적인 리뷰입니다.<br>
                허위 리뷰 작성 시 추후 이용이 제한될 수 있습니다.
            </p>

            <button id="closeBtn" class="closeBtn">닫기</button>

    <script>
        // 주문번호 동적 설정
        const urlParams = new URLSearchParams(window.location.search);
        const reviewNum = urlParams.get("reviewNum");
        const orderNum = urlParams.get("orderNum")
        if (reviewNum) {
            document.getElementById("reviewNum").textContent = reviewNum;
        }

        if (orderNum) {
            document.getElementById("orderNum").textContent = orderNum;
        }
    </script>
