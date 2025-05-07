<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/login.css">
</head>
<body>
	<div class="container">
		<!-- 헤더 -->
		<jsp:include page="/header/mainHeader.jsp" />
		<!-- 컨텐츠  -->
		<div class="content">
		<div class="loginContainer">
			<h2 class="loginTitle">로그인</h2>
		    <!-- 로그인 폼 -->
		    <form action="login" method="post" class="loginForm">
		    	<input type="text" id="userId" class="inputField" name="userId" value="${userId}" placeholder="아이디" required>
		        <input type="password" id="pwd" class="inputField" name="pwd" placeholder="비밀번호" required>  
		        <!-- 아이디 저장 & 자동 로그인 -->
		        <div class="loginOptions">
		        	<label><input type="checkbox" id="saveId" name="saveId" value="on" ${cookie.saveId.value eq 'on' ? 'checked' : ''}> 아이디 저장</label>
		        	<label><input type="checkbox" id="autoLogin" name="autoLogin" value="on" ${cookie.autoLogin.value eq 'on' ? 'checked' : ''}> 자동 로그인</label>
		        </div>
		        <p id="loginErr" style="color:red;"></p>
		        <!-- 로그인 버튼 -->
		        <button type="button" onclick="loginSubmit()" class="loginBtn">로그인</button>
		   	</form>
		    <hr>
		    <!-- 하단 메뉴 -->
		    <div class="bottomMenu">
		    	<a href="${contextPath}/join">회원가입</a>
		        <a href="${contextPath}/findId">아이디 찾기</a>
		        <a href="${contextPath}/findPwd">비밀번호 변경</a>
		    </div>
		</div> <!-- 로그인 컨텐츠  -->
		</div>
	<jsp:include page="/header/footer.jsp" />
	</div> <!-- 전체 컨테이너 -->
</body>
<script>
function loginSubmit() {
  $.ajax({
    url: "login",
    type: "POST",
    dataType: "json",
    data: {
      userId: $("#userId").val(),
      pwd: $("#pwd").val(),
      saveId: $("#saveId").is(":checked") ? "on" : null,
      autoLogin: $("#autoLogin").is(":checked") ? "on" : null
    },
    success: function(result) {
      if (result.success) {
        window.location.href = result.redirectUrl; 
      } else {
        $("#loginErr").text("아이디 또는 비밀번호가 잘못되었습니다.");
      }
    },
    error: function() {
      alert("서버와 통신 중 오류가 발생했습니다.");
    }
  });
}
</script>
</html>