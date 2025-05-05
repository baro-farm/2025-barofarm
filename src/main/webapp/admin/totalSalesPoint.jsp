<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/header/reset.css">
<link rel="stylesheet" href="${contextPath}/admin/totalSalesPoint.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<title>관리자 | 매출 조회</title>
</head>
<body>
	<jsp:include page="/header/adminHeader.jsp" />
	<header id="header">
			<jsp:include page="/header/adminSellerTop.jsp" />
	</header>
	<div id="content">
		<div id="wrapper">
		<div class="point_header">
			<span id="title">전체 매출</span>
		</div>
		<div class="point-a">
			<canvas id="pointChart" width="470" height="250"></canvas>
			<div class="point-b">
				<div class="point-c">
					<p class="p-title">당월 매출</p>
					<p class="p-content"><fmt:formatNumber value="${curPoint.currentMonth}" type="number" /> P </p>
					<p class="p-small">( 전월 대비 : ${curPoint.increasePercentage }%)</p>
				</div>
				<div class="point-c">
					<p class="p-title">전월 매출</p>
					<p class="p-content"><fmt:formatNumber value="${curPoint.lastMonth}" type="number" /> P</p>
				</div>
			</div>
		</div>
			<div class="selectBox">
				<!-- 필터링 옵션 -->
					<form id="sortForm" method="get" action="${contextPath}/totalSalesPoint">
						<select name="sort" id="sort">
							<option value="all" ${param.sort == 'all' ? 'selected' : ''}>전체</option>
							<option value="charging"  ${param.sort == 'charging' ? 'selected' : ''}>충전 내역</option>
							<option value="use"  ${param.sort == 'use' ? 'selected' : ''}>사용 내역</option>
						</select>
					</form>
			</div>
		
			<!-- 리스트 내역  -->
			<table id="point_table" class="table">
				<thead>
					<tr>
						<th style="font-weight: bold;">충전 일자</th>
						<th style="font-weight: bold;">유형</th>
						<th style="font-weight: bold;">유저 ID</th>
						<th style="font-weight: bold;">판매자 명</th>
						<th style="font-weight: bold;">상점 명</th>
						<th style="font-weight: bold;">사용처</th>
						<th style="font-weight: bold;">거래 금액</th>
						<th style="font-weight: bold;">잔여 금액</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${pointList}">
						<tr>	
							<td>${p.createdAt }</td>
							<td>${p.pType}</td>
							<td>${p.userId }</td>
							<td>${p.userName }</td>
							<td>${p.storeName }</td>
							<td>${p.type }</td>
							<td><fmt:formatNumber value="${p.usedPoint}" type="number" /></td>
							<td><fmt:formatNumber value="${p.currPoint}" type="number" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		<div id="paging">
					<!-- 이전 페이지 -->
					<c:choose>
					  <c:when test="${pageInfo.curPage > 10}">
					    <a href="?page=${pageInfo.curPage - 10}&sort=${sort}">&laquo;</a>
					  </c:when>
					  <c:otherwise>
					    <a class="disabled">&laquo;</a>
				</c:otherwise>
					</c:choose>
					<c:choose>
					  <c:when test="${pageInfo.curPage > 1}">
					    <a href="?page=${pageInfo.curPage - 1}&sort=${sort}">&lsaquo;</a>
					  </c:when>
					  <c:otherwise>
					 <a class="disabled">&lsaquo;</a>
				</c:otherwise>
					</c:choose>
					<!-- 페이지 번호 -->
					<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="page">
					  <c:choose>
					    <c:when test="${page == pageInfo.curPage}">
					      <a href="?page=${page}&sort=${sort}" class="select">${page}</a>
					    </c:when>
					    <c:otherwise>
					      <a href="?page=${page}&sort=${sort}" class="btn">${page}</a>
					    </c:otherwise>
					  </c:choose>
					</c:forEach>
					<!-- 다음 페이지 -->
					<c:choose>
					  <c:when test="${pageInfo.curPage < pageInfo.allPage}">
					    <a href="?page=${pageInfo.curPage + 1}&sort=${sort}">&rsaquo;</a>
					  </c:when>
					  <c:otherwise>
					 <a class="disabled">&rsaquo;</a>
				</c:otherwise>
					</c:choose>
					<c:choose>
					  <c:when test="${pageInfo.curPage < pageInfo.allPage}">
					    <a href="?page=${pageInfo.curPage + 10}&sort=${sort}">&raquo;</a>
					  </c:when>
					  <c:otherwise>
					 <a class="disabled">&raquo;</a>
				</c:otherwise>
					</c:choose>
				</div>
		</div>
	</div>
	
	<script>
	// statsJson은 서버에서 넘긴 JSON 문자열
		const stats = ${statsJson};
	  console.log(stats);
	  const labels = stats.map(item => item.month);
	  const chargeData = stats.map(item => item.chargePoint);
	  const useData = stats.map(item => item.usePoint);

	  const ctx = document.getElementById('pointChart').getContext('2d');
	  const pointChart = new Chart(ctx, {
	    type: 'line',
	    data: {
	      labels: labels,
	      datasets: [
	        {
	          label: '충전 포인트',
	          data: chargeData,
	          borderColor: 'rgb(75, 192, 192)',
	          backgroundColor: 'rgba(75, 192, 192, 0.2)',
	          tension: 0.3
	        },
	        {
	          label: '사용 포인트',
	          data: useData.map(p => Math.abs(p)),
	          borderColor: 'rgb(255, 99, 132)',
	          backgroundColor: 'rgba(255, 99, 132, 0.2)',
	          tension: 0.3
	        }
	      ]
	    },
	    options: {
	      responsive: false,
	      plugins: {
	        title: {
	          display: true,
	          text: '최근 5개월 충전/사용 포인트 추이'
	        }
	      }
	    }
	  });

	  document.getElementById("sort").addEventListener("change", function () {
	    document.getElementById("sortForm").submit();
	  });
	</script>
	
</body>
</html>