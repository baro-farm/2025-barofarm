<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>콕팜 목록</title>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

    <link rel="stylesheet" href="${contextPath}/common/kockFarmList.css">
    <link rel="stylesheet" href="${contextPath}/header/reset.css">
</head>

<body>
	<div class="main">
		<jsp:include page="/header/mainHeader.jsp" />
			<div class="wrapper">
			<jsp:include page="/header/sideMenu.jsp" />
			    <div class="content">
				    <div class="searchDiv">
				    	<div>
					    	<h1 class="title">콕팜</h1>
					        <p class="subtitle">원하는 농작물을 직접 신청하세요!</p>
				        </div>
						<form method="get" action="${contextPath}/kockFarmList" >
						  <select name="searchType">
						    <option value="title" ${param.searchType == 'title' ? 'selected' : ''}>제목</option>
						    <option value="content" ${param.searchType == 'content' ? 'selected' : ''}>내용</option>
						  </select>
						  
							<!-- 매칭 상태 필터 추가 -->
							<select name="status">
							    <option value="all" ${param.status == 'all' ? 'selected' : ''}>전체</option>
							    <option value="true" ${param.status == 'true' ? 'selected' : ''}>완료</option>
							    <option value="false" ${param.status == 'false' ? 'selected' : ''}>대기</option>
							</select>
						  <input type="text" name="keyword" value="${param.keyword}" placeholder="검색어 입력">
						  <input type="date" name="startDateFrom" value="${param.startDateFrom}" />
						  ~
						  <input type="date" name="startDateTo" value="${param.startDateTo}" />
						  <button type="submit">검색</button>
						</form>
				    </div>
				        
			        <table class="table">
			            <thead>
			                <tr>
			                    <th style="font-weight: bold;">순번</th>
			                    <th style="font-weight: bold;">매칭</th>
			                    <th style="font-weight: bold;">제목</th>
			                    <th style="font-weight: bold;">작성자</th>
			                    <th style="font-weight: bold;">작성일자</th>
			                </tr>
			            </thead>
			            <tbody>
			            	<c:forEach var="kock" items="${kocks }" varStatus="status">
				                <tr id="kock-tr">
				                    <td>${status.count }</td>
					                    <c:if test="${ kock.matched eq true }">
					                    <td><span class="statusB">완료</span></td>
					                    </c:if>
					                    <c:if test="${ kock.matched eq false }">
					                    <td><span class="statusA">대기</span></td>
					                    </c:if>
					                    
					                    <c:if test="${empty user  }">
					                    <td><a href="${contextPath }/login">${kock.title }</a></td>
					                    </c:if>
					                    <c:if test="${!empty user}">
					                    <td><a href="${contextPath }/detailKockFarm?kockNum=${kock.kockNum }">${kock.title }</a></td>
					                    </c:if>
				                    <td>${kock.userName }</td>
				                    <fmt:parseDate value="${kock.createdAt}" pattern="yyyy-MM-dd" var="createdDate"/>
				                    <td><fmt:formatDate value="${createdDate }" pattern="yyyy-MM-dd"/></td>
				                </tr>
			                </c:forEach>
			            </tbody>
			        </table>
			        <div class="paging" id="pagingArea" style="text-align: center; margin-top: 20px;">
				    	  <c:if test="${pi.currentPage > 1}">
						    <a href="?page=1&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}&status=${param.status}">&laquo;</a>
						  </c:if>
						  <c:if test="${pi.startPage > 1}">
						    <a href="?page=${pi.startPage - 1}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}&status=${param.status}">&lt;</a>
						  </c:if>
						
						  <c:forEach begin="${pi.startPage}" end="${pi.endPage}" var="p">
						    <a href="?page=${p}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}&status=${param.status}"
						       class="${p == pi.currentPage ? 'active' : ''}">${p}</a>
						  </c:forEach>
						
						  <c:if test="${pi.endPage < pi.maxPage}">
						    <a href="?page=${pi.endPage + 1}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}&status=${param.status}">&gt;</a>
						  </c:if>
						  <c:if test="${pi.currentPage < pi.maxPage}">
						    <a href="?page=${pi.maxPage}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}&status=${param.status}">&raquo;</a>
						  </c:if>
					</div>
					<c:if test="${isSeller eq false }">
			        <div class="buttons">
			            <button class="btn btn-list" onclick="location.href='insertKockFarm'">글 등록</button>
			        </div>
			        </c:if>
			    </div>
	    	</div>
   		<jsp:include page="/header/footer.jsp" />
	</div>
</body>

</html>