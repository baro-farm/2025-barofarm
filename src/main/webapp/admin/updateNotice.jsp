<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 | 공지사항 수정</title>
<link rel="stylesheet" href="${contextPath}/header/reset.css" />
<link rel="stylesheet" href="${contextPath}/admin/updateNotice.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
</head>
<body>
	<jsp:include page="/header/adminHeader.jsp" />
	<header id="header">
			<jsp:include page="/header/adminSellerTop.jsp" />
    </header>
    <div id="content">
    <div id="wrapper">
      <div id="title">공지사항 수정</div>
      <form id="notice_form" action="updateNotice" method="POST">
       <input type="hidden" name="noticeNum" value="${notice.noticeNum}" />
      <input type="text" id="notice_title" name="title" placeholder="제목을 입력하세요" value="${notice.title }"/>
      <div id="pin_notice">
      	<input type="checkbox" id="fixed" name="fixed" <c:if test="${notice.fixed == 1}">checked</c:if> />
      	
    	<label for="fixed">공지 상단 고정</label>
      </div>
      <div id="editor"></div>
      <input type="hidden" id="notice_content" name="notice_content" />
  	  <div id="notice_origin_content" style="display:none;">${notice.content}</div>
      <div id="btns">
    	  <button id="cancelBtn">취소하기</button>
	      <button type="submit" id="updateBtn">수정하기</button>
      </div>
      </form>
    </div>
    </div>
    <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
    <script src="${contextPath }/admin/updateNotice.js"></script>
</body>
</html>