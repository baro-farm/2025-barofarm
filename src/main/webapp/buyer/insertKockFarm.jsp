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
	    <div class="title_head">
	        <div class="title">콕팜</div>
	        <p>거래를 제안하세요!</p>
	    </div>

	    <form action="insertKockFarm" method="post" enctype="multipart/form-data">
	      <div class="form-big">
	        <div class="form-group">
	          <label for="title">제목</label>
	          <input type="text" id="title" name="title" required>
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
	            <option value="1" selected >배추/무/대파/부추</option>
	            <option value="2">오이/호박/가지</option>
	            <option value="3">고추/피망/파프리카/열매채소</option>
	            <option value="4">감자/고구마</option>
	            <option value="5">양상추/양배추/새싹채소</option>
	            <option value="6">당근/연근/뿌리채소</option>
	            <option value="7">마늘/양파/생강/파</option>
	          </select>
	        </div>
	        <div class="form-group">
	          <label for="quantity">수량</label>
	          <input type="number" id="quantity" name="quantity" required min=1 value=1>
	        </div>  
	      </div>
	      
	      <div class="form-big">
	        <div class="form-group">
	          <label for="price">가격</label>
	          <input type="number" id="price" name="price" required min=1 value=1>
	        </div>
	        <div class="form-group">
	          <label for="shipDate">희망 배송일자</label>
	
	            <input
	            type="date"
	            id="shipDate"
	            name="shipDate"
	            max="2029-12-31" 
	            placeholder="날짜 선택" />
	        
	        </div>
	      </div>
	      <div class="form-row">
	        <label for="ifile" >이미지첨부</label>
	        <img src="${contextPath }/img/kockUpload.PNG" alt="이미지선택" id="preview" width="100px" 
					onclick="document.getElementById('ifile').click();"/> 
	        
   	      	<button type="button" id="preview" class="upload-btn" onclick="document.getElementById('ifile').click()">업로드</button>
	        
	        <input type="file" id="ifile" name="ifile" accept="image/*" style="display:none" 
	        onchange="readURL(this);" />
	      </div>
	     
	      <div class="form-content">
	        <label for="content">내용</label>
	        <textarea id="content" name="content" placeholder="원하는 내용을 작성하세요." required></textarea>
	      </div>
	
	      <div class="button-group">
	        <button type="submit" class="btn btn-submit">등록하기</button>
	        <button type="button" class="btn btn-cancel" onclick="location.href='kockFarmList'">취소</button>
	      </div>
	    </form>
  	</div>
</div> 
<script>
	//브라우저에서 오늘 날짜를 ISO 포맷(YYYY-MM-DD)으로 구하기
	var today = new Date().toISOString().split('T')[0];
	
	// input 요소의 value와 min 속성을 오늘 날짜로 설정하기
	var shipDateInput = document.getElementById("shipDate");
	shipDateInput.value = today;
	shipDateInput.min = today;
</script> 
</body>
</html>