<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>관리자 | 스토어 목록 조회</title>
	<link rel="stylesheet" href="${contextPath}/header/reset.css">
    <link rel="stylesheet" href="${contextPath}/admin/storeList.css" />
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
        <div class="store_header">
          <span id="title">스토어 목록 조회</span>
        </div>
        <select name="search_by_column" id="search_by_column">
          <option value="sub">팜링 구독 스토어</option>
          <option value="not_sub">팜링 미구독 스토어</option>
        </select>
        <table id="store_table" class="table display nowrap" width="100%">
          <thead>
            <tr>
              <th>번호</th>
              <th>아이디</th>
              <th>이름</th>
              <th>전화번호</th>
              <th>생년월일</th>
              <th>이메일</th>
              <th>스토어명</th> 
              <th>사업자등록번호</th>
              <th>팜링구독여부</th>
              <th>매출액</th>
              <th>가입일자</th>
              <th>탈퇴여부</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="row" items="${requestScope.storeList}" >
          	<tr>
	          	<td>${row.sellerNum }</td>
	          	<td>${row.userId }</td>
	          	<td>${row.userName }</td>
	          	<td>${row.phone }</td>
	          	<td>${row.birthDate }</td>
	          	<td>${row.email }</td>
	          	<td>${row.storeName }</td>
	          	<td>${row.businessNum }</td>
	          	<td><c:if test="${row.isAlarm eq 1}">O</c:if>
	          	<c:if test="${row.isAlarm eq 0}">X</c:if>
	          	</td>
	          	<td>${row.amount}</td>
	            <td>${row.createdAt }</td>
	          	<td><c:if test="${row.isDeleted eq 1}">O</c:if>
	          	<c:if test="${row.isDeleted eq 0}">X</c:if>
	          	</td>	          
          	</tr>
          </c:forEach>          
          </tbody>
        </table>
      </div>
    </div>
	<script src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"></script>
	<script src="${contextPath}/admin/storeList.js"></script>
</body>
</html>