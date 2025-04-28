<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>관리자 | 회원 정보 검색</title>
	<link rel="stylesheet" href="${contextPath}/header/reset.css">
    <link rel="stylesheet" href="${contextPath}/admin/userList.css" />
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
        <div class="user_header">
          <span id="title">회원 정보 검색</span>
        </div>
        <table id="user_table" class="table display nowrap" width="100%">
          <thead>
            <tr>
              <th>번호</th>
              <th>아이디</th>
              <th>이름</th>
              <th>전화번호</th>
              <th>생년월일</th>
              <th>이메일</th>
              <th>회원분류</th>
              <th>탈퇴여부</th>
              <th>가입일자</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="row" items="${requestScope.userList}" >
          	<tr>
	          	<td>${row.userNum}</td>
	          	<td>${row.userId }</td>
	          	<td>${row.userName }</td>
	          	<td>${row.phone }</td>
	          	<td>${row.birthDate }</td>
	          	<td>${row.email }</td>
	          	<td><c:if test="${row.isSeller eq true}">판매자</c:if>
	          	<c:if test="${row.isSeller eq false}">구매자</c:if>
	          	</td>
	          	<td><c:if test="${row.isDeleted eq true}">O</c:if>
	          	<c:if test="${row.isDeleted eq false}">X</c:if>
	          	</td>	          
	          	<td>${row.createdAt }</td>
          	</tr>
          </c:forEach>          
          </tbody>
        </table>
      </div>
    </div>
	<script src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"></script>
	<script src="${contextPath}/admin/userList.js"></script>
</body>
</html>