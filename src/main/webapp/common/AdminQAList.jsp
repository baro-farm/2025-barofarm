<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="pageSize" value="${pageInfo.pageSize}" />
<c:set var="totalCount" value="${pageInfo.totalCount}" />
<c:set var="curPage" value="${pageInfo.curPage}" />
<c:if test="${not empty param.error}">
    <script>
        <c:choose>
            <c:when test="${param.error == 'notAuthor'}">
                alert("작성자만 조회가 가능합니다.");
            </c:when>
            <c:when test="${param.error == 'fail'}">
                alert("오류가 발생했습니다.");
            </c:when>
        </c:choose>
    </script>
</c:if>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>문의하기</title>
	<link rel="stylesheet" href="${contextPath}/reset.css" />
    <link rel="stylesheet" href="${contextPath}/common/AdminQAList.css" /> 
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
		          		<td>${totalCount - ((curPage - 1) * pageSize + status.index)}</td>
		          		<td>
		          			<c:choose>
						        <c:when test="${q.answerStatus}">
						          <span class="statusA">답변 완료</span>
						        </c:when>
						        <c:otherwise>
						          <span class="statusB">답변 대기</span>
						        </c:otherwise>
					      	</c:choose>
		          		</td>
		          		<td>
				        	<a href="detailAdminQA?questionNum=${q.questionNum}" class="qtitle">[${q.type}] ${q.title}</a>
		          		</td>
						<td><p>${q.userId}</p></td>
		          		<td>${q.createdAt}</td>
		          	</tr>
		         </c:forEach>
		        </tbody>
		      </table>
		      <div id="paging">
					<c:choose>
						<c:when test="${pageInfo.curPage > 1 }">
							<a href="adminQAList?page=${pageInfo.curPage-1 }">&lt;</a>
						</c:when>
						<c:otherwise>
							<a>&lt;</a>
						</c:otherwise>
					</c:choose>
					
						<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage }" step="1" var="page">
							<c:choose>
								<c:when test="${page eq pageInfo.curPage}">
									<a href="adminQAList?page=${page }" class="select">${page }</a>
								</c:when>
								<c:otherwise>
									<a href="adminQAList?page=${page }" class="btn">${page }</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>	
						
					<c:choose>
						<c:when test="${pageInfo.curPage < pageInfo.allPage }">
							<a href="adminQAList?page=${pageInfo.curPage+1 }">&gt;</a>
						</c:when>
						<c:otherwise>
							<a>&gt;</a>
						</c:otherwise>	
					</c:choose>
				</div>
		      	<c:if test="${user != null}">
		      		<a href="insertAdminQA" class="writeButton">글쓰기</a>
		      	</c:if>
			</div> <!-- content -->
		</div> <!-- wrapper -->
		<jsp:include page="/header/footer.jsp" />
    </div> <!-- container -->
</body>
</html>