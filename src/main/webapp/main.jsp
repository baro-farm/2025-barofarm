<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="${contextPath}/main.css">
</head>
<body>
	<div class="container">
		<jsp:include page="./header/mainHeader.jsp" />
		<div class="content">
			<div class="sideMenu">
				<jsp:include page="./header/sideMenu.jsp" />
			</div>
			<!-- 배너  -->
			<div id="carouselExample" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
				<div class="carousel-inner">
		     		<div class="carousel-item active">
		        		<img src="${contextPath}/img/bener1.jpg" class="d-block w-100" alt="...">
		      		</div>
				    <div class="carousel-item">
				    	<img src="${contextPath}/img/bener2.jpg" class="d-block w-100" alt="...">
				    </div>
					<div class="carousel-item">
				        <img src="${contextPath}/img/bener3.jpg" class="d-block w-100" alt="...">
				     </div>
				    <div class="carousel-item">
				        <img src="${contextPath}/img/bener1.jpg" class="d-block w-100" alt="...">
				     </div>
				    <div class="carousel-item">
				        <img src="${contextPath}/img/bener2.jpg" class="d-block w-100" alt="...">
				     </div>
				</div>
		    	<button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
		      		<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		      		<span class="visually-hidden">Previous</span>
		    	</button>
		    	<button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
			      	<span class="carousel-control-next-icon" aria-hidden="true"></span>
			      	<span class="visually-hidden">Next</span>
		    	</button>
			</div>
			<!-- 상품 노출 -->
			<div class="productTable">
			    <!-- 판매량 베스트 TOP5 -->
			    <section class="bestProducts">
			    	<h2 class="listTitle">베스트 TOP5</h2>
			      	<div class="product-list">
			        	<div class="product">
				          	<div class="productImgBox">
				          		<a href=""><img src="${contextPath}/img/fruits1.jpg" alt=""></a>
				          	</div>
					        <p><a href="" class="productName">제품명/국내산/패키지asdasdaaaaaaaaaaaa</a></p>
					        <p><a href="" class="storeName">상추마루</a></p>
					        <p class="price">5,500원</p>
					        <p class="reviewScore">⭐ 4.5 (212)</p>
						</div>
						<div class="product">
					    	<div class="productImgBox">
					        	<a href=""><img src="${contextPath}/img/fruits2.jpg" alt=""></a>
					        </div>
				          	<p><a href="" class="productName">제품명/국내산/패키지</a></p>
				          	<p><a href="" class="storeName">상추마루</a></p>
				          	<p class="price">5,500원</p>
				          	<p class="reviewScore">⭐ 4.5 (212)</p>
				        </div>
				        <div class="product">
					    	<div class="productImgBox">
					        	<a href=""><img src="${contextPath}/img/fruits3.jpg" alt=""></a>
					        </div>
				          	<p><a href="" class="productName">제품명/국내산/패키지</a></p>
				          	<p><a href="" class="storeName">상추마루</a></p>
				          	<p class="price">5,500원</p>
				          	<p class="reviewScore">⭐ 4.5 (212)</p>
				        </div>
				        <div class="product">
					    	<div class="productImgBox">
					        	<a href=""><img src="${contextPath}/img/fruits2.jpg" alt=""></a>
					        </div>
				          	<p><a href="" class="productName">제품명/국내산/패키지</a></p>
				          	<p><a href="" class="storeName">상추마루</a></p>
				          	<p class="price">5,500원</p>
				          	<p class="reviewScore">⭐ 4.5 (212)</p>
				        </div>
				        <div class="product">
					    	<div class="productImgBox">
					        	<a href=""><img src="${contextPath}/img/fruits1.jpg" alt=""></a>
					        </div>
				          	<p><a href="" class="productName">제품명/국내산/패키지</a></p>
				          	<p><a href="" class="storeName">상추마루</a></p>
				          	<p class="price">5,500원</p>
				          	<p class="reviewScore">⭐ 4.5 (212)</p>
				        </div>
		      		</div>
		      		<button class="viewMore" >상품 더보기</button>
		    	</section>
		    	<hr>
		    	<!-- 신제품 TOP5 -->
		    	<section class="newProducts">
		      		<h2 class="listTitle">신제품 TOP5</h2>
		      		<div class="product-list">
			      		<div class="product">
					          	<div class="productImgBox">
					          		<a href=""><img src="${contextPath}/img/fruits1.jpg" alt=""></a>
					          	</div>
						        <p><a href="" class="productName">제품명/국내산/패키지asdasdaaaaaaaaaaaa</a></p>
						        <p><a href="" class="storeName">상추마루</a></p>
						        <p class="price">5,500원</p>
						        <p class="reviewScore">⭐ 4.5 (212)</p>
							</div>
							<div class="product">
						    	<div class="productImgBox">
						        	<a href=""><img src="${contextPath}/img/fruits2.jpg" alt=""></a>
						        </div>
					          	<p><a href="" class="productName">제품명/국내산/패키지</a></p>
					          	<p><a href="" class="storeName">상추마루</a></p>
					          	<p class="price">5,500원</p>
					          	<p class="reviewScore">⭐ 4.5 (212)</p>
					        </div>
					        <div class="product">
						    	<div class="productImgBox">
						        	<a href=""><img src="${contextPath}/img/fruits3.jpg" alt=""></a>
						        </div>
					          	<p><a href="" class="productName">제품명/국내산/패키지</a></p>
					          	<p><a href="" class="storeName">상추마루</a></p>
					          	<p class="price">5,500원</p>
					          	<p class="reviewScore">⭐ 4.5 (212)</p>
					        </div>
					        <div class="product">
						    	<div class="productImgBox">
						        	<a href=""><img src="${contextPath}/img/fruits2.jpg" alt=""></a>
						        </div>
					          	<p><a href="" class="productName">제품명/국내산/패키지</a></p>
					          	<p><a href="" class="storeName">상추마루</a></p>
					          	<p class="price">5,500원</p>
					          	<p class="reviewScore">⭐ 4.5 (212)</p>
					        </div>
					        <div class="product">
						    	<div class="productImgBox">
						        	<a href=""><img src="${contextPath}/img/fruits1.jpg" alt=""></a>
						        </div>
					          	<p><a href="" class="productName">제품명/국내산/패키지</a></p>
					          	<p><a href="" class="storeName">상추마루</a></p>
					          	<p class="price">5,500원</p>
					          	<p class="reviewScore">⭐ 4.5 (212)</p>
					        </div>
			      		</div>
		      			<button class="viewMore">상품 더보기</button>
		    	</section>
		  </div>
			<hr>
		  	<!-- 공지사항 및 문의하기 -->
			<div class="infoSection">
				<div class="notice">
					<div class="noticeHeader">
				        <h3>공지사항</h3>
				        <a href="#">+</a>
					</div>
					<ul>
				    	<li><a href="#">업무처리 매뉴얼</a><span>2019-12-29</span></li>
				        <li><a href="#">고객센터 운영시간 변경 안내</a><span>2019-12-29</span></li>
				        <li><a href="#">불량 야채 사진 변경 안내</a><span>2019-12-29</span></li>
				    </ul>
			    </div>
		    	<div class="faq">
			    	<div class="faqHeader">
				        <h3>문의하기</h3>
				        <a href="#">+</a>
			      	</div>
		      		<ul>
				        <li><a href="#">[배송] 주문한 상품은 언제 배송되나요?</a><span>2019-12-29</span></li>
				        <li><a href="#">[교환/환불] 상품을 교환하고 싶어요.</a><span>2019-12-29</span></li>
				        <li><a href="#">[배송] 배송 상태가 보이지 않아요.</a><span>2019-12-29</span></li>
		      		</ul>
		    	</div>
			</div>
		</div>
	<jsp:include page="./header/footer.jsp" />
	</div><!-- 전체 컨테이너  -->
</body>
</html>