<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
		          		<td>${pageInfo.totalCount - ((pageInfo.curPage - 1) * pageInfo.pageSize + status.index)}</td>
		          		<td><a href="detailNotice?noticeNum=${notice.noticeNum}">${notice.title}</a></td>
						<td><p>관리자</p></td>
		          		<td>${notice.createdAt}</td>
		          	</tr>
		         	</c:forEach>
		        </tbody>
		      </table>
		      <div id="paging">
					<!-- 이전 페이지 -->
					<c:choose>
					  <c:when test="${pageInfo.curPage > 10}">
					    <a href="${contextPath}/userNoticeList?page=${pageInfo.curPage - 10}&sort=${sort}">&laquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&laquo;</a>
					  </c:otherwise>
					</c:choose>
					<c:choose>
					  <c:when test="${pageInfo.curPage > 1}">
					    <a href="${contextPath}/userNoticeList?page=${pageInfo.curPage - 1}&sort=${sort}">&lsaquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&lsaquo;</a>
					  </c:otherwise>
					</c:choose>
					<!-- 페이지 번호 -->
					<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="page">
					  <c:choose>
					    <c:when test="${page == pageInfo.curPage}">
					      <a href="${contextPath}/userNoticeList?page=${page}&sort=${sort}" class="select">${page}</a>
					    </c:when>
					    <c:otherwise>
					      <a href="${contextPath}/userNoticeList?page=${page}&sort=${sort}" class="btn">${page}</a>
					    </c:otherwise>
					  </c:choose>
					</c:forEach>
					<!-- 다음 페이지 -->
					<c:choose>
					  <c:when test="${pageInfo.curPage < pageInfo.allPage}">
					    <a href="${contextPath}/userNoticeList?page=${pageInfo.curPage + 1}&sort=${sort}">&rsaquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&rsaquo;</a>
					  </c:otherwise>
					</c:choose>
					<c:choose>
					  <c:when test="${pageInfo.curPage < pageInfo.allPage}">
					    <a href="${contextPath}/userNoticeList?page=${pageInfo.curPage + 10}&sort=${sort}">&raquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&raquo;</a>
					  </c:otherwise>
					</c:choose>
				</div>
			</div> <!-- content -->
		</div> <!-- wrapper -->
		<jsp:include page="/header/footer.jsp" />
    </div> <!-- container -->
</body>
</html>