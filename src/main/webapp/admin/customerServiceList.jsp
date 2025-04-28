<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>관리자 | 고객센터 문의글 조회</title>
	<link rel="stylesheet" href="${contextPath}/header/reset.css">
    <link rel="stylesheet" href="${contextPath}/admin/customerServiceList.css" />
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
			<jsp:include page="/header/adminSellerTop.jsp" />
    </header>
    <div id="content" class="table display nowrap">
      <div id="wrapper">
        <div class="notice-header">
          <span id="title">고객센터 문의글 조회</span>
        </div>
        <table id="service_table" class="table display nowrap" width="100%">
          <thead>
            <tr>
            <th>번호</th>
              <th>아이디</th>
              <th>제목</th>
              <th>내용</th>
              <th>작성일자</th>
              <th>수정일자</th>
              <th>카테고리</th>
              <th>답변여부</th>
              <th>답변등록</th>
              <th>답변수정</th>
              <th>답변작성일자</th>
              <th>답변수정일자</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="row" items="${requestScope.customerServiceList}" >
          	<tr>
	          	<td>${row.questionNum}</td>
	          	<td>${row.userId }</td>
	          	<td>${row.title }</td>
	          	<td>${row.previewContent }</td>
	          	<td>${row.questionCreatedAt }</td>
	          	<td>${row.questionUpdatedAt }</td>
	          	<td>${row.type }</td>
	          	<td><c:if test="${row.answerStatus eq 1}">답변완료</c:if>
	          	<c:if test="${row.answerStatus eq 0}">미답변</c:if>
	          	</td>
	          	<td><button class="insertBtn" data-num="${row.questionNum }">등록</button></td>
              	<td><button class="updateBtn" data-num="${row.questionNum }">수정</button></td>
              	<td><c:if test="${row.answerStatus eq 1}">${row.answerCreatedAt }</c:if></td>
              	<td><c:if test="${row.answerStatus eq 1}">${row.answerUpdatedAt }</c:if></td>
          	</tr>
          </c:forEach>          
          </tbody>
        </table>
      </div>
    </div>
    <div id="insertModal" class="modal">
      <div class="modal_content">
        <div class="modal_wrapper">
          <p class="customer_title"></p>
          <p class="customer_content"></p>
        </div>
        <textarea class="answer_content"></textarea>
        <button id="insertAnswerBtn">등록하기</button>
      </div>
    </div>
    <div id="updateModal" class="modal">
      <div class="modal_content">
        <div class="modal_wrapper">
          <p class="customer_title"></p>
          <p class="customer_content"></p> 
        </div>
        <textarea class="answer_content"></textarea>
        <button id="updateAnswerBtn">수정하기</button>
      </div>
    </div>
	<script src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"></script>
	<script src="${contextPath}/admin/customerServiceList.js"></script>
</body>
</html>