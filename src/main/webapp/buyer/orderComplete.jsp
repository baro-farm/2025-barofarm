<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 완료</title>
<link rel="stylesheet" href="${contextPath }/buyer/orderComplete.css">
<style>
    .complete-box {
        max-width: 600px;
        margin: 0 auto;
        background-color: #f8fff3;
        border: 2px solid #cbe8b2;
        border-radius: 12px;
        padding: 40px 30px;
        text-align: center;
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
    }

    .complete-box h1 {
        font-size: 28px;
        color: #4CAF50;
        margin-bottom: 20px;
    }

    .complete-box .order-info {
        font-size: 18px;
        margin: 20px 0;
        color: #333;
    }

    .complete-box .order-info strong {
        font-weight: bold;
        color: #111;
    }

    .complete-box .btn-group {
        margin-top: 30px;
    }

    .complete-box .btn-group a {
        display: inline-block;
        margin: 0 10px;
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        text-decoration: none;
        border-radius: 6px;
        transition: background-color 0.2s ease;
    }

    .complete-box .btn-group a:hover {
        background-color: #43a047;
    }
</style>
</head>

<body>
	<jsp:include page="/header/mainHeader.jsp" />
	<div class="container">
		<div class="sideMenu">
			<jsp:include page="/header/buyerMenu.jsp" />
		</div>
		<div id="content">
			<div class="complete-box">
				<h1>결제가 완료되었습니다</h1>

				<div class="order-info">
					주문번호: <strong>${param.transactionId}</strong>
				</div>

				<div class="btn-group">
					<a href="${contextPath}/main">메인으로</a>
					<a href="${contextPath}/buyerOrderList">주문 내역 보기</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>