<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꾸러미 상세 페이지</title>
	<!-- 
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	 -->
	<link rel="stylesheet" href="${contextPath}/reset.css" />
    <link rel="stylesheet" href="${contextPath}/detailPackage.css" /> 
    <script defer src="detailProduct.js"></script>
</head>
<body>
	<div class="container">
		<jsp:include page="/header/mainHeader.jsp" />
		<div class="wrapper">
			<jsp:include page="/header/sideMenu.jsp" />
			<div class="content">
				<!-- 상품정보 -->
			    <div class="packInfoBox">
			            <!-- 이미지 -->
			            <div class="packImg">
			                <img src="${contextPath}${pack.imgUrl}" alt="상품 이미지">
			            </div>
			            <div class="packInfo">
			                <!-- 스토어상품정보 -->
			                <div class="storeName"><a href="${contextPath}/storeProductList?sellerNum=${pack.sellerNum}">${pack.storeName} &gt;</a></div>
			                <div class="packTitle">${pack.packageName}</div>
			                <div class="additional">
			                    <div class="reviewScore">⭐ ${pack.avgRating} (${pack.reviewCount})</div>
			                </div>

			                 <!-- 총 가격 -->
			                <div class="totalPrice"><fmt:formatNumber value="${pack.packagePrice }" type="number" />원</div>
			                
			                <!-- 버튼 영역 -->
			                <div class="actionButtons">
			                    <a href="#" id="purchase" data-package-num="${pack.packageNum}">구매하기</a>
			                </div>
			            </div>
			        </div>

			        <!-- 상세정보 -->
			        <div class="tabs" id="details">
			            <ul class="tabList">
			                <li class="tab focus"><a href="#details">상세정보</a></li>
			                <li class="tab"><a href="#reviews">리뷰 보기(${pack.reviewCount})</a></li>
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
			                <li class="tab focus"><a href="#reviews">리뷰 보기(${pack.reviewCount})</a></li>
			                <li class="tab"><a href="#info">안내사항</a></li>
			              </ul>
			        </div>
					<div id="review" class="review">
						<jsp:include page="/detailPackReviewSection.jsp" />
					</div>
			        <!-- 안내사항 -->
			        <div class="tabs" id="info">
			            <ul class="tabList">
			                <li class="tab"><a href="#details">상세정보</a></li>
			                <li class="tab"><a href="#reviews">리뷰 보기(${pack.reviewCount})</a></li>
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
			  initialValue: `${pack.content}`
			});

		    $(document).on("click", "a.reviewPageLink", function(e){
		        e.preventDefault();
		       const href = $(e.currentTarget).attr("href");
		        const absoluteUrl = new URL(href, window.location.origin);  // ← 반드시 절대경로로
		        const params = new URLSearchParams(absoluteUrl.search);

		        const packageNum = params.get("packageNum");
		        const page = params.get("reviewPage");
				
		        const scrollPos = $(window).scrollTop();
		         console.log("요청 URL:", absoluteUrl.toString(), ", packageNum:", packageNum, ", reviewPage:", page);

		        $.ajax({
		            url: absoluteUrl.pathname,
		            type: "get",
		            headers: { "X-Requested-With": "XMLHttpRequest" },  
		            data: { packageNum: packageNum, page: page, type: 'review' },
		            success: function(data){
		                $("#review").html(data);
		                $(window).scrollTop(scrollPos);
		            },
		            error: function(xhr, status, error){
		                console.log("Ajax 오류:", status, error);
		                console.log("서버 응답:", xhr.responseText);
		            }
		        });
		    });
		});
	</script>
    <script src="${contextPath}/detailPackage.js"></script>
</body>
</html>