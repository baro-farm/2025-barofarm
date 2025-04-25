<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> <html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>광고 신청하기</title>
	<link rel="stylesheet" href="${contextPath }/seller/detailStoreInfo.css">
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<jsp:include page="/header/sellerHeader.jsp" />
    <header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
    </header>
    <div id="content">
        <h2 class="title">스토어 정보 조회</h2>
		  <form method="post" action="${contextPath }/detailStoreInfo">
		    <div class="form-group">
		      <label>아이디</label>
		      <div class="readonly-text">${seller.userId }</div>
		    </div>
		    <div class="form-group">
		      <label>비밀번호</label>
		      <input type="password" value="${seller.pwd }" />
		    </div>
		    <div class="form-group">
		      <label>이름</label>
		      <div class="readonly-text">김당근</div>
		    </div>
		    <div class="form-group">
		      <label>전화번호</label>
		      <input type="text" value="${seller.phone }" />
		    </div>
		    <div class="form-group">
		      <label>이메일</label>
		      <input type="email" value="${seller.email }" />
		    </div>
		    <div class="form-group">
				<c:set var="address" value="${user.addresses[0]}"/>
		      <label>우편변호</label>
		      <div class="address-group">
		        <input type="text" placeholder="우편번호" style="width: 80px;" id="postCode"  value="${address.postCode }"/>
		        <button type="button" onclick="sample6_execDaumPostcode()">주소검색</button>
		      </div>
		    </div>
		    <div class="form-group" >
   		      <label>주소</label>
   		      <div class="address-group2">
   		      	<input type="text" placeholder="주소" value="${address.addr1 }" id="addr1" />
		      	<input type="text" placeholder="상세주소" value="${address.addr2 }"  id="addr2" />
   		      </div>
	      </div>
		    <div class="form-group">
		      <label>스토어명</label>
		      <input type="text" value="${seller.storeName }" />
		    </div>
		    <div class="form-group">
		      <label>사업자 번호</label>
		      <input type="text" placeholder="사업자 번호" value="${seller.businessNum }" />
		    </div>
		    <button class="submit-btn" type="submit">수정</button>
		  </form>
    </div>
    
    <script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                
                } 

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postCode').value = data.zonecode;
                document.getElementById("addr1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addr2").focus();
            }
        }).open();
    }
</script>
</body>
</html>