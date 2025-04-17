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
	      <div class="amount">9,290P</div>
	      <div class="notice">포인트가 부족합니다. 광고 신청을 원하시면 포인트를 충전하세요.</div>
	    </div>
	  </div>
	
	  <form method="post" action="insertAdsBySeller" enctype="multipart/form-data">
	    <div class="form-group">
	      <div class="form-row">
	        <label>제목</label>
	        <input type="text" />
	      </div>
	      <div class="form-row">
	        <label>상품명</label>
	        <input type="text" />
	      </div>
	    </div>
	
	    <div class="form-group">
	      <div class="form-row">
	        <label>작성자</label>
	        <input type="text" />
	      </div>
	      <div class="form-row">
	        <label>상품 링크</label>
	        <input type="text" />
	      </div>
	    </div>
	  
	    <div class="form-row">
	      <label>광고 예상<br>시작일</label>
	      <input type="date" readonly value="2025-04-01" />
	      <span class="info-text">광고 게시일로부터 일주일입니다.</span>
	    </div>
	
	    <div class="form-row">
	      <label>파일첨부</label>
	      <input type="file" id="file-input" style="display:none" />
	      <button type="button" class="upload-btn" onclick="document.getElementById('file-input').click()">업로드</button>
	      <span id="file-name-display">선택된 파일 없음</span>
	    </div>
	
	    <div class="form-row">
	      <label>내용</label>
	      <textarea></textarea>
	    </div>
	
	
	    <div class="buttons">
	      <button class="btn btn-list">글 등록</button>
	      <button class="btn btn-delete">취소</button>
	  </div>
	  </form>
	</div>
</div>
  <script>
    // 파일 이름 표시
    document.getElementById("file-input").addEventListener("change", function() {
      const fileName = this.files[0] ? this.files[0].name : "선택된 파일 없음";
      document.getElementById("file-name-display").textContent = fileName;
    });

    // 폼 제출 시 파일 확인 (서버 업로드 없이 기본 기능)
    document.getElementById("ad-form").addEventListener("submit", function(e) {
      e.preventDefault();
      alert("제출이 완료되었습니다 (예시). 실제 서버 업로드는 별도 구현 필요!");
    });
  </script>
</body>
</html>
