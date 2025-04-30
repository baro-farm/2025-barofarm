<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>알림 내역</title>

    <!-- ✅ jQuery & DataTables 라이브러리 -->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <link rel="stylesheet" href="${contextPath }/seller/alarmList.css">
	<link rel="stylesheet" href="${contextPath }/common/modal.css">
</head>
<body>
	<jsp:include page="/header/sellerHeader.jsp" />
	<header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
	</header>	    
    <div id="content">
     <div class="container-header">
         <h2 class="title">알림 내역</h2>
         <div class="subscribe-box">
         	당신의 알람을 확인하세용~
          </div>
      </div>
		<form method="get" action="${contextPath}/sellerAlarmList" class="searchForm" >
		 <select name="searchType">
		   <option value="type" ${param.searchType == 'type' ? 'selected' : ''}>유형</option>
		 </select>
		<!-- 매칭 상태 필터 추가 -->
		<select name="status">
		    <option value="all" ${param.status == 'all' ? 'selected' : ''}>전체</option>
		    <option value="true" ${param.status == 'true' ? 'selected' : ''}>확인</option>
		    <option value="false" ${param.status == 'false' ? 'selected' : ''}>미확인</option>
		</select>
		 <input type="text" name="keyword" value="${param.keyword}" placeholder="검색어 입력">
		 <input type="date" name="startDateFrom" value="${param.startDateFrom}" />
		 ~
		 <input type="date" name="startDateTo" value="${param.startDateTo}" />
		  <button type="submit">검색</button>
		</form>
      <div class="history-section">
          <h3>알림 내역</h3>
      <table id="banner-table" class="table">
          <thead>
              <tr>
                 <th style="font-weight: bold;">순번</th>
                 <th style="font-weight: bold;">보낸 사람</th>
                 <th style="font-weight: bold;">유형</th>
                 <th style="font-weight: bold;">알림 제목</th>
                 <th style="font-weight: bold;">알림 내용</th>
                 <th style="font-weight: bold;">날짜</th>
                 <th style="font-weight: bold;">확인 여부</th>
             </tr>
         </thead>
         <tbody>
            <c:forEach var="alarm" items="${alarmList }" varStatus="status">
              <tr>
                  <td>${status.count }</td>		                
                  <td>김당근</td>
                  <td>${alarm.type }</td>
                  <td>${alarm.content2 }</td>
                  <td>${alarm.content1 }</td>
                  <td>${alarm.createdAt }</td>
                  <td><button>확인</button></td>
              </tr>
            </c:forEach>
         </tbody>
     </table>
     </div>
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
  </div>

</body>
</html>
