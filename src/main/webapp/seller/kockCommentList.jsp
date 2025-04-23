<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>판매자|상품관리</title>
    <link rel="stylesheet" href="${contextPath }/seller/productList.css" />

    <link href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css" rel="stylesheet"
        integrity="sha384-jFvlDSY24z+oXMByOoX2Z1gM+M5NMd0uG7sDa4skv2mHXPuS0/RYXwPGLK0+Mgdc" crossorigin="anonymous" />

    <script src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"
        integrity="sha384-FcKnveOKVsyQDhaxWTmHPNxY0wtv3QwEmOUwRZ5g+QqTQvSKKmnkT0NiFcDCCIvg"
        crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    
	<script>
	$(document).ready(function() {
		
		//콕팜 버튼 눌렀을 때 
		$('.btn.add').on('click', function() {
		    window.location.href = '${contextPath}/insertProduct';
		});

	    // 1️ 헤더(전체선택) 체크박스 클릭
	    $('.selectAll').on('change', function() {
	        const isChecked = $(this).is(':checked');
	        $('.rowCheck').prop('checked', isChecked);  // 하위 체크박스 전부 체크/해제
	    });

	    // 2️ 하위 체크박스 클릭 시 → 헤더 체크박스 상태 갱신
	    $('#notice_table').on('change', '.rowCheck', function() {
	        const total = $('.rowCheck').length;
	        const checked = $('.rowCheck:checked').length;

	        if (total === checked) {
	            $('.selectAll').prop('checked', true);  // 모두 체크됐으면 헤더도 체크
	        } else {
	            $('.selectAll').prop('checked', false); // 하나라도 풀렸으면 헤더 해제
	        }
	    });
	    
	});
	</script>
</head>

<body>
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
    <div id="content">
    
        <div class="notice-header">
            <span id="title">콕팜 작성 댓글</span>
        </div>
		
		<div class="table-wrapper">
		
			<table id="notice_table" class="table">
			        <thead>
			          <tr>
			            <th><input type="checkbox" class="selectAll"></th>
			            <th style="font-weight: bold;">게시글번호</th>
			            <th style="font-weight: bold;">카테고리</th>
			            <th style="font-weight: bold;">댓글</th>
			            <th style="font-weight: bold;">등록일자</th>
			            <th style="font-weight: bold;">체결여부</th>
			          </tr>
			        </thead>
			        <tbody>
			          <c:forEach var="comment" items="${commentList}">
		                <tr>
		                    <td><div class="uiGridCell"><input type="checkbox" class="rowCheck"></div></td>
		                    <td><div class="uiGridCell kockNum"><a href="#">${comment.kockNum }</a></div></td>
		                    <td><div class="uiGridCell"><a href="#">${comment.cateName }</a></div></td>
		                    <td><div class="uiGridCell">${comment.name }</div></td>
		                    <td><div class="uiGridCell">${comment.createdAt }</div></td>
		                    <td><div class="uiGridCell">${comment.productNum }건</div></td>

		                    <td>
	                    		<c:if test="${comment.isMatched == true }">
	                    			<div class="uiGridCell"><span class="status on-sale">체결</span></div>
	                    		</c:if>
		                    </td>		                    
		                </tr>
			         </c:forEach>
			        </tbody>
			      </table>
      		</div>
        <div class="actions">
            <button class="btn delete">상태변경</button>
            <button class="btn edit">수정</button>
            <button class="btn add">상품등록</button>
        </div>
    </div>
</body>
</html>