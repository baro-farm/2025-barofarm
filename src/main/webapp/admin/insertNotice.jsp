<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="java.util.List" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page import="dto.admin.Notice" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 | 공지사항 작성</title>
<link rel="stylesheet" href="${contextPath}/header/reset.css" />
<link rel="stylesheet" href="${contextPath}/admin/insertNotice.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
</head>
<body>
	<jsp:include page="/header/adminHeader.jsp" />
	<header id="header">
			<jsp:include page="/header/adminSellerTop.jsp" />
    </header>
    <div id="content">
    <div id="wrapper">
      <div id="title">공지사항 작성</div>
      <form id="notice_form" action="insertNotice" method="POST"> 
	      <input type="text" id="notice_title" name="title" placeholder="제목을 입력하세요" />
	      <div id="pin_notice">
	      	<input type="checkbox" id="fixed" name="fixed" />
	    	<label for="fixed">공지 상단 고정</label>
	      </div>
	      <div id="editor"></div>
	      <!-- 이 div에 실제 에디터 내용을 담을 숨겨진 input 추가 -->
	  		<input type="hidden" id="notice_content" name="notice_content" />
	      <div id="btns">
	    	  <button id="cancelBtn">취소하기</button>
		      <button type="submit" id="insertBtn">작성하기</button>
	      </div>
      </form>
    </div>
    </div>
    <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
    <script src="${contextPath }/admin/insertNotice.js"></script>
</body>
</html>