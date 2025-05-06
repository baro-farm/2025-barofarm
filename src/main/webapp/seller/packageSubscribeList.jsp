<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>판매자 | 꾸러미 구독 관리</title>

<link rel="stylesheet" href="${contextPath }/seller/packageSubscribeList.css" />
<link href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css" rel="stylesheet" />
<script src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"	></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
        $(document).ready(function() {
		    function getMonthsDifference(date1, date2) {
		        var start = new Date(date1);
		        var end = new Date(date2);
		        var years = end.getFullYear() - start.getFullYear();
		        var months = end.getMonth() - start.getMonth();
		        return years * 12 + months + 1; // +1: 시작, 종료월 모두 포함
		    }
	
		    $('.date-range').each(function() {
		        var startDate = $(this).data('start');
		        var endDate = $(this).data('end');
		        var months = getMonthsDifference(startDate, endDate);
		        $(this).text(startDate + ' ~ ' + endDate + ' (' + months + '회차)');
		    });
		});
    </script>
</head>
<body>
	<jsp:include page="/header/sellerHeader.jsp" />
	<header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
	</header>

	<div id="content">
		<div class="notice-header">
			<span id="title">꾸러미 구독 관리</span>
		</div>
		<div class="filterBox">
			<form method="get" action="${contextPath}/sellerPackSubscribeList"
				style="display: flex; width: 100%; justify-content: space-between;">
				<!-- 왼쪽: 조회기간 -->
				<div class="filterSection leftSection">
					<div class="buttonGroup">
						<label>조회기간</label>
						<button type="button" onclick="setDateRange('today')">오늘</button>
						<button type="button" onclick="setDateRange('1week')">1주일</button>
						<button type="button" onclick="setDateRange('1month')">1개월</button>
						<button type="button" onclick="setDateRange('3months')">3개월</button>
					</div>
					<div class="dateGroup">
						<input type="date" name="startDate" value="${param.startDate}">
						~ <input type="date" name="endDate" value="${param.endDate}">
					</div>
				</div>

				<!-- 오른쪽: 상세조건 -->
				<div class="filterSection rightSection">
					<div class="filterGroup">
						<label>상세조건</label> <select name="searchType" id="searchType">
							<option value="all"
								${param.searchType == 'all' ? 'selected' : ''}>전체</option>
							<option value="packageNum"
								${param.searchType == 'packageNum' ? 'selected' : ''}>상품번호</option>
							<option value="packageName"
								${param.searchType == 'packageName' ? 'selected' : ''}>상품명</option>
							<option value="userName"
								${param.searchType == 'userName' ? 'selected' : ''}>구매자명</option>
						</select>
					</div>
					<input type="text" name="searchKeyword" placeholder="검색어 입력" value="${param.searchKeyword}">
				</div>
				<div class="filterGroup">
	      <button type="submit" class="searchBtn">검색</button>
	    </div>
			</form>
		</div>
		<div class="tableWrapper">
			<table id="notie_table" class="table">
				<thead>
					<tr>
						<th style="font-weight: bold;">품번</th>
						<th style="font-weight: bold;">카테고리</th>
						<th style="font-weight: bold;">제품명</th>
						<th style="font-weight: bold;">판매기간</th>
						<th style="font-weight: bold;">구매자ID</th>
						<th style="font-weight: bold;">구매자 이름</th>
						<th style="font-weight: bold;">구독시작일</th>
						<th style="font-weight: bold;">회차정보</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="s" items="${packSubList }">
						<tr>
							<td><div class="uiGridCell packageNum"
									data-packagenum="${s.packageNum }">${s.packageNum}</div></td>
							<td>${s.packageUnit }</td>
							<td><div class="uiGridCell packageNum">${s.packageName}</div></td>
							<td class="date-range" data-start="${s.startDate}"
								data-end="${s.endDate}"></td>
							<td><div class="uiGridCell id">${s.userId}</div></td>
							<td><div class="uiGridCell id">${s.userName}</div></td>
							<td><div class="uiGridCell">${s.subStartDate}</div></td>
							<td><div class="uiGridCell">${s.subRound}</div></td>
						</tr>
					</c:forEach>

				</tbody>

			</table>
		</div>
		
		<div id="paging">
					<!-- 이전 페이지 -->
					<c:choose>
					  <c:when test="${pageInfo.curPage > 10}">
					    <a href="${pagingUrl}&page=${pageInfo.curPage - 10}&sort=${sort}">&laquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&laquo;</a>
					  </c:otherwise>
					</c:choose>
					<c:choose>
					  <c:when test="${pageInfo.curPage > 1}">
					    <a href="${pagingUrl}&page=${pageInfo.curPage - 1}&sort=${sort}">&lsaquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&lsaquo;</a>
					  </c:otherwise>
					</c:choose>
					<!-- 페이지 번호 -->
					<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="page">
					  <c:choose>
					    <c:when test="${page == pageInfo.curPage}">
					      <a href="${pagingUrl}&page=${page}&sort=${sort}" class="select">${page}</a>
					    </c:when>
					    <c:otherwise>
					      <a href="${pagingUrl}&page=${page}&sort=${sort}" class="btn">${page}</a>
					    </c:otherwise>
					  </c:choose>
					</c:forEach>
					<!-- 다음 페이지 -->
					<c:choose>
					  <c:when test="${pageInfo.curPage < pageInfo.allPage}">
					    <a href="${pagingUrl}&page=${pageInfo.curPage + 1}&sort=${sort}">&rsaquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&rsaquo;</a>
					  </c:otherwise>
					</c:choose>
					<c:choose>
					  <c:when test="${pageInfo.curPage < pageInfo.allPage}">
					    <a href="${pagingUrl}&page=${pageInfo.curPage + 10}&sort=${sort}">&raquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&raquo;</a>
					  </c:otherwise>
					</c:choose>
				</div>
	</div>
	<script>
		function setDateRange(range) {
		    const today = new Date();
		    let start = new Date();
		
		    if (range === 'today') {
		        start = today;
		    } else if (range === '1week') {
		        start.setDate(today.getDate() - 7);
		    } else if (range === '1month') {
		        start.setMonth(today.getMonth() - 1);
		    } else if (range === '3months') {
		        start.setMonth(today.getMonth() - 3);
		    }
		
		    const formatDate = date => date.toISOString().split('T')[0];
		    document.querySelector('input[name="startDate"]').value = formatDate(start);
		    document.querySelector('input[name="endDate"]').value = formatDate(today);
		}
		</script>
</body>
</html>