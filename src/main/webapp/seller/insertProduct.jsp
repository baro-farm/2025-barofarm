<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>판매자 | 일반 상품 등록</title>
		<link rel="stylesheet" href="${contextPath}/reset.css" />
		<link rel="stylesheet" href="${contextPath}/seller/insertProduct.css" />
		<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
	</head>
<body>
	<jsp:include page="/header/sellerHeader.jsp" />
	<header id="header">
      <div id="info">
        <span id="email">kosta@kosta.com</span>
        <span>마이스토어</span>
        <span>로그아웃</span>
      </div>
    </header>

    <div id="content">
      <span id="title">상품 등록</span>
      <!-- 상품명,가격,재고, 카테고리,옵션,배송비,상품이미지 -->
      <form action="${contextPath }/insertProduct" id="product_form" method="POST" enctype="multipart/form-data">
        <div class="input_div">
          <label for="product_name">상품명</label>
          <input type="text" id="product_name" name="product_name"/>
        </div>
        <div class="input_div">
          <label for="product_price">가격</label>
          <input type="number" id="product_price" name="product_price"/>
        </div>
        <div class="input_div">
          <label for="product_stock">재고</label>
          <input type="number" id="product_stock" name="product_stock"/>
        </div>
        <div class="input_div">
          <label for="product_category">카테고리</label>
          <select name="product_category" id="product_category">
            <option value="1">배추/무/대파/부추</option>
            <option value="2">오이/호박/가지</option>
            <option value="3">고추/피망/파프리카/열매채소</option>
            <option value="4">감자/고구마</option>
            <option value="5">양상추/양배추/새싹채소</option>
            <option value="6">당근/연근/뿌리채소</option>
            <option value="7">마늘/양파/생강/파</option>
          </select>
        </div>
        <!-- 옵션 등록 영역 -->
        <div class="option-section">
          <label>옵션</label>
          <div class="option-inputs">
            <input type="text" id="option_name" placeholder="예: 1kg" />
            <input
              type="number"
              id="option_price"
              placeholder="추가 가격 (원) 또는 기본 가격 0(원)"
            />
            <button type="button" id="add_option_btn">추가</button>
          </div>
        </div>
        <ul id="option_list"></ul>
        <div class="input_div">
          <label for="product_image">상품 이미지</label>
          <input
            type="file"
            id="product_image"
            name="product_image"
            accept="image/*"
          />
        </div>
        <div id="preview"></div>
        <div id="editor_div">
          <span id="product_content_title">상품 상세설명</span>
          <div id="editor"></div>
          <input type="hidden" id="product_content" name="product_content" />
        </div>
        <div id="submit">
            <input type="submit" id="insert_product" value="등록하기"></input>
        </div>
      </form>
    </div>
    <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
    <script src="${contextPath }/seller/insertProduct.js" ></script>
</body>
</html>