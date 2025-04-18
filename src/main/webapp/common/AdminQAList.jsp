<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>문의하기 리스트 조회</title>
	<link rel="stylesheet" href="${contextPath}/reset.css" />
    <link rel="stylesheet" href="${contextPath}/common/AdminQAList.css" />
    <link rel="stylesheet" href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css" /> 
</head>
<body>
	<div class="container">
		<jsp:include page="/header/mainHeader.jsp" />
		<div class="wrapper">
			<jsp:include page="/header/sideMenu.jsp" />
			<div class="content">
		    <h1 class="title">문의하기</h1>
        	<p class="subtitle">서비스와 관련하여 관리자에게 문의</p>
		    <table id="notice_table" class="table">
		        <thead>
		          <tr>
		            <th style="font-weight: bold;">순번</th>
		            <th style="font-weight: bold;">답변상태</th>
		            <th style="font-weight: bold;">제목</th>
		            <th style="font-weight: bold;">작성자</th>
		            <th style="font-weight: bold;">작성일자</th>
		          </tr>
		        </thead>
		        <tbody>
		          <c:forEach var="q" items="${requestScope.adminQAList}" varStatus="status">
		          	<tr>
		          		<td>${status.count }</td>
		          		<td>
		          			<c:choose>
						        <c:when test="${q.answerStatus}">
						          <span>답변 완료</span>
						        </c:when>
						        <c:otherwise>
						          <span>답변 대기</span>
						        </c:otherwise>
					      	</c:choose>
		          		</td>
		          		<td>
		          			<p>[ ${q.type} ]</p>
		          			<a href="detailAdminQA?questionNum=${q.questionNum}" class="ellipsis">${q.title}</a>
		          		</td>
						<td><p>${q.userId}</p></td>
		          		<td>${q.createdAt}</td>
		          	</tr>
		         </c:forEach>
		        </tbody>
		      </table>
		      <!-- 구매자 글쓰기 버튼 -->
        	<a href="insertAdminQ" class="writeButton">글쓰기</a>
			</div> <!-- content -->
		</div> <!-- wrapper -->
		<jsp:include page="/header/footer.jsp" />
    </div> <!-- container -->
	<script src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"></script>
	<script>
		new DataTable('AdminQAList', {
			layout: {
				topStart: null,
				topEnd: null,
				bottomStart: null,
		   		bottomEnd: 'paging' // 하단 오른쪽에 페이지네이션만 표시
		  	}
		});
	</script>
</body>
</html>