<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    
<link rel="stylesheet" href="${contextPath}/header/sellerHeader.css">
<div class="inner_body">
    <div class="sidebar">
        <div class="logo">
            <div>
            	<a href="${contextPath}/main">
 	               <img src="${contextPath }/img/barologo2.png" alt="barologo1" border="0" width="100">
				</a>
            </div>
        </div>
        <ul>
            <li><a href="${contextPath }/sellerProductList">상품 관리</a></li>
            <li><a href="${contextPath }/sellerProdOrderList">상품 주문관리</a></li>
            <li><a href="${contextPath}/sellerPackageList">꾸러미 판매 관리</a></li>
            <li><a href="${contextPath}/sellerPackOrderList">꾸러미 주문 관리</a></li>
            <li><a href="${contextPath}/sellerPackSubscribeList">꾸러미 구독 관리</a></li>
            <li><a href="#">리뷰</a></li>
            <li><a href="#">콕팜</a></li>
            <li><a href="${contextPath }/farmPointList">팜포인트</a></li>
            <li><a href="${contextPath }/sellerAlarmList">알림 내역</a></li>
            <li><a href="#">문의 내역</a></li>
            <li><a href="#">취소 내역</a></li>
            <li><a href="${contextPath }/detailStoreInfo">스토어 정보</a></li>
            <li><a href="${contextPath }/sellerAdsList">배너 광고</a></li>
        </ul>
    </div>
</div>
