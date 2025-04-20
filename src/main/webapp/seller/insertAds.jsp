<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>광고 신청하기</title>
  <link rel="stylesheet" href="${contextPath}/seller/insertAds.css">

</head>
<body>
<div class="main">
	<c:import url="/header/mainHeader.jsp" />

	<div class="container">
	  <div class="header">
	    <h2>광고 신청하기</h2>
	    <div class="points">                         
			<div class="amount" id="pointAmount" data-point="${point}">${point}P</div>
  			<div class="notice" id="pointNotice">포인트가 부족합니다. 광고 신청을 원하시면 포인트를 충전하세요.</div>
	    </div>
	  </div>
	
	  <form method="post" action="insertAdsBySeller" enctype="multipart/form-data">
	    <div class="form-group">
	      <div class="form-row">
	        <label for="title">제목</label>
	        <input type="text" name="title" required="required"/>
	      </div>
	      <div class="form-row">
	        <label for="productName">상품명</label>
	        <input type="text" name="productName" required="required"/>
	      </div>
	    </div>
	
	    <div class="form-group">
	      <div class="form-row">
	        <label>작성자</label>
	        <input type="text" value="${userName}" readonly="readonly"/>
	      </div>
	      <div class="form-row">
	        <label for="productUrl">상품 링크</label>
	        <input type="text" name="productUrl" required="required"/>
	      </div>
	    </div>
	    <div class="form-row">
	      <label for="imgUrl">파일첨부</label>
	      <input type="file" accept="image/*" name="imgUrl" id="ifile" style="display:none" required="required" onchange="readURL(this);"/>
<!-- 	      <button type="button" class="upload-btn" onclick="document.getElementById('ifile').click()">업로드</button> -->	      
		  <img src="${contextPath }/img/kockUpload.PNG" alt="이미지선택" id="preview" width="100px" 
					onclick="document.getElementById('ifile').click();"/> 	
	    </div>
	
	    <div class="form-row">
	      <label for="content">내용</label>
	      <textarea name="content" required="required"></textarea>
	    </div>
	
	
	    <div class="buttons">
	      <button class="btn btn-list" id="submitBtn">글 등록</button>
	      <button class="btn btn-delete" onclick="location.href = '${contextPath}/sellerAdsList'">취소</button>
	  </div>
	  </form>
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

	window.addEventListener("DOMContentLoaded", function () {
		const pointEl = document.getElementById("pointAmount");
		const noticeEl = document.getElementById("pointNotice");
		const submitBtn = document.getElementById("submitBtn");

		let point = parseInt(pointEl.dataset.point || "0");

		// 쉼표 형식으로 포인트 표시
		pointEl.textContent = point.toLocaleString() + "P";

		if (point < 20000) {
			noticeEl.style.display = "block";
			submitBtn.disabled = true;
			submitBtn.classList.add("disabled");
		} else {
			noticeEl.style.display = "none";
			submitBtn.disabled = false;
			submitBtn.classList.remove("disabled");
		}
	});
</script>

</body>
</html>
