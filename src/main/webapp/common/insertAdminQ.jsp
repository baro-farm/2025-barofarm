<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>문의하기 작성</title>
	<link rel="stylesheet" href="${contextPath}/reset.css">
	<link rel="stylesheet" href="${contextPath}/common/insertAdminQ.css">
</head>
<body>
	<div class="container">
		<jsp:include page="/header/mainHeader.jsp" />
		<div class="wrapper">
			<jsp:include page="/header/sideMenu.jsp" />
        	<div class="content">	
        	
		    	<h1 class="title">문의하기</h1>
		        <p class="subtitle">서비스와 관련하여 관리자에게 문의</p>
		        
		        <form action="insertAdminQ" method="POST">
	            	<label id="writerId">작성자</label>
	            	<input type="text" id="writerIdBox" value="${user.userId}" readonly />
	            	<br>
	            	<label for="category">카테고리</label>
	            	<select name="type" id="qa_category">
		                <option value="주문 / 결제">주문 / 결제</option>
		                <option value="주문 취소">주문 취소</option>
		                <option value="배송">배송</option>
		                <option value="배송 일정">배송 일정</option>
		                <option value="기타">기타</option>
	            	</select>
	            	<br>
	            	<label id="qTitle">제목</label>
	            	<input type="text" id="qTitle" name="title" />
	            	<br>
	            	<label id="qContent">내용</label>
	        		<input type="text" id="qContent" name="content" />
			        <div id="bottom">
				      	<button type="submit" id="insertBtn">작성하기</button>
			    	  	<button id="backBtn">취소하기</button>
			      	</div>
      			</form>
      		</div>
    	</div>
	</div>
</body>
</html>