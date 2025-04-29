<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>판매자|꾸러미관리</title>
    <link rel="stylesheet" href="${contextPath }/seller/packageList.css" />

    <link href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css" rel="stylesheet"
        integrity="sha384-jFvlDSY24z+oXMByOoX2Z1gM+M5NMd0uG7sDa4skv2mHXPuS0/RYXwPGLK0+Mgdc" crossorigin="anonymous" />

    <script src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"
        integrity="sha384-FcKnveOKVsyQDhaxWTmHPNxY0wtv3QwEmOUwRZ5g+QqTQvSKKmnkT0NiFcDCCIvg"
        crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    
	<script>
	$(document).ready(function() {
	    // 마우스 올렸을 때
	    $('#package_table').on('mouseenter', 'tr', function() {
	        const packageNum = $(this).data('packagenum');
	        // 같은 packageNum 가진 tr들에 hovered 클래스 추가
	        $(`tr[data-packagenum="\${packageNum}"]`).addClass('hovered');
	    });

	    // 마우스 내렸을 때
	    $('#package_table').on('mouseleave', 'tr', function() {
	        const packageNum = $(this).data('packagenum');
	        // 같은 packageNum 가진 tr들에서 hovered 클래스 제거
	        $(`tr[data-packagenum="\${packageNum}"]`).removeClass('hovered');
	    });
	    
		//글쓰기 버튼 눌렀을 때 
		$('.btn.add').on('click', function() {
		    window.location.href = '${contextPath}/insertPackage';
		});
		
		//수정버튼 눌렀을 때. 체크박스는 1개만 가능
		 $('.btn.edit').on('click', function() {
		        const $checked = $('#package_table tbody input[type="checkbox"]:checked');
		        if ($checked.length === 0) {
		            alert('수정할 상품을 선택해주세요.');
		            return;
		        }
		        if ($checked.length > 1) {
		            alert('하나의 상품만 선택해주세요.');
		            return;
		        }
		        const packageNum = $checked.closest('tr').find('.packageNum a').text().trim();
		        window.location.href = `${contextPath}/updatePackage?packageNum=\${packageNum}`;
		    });
		
		
	    // +, - 버튼 클릭 이벤트
	    $('#package_table').on('click', 'button', function() {
	        const $button = $(this);
	        const $row = $button.closest('tr');
	        const $stockInputBox = $row.find('.stock');
	        let stock = parseInt($stockInputBox.val());
	
	        if ($button.text() === '+') {
	            stock += 1;
	        } else if ($button.text() === '-') {
	            if (stock > 0) stock -= 1;
	        }
	        $stockInputBox.val(stock); // 화면에 재고 수량 반영
	    });
	
	    // 저장 버튼 클릭
	    $('#package_table').on('click', 'button:contains("저장")', function() {
	        const $row = $(this).closest('tr');
	        const packageNum = $row.find('.packageNum a').text().trim();
	        const $stockInputBox = $row.find('.stock');
	        let stock = parseInt($stockInputBox.val());
	      
	        // 음수일 때 막기
	        if (stock < 0) {
	            alert('재고는 0 이상이어야 합니다');
	            return;  
	        }
	        
	        $.ajax({
	            url: '${contextPath}/updateStock',
	            method: 'POST',
	            data: { packageNum: packageNum, stock: stock },
	            success: function(response) {
	                if (response ==='success') {
	                    alert('재고가 성공적으로 저장되었습니다!');
	                } else {
	                    alert('저장 실패!');
	                }
	            },
	            error: function() {
	                alert('서버 오류 발생!');
	            }
	        });
	    });
	    
	 // 상태변경 버튼 클릭(여러개 선택 가능)
	    $('.btn.delete').on('click', function() {
	        const $checked = $('#package_table tbody input[type="checkbox"]:checked');
	        if ($checked.length === 0) {
	            alert('상태를 변경할 상품을 선택해주세요.');
	            return;
	        }

	        // 선택된 상품들의 packageNum과 status 수집
	        let packages = [];
	        $checked.each(function() {
	            const $row = $(this).closest('tr');
	            const packageNum = $row.find('.packageNum a').text().trim();
	            const statusText = $row.find('.status').text().trim();
	            const currentStatus = (statusText === '판매중') ? true : false;

	            // ✅ 상태 반전 (true → false, false → true)
	            const newStatus = !currentStatus;

	            packages.push({ packageNum: packageNum, status: newStatus });
	        });

	        // 서버로 전송 (JSON)
	        $.ajax({
	            url: '${contextPath}/updateProdStatus',
	            method: 'POST',
	            contentType: 'application/json',
	            data: JSON.stringify(packages),
	            success: function(response) {
	                if (response === 'success') {
	                    alert('상태가 변경되었습니다');
	                    location.reload();  // 새로고침으로 반영
	                } else {
	                    alert('상태 변경 실패!');
	                }
	            },
	            error: function() {
	                alert('서버 오류 발생!');
	            }
	        });
	    });
	    
	    
	    // 1️ 헤더(전체선택) 체크박스 클릭
	    $('.selectAll').on('change', function() {
	        const isChecked = $(this).is(':checked');
	        $('.rowCheck').prop('checked', isChecked);  // 하위 체크박스 전부 체크/해제
	    });

	    // 2️ 하위 체크박스 클릭 시 → 헤더 체크박스 상태 갱신
	    $('#package_table').on('change', '.rowCheck', function() {
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
	
	<jsp:include page="/header/sellerHeader.jsp" />
    
    <header id="header">
			<jsp:include page="/header/adminSellerTop.jsp" />
    </header>
    
    <div id="content">
        <div class="noticeHeader">
            <span id="title">꾸러미 관리</span>
        </div>
		<div class="filterWrapper">
		    <form id="sortForm" method="get" action="${contextPath}/sellerpackageList">
		       	<select name="sellStat" id="sellStat" onchange="this.form.submit()">
		            <option value="all" ${param.sellStat == 'all' ? 'selected' : ''}>전체</option>
		            <option value="sales" ${param.sellStat == 'sales' ? 'selected' : ''}>판매중</option>
		            <option value="salesStop" ${param.sellStat == 'salesStop' ? 'selected' : ''}>판매중단</option>
		        </select>
		        <select name="sort" id="sortSelect" onchange="this.form.submit()">
		            <option value="new" ${param.sort == 'new' ? 'selected' : ''}>최신등록순</option>
		            <option value="lowPrice" ${param.sort == 'lowPrice' ? 'selected' : ''}>낮은가격순</option>
		            <option value="highPrice" ${param.sort == 'highPrice' ? 'selected' : ''}>높은가격순</option>
		            <option value="sales" ${param.sort == 'sales' ? 'selected' : ''}>누적판매순</option>
		            <option value="reviewCount" ${param.sort == 'reviewCount' ? 'selected' : ''}>리뷰많은순</option>
		            <option value="rating" ${param.sort == 'rating' ? 'selected' : ''}>평점높은순</option>
		        </select>

		    </form>
		</div>
		<div class="tableWrapper">
		
		<table id="package_table" class="table">
		        <thead>
		          <tr>
		            <th><input type="checkbox" class="selectAll"></th>
		            <th style="font-weight: bold;">품번</th>
		            <th style="font-weight: bold;">상품명</th>
		            <th style="font-weight: bold;">카테고리</th>
		            <th style="font-weight: bold;">가격</th>	            
		          	<th style="font-weight: bold;">재고</th>
		            <th style="font-weight: bold;">판매건수</th>
		            <th style="font-weight: bold;">리뷰건수</th>
		          	<th style="font-weight: bold;">등록일자</th>
		          	<th style="font-weight: bold;">수정일자</th>
		          </tr>
		        </thead>
		        <tbody>
		          <c:forEach var="p" items="${packageList}">
		          		<tr data-packagenum="${p.packageNum }">
					    	<td><input type="checkbox" class="rowCheck"></td>
					        <td class="packageNum"><a href="${contextPath }/detailpackage?packageNum=${p.packageNum}">${p.packageNum}</a></td>
					        <td><a href="${contextPath }/detailpackage?packageNum=${p.packageNum}">${p.packageName}</a></td>
					        <td>${p.cateName}</td>
					        <td><fmt:formatNumber value="${p.packagePrice}" type="number" />원</td>     
					        
					       <td>
                       			<div class="uiGridCell"><button class="stockBtn">-</button> <input type='number' min='0'  class="stock" value="${p.stock}"> <button class="stockBtn">+</button> <button class="saveBtn">저장</button></div>
                       		</td>
					        <td>${p.salesVolume}건</td>
					        <td>★ ${p.avgRating}(${p.reviewCount})</td>
					        <td>${p.createdAt}</td>
					        <td>${p.updatedAt}</td>         		
		          		</tr>
		         </c:forEach>
		        </tbody>
		      </table>
      		</div>
        <div class="actions">
            <button class="btn edit">수정</button>
            <button class="btn add">상품등록</button>
        </div>
        
        <c:set var="startPage" value="${page - 2}" />
		<c:set var="endPage" value="${page + 2}" />
		
		<c:if test="${startPage < 1}">
		    <c:set var="endPage" value="${endPage + (1 - startPage)}" />
		    <c:set var="startPage" value="1" />
		</c:if>
		
		<c:if test="${endPage > totalPages}">
		    <c:set var="startPage" value="${startPage - (endPage - totalPages)}" />
		    <c:set var="endPage" value="${totalPages}" />
		</c:if>
		
		<c:if test="${startPage < 1}">
		    <c:set var="startPage" value="1" />
		</c:if>
		
		<div class="pagination">		    
		    <!-- << 현재 페이지 - 5 -->
			<c:if test="${currentPage > 1}">
				<a href="?page=${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}&sellStat=${param.sellStat}&sort=${param.sort}">&laquo;</a>
			</c:if>
			
			<!-- < 이전 페이지 -->
			<c:if test="${currentPage > 1}">
				<a href="?page=${currentPage - 1}&sellStat=${param.sellStat}&sort=${param.sort}">&lsaquo;</a>
			</c:if>
			
			<!-- 페이지 번호 -->
			<c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
				<a href="?page=${i}&sellStat=${param.sellStat}&sort=${param.sort}" class="${currentPage == i ? 'active' : ''}">${i}</a>
			</c:forEach>
			
			<!-- > 다음 페이지 -->
			<c:if test="${currentPage < totalPages}">
			    <a href="?page=${currentPage + 1}&sellStat=${param.sellStat}&sort=${param.sort}">&rsaquo;</a>

			</c:if>
			
			<!-- >> 현재 페이지 + 5 -->
			<c:if test="${currentPage < totalPages}">
			    <a href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}&sellStat=${param.sellStat}&sort=${param.sort}">&raquo;</a>
			</c:if>
		</div>
		
    </div>
</body>
</html>