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
		
		//글쓰기 버튼 눌렀을 때 
		$('.btn.add').on('click', function() {
		    window.location.href = '${contextPath}/insertProduct';
		});
		
		//수정버튼 눌렀을 때. 체크박스는 1개만 가능
		 $('.btn.edit').on('click', function() {
		        const $checked = $('#notice_table tbody input[type="checkbox"]:checked');
		        if ($checked.length === 0) {
		            alert('수정할 상품을 선택해주세요.');
		            return;
		        }
		        if ($checked.length > 1) {
		            alert('하나의 상품만 선택해주세요.');
		            return;
		        }
		        const productNum = $checked.closest('tr').find('.productNum a').text().trim();
		        window.location.href = '${contextPath}/updateProduct?productNum=' + productNum;
		    });
		
		
	    // +, - 버튼 클릭 이벤트
	    $('#notice_table').on('click', 'button', function() {
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
	    $('#notice_table').on('click', 'button:contains("저장")', function() {
	        const $row = $(this).closest('tr');
	        const productNum = $row.find('.productNum a').text().trim();
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
	            data: { productNum: productNum, stock: stock },
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
	        const $checked = $('#notice_table tbody input[type="checkbox"]:checked');
	        if ($checked.length === 0) {
	            alert('상태를 변경할 상품을 선택해주세요.');
	            return;
	        }

	        // 선택된 상품들의 productNum과 status 수집
	        let products = [];
	        $checked.each(function() {
	            const $row = $(this).closest('tr');
	            const productNum = $row.find('.productNum a').text().trim();
	            const statusText = $row.find('.status').text().trim();
	            const currentStatus = (statusText === '판매중') ? true : false;

	            // ✅ 상태 반전 (true → false, false → true)
	            const newStatus = !currentStatus;

	            products.push({ productNum: productNum, status: newStatus });
	        });

	        // 서버로 전송 (JSON)
	        $.ajax({
	            url: '${contextPath}/updateProdStatus',
	            method: 'POST',
	            contentType: 'application/json',
	            data: JSON.stringify(products),
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
	
	<jsp:include page="/header/sellerHeader.jsp" />
    
    <header id="header">
			<jsp:include page="/header/adminSellerTop.jsp" />

    </header>
    
    <div id="content">
        <div class="notice-header">
            <span id="title">상품 관리</span>
        </div>
		
		<div class="table-wrapper">
		
		<table id="notice_table" class="table">
		        <thead>
		          <tr>
		            <th><input type="checkbox" class="selectAll"></th>
		            <th style="font-weight: bold;">상품번호</th>
		            <th style="font-weight: bold;">상품명</th>
		            <th style="font-weight: bold;">카테고리</th>
		            <th style="font-weight: bold;">상품상태</th>
		            <th style="font-weight: bold;">가격</th>
		            <th style="font-weight: bold;">썸네일</th>
		            <th style="font-weight: bold;">등록일자</th>
		            <th style="font-weight: bold;">상품재고</th>
		            <th style="font-weight: bold;">판매건수</th>
		            <th style="font-weight: bold;">리뷰건수</th>
		          	<th style="font-weight: bold;">리뷰평균</th>
		          </tr>
		        </thead>
		        <tbody>
		          <c:forEach var="product" items="${productList}">
	                <tr>
	                    <td><div class="uiGridCell"><input type="checkbox" class="rowCheck"></div></td>
	                    <td><div class="uiGridCell productNum"><a href="#">${product.productNum }</a></div></td>
	                    <td><div class="uiGridCell"><a href="#">${product.productName }</a></div></td>
	                    <td><div class="uiGridCell">${product.cateName }</div></td>
	                    <td>
	                    	<c:choose>
	                    		<c:when test="${product.status == true }">
	                    			<div class="uiGridCell"><span class="status on-sale">판매중</span></div>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<div class="uiGridCell"><span class="status stopped">판매중단</span></div>
	                    		</c:otherwise>
	                    	</c:choose>
	                    	
	                    </td>
	                    <td><div class="uiGridCell">${product.price }원</div></td>
	                    <td><div class="uiGridCell"><img src="upload/${product.imgUrl}" width="50" alt="상품이미지"></div></td>
	                    <td><div class="uiGridCell">${product.createdAt }</div></td>
	                    <td><div class="uiGridCell"><button class="stockBtn">-</button> <input type='number' min='0'  class="stock" value="${product.stock }"> <button class="stockBtn">+</button><br> <button class="saveBtn">저장</button></div></td>
	                    <td><div class="uiGridCell">${product.salesVolume }건</div></td>
	                    <td><div class="uiGridCell">${product.productNum }건</div></td>
	                    <td>
	                    	<div class="uiGridCell">
	                    		<span>${product.avgRating }</span>
	                    	
								<c:forEach var="i" begin="1" end="5">
								    <c:choose>
								      <c:when test="${i <= product.avgRating}">
								        ★
								      </c:when>
								      <c:otherwise>
								        ☆
								      </c:otherwise>
								    </c:choose>
								</c:forEach>
							</div>
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