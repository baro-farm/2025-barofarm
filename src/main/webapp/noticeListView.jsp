<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>NoticeView</title>
    <link rel="stylesheet" href="${contextPath}/reset.css">
    <link rel="stylesheet" href="${contextPath}/noticeListView.css" />
   <link
      href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css"
      rel="stylesheet"
    /> 
</head>
<body>
    <div class="container">
		<jsp:include page="./header/mainHeader.jsp" />
		<div class="wrapper">
			<jsp:include page="./header/sideMenu.jsp" />
			<div class="content">
		    <h1 class="title">공지사항</h1>
        	<p class="subtitle">관리자가 쓰는 서비스 관련 공지</p>
		    <table id="notice_table" class="table">
		        <thead>
		          <tr>
		            <th style="font-weight: bold;">순번</th>
		            <th style="font-weight: bold;">제목</th>
		            <th style="font-weight: bold;">작성자</th>
		            <th style="font-weight: bold;">작성일자</th>
		          </tr>
		        </thead>
		        <tbody>
		          <c:forEach var="notice" items="${requestScope.noticeList}" varStatus="status">
		          	<tr>
		          		<td>${status.count }</td>
		          		<td><a href="detailNotice?noticeNum=${notice.noticeNum}" class="ellipsis">${notice.title}</a></td>
						<td><p>관리자</p></td>
		          		<td>${notice.createdAt}</td>
		          	</tr>
		         </c:forEach>
		        </tbody>
		      </table>
			</div> <!-- content -->
		</div> <!-- wrapper -->
		<jsp:include page="./header/footer.jsp" />
    </div> <!-- container -->
	<script src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"></script>
	<script>
		new DataTable('#notice_table', {
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