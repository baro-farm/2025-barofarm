<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>팜포인트 조회</title>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/seller/farmPointList.css" />
</head>
<body>
	<div class="container">
		<div class="wrapper">
			<div class="inner_body">
			        <div class="sidebar">
						<jsp:include page="/header/sellerHeader.jsp" />
			        </div>
		    </div>
		    <header id="header">
		        <div id="info">
		            <span id="email">kosta@kosta.com</span>
		            <span>내 정보</span>
		            <span>로그아웃</span>
		        </div>
		    </header>
    			<div class="content">
		        <div class="point-header">
		            <h1 class="title">팜포인트</h1>
		            <div class="subscribe-box">
		                <span class="question-icon">❓</span>
		                <button class="subscribe-btn">
		                    팜링 구독하기
		                </button>
		            </div>
		            <div class="subscribe-box">
		                <button class="subscribed-btn">
		                    ✔ 팜링 구독중
		                </button>
		            </div>
		        </div>
		        <div class="point-box">
		            <span class="point-label">보유 포인트</span>
		            <div>
		            <span class="point-value">💰 ${point }P</span>
		            <button class="charge-btn">충전하기</button>
		            </div>
		        </div>
		
		        <div class="history-section">
		        	<div>
   			            <h3>포인트 내역</h3>
		        		<form method="get" action="${contextPath}/farmPointList" >
						  <select name="searchType">
						    <option value="usedPoint" ${param.searchType == 'usedPoint' ? 'selected' : ''}>사용/충전</option>
						    <option value="type" ${param.searchType == 'type' ? 'selected' : ''}>상세내역</option>
						  </select>
						  <input type="text" name="keyword" value="${param.keyword}" placeholder="검색어 입력">
						  <input type="date" name="startDateFrom" value="${param.startDateFrom}" />
						  ~
						  <input type="date" name="startDateTo" value="${param.startDateTo}" />
						  <button type="submit">검색</button>
						</form>
		        	</div>
		            <hr>
		
		            <table id="point-table" class="display nowrap" >
		                <thead>
		                    <tr>
		                        <th></th>
		                        <th>날짜</th>
		                        <th>상세내역</th>
		                        <th>포인트</th>
		                        <th>잔여 포인트</th>
		                    </tr>
		                </thead>
		                <tbody>
			              <c:forEach var="up" items="${usePointList}" varStatus="status">
			              	<tr>
			              		<c:if test="${up.usedPoint < 0 }">
			              		<td class="point-date"><span class="status use">사용</span></td>
			              		</c:if>
			              		<c:if test="${up.usedPoint > 0 }">
			              		<td class="point-date"><span class="status charge">충전</span></td>
			              		</c:if>
		                        <td>${up.createdAt.toLocalDate()}</td>
		                        <td>${up.type }</td>
			              		<c:if test="${up.usedPoint < 0 }">				                        
		                        <td class="minus">${up.usedPoint }P</td>
		                        </c:if>
		                        <c:if test="${up.usedPoint > 0 }">				                        
		                        <td class="plus">+${up.usedPoint }P</td>
		                        </c:if>
		                        <td>${up.currPoint }P</td>
		                    </tr>
			              </c:forEach>				                    
		                </tbody>
		            </table>
		        </div>
				<div class="paging" id="pagingArea" style="text-align: center; margin-top: 20px;">
				  <c:if test="${pi.startPage > 1}">
				    <a href="?page=${pi.startPage - 1}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}">&laquo;</a>
				  </c:if>
				
				  <c:forEach begin="${pi.startPage}" end="${pi.endPage}" var="p">
				    <a href="?page=${p}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}"
				       class="${p == pi.currentPage ? 'active' : ''}">${p}</a>
				  </c:forEach>
				
				  <c:if test="${pi.endPage < pi.maxPage}">
				    <a href="?page=${pi.endPage + 1}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}">&raquo;</a>
				  </c:if>
				</div>
		    </div>
		</div>
	</div>				    
</body>
</html>