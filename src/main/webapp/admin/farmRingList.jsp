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
    <link rel="stylesheet" href="${contextPath }/admin/farmRingList.css">
</head>
<body>
	<jsp:include page="/header/adminHeader.jsp" />
	<header id="header">
			<jsp:include page="/header/adminSellerTop.jsp" />
    </header>    
    <div id="content">
     <div class="pkHeader">
         <span id="title">콕팜링 구독자 조회</span>
      </div>
      		<div class="selectBox">
			<form method="get" action="${contextPath}/sellerAlarmList" class="searchForm" >
			 <select name="searchType">
			   <option value="type" ${param.searchType == 'type' ? 'selected' : ''}>유형</option>
			 </select>
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
                 <th style="font-weight: bold;">이름</th>
                 <th style="font-weight: bold;">스토어이름</th>
                 <th style="font-weight: bold;">사업자 번호</th>
                 <th style="font-weight: bold;">전화번호</th>
             </tr>
         </thead>
         <tbody>
         <c:choose>
          <c:when test="${empty farmRingList}">
		    <tr>
		      <td colspan="7" style="text-align: center;">알림이 없습니다.</td>
		    </tr>
		  </c:when>
		  <c:otherwise>
            <c:forEach var="ring" items="${farmRingList }" varStatus="status">
              <tr>
                  <td>${status.count }</td>		                
                  <td>${ring.userName }</td>
                  <td><a href="${contextPath}/storeProductList?sellerNum=${ring.sellerNum }">${ring.storeName }</a></td>
                  <td>${ring.businessNum }</td>
                  <td>${ring.phone }</td>
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

</body>
</html>
