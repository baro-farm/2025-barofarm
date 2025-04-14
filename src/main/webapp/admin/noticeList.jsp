<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>관리자 | 공지사항</title>
    <link rel="stylesheet" href="${contextPath}/header/reset.css">
    <link rel="stylesheet" href="${contextPath}/admin/noticeList.css" />
    <link
      href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css"
      rel="stylesheet"
      integrity="sha384-jFvlDSY24z+oXMByOoX2Z1gM+M5NMd0uG7sDa4skv2mHXPuS0/RYXwPGLK0+Mgdc"
      crossorigin="anonymous"
    /> 
</head>
<body>
	<jsp:include page="/header/adminHeader.jsp" />
	<header id="header">
      <div id="info">
        <span id="email">kosta@kosta.com</span>
        <span>내 정보</span>
        <span>로그아웃</span>
      </div>
    </header>
    <div id="content">
    <div id="wrapper">
      <div class="notice_header">
        <span id="title">공지사항</span>
        <button id="new_notice" onclick="location.href='${contextPath}/insertNotice'">공지 작성</button>
      </div>
      <table id="notice_table" class="table display nowrap">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성일자</th>
            <th>수정일자</th>
            <th>고정</th>
            <th>수정</th>
            <th>삭제</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="notice" items="${requestScope.noticeList}" varStatus="status">
          	<tr>
          		<td>${notice.noticeNum}</td>
          		<td><div class="ellipsis">${notice.title}</div></td>
				<td><div class="ellipsis">${notice.previewContent}</div></td>
          		<td>${notice.createdAt}</td>
          		<td>${notice.updatedAt}</td>
          		<td>${notice.fixed}</td>
	            <td><button class="updateBtn" value="${notice.noticeNum}">수정</button></td>
	            <td><button class="deleteBtn" value="${notice.noticeNum}" data-num="${notice.noticeNum}">삭제</button></td>
          	</tr>
         </c:forEach>
        </tbody>
      </table>
    </div>
    </div>
    <div id="deleteModal" class="modal">
      <div class="modal_content">
        <p id="modal_title">이 글을 삭제하시겠습니까?</p>
        <div id="selected_title"></div>
        <div id="selected_content"></div>
        <button id="confirmDelete">삭제</button>
        <button id="cancelDelete">취소</button>
      </div>
    </div>
    <script src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"></script>
	<script src="${contextPath}/admin/noticeList.js"></script>
</body>
</html>