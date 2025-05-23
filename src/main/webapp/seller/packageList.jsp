<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>판매자 | 꾸러미관리</title>
	<link rel="stylesheet" href="${contextPath}/seller/packageList.css" />
	<link href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css" rel="stylesheet" />
	<script src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"></script>
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
	       
	        if (!packageNum) {
	            alert('상품 번호를 찾을 수 없습니다.');
	            return;
	        }
	        
	        const $stockInputBox = $row.find('.stock');
	        let stock = parseInt($stockInputBox.val());
	      
	        // 음수일 때 막기
	        if (isNaN(stock) || stock < 0) {
	            alert('재고는 0 이상이어야 합니다');
	            return;  
	        }
	        
	        $.ajax({
	            url: '${contextPath}/updatePackStock',
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
	});
	
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
		<div class="pkHeader">
			<span id="title">꾸러미 관리</span>
		</div>
		<div class="selectBox">
		<button class="btn add">상품등록</button>
			<!-- 필터링 옵션 -->
				<form id="sortForm" method="get" action="${contextPath}/sellerPackageList">
					<select name="sellStat" id="sellStat" onchange="this.form.submit()">
						<option value="all" ${param.sellStat == 'all' ? 'selected' : ''}>전체</option>
						<option value="sales" ${param.sellStat == 'sales' ? 'selected' : ''}>판매중</option>
						<option value="salesStop" ${param.sellStat == 'salesStop' ? 'selected' : ''}>판매종료</option>
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
			<!-- 리스트 내역  -->
			<table id="package_table" class="table">
				<thead>
					<tr>
						<th style="font-weight: bold;">품번</th>
						<th style="font-weight: bold;">카테고리</th>
						<th style="font-weight: bold;" colspan="2">상품명</th>
						<th style="font-weight: bold;">판매기간</th>
						<th style="font-weight: bold;">가격</th>
						<th style="font-weight: bold;">재고</th>
						<th style="font-weight: bold;">판매상태</th>
						<th style="font-weight: bold;">판매건수</th>
						<th style="font-weight: bold;">리뷰건수</th>
						<th style="font-weight: bold;">등록일자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${packageList}">
						<tr data-packagenum="${p.packageNum }">	
							<td class="packageNum"><a href="${contextPath }/detailPackage?packageNum=${p.packageNum}">${p.packageNum}</a></td>
							<td>${p.cateName}</td>
							<td><a href="${contextPath }/detailPackage?packageNum=${p.packageNum}">${p.packageName}</a></td>
							<td><a href="${contextPath}/updatePackage?packageNum=${p.packageNum}" class="pkEdit"><button>수정</button></a></td>
							<td class="date-range" data-start="${p.startDate}" data-end="${p.endDate}"></td>
							<td><fmt:formatNumber value="${p.packagePrice}" type="number" />원</td>
							<td>
								<div class="uiGridCell">
									<button class="stockBtn">-</button>
									<input type='number' min='0' class="stock" value="${p.stock}">
									<button class="stockBtn">+</button>
									<button class="saveBtn">저장</button>
								</div>
							</td>
							<td>
								<div class="${p.sellStatus == '판매 종료' ? 'red' : 'green'}"></div>${p.sellStatus }
							</td>
							<td>${p.salesVolume}건</td>
							<td>★ ${p.avgRating}(${p.reviewCount})</td>
							<td>${p.createdAt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
			<c:choose>
				<c:when test="${currentPage > 1}">
					<a href="?page=${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}&sellStat=${param.sellStat}&sort=${param.sort}">&laquo;</a>
				</c:when>
				<c:otherwise>
					    <a class="disabled">&laquo;</a>
				</c:otherwise>
			</c:choose>
			<!-- < 이전 페이지 -->
			<c:choose>
				<c:when test="${currentPage > 1}">
					<a href="?page=${currentPage - 1}&sellStat=${param.sellStat}&sort=${param.sort}">&lsaquo;</a>
				</c:when>
				<c:otherwise>
					 <a class="disabled">&lsaquo;</a>
				</c:otherwise>
			</c:choose>
			<!-- 페이지 번호 -->
			<c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
				<a href="?page=${i}&sellStat=${param.sellStat}&sort=${param.sort}" class="${currentPage == i ? 'active' : ''}">${i}</a>
			</c:forEach>
			<!-- > 다음 페이지 -->
			<c:choose>
				<c:when test="${currentPage < totalPages}">
					<a href="?page=${currentPage + 1}&sellStat=${param.sellStat}&sort=${param.sort}">&rsaquo;</a>
				</c:when>
				<c:otherwise>
					    <a class="disabled">&rsaquo;</a>
				</c:otherwise>
			</c:choose>
			<!-- >> 현재 페이지 + 5 -->
			<c:choose>
				<c:when test="${currentPage < totalPages}">
					<a href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}&sellStat=${param.sellStat}&sort=${param.sort}">&raquo;</a>
				</c:when>
				<c:otherwise>
					    <a class="disabled">&raquo;</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>