<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    
<link rel="stylesheet" href="${contextPath}/header/adminHeader.css">
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
            <li><a href="${contextPath }/userList">회원 정보 검색</a></li>
            <li><a href="${contextPath }/storeList">스토어 정보 검색</a></li>
            <li><a href="${contextPath }/noticeList">공지사항</a></li>
            <li><a href="${contextPath }/customerServiceList">고객센터</a></li>
            <li><a href="${contextPath }/adminAdsList">배너 광고</a></li>
            <li><a href="#">전체 매출</a></li>
            <li><a href="${contextPath }/adminProdOrderList">일반 상품 주문 조회</a></li>
            <li><a href="${contextPath }/adminPackOrderList">꾸러미 상품 주문 조회</a></li>
        </ul>
    </div>
</div>