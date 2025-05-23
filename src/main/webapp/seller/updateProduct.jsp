<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 | 일반 상품 수정</title>
<link rel="stylesheet" href="${contextPath}/reset.css" />
<link rel="stylesheet" href="${contextPath}/seller/updateProduct.css" />
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<jsp:include page="/header/sellerHeader.jsp" />
	<header id="header">
			<jsp:include page="/header/adminSellerTop.jsp" />
    </header>
	<div id="content">
		<span id="title">상품 수정</span>
		<!-- 상품명,가격,재고, 카테고리,옵션,배송비,상품이미지 -->
		<form action="${contextPath }/updateProduct" id="product_form"
			method="POST" enctype="multipart/form-data">
			<div class="input_div">
				<label for="product_name">상품명</label> <input type="text"
					id="product_name" name="product_name"
					value="${product.productName}" />
			</div>
			<div class="input_div">
				<label for="product_price">가격</label> <input type="number"
					id="product_price" name="product_price" value="${product.price }" />
			</div>
			<div class="input_div">
				<label for="product_category">카테고리</label> <select
					name="product_category" id="product_category">
					<option value="1" ${product.cateNum == 1 ? 'selected' : ''}>배추/무/대파/부추</option>
					<option value="2" ${product.cateNum == 2 ? 'selected' : ''}>오이/호박/가지</option>
					<option value="3" ${product.cateNum == 3 ? 'selected' : ''}>고추/피망/파프리카/열매채소</option>
					<option value="4" ${product.cateNum == 4 ? 'selected' : ''}>감자/고구마</option>
					<option value="5" ${product.cateNum == 5 ? 'selected' : ''}>양상추/양배추/새싹채소</option>
					<option value="6" ${product.cateNum == 6 ? 'selected' : ''}>당근/연근/뿌리채소</option>
					<option value="7" ${product.cateNum == 7 ? 'selected' : ''}>마늘/양파/생강/파</option>
				</select>
			</div>
			<!-- 옵션 등록 영역 -->
			<div class="option-section">
				<label>옵션</label>
				<div class="option-inputs">
					<input type="text" id="option_name" placeholder="예: 1kg" /> <input
						type="number" id="option_price"
						placeholder="추가 가격 (원) 또는 기본 가격 0(원)" /> <input type="number"
						id="option_stock" placeholder="재고 (개수)" />
					<button type="button" id="add_option_btn">추가</button>
				</div>
			</div>
			<ul id="option_list">
				<c:forEach var="opt" items="${productOption}">
					<li>									
						<span>${opt.option} (<fmt:formatNumber value="${opt.price}" type="number" />원, 재고: <fmt:formatNumber value="${opt.stock}" type="number" />개)</span> 
						<input type="hidden" name="option_name" value="${opt.option}" />
						<input type="hidden" name="option_price" value="${opt.price}" />
						<input type="hidden" name="option_stock" value="${opt.stock}" />
						<input type="hidden" name="option_num" value="${opt.optionNum}" />
						<button type="button" class="edit-option-btn">수정</button>
						<button type="button" class="delete-option-btn">삭제</button>
					</li>
				</c:forEach>
			</ul>
			<div class="input_div">
				<label for="product_image">상품 이미지</label> <input type="file"
					id="product_image" name="product_image" accept="image/*" />
			</div>
			<c:if test="${not empty product.imgUrl}">
				<div id="preview">
					<img src="${contextPath}${product.imgUrl}" alt="현재 이미지" />
				</div>
			</c:if>
			<div id="editor_div">
				<span id="product_content_title">상품 상세설명</span>
				<div id="editor"></div>
				<input type="hidden" id="product_content" name="product_content" />
				<div id="origin_content" style="display: none;">${product.content }</div>
			</div>
			<input type="hidden" name="productNum" value="${product.productNum}">
			<div id="submit">
				<button type="submit" id="update_btn">수정하기</button>
				<button type="button" id="toggle_btn"
					data-product-num="${product.productNum}"
					data-status="${product.status}">
					<c:choose>
						<c:when test="${product.status}">판매 중단하기</c:when>
						<c:otherwise>판매 재개하기</c:otherwise>
					</c:choose>
				</button>
			</div>
		</form>
	</div>
	<script
		src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
	<script src="${contextPath }/seller/updateProduct.js"></script>
</body>
</html>