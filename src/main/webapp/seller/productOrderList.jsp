<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>판매자|상품 주문 관리</title>

    <link rel="stylesheet" href="${contextPath }/seller/productOrderList.css" />

    <link href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css" rel="stylesheet"
        integrity="sha384-jFvlDSY24z+oXMByOoX2Z1gM+M5NMd0uG7sDa4skv2mHXPuS0/RYXwPGLK0+Mgdc" crossorigin="anonymous" />

    <script src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"
        integrity="sha384-FcKnveOKVsyQDhaxWTmHPNxY0wtv3QwEmOUwRZ5g+QqTQvSKKmnkT0NiFcDCCIvg"
        crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script>
        $(document).ready(function () {
            const modal = $("#modal");
            const closeBtn = $(".close");
            const modalContent = $("#modal .modalContent");
            console.log("page load");
           
            //테이블 체크 박스 눌렀을때 전체 체크박스 선택
            $("thead input[type='checkbox']").on("click", function () {
                let isChecked = $(this).prop("checked");
                $("tbody input[type='checkbox']").prop("checked", isChecked);
            });
            
            // 주문번호 클릭 시 AJAX로 상세 페이지 불러오기
            $(document).on("click", ".orderNum", function (event) {
                event.preventDefault(); // a 태그의 기본 이벤트 방지

                const orderId = $(this).text().trim(); // 주문번호 가져오기

                $.ajax({
                    url: "productOrderDetailList.html",
                    method: "GET",
                    dataType: "html",
                    success: function (response) {
                        modalContent.html(response); // 모달 내부에 HTML 삽입
                        modal.css("display", "flex"); // 모달 표시
                        $("body").addClass("modal-open");

                        // 주문번호를 모달 내부의 span에 추가
                        $("#modal #orderId").text(orderId);
                    },
                    error: function () {
                        alert("주문 상세 정보를 불러오는 데 실패했습니다.");
                    }
                });
            });

            // 닫기 버튼 클릭 시 모달 닫기
            $(document).on("click", ".close", function () {
                modal.css("display", "none");
                $("body").removeClass("modal-open");
                
            });

            // 모달 바깥 영역 클릭 시 닫기
            $(window).on("click", function (event) {
                if ($(event.target).is("#modal")) {
                    modal.css("display", "none");
                    $("body").removeClass("modal-open");
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
            <span id="title">상품 주문 관리</span>
        </div>
        
<div class="filterBox">
  <form method="get" action="${contextPath}/sellerProdOrderList" style="display: flex; width: 100%; justify-content: space-between;">
    
    <!-- 왼쪽: 조회기간 -->
    <div class="filterSection leftSection">
      <div class="filterGroup">
        <label>조회기간</label>
        <select name="dateType">
          <option value="paymentDate" ${param.dateType == 'paymentDate' ? 'selected' : ''}>결제일</option>
          <option value="shippingDate" ${param.dateType == 'shippingDate' ? 'selected' : ''}>발송처리일</option>
        </select>
      </div>
      <div class="buttonGroup">
        <button type="button" onclick="setDateRange('today')">오늘</button>
        <button type="button" onclick="setDateRange('1week')">1주일</button>
        <button type="button" onclick="setDateRange('1month')">1개월</button>
        <button type="button" onclick="setDateRange('3months')">3개월</button>
      </div>
      <div class="dateGroup">
        <input type="date" name="startDate" value="${param.startDate}"> ~ 
        <input type="date" name="endDate" value="${param.endDate}">
      </div>
    </div>
    
    <!-- 가운데: 검색 버튼 -->
    <div class="filterSection centerSection">
      <button type="submit" class="searchBtn">검색</button>
    </div>
    
    <!-- 오른쪽: 상세조건 -->
    <div class="filterSection rightSection">
      <div class="filterGroup">
        <label>상세조건</label>
        <select name="searchType" id="searchType">
          <option value="all" ${param.searchType == 'all' ? 'selected' : ''}>전체</option>
          <option value="userName" ${param.searchType == 'userName' ? 'selected' : ''}>구매자명</option>
          <option value="pdOrderNum" ${param.searchType == 'pdOrderNum' ? 'selected' : ''}>주문번호</option>
          <option value="productNum" ${param.searchType == 'productNum' ? 'selected' : ''}>상품번호</option>
          <option value="trackingNum" ${param.searchType == 'trackingNum' ? 'selected' : ''}>송장번호</option>
        </select>
      </div>
      <input type="text" name="searchKeyword" placeholder="검색어 입력" value="${param.searchKeyword}">
    </div>
    
  </form>
</div>
		
		<script>
		function setDateRange(range) {
		    const today = new Date();
		    let start = new Date();
		
		    if (range === 'today') {
		        start = today;
		    } else if (range === '1week') {
		        start.setDate(today.getDate() - 7);
		    } else if (range === '1month') {
		        start.setMonth(today.getMonth() - 1);
		    } else if (range === '3months') {
		        start.setMonth(today.getMonth() - 3);
		    }
		
		    const formatDate = date => date.toISOString().split('T')[0];
		    document.querySelector('input[name="startDate"]').value = formatDate(start);
		    document.querySelector('input[name="endDate"]').value = formatDate(today);
		}
		</script>
		
        <div class="tableWrapper">
	        <table id="notie_table" class="table">
	            <thead>
	            	<tr>
		            <th style="font-weight: bold;">총<br>주문번호</th>
		            <th style="font-weight: bold;">제품<br>주문번호</th>
		            <th style="font-weight: bold;">제품번호</th>
		            <th style="font-weight: bold;">옵션</th>
		            <th style="font-weight: bold;">단가</th>
		            <th style="font-weight: bold;">수량</th>
		            <th style="font-weight: bold;">총가격</th>
		            <th style="font-weight: bold;">결제일</th>		            
		            <th style="font-weight: bold;">구매자ID</th>
		            <th style="font-weight: bold;">수령인</th>
		            <th style="font-weight: bold;">주소</th>
		            <th style="font-weight: bold;">전화번호</th>
		            <th style="font-weight: bold;">주문상태</th>
		            <th style="font-weight: bold;">배송상태</th>
		          	<th style="font-weight: bold;">적용</th>

		          	
		          </tr>
	            </thead>
	            <tbody>
					<c:forEach var="order" items ="${prodOrderList }">
					
					    <tr>
				        <td><div class="uiGridCell orderNum"><a href="#">${order.pdOrderNum}</a></div></td>
				        <td><div class="uiGridCell"><a href="#">${order.orderItem}</a></div></td>
				        <td><div class="uiGridCell">${order.productNum}</div></td>
				        <td><div class="uiGridCell">${order.option}</div></td>
				        <td><div class="uiGridCell">${order.optionPrice}원</div></td>
				        <td><div class="uiGridCell">${order.amount}</div></td>
				        <td><div class="uiGridCell">${order.totalPrice}원</div></td>


				        <td><div class="uiGridCell">${order.orderDate}</div></td>
				        <td><div class="uiGridCell id">${order.userId}</div></td>

				        <td><div class="uiGridCell id">${order.userName}</div></td>
				        <td><div class="uiGridCell">${order.address}</div></td>
				        <c:set var="rawPhone" value="${order.phone}" />
							<c:if test="${not empty rawPhone and fn:length(rawPhone) == 11}">
							  <td><div class="uiGridCell id">
							    ${fn:substring(rawPhone, 0, 3)}-${fn:substring(rawPhone, 3, 7)}-${fn:substring(rawPhone, 7, 11)}
							  </div></td>
							</c:if>
							<c:if test="${empty rawPhone or fn:length(rawPhone) != 11}">
							  <td><div class="uiGridCell id">${order.phone}</div></td>
							</c:if> 
				        <td><div class="uiGridCell">${order.orderStatus}</div></td>
				        <td>
				            <div class="uiGridCell">
				                <select class="status-select">
				                    <option ${order.deleveryStatus == '준비중' ? 'selected' : ''}>준비중</option>
				                    <option ${order.deleveryStatus == '배송중' ? 'selected' : ''}>배송중</option>
				                    <option ${order.deleveryStatus == '배송완료' ? 'selected' : ''}>배송완료</option>
				                </select>
				            </div>
				        </td>
				        <td><div class="uiGridCell"><button class="apply-btn">적용</button></div></td>
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
		    <c:if test="${currentPage > 1}">
		        <a href="?page=${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}
		        &dateType=${param.dateType}
		        &startDate=${param.startDate}
		        &endDate=${param.endDate}
		        &searchType=${param.searchType}
		        &searchKeyword=${param.searchKeyword}">&laquo;</a>
		    </c:if>
		    
		    <!-- < 이전 페이지 -->
		    <c:if test="${currentPage > 1}">
		        <a href="?page=${currentPage - 1}
		        &dateType=${param.dateType}
		        &startDate=${param.startDate}
		        &endDate=${param.endDate}
		        &searchType=${param.searchType}
		        &searchKeyword=${param.searchKeyword}">&lsaquo;</a>
		    </c:if>
		    
		    <!-- 페이지 번호 -->
		    <c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
			    <a href="?page=${i}
			    &dateType=${empty param.dateType ? '' : param.dateType}
			    &startDate=${empty param.startDate ? '' : param.startDate}
			    &endDate=${empty param.endDate ? '' : param.endDate}
			    &searchType=${empty param.searchType ? '' : param.searchType}
			    &searchKeyword=${empty param.searchKeyword ? '' : param.searchKeyword}"
			   class="${currentPage == i ? 'active' : ''}">${i}</a>
		    </c:forEach>
		    
		    <!-- > 다음 페이지 -->
		    <c:if test="${currentPage < totalPages}">
		        <a href="?page=${currentPage + 1}
		        &dateType=${param.dateType}
		        &startDate=${param.startDate}
		        &endDate=${param.endDate}
		        &searchType=${param.searchType}
		        &searchKeyword=${param.searchKeyword}">&rsaquo;</a>
		    </c:if>
		    
		    <!-- >> 현재 페이지 + 5 -->
		    <c:if test="${currentPage < totalPages}">
		        <a href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}
		        &dateType=${param.dateType}
		        &startDate=${param.startDate}
		        &endDate=${param.endDate}
		        &searchType=${param.searchType}
		        &searchKeyword=${param.searchKeyword}">&raquo;</a>
		    </c:if>
		</div>
    </div>
    
    

   
</a>

    <div id="modal" class="modal">
        <div id="modalContent" class="modalContent">
            <span class="close">&times;</span>
            <p class="modalTitle">주문 상세 조회</p>
        </div>
    </div>

</body>
</html>