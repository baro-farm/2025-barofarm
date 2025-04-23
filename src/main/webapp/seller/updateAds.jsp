<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>광고 수정</title>
  <link rel="stylesheet" href="${contextPath }/seller/insertAds.css">

</head>
<body>
<div class="main">
	<div class="wrapper">
		<div class="sidebar">
				<jsp:include page="/header/sellerHeader.jsp" />
        </div>	
        <header id="header">
	        <div id="info">
	            <span id="email">kosta@kosta.com</span>
	            <span>내 정보</span>
	            <span>로그아웃</span>
	        </div>
	    </header>	    
		<div class="content">
		  <div class="content-header">
	        <h2>광고 신청 수정하기</h2>
	        <div class="points">
	          <div class="notice">배너 이미지 크기는 1280x850픽셀 ±10픽셀 이내여야 합니다.</div>
	        </div>
	    </div>
	
	    <form method="post" action="updateAdsBySeller" enctype="multipart/form-data">
        	<input type="hidden" name="adsNum" value="${ads.adsNum }">
	      <div class="form-group">
	        <div class="form-row">
	          <label>제목</label>
	          <input type="text" value="${ads.title }" name="title"/>
	        </div>
	        <div class="form-row">
	          <label>상품명</label>
	          <input type="text" value="${ads.productName }" name="productName"/>
	        </div>
	      </div>
	
	      <div class="form-group">
	        <div class="form-row">
	          <label>작성자</label>
	          <input type="text" value="${userName }" readonly="readonly"/>
	        </div>
	        <div class="form-row">
	          <label>상품 링크</label>
	          <input type="text" value="${ads.productUrl }" name="productUrl"/>
	        </div>
	      </div>
	  
	      <div class="form-row">
	        <label for="ifile" >이미지첨부</label>
	        <input type="file" id="ifile" name="ifile" accept="image/*" style="display:none" onchange="readURL(this);" />					
	      	<c:choose>
					<c:when test="${ads.imgUrl eq null }">
						<img src="${contextPath }/img/kockUpload.PNG" 
							width="100px" id="preview" onclick="document.getElementById('ifile').click();">
					</c:when>
					<c:otherwise>
						<img src="kockImg?imgUrl=${ads.imgUrl }"
							width="100px" id="preview" onclick="document.getElementById('ifile').click();">
					</c:otherwise>
			</c:choose>
	      </div>
	      
	      <div class="form-row">
	        <label>내용</label>
	        <textarea name="content">${ads.content }</textarea>
	      </div>
	
	
	      	<div class="buttons">
	        <button type="submit" class="btn btn-list">글 등록</button>
	        <button type="button" class="btn btn-delete" onclick="location.href='sellerAdsList'">취소</button>
	    	</div>
	    </form>
	  	</div>
	</div>
</div>
<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				const img = new Image();
				img.onload = function() {
					const width = img.width;
					const height = img.height;
					const widthValid = width >= 1270 && width <= 1290;
					const heightValid = height >= 840 && height <= 860;

					if (widthValid && heightValid) {
						document.getElementById("preview").src = e.target.result;
					} else {
						alert("이미지 크기는 1280x850픽셀 ±10픽셀 이내여야 합니다.");
						input.value = "";
						document.getElementById("preview").src = "${contextPath}/img/kockUpload.PNG";
					}
				};
				img.src = e.target.result;
			};
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
</body>
</html>
