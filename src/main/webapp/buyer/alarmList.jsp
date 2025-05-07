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
    <link rel="stylesheet" href="${contextPath }/buyer/alarmList.css">
</head>
<body>
	<jsp:include page="/header/mainHeader.jsp"/>
	<div class="container">
		<div class="wrapper">
			<div class="sideMenu">
				<jsp:include page="/header/buyerMenu.jsp" />
			</div> 
    <div class="content">
     	<div class="header">알림 내역</div>
     
      		<div class="selectBox">
			<form method="get" action="${contextPath}/buyerAlarmList" class="searchForm" >
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
			</div>


      <div class=tableWrapper>
      <table id="banner-table" class="table">
          <thead>
              <tr>
                 <th style="font-weight: bold;">순번</th>
                 <th style="font-weight: bold;">유형</th>
                 <th style="font-weight: bold;">보낸 사람</th>
                 <th style="font-weight: bold;">알림</th>
                 <th style="font-weight: bold;">알림 내용</th>
                 <th style="font-weight: bold;">날짜</th>
                 <th style="font-weight: bold;">확인 여부</th>
             </tr>
         </thead>
         <tbody>
         <c:choose>
          <c:when test="${empty alarmList}">
		    <tr>
		      <td colspan="7" style="text-align: center;">알림이 없습니다.</td>
		    </tr>
		  </c:when>
		  <c:otherwise>
            <c:forEach var="alarm" items="${alarmList }" varStatus="status">
	              <tr>
	                  <td>${status.count }</td>		                
	                  <td>${alarm.type }</td>
	                  <td>${alarm.userName }</td>
	                  <td>
	                  <a href="${contextPath}/detailKockFarm?kockNum=${alarm.targetNum}">
	                  ${alarm.content2 }
	                  </a>
	                  </td>
	                  <td>${alarm.content1 }</td>
	                  
	                  <td>${alarm.createdAt.toLocalDate() }</td>
	                  <c:if test="${alarm.checked eq false}">
	                  <td><button onclick="markAsRead(${alarm.alarmNum}, this)" class="close-notif">확인</button></td>
	                  </c:if>
	                  <c:if test="${alarm.checked eq true}">
	                  <td>읽음</td>
	                  </c:if>
	              </tr>
	            </c:forEach>
            </c:otherwise>
            </c:choose>
         </tbody>
     </table>
     </div>

        <div class="pagination" id="pagingArea" style="text-align: center; margin-top: 20px;">
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
	</div>
	</div>
	<script>
async function markAsRead(alarmNum, btnElement) {
    const res = await fetch(`${contextPath}/checkAlarm`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ alarmNum })
    });

    if (res.ok) {
        // 버튼 있는 <td>를 "읽음"으로 변경
        const td = btnElement.closest('td');
        td.textContent = '읽음';
    } else {
        alert('알림 확인 처리 실패');
    }
}
</script>
</body>
</html>
