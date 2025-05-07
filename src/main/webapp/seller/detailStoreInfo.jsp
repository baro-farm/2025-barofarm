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
		      <label>이름</label>
		      <div class="readonly-text">${seller.userName }</div>
		    </div>
		    <div class="form-group">
		      <label>비밀번호</label>
		      <input type="password" value="${seller.pwd }" />
		    </div>
		    <div class="form-group">
			  <label>전화번호</label>
			  <div class="phone-group">
			    <input type="text" id="phone1" maxlength="3" value="${seller.phone.substring(0,3)}" />
			    -
			    <input type="text" id="phone2" maxlength="4" value="${seller.phone.substring(3,7)}" />
			    -
			    <input type="text" id="phone3" maxlength="4" value="${seller.phone.substring(7)}" />
			  </div>
			</div>
		    
		    <div class="form-group">
		      <label>이메일</label>
		      <input type="email" value="${seller.email }" />
		    </div>
		    <div class="form-group">
				<c:set var="address" value="${user.addresses[0]}"/>
		      <label>우편번호</label>
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
			  <div class="biz-group">
			    <input type="text" id="biz1" maxlength="3" value="${seller.businessNum.substring(0,3)}" readonly />
			    -
			    <input type="text" id="biz2" maxlength="2" value="${seller.businessNum.substring(3,5)}" readonly />
			    -
			    <input type="text" id="biz3" maxlength="5" value="${seller.businessNum.substring(5)}" readonly />
			  </div>
			</div>
		    <div class="form-group bttn">
		    	<button class="submit-btn" type="button" id="updateBtn">수정하기</button>
		    	<button class="del-btn" type="button" id="deleteBtn">탈퇴하기</button>
		    </div>
		    
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
<!-- 탈퇴 모달 -->
	<div id="talModal" class="modal-wrapper" style="display:none;">
		<div class="modal">
			<p>정말 탈퇴하시겠습니까?<br>탈퇴 시 모든 정보가 사라집니다!</p>
		    <div class="buttons">
		        <button class="btn btn-subscribe">회원 유지</button>
		        <button class="btn btn-cancel">탈퇴하기</button>
		    </div>
        </div>
	</div>
<script>
document.getElementById('deleteBtn').addEventListener('click', function () {
    document.getElementById('talModal').style.display = 'flex';
});

document.querySelector('#talModal .btn-subscribe').addEventListener('click', function () {
    document.getElementById('talModal').style.display = 'none';
});

document.querySelector('#talModal .btn-cancel').addEventListener('click', function () {
    fetch('${contextPath}/deleteUserBySeller', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ userId: '${seller.userId}' })
    })
    .then(res => res.json())
    .then(result => {
        alert(result.msg);
        if (result.redirect) {
            location.href = result.redirect;
        }
    })
    .catch(err => {
        alert('서버 통신 중 오류가 발생했습니다.');
        console.error(err);
    });
});


/*  수정 */
 
document.getElementById('updateBtn').addEventListener('click', function () {
    const data = {
        userId: '${seller.userId}',
        pwd: document.querySelector('input[type="password"]').value,
        phone: document.getElementById('phone1').value +
               document.getElementById('phone2').value +
               document.getElementById('phone3').value,
        email: document.querySelector('input[type="email"]').value,
        postCode: document.getElementById('postCode').value,
        addr1: document.getElementById('addr1').value,
        addr2: document.getElementById('addr2').value,
        storeName: document.querySelector('input[value="${seller.storeName }"]').value,
        businessNum: document.getElementById('biz1').value +
                     document.getElementById('biz2').value +
                     document.getElementById('biz3').value
    };

    fetch('${contextPath}/detailStoreInfo', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(result => {
        location.reload();
    })
    .catch(err => {
        alert('수정 중 오류가 발생했습니다.');
        console.error(err);
    });
});



</script>

</body>
</html>