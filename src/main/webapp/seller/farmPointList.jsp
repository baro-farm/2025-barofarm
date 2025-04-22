<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
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
			<jsp:include page="/header/sellerHeader.jsp" />
				<div class="content" style="margin: 0 auto;">
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
				            <span class="point-value">💰 9,290P</span>
				            <button class="charge-btn">충전하기</button>
				        </div>
				
				        
				
				
				        <div class="history-section">
				            <h3>포인트 내역</h3>
				            <hr>
				
				            <table id="point-table" class="display nowrap" >
				                <thead>
				                    <tr>
				                        <th>날짜</th>
				                        <th>상세내역</th>
				                        <th>포인트</th>
				                        <th>잔여 포인트</th>
				                    </tr>
				                </thead>
				                <tbody>
				                    <tr>
				                        <td class="point-date"><span class="status use">사용</span>2025.04.01</td>
				                        <td> 원해요 알림 수수료 차감</td>
				                        <td class="minus">-200P</td>
				                        <td>9,290P</td>
				                    </tr>
				                    <tr>
				                        <td class="point-date"><span class="status use">사용</span>2025.04.02</td>
				                        <td>원해요 결제 수수료 차감2</td>
				                        <td class="minus">-2000P</td>
				                        <td>9,490P</td>
				                    </tr>
				                    <tr>
				                        <td class="point-date"><span class="status charge">충전</span>2025.04.01</td>
				                        <td>포인트 충전</td>
				                        <td class="plus">+10000P</td>
				                        <td>11,490P</td>
				                    </tr>
				                </tbody>
				            </table>
				        </div>
				
				        <div id="table_paging" class="pagination">
				            <span class="prev">&lt;</span>
				            <span class="page active">1</span>
				            <span class="page">2</span>
				            <span class="page">3</span>
				            <span class="next">&gt;</span>
				        </div>
			    </div>
		</div>
	</div>				    
</body>
</html>