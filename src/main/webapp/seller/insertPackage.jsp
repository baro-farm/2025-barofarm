<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 | 꾸러미 상품 등록</title>
<link rel="stylesheet" href="${contextPath}/reset.css" />
<link rel="stylesheet" href="${contextPath}/seller/insertPackage.css" />
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
</head>
<body>
	<jsp:include page="/header/sellerHeader.jsp" />
	<header id="header">
		<div id="info">
			<span id="email">${user.getUserId()}</span> <span>마이스토어</span> <span>로그아웃</span>
		</div>
	</header>

	<div id="content">
		<span id="title">꾸러미 상품 등록</span>
		<!-- 상품명,가격,재고, 카테고리,옵션,배송비,상품이미지 -->
		<form action="${contextPath }/insertPackage" id="package_form"
			method="POST" enctype="multipart/form-data">
			<div class="input_div">
				<label for="product_name">상품명</label> <input type="text"
					id="product_name" name="product_name" required />
			</div>
			<!-- 최대 인원 선택 -->
			<div class="input_div">
				<label for="product_category">판매 단위</label> <select
					name="product_category" id="product_category">
					<option value="8">혼밥 (1인 꾸러미)</option>
					<option value="9">커플 (2인 꾸러미)</option>
					<option value="10">트리오 (3인 꾸러미)</option>
					<option value="11">패밀리 (4인 꾸러미)</option>
				</select>
			</div>
			<div class="input_div" style="display:none;">
				<label for="max_people">판매 단위</label> <select id="package_unit" name="package_unit">
					<option value="1인">1인</option>
					<option value="2인">2인</option>
					<option value="3인">3인</option>
					<option value="4인">4인</option>
				</select>
			</div>
			<div id="options_container">
				<div class="input_div">
					<label for="package_price">가격 </label> <input type="number"
						name="package_price" id="package_price" placeholder="가격 (원)"
						required>
				</div>
			</div>
			<div class="input_div">
				<label for="package_stock">재고</label> <input type="number"
					id="package_stock" name="package_stock" required />
			</div>
			<!-- select나 라디오 버튼으로 최대 몇인까지 가능하게 할 건지 추가하고 옵션으로 1인은 얼마, 2인은 얼마 이렇게 판매자가 기입을 하면? -->



			<!-- 옵션 입력 영역 -->
			<div class="input_div">
				<label for="sale_date">판매 기간</label>
				<div id="sale_date_div">
					<div>

						<label for="start" class="">시작일</label> <input type="date"
							id="start" name="start_date" value="2025-04-04" min="2025-04-04"
							max="2029-12-31" />
					</div>
					<div>
						<label for="end">종료일</label> <input type="date" id="end"
							name="end_date" value="2025-04-04" min="2025-04-04"
							max="2029-12-31" />
					</div>

				</div>
			</div>
			<div class="input_div">
				<label for="product_image">상품 이미지</label> <input type="file"
					id="product_image" name="product_image" accept="image/*" required />
			</div>
			<div id="preview"></div>
			<div id="editor_div">
				<span id="product_content">상품 상세설명</span>
				<div id="editor"></div>
				<input type="hidden" id="package_content" name="package_content" />
			</div>
			<div id="submit">
				<input type="submit" id="insert_product" value="등록하기"></input>
			</div>
		</form>
	</div>
	<script
		src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
	<script src="${contextPath }/seller/insertPackage.js"></script>
</body>
</html>