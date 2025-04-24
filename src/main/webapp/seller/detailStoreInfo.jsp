<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> <html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>광고 신청하기</title>
	<link rel="stylesheet" href="${contextPath }/seller/detailStoreInfo.css">
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<jsp:include page="/header/sellerHeader.jsp" />
    <header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
    </header>
    <div id="content">
        <h2 class="title">스토어 정보 조회</h2>
		  <form>
		    <div class="form-group">
		      <label>아이디</label>
		      <input type="text" value="아이디는 변경 불가" readonly/>
		    </div>
		    <div class="form-group">
		      <label>비밀번호</label>
		      <input type="password" value="1234" />
		    </div>
		    <div class="form-group">
		      <label>이름</label>
		      <div class="readonly-text">김당근</div>
		    </div>
		    <div class="form-group">
		      <label>전화번호</label>
		      <input type="text" value="010-1234-1234" />
		    </div>
		    <div class="form-group">
		      <label>이메일</label>
		      <input type="email" value="abc@email.com" />
		    </div>
		    <div class="form-group">
		      <label>주소</label>
		      <div class="address-group">
		        <input type="text" placeholder="우편번호" style="width: 80px;" />
		        <button type="button">주소검색</button>
		      </div>
		    </div>
		    <div class="form-group" style="margin-left: 100px;">
		      <input type="text" placeholder="주소" style="width: 180px;" />
		      <input type="text" placeholder="상세주소" style="width: 180px;" />
		    </div>
		    <div class="form-group">
		      <label>스토어명</label>
		      <input type="text" value="언니네 텃밭" />
		    </div>
		    <div class="form-group">
		      <label>사업자 번호</label>
		      <input type="text" placeholder="사업자 번호" />
		    </div>
		    <button class="submit-btn" type="submit">수정</button>
		  </form>
    </div>
</body>
</html>