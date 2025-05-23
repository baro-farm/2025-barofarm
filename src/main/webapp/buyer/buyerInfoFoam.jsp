<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="dto.User" %>
<%@ page import="vo.UserVO" %>

<%
UserVO user = (UserVO)request.getAttribute("user");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 정보</title>
    <link rel="stylesheet" href="${contextPath}/buyer/buyerInfoFoam.css">
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body>
		
<jsp:include page="/header/mainHeader.jsp"/>

<div class="container">
	<div class="wrapper">
		<div class="sideMenu">
			<jsp:include page="/header/buyerMenu.jsp" />
		</div>
		<div class="content">
		    <div class="header">내 정보 수정</div>
			<div class="infoContent" style="width:500px; margin:0 auto; margin-top:50px;">
		    <form action="${pageContext.request.contextPath}/updateInfo" method="POST">
		        <div class="formGroup">
		            <label>아이디</label>
		            <input type="text" value="${user.userId}" readonly   name="userId">
		        </div>
		
		        <div class="formGroup">
		            <label>비밀번호</label>
		            <input type="password" value="${user.pwd }" name="pwd" >
		        </div>
		
		        <div class="formGroup">
		            <label>이름</label>
		            <input type="text"value="${user.userName}" name="userName">
		        </div>
		
		        <div class="formGroup">
		            <label>전화번호</label>
		            <div class="phone-wrapper">
				        <input type="text" id="phone1" name="phone1" maxlength="3" value="${fn:substring(user.phone, 0, 3)}" pattern="\d*" inputmode="numeric" required>
				        -
				        <input type="text" id="phone2" name="phone2" maxlength="4" value="${fn:substring(user.phone, 3, 7)}" pattern="\d*" inputmode="numeric" required>
				        -
				        <input type="text" id="phone3" name="phone3" maxlength="4" value="${fn:substring(user.phone, 7, 11)}" pattern="\d*" inputmode="numeric" required>
				    </div>
		        </div>
		
		        <div class="formGroup">
		            <label>이메일</label>
		            <input type="email" value="${user.email}" name="email">
		        </div>
				
		        <div class="formGroup">
		            <label>주소</label>
		            <div class="address-container">
					<c:set var="address" value="${user.addresses[0]}"/>
		                <input type="text" placeholder="우편번호" readonly  name="postCode" id="postCode" value="${address.postCode}">
		                <button type="button" class="address-btn" id="address-btn"  onclick="sample6_execDaumPostcode()" >주소검색</button>
		            </div>
		            
		            <input type="text" placeholder="주소" readonly  name="addr1" id="addr1" value="${address.addr1}" >
		            <input type="text" placeholder="상세주소" name="addr2" id="addr2" value="${address.addr2}">
		        </div>
				
		        <div class="btn-group">
		            <button type="submit" class="btn btn-save">저장</button>
		            
		        </div>
		        

		    </form>
		    	<div class="deleteLink">
				    <p id="deleteUserBtn" style="color: #888; font-size: 13px; cursor: pointer; margin-top:20px;">&gt; 회원탈퇴</p>
				</div>
		    </div>
	    </div><!-- end of content -->
    </div><!-- end of warraper -->
</div>

<!-- 그대로 유지 -->
<div id="confirmModal" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%;
    background: rgba(0,0,0,0.5); justify-content: center; align-items: center; z-index: 9999;">
    <div style="background:#fff; padding:30px; border-radius:10px; text-align:center; width:300px;">
        <p style="margin-bottom:20px;">정말로 회원 탈퇴하시겠습니까?</p>
        <button id="confirmDelete" class="btn btn-delete" style="margin-right:10px;">확인</button>
        <button id="cancelDelete" class="btn btn-cancel">취소</button>
    </div>
</div>

<!-- 정보 수정 확인 모달 -->
<div id="updateConfirmModal" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%;
    background: rgba(0,0,0,0.5); justify-content: center; align-items: center; z-index: 9999;">
    <div style="background:#fff; padding:30px; border-radius:10px; text-align:center; width:300px;">
        <p style="margin-bottom:20px;">정말로 정보를 수정하시겠습니까?</p>
        <button id="confirmUpdate" class="btn btn-save" style="margin-right:10px;">확인</button>
        <button id="cancelUpdate" class="btn">취소</button>
    </div>
</div>

<script>
    document.querySelector('form').addEventListener('submit', function(e) {
        const phone1 = document.getElementById('phone1').value.trim();
        const phone2 = document.getElementById('phone2').value.trim();
        const phone3 = document.getElementById('phone3').value.trim();

        const fullPhone = phone1 + phone2 + phone3;

        // hidden input 추가
        let hiddenPhone = document.getElementById('phone');
        if (!hiddenPhone) {
            hiddenPhone = document.createElement('input');
            hiddenPhone.type = 'hidden';
            hiddenPhone.name = 'phone';
            hiddenPhone.id = 'phone';
            this.appendChild(hiddenPhone);
        }
        hiddenPhone.value = fullPhone;
    });
    
    document.getElementById('deleteUserBtn').addEventListener('click', function () {
        document.getElementById('confirmModal').style.display = 'flex';
    });

    document.getElementById('cancelDelete').addEventListener('click', function () {
        document.getElementById('confirmModal').style.display = 'none';
    });

    document.getElementById('confirmDelete').addEventListener('click', function () {
        fetch("${contextPath}/deleteUser", {
            method: 'POST'
        }).then(() => {
            window.location.href = "${contextPath}/main.jsp";
        });
    });
    
    document.querySelector('.btn-save').addEventListener('click', function (e) {
        e.preventDefault(); // 기본 submit 막기
        document.getElementById('updateConfirmModal').style.display = 'flex';
    });
    
    document.getElementById('cancelUpdate').addEventListener('click', function () {
        document.getElementById('updateConfirmModal').style.display = 'none';
    });

    document.getElementById('confirmUpdate').addEventListener('click', function () {
        const phone1 = document.getElementById('phone1').value.trim();
        const phone2 = document.getElementById('phone2').value.trim();
        const phone3 = document.getElementById('phone3').value.trim();

        let hiddenPhone = document.getElementById('phone');
        if (!hiddenPhone) {
            hiddenPhone = document.createElement('input');
            hiddenPhone.type = 'hidden';
            hiddenPhone.name = 'phone';
            hiddenPhone.id = 'phone';
            document.querySelector('form').appendChild(hiddenPhone);
        }
        hiddenPhone.value = phone1 + phone2 + phone3;

        document.querySelector('form').submit(); // 수동 submit
    });
</script>
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