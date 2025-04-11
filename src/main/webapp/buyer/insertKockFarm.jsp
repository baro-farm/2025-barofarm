<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>콕팜 작성하기</title>
  	<link rel="stylesheet" href="${contextPath}/buyer/insertKockFarm.css" />
  	<script>
  		function readURL(input) {
  			if(input.files && input.files[0]) {
  				var reader = new FileReader();
  				
  				//여기는 함수 정의
  				reader.onload = function(e) {
  					document.getElementById("preview").src = e.target.result;
  				}
  				
  				reader.readAsDataURL(input.files[0]);
  			} 
  		}
   	</script>
</head>
<body>
<div class="main">
	<c:import url="/header/mainHeader.jsp" />
	 <div class="container">
    <h2>원해요</h2>
    <p>원하는 상품을 직접 신청하세요!</p>

    <form action="insertKockFarm" method="post" enctype="multipart/form-data">
      <div class="form-big">
        <div class="form-group">
          <label for="title">제목</label>
          <input type="text" id="title" name="title">
        </div>
  
        <div class="form-group">
          <label for="writer">작성자</label>
          <input type="text" id="userNum" name="userNum" value="2" readonly>
        </div>
      </div>

      <div class="form-big">
        <div class="form-group">
          <label for="cateNum">카테고리</label>
          <select name="cateNum" id="cateNum">
            <option value="1">배추/무/대파/부추</option>
            <option value="2">오이/호박/가지</option>
            <option value="fruiting_veggie">고추/피망/파프리카/열매채소</option>
            <option value="root_potato">감자/고구마</option>
            <option value="lettuce_cabbage">양상추/양배추/새싹채소</option>
            <option value="carrot_root">당근/연근/뿌리채소</option>
            <option value="spicy_base">마늘/양파/생강/파</option>
          </select>
        </div>
        <div class="form-group">
          <label for="quantity">수량</label>
          <input type="number" id="quantity" name="quantity">
        </div>  
      </div>
      
      <div class="form-big">
        <div class="form-group">
          <label for="price">가격</label>
          <input type="number" id="price" name="price">
        </div>
        <div class="form-group">
          <label for="shipDate">희망 배송일자</label>

            <input
            type="date"
            id="shipDate"
            name="shipDate"
            value="2025-04-11"
            min="2025-04-11"
            max="2029-12-31" 
            placeholder="날짜 선택" />
        
        </div>
      </div>
      <div class="form-row">
        <label for="ifile" >파일첨부</label>
        <input type="file" id="ifile" name="ifile" accept="image/*" style="display:none" onchange="readURL(this);" />
        <button type="button" class="upload-btn" onclick="document.getElementById('file-input').click()">업로드</button>
        <span id="file-name-display">선택된 파일 없음</span>
      </div>
     
      <div class="form-content">
        <label for="content">내용</label>
        <textarea id="content" name="content" placeholder="원하는 내용을 작성하세요.">
      	</textarea>
      </div>

      <div class="button-group">
        <button type="submit" class="btn btn-submit">등록하기</button>
        <button type="button" class="btn btn-cancel">취소</button>
      </div>
    </form>
  </div>
</div>  
</body>
</html>