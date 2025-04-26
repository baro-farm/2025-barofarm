<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="pageSize" value="${pageInfo.pageSize}" />
<c:set var="totalCount" value="${pageInfo.totalCount}" />
<c:set var="curPage" value="${pageInfo.curPage}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>공지사항</title>
    <link rel="stylesheet" href="${contextPath}/reset.css">
    <link rel="stylesheet" href="${contextPath}/common/userNoticeList.css" />
</head>
<body>
    <div class="container">
		<jsp:include page="/header/mainHeader.jsp" />
		<div class="wrapper">
			<jsp:include page="/header/sideMenu.jsp" />
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
		        	<c:forEach var="n" items="${requestScope.fixList}">
					  <tr class="fixedList">
					  	<td><p class="fixed">공지</p></td>
		          		<td><a href="detailNotice?noticeNum=${n.noticeNum}" class="fixTitle">${n.title}</a></td>
						<td><p>관리자</p></td>
		          		<td>${n.createdAt}</td>
		          	</tr>
					</c:forEach>
		          	<c:forEach var="notice" items="${requestScope.noticeList}" varStatus="status">
		          	<tr>
		          		<td>${totalCount - ((curPage - 1) * pageSize + status.index)}</td>
		          		<td><a href="detailNotice?noticeNum=${notice.noticeNum}">${notice.title}</a></td>
						<td><p>관리자</p></td>
		          		<td>${notice.createdAt}</td>
		          	</tr>
		         	</c:forEach>
		        </tbody>
		      </table>
		      <div id="paging">
					<c:choose>
						<c:when test="${pageInfo.curPage > 1 }">
							<a href="userNoticeList?page=${pageInfo.curPage-1 }">&lt;</a>
						</c:when>
						<c:otherwise>
							<a>&lt;</a>
						</c:otherwise>
					</c:choose>
					
						<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage }" step="1" var="page">
							<c:choose>
								<c:when test="${page eq pageInfo.curPage}">
									<a href="userNoticeList?page=${page }" class="select">${page }</a>
								</c:when>
								<c:otherwise>
									<a href="userNoticeList?page=${page }" class="btn">${page }</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>	
						
					<c:choose>
						<c:when test="${pageInfo.curPage < pageInfo.allPage }">
							<a href="userNoticeList?page=${pageInfo.curPage+1 }">&gt;</a>
						</c:when>
						<c:otherwise>
							<a>&gt;</a>
						</c:otherwise>	
					</c:choose>
				</div>
			</div> <!-- content -->
		</div> <!-- wrapper -->
		<jsp:include page="/header/footer.jsp" />
    </div> <!-- container -->
</body>
</html>