<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title><!-- 
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script> -->
	<link rel="stylesheet" href="${contextPath}/reset.css">
	<link rel="stylesheet" href="${contextPath}/main.css">
	<link rel="stylesheet" href="${contextPath}/bootstrap-4.0/carousel.css">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="${contextPath}/bootstrap-4.0/carousel.min.js"></script>
</head>
<body>
	<div class="container">
		<jsp:include page="/header/mainHeader.jsp" />
		<!-- 배너  -->
		<div id="carouselExample" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
				<div class="carousel-inner">
   				    <c:if test="${empty bannerList}">
					    <div class="carousel-item active">
					    	<img src="${contextPath}/img/bener3.jpg" class="d-block w-100" alt="...">
					    </div>
					    <div class="carousel-item">
					    	<img src="${contextPath}/img/bener2.jpg" class="d-block w-100" alt="...">
					    </div>
				    </c:if>
				    <c:if test="${not empty bannerList}">
     				    <c:forEach var="banner" items="${bannerList}" varStatus="status">
			                <div class="carousel-item ${status.first ? 'active' : ''}">
			                    <a href="${banner.targetUrl}">
			                        <img src="${contextPath}/kockImg?imgUrl=${banner.imgUrl}" class="d-block w-100" alt="...">
			                    </a>
			                </div>
			            </c:forEach>
				     </c:if>
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
		<div class="wrapper">
			<jsp:include page="/header/sideMenu.jsp" />
			<!-- 상품 노출 -->
			<div class="content">
			<div class="productTable">
			    <!-- 판매량 베스트 TOP5 -->
			    <section class="bestProducts">
			    	<h2 class="listTitle">BEST</h2>
			      	<div class="product-list">
			      		<c:forEach var="p" items="${bestProducts}">
				        	<div class="product">
					          	<div class="productImgBox">
					          		<a href="${contextPath}/detailProduct?productNum=${p.productNum}">
						          		<c:if test="${empty p.imgUrl}">
							    			<img src="${contextPath}/img/fruits1.jpg" alt="">
						    			</c:if>
						    			<c:if test="${not empty p.imgUrl}">
				                    		<img src="${contextPath}/${p.imgUrl}" alt="">
				                    	</c:if>
					          		</a>
					          	</div>
					          	<div class="nameBox">
							        <p><a href="${contextPath}/detailProduct?productNum=${p.productNum}" class="productName">${p.productName}</a></p>
							        <p><a href="${contextPath}/detailProduct?productNum=${p.productNum}" class="storeName">${p.storeName}</a></p>
							        <p class="price"><fmt:formatNumber value="${p.price}" type="number" />원</p>
							        <p class="reviewScore">
							        	<i class="bi bi-star-fill" style="color: #FFB534;"></i> 
							        	<fmt:formatNumber value="${p.avgRating}" type="number" maxFractionDigits="1" />
							        	(${p.reviewCount})
							        </p>
						        </div>
							</div>
						</c:forEach>
		      		</div>
		      		<a href="bestProductList" class="viewMore" >상품 더보기</a>
		    	</section>
		    	<hr>
		    	<!-- 신제품 TOP5 -->
		    	<section class="newProducts">
		      		<h2 class="listTitle">NEW</h2>
		      		<div class="product-list">
		      			<c:forEach var="p" items="${newProducts}">
			      			<div class="product">
			      				
					          	<div class="productImgBox">
					          		<a href="${contextPath}/detailProduct?productNum=${p.productNum}">
						          		<c:if test="${empty p.imgUrl}">
							    			<img src="${contextPath}/img/fruits2.jpg" alt="">
						    			</c:if>
						    			<c:if test="${not empty p.imgUrl}">
				                    		<img src="${contextPath}${p.imgUrl}" alt="">
				                    	</c:if>
					          		</a>
					          	</div>
					          	<div class="nameBox">
							        <p><a href="${contextPath}/detailProduct?productNum=${p.productNum}" class="productName">${p.productName}</a></p>
							        <p><a href="${contextPath}/detailProduct?productNum=${p.productNum}" class="storeName">${p.storeName}</a></p>
							        <p class="price"><fmt:formatNumber value="${p.price}" type="number" />원</p>
							        <p class="reviewScore">
						        	<i class="bi bi-star-fill" style="color: #FFB534;"></i>
						        	<fmt:formatNumber value="${p.avgRating}" type="number" maxFractionDigits="1" /> 
						        	(${p.reviewCount})
						        </p>
						        </div>
							</div>
						</c:forEach>
			      	</div>
		      		<a href="newProductList" class="viewMore">상품 더보기</a>
		    	</section>
		  </div>
			<hr>
		  	<!-- 공지사항 및 문의하기 -->
			<div class="infoSection">
				<div class="notice">
					<div class="noticeHeader">
				        <h3>공지사항</h3>
				        <a href="userNoticeList" class="plus">+</a>
					</div>
					<ul class="recentNotice">
					  <c:forEach var="n" items="${noticeList}">
					    <li>
					      <a href="detailNotice?noticeNum=${n.noticeNum}">${n.title}</a>
					      <span>${n.createdAt}</span>
					    </li>
					  </c:forEach>
					  </ul>
			    </div>
		    	<div class="faq">
			    	<div class="faqHeader">
				        <h3>문의하기</h3>
				        <a href="adminQAList" class="plus">+</a>
			      	</div>
		      		<ul class="recentAdminQA">
					  <c:forEach var="q" items="${adminQA}">
					    <li>
					      <a href="detailAdminQA?questionNum=${q.questionNum}">${q.title}</a>
					      <span>${q.createdAt}</span>
					    </li>
					  </c:forEach>
					  </ul>
		    	</div>
			</div>
			</div>
		</div>
		<jsp:include page="/header/footer.jsp" />
	</div><!-- 전체 컨테이너  -->
	<script>
	  $(function() {
	    $('.carousel').carousel({
	      interval: 4000
	    });
	  });
	</script>
</body>
</html>