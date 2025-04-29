<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 페이지</title>
	<!-- 
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	 -->
	<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.min.css">
	<link rel="stylesheet" href="${contextPath}/reset.css" />
    <link rel="stylesheet" href="${contextPath}/detailProduct.css" /> 
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script defer src="detailProduct.js"></script>
</head>
<body>
	<div class="container">
		<jsp:include page="/header/mainHeader.jsp" />
		<div class="wrapper">
			<jsp:include page="/header/sideMenu.jsp" />
			<div class="content">
				<!-- 상품정보 -->
			    <div class="prodInfoBox">
			            <!-- 이미지 -->
			            <div class="prodImg">
			                <img src="${contextPath}${product.imgUrl}" alt="상품 이미지">
			            </div>
			            <div class="prodInfo">
			                <!-- 스토어상품정보 -->
			                <div class="storeName"><a href="${contextPath}/storeProductList?sellerNum=${product.sellerNum}">${product.storeName} &gt;</a></div>
			                <div class="prodTitle">${product.productName}</div>
			                <div class="additional">
			                    <div class="reviewScore">⭐ ${product.avgRating} (${product.reviewCount})</div>
			                    <div class="price"><fmt:formatNumber value="${product.price }" type="number" />원</div>
			                </div>
			          
			                <!-- 주문옵션전체영역 -->
			                <div class="orderOption">
			                    <select id="optionSelect" class="selectBox">
			                    	<option value="">옵션 선택</option>
			                        <c:forEach var="o" items="${option}">
			                        	<option value="${o.option}|${o.price}">${o.option} - <fmt:formatNumber value="${o.price}" type="number" />원</option>
			                        </c:forEach>
			                    </select>
			                    <div id="selectedOptions"></div>
			                </div>
			
			                 <!-- 총 가격 -->
			                <div class="totalPrice"><span id="totalPrice">0</span>원</div>
			                
			                <!-- 버튼 영역 -->
			                <div class="actionButtons">
			                    <a href="" id="basket">장바구니</a>
			                    <a href="#" id="purchase">구매하기</a>
			                </div>
			            </div>
			        </div>

			        <!-- 상세정보 -->
			        <div class="tabs" id="details">
			            <ul class="tabList">
			                <li class="tab focus"><a href="#details">상세정보</a></li>
			                <li class="tab"><a href="#reviews">리뷰 보기(${product.reviewCount})</a></li>
			                <li class="tab"><a href="#qna">문의(11)</a></li>
			                <li class="tab"><a href="#info">안내사항</a></li>
			              </ul>
			        </div>
			        <div class="contentDetail">
			            <div id="viewer"></div>
			        </div>
			        <!-- 리뷰 -->
			        <div class="tabs" id="reviews">
			            <ul class="tabList">
			                <li class="tab"><a href="#details">상세정보</a></li>
			                <li class="tab focus"><a href="#reviews">리뷰 보기(${product.reviewCount})</a></li>
			                <li class="tab"><a href="#qna">문의(11)</a></li>
			                <li class="tab"><a href="#info">안내사항</a></li>
			              </ul>
			        </div>
					<div id="review" class="review">
						<jsp:include page="/detailReviewSection.jsp" />
					</div>
			        <!-- 문의 -->
			        <div class="tabs" id="qna">
			            <ul class="tabList">
			                <li class="tab"><a href="#details">상세정보</a></li>
			                <li class="tab"><a href="#reviews">리뷰 보기(${product.reviewCount})</a></li>
			                <li class="tab focus"><a href="#qna">문의(11)</a></li>
			                <li class="tab"><a href="#info">안내사항</a></li>
			              </ul>
			        </div>
					<div id="prodQA" class="prodQA">
						<jsp:include page="/detailQASection.jsp" />
					</div>
			        <!-- 안내사항 -->
			        <div class="tabs" id="info">
			            <ul class="tabList">
			                <li class="tab"><a href="#details">상세정보</a></li>
			                <li class="tab"><a href="#reviews">리뷰 보기(${product.reviewCount})</a></li>
			                <li class="tab"><a href="#qna">문의(11)</a></li>
			                <li class="tab focus"><a href="#info">안내사항</a></li>
			              </ul>
			        </div>
			        <div class="infoContent">
			            <div>
			                <p>📌 결제안내</p>
			                <p>전화 주문은 받지 않습니다.</p>
			                <p>온라인 홈페이지를 통해 주문 하시기를 부탁드립니다.</p>
			            </div>
			            <div>
			                <p>📦 배송안내</p>
			                <p>바로팜은은 생산자가 산지에서 직접 포장, 일반 택배사를 이용해 발송합니다.</p>
			                <p>품목마다 택배사, 발송 요일이 다릅니다. 상세페이지 뒷부분 배송 안내를 참고하세요.</p>
			                <p>주문요청 항목은 메모, 배송이 지연될 수 있으니 참고하세요.</p>
			                <p>도서산간 지역은 배송비가 추가됩니다.</p>
			                <p>포장과 상자는 생산자의 상황에 따라 달라질 수 있습니다.</p>
			                <p>물품 사진은 생산자가가 직접 찍은 사진입니다. 투박하지만 정직과 정성을 담았습니다.</p>
			                <p>물품에 대해 궁금하신 점은 중앙사무국(02-582-1416)으로 전화주세요.</p>
			            </div>
			        </div>
			    </div>
    	</div>
    	<jsp:include page="/header/footer.jsp" />
    </div>

	<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
	<script>
	const viewer = toastui.Editor.factory({
		  el: document.querySelector('#viewer'),
		  viewer: true,
		  initialValue: `${product.content}`
		});
	</script>
</body>
</html>