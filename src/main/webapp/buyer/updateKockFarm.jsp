<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>콕팜 수정하기</title>
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
	
	    <form action="updateKockFarm" method="post" enctype="multipart/form-data">
	        <input type="hidden" name="kockNum" value="${kock.kockNum }">
	      <div class="form-big">
	        <div class="form-group">
	          <label for="title">제목</label>
	          <input type="text" id="title" name="title" value="${kock.title }" required>
	        </div>
	  
	        <div class="form-group">
	          <label for="writer">작성자</label>
	          <input type="text" id="writer" name="writer" value="${kock.userName }" readonly required>
	        </div>
	      </div>
	      
	
	
	      <div class="form-big">
	        <div class="form-group">
	          <label for="category">카테고리</label>
	          <input type="text" value="${kock.name }" readonly="readonly" />
	        </div>
	        <div class="form-group">
	          <label for="quantity">수량</label>
	          <input type="number" id="quantity" name="quantity" value="${kock.quantity }" required min="1">
	        </div>  
	      </div>
	      
	      <div class="form-big">
	        <div class="form-group">
	          <label for="price">가격</label>
	          <input type="number" id="price" name="price" value="${kock.price }" required min="1">
	        </div>
	        <div class="form-group">
	          <label for="delivery-date">희망 배송일자</label>
	
	            <input
	            type="date"
	            id="shipDate"
	            name="shipDate"
	            value="${kock.shipDate }"
	            max="2029-12-31" 
	            placeholder="날짜 선택" />
	        
	        </div>
	      </div>
	      
   	      <div class="form-row">
	        <label for="ifile" >이미지첨부</label>
	        <input type="file" id="ifile" name="ifile" accept="image/*" style="display:none" onchange="readURL(this);" />					
	      	<c:choose>
					<c:when test="${kock.imgUrl eq null }">
						<img src="${contextPath }/img/kockUpload.PNG" 
							width="100px" id="preview" onclick="document.getElementById('ifile').click();">
					</c:when>
					<c:otherwise>
						<img src="kockImg?imgUrl=${kock.imgUrl }"
							width="100px" id="preview" onclick="document.getElementById('ifile').click();">
					</c:otherwise>
			</c:choose>
	      </div>
	
	      <div class="form-content">
	        <label for="content">내용</label>
	        <textarea id="content" name="content" required>${kock.content }</textarea>
	      </div>
	
	      <div class="button-group">
	        <button type="submit" class="btn btn-submit">수정</button>
	        <button type="button" class="btn btn-cancel" onclick="location.href='kockFarmList'">취소</button>
	      </div>
	    </form>
  	</div>
	<jsp:include page="/header/footer.jsp" />
</div>
<script>
	//브라우저에서 오늘 날짜를 ISO 포맷(YYYY-MM-DD)으로 구하기
	var today = new Date().toISOString().split('T')[0];
	
	// input 요소의 value와 min 속성을 오늘 날짜로 설정하기
	var shipDateInput = document.getElementById("shipDate");
	shipDateInput.min = today;
</script> 
</body>
</html>