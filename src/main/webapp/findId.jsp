<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이디 찾기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/reset.css">
    <link rel="stylesheet" href="${contextPath}/find.css">
</head>
<body>
	<div class="container">
		<jsp:include page="/header/mainHeader.jsp" />
		<div class="content">
			<div class="findIdContainer">
				<h2 class="findTitle">아이디 찾기</h2>
				<div class="findForm">
				  <input type="email" id="emailInput" class="inputField" placeholder="이메일">
				  <input type="text" id="phoneInput" class="inputField" placeholder="전화번호">
				  <button type="button" id="findIdBtn" class="findBtn">아이디 찾기</button>
				</div>
				<div id="resultBox"></div>
				<hr>
				<!-- 하단 메뉴 -->
				<div class="bottomMenu">
					<a href="login">로그인</a>
				   	<a href="findPwd">비밀번호 변경</a>
				</div>
			</div>
    	</div>
    	<jsp:include page="/header/footer.jsp" />
	</div>
	
	<script>
		$(document).ready(function() {
			  $("#findIdBtn").click(function() {
			    const email = $("#emailInput").val().trim();
			    const phone = $("#phoneInput").val().trim();
	
			    if (email === "" || phone === "") {
			      alert("이메일과 전화번호를 모두 입력해주세요!");
			      return;
			    }
	
			    $.ajax({
			      url: `${contextPath}/findId`,
			      method: "POST",
			      contentType: "application/json",
			      //객체 → 문자열 (네트워크로 전송) → 다시 객체로 파싱
			      data: JSON.stringify({ email: email, phone: phone }),
			      success: function(data) {
			        if (data.success) {
			          console.log("userId:", data.userId, "타입:", typeof data.userId);
			          $("#resultBox").html(`님의 아이디는: <b>${data.userId}</b>`);
			        } else {
			          $("#resultBox").text("해당 정보로 등록된 아이디를 찾을 수 없습니다.");
			        }
			      },
			      error: function() {
			        alert("서버 오류가 발생했습니다.");
			      }
			    });
			  });
			});
	</script>
</body>
</html>