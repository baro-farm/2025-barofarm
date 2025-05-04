<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.time.LocalDate" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	LocalDate today = LocalDate.now();
%>

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
            
            
            
            function toggleSearchInput() {
                const selected = $("#searchType").val();
                if (selected === "all") {
                    $("#searchKeyword").prop("disabled", true).val("");  // 전체 선택 시 비활성화 + 값 비움
                } else {
                    $("#searchKeyword").prop("disabled", false);
                }
            }

            // 페이지 로드 시 초기 체크
            toggleSearchInput();

            // 셀렉트 박스 값 변경 시
            $("#searchType").on("change", function() {
                toggleSearchInput();
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
            
         // 배송상태 적용 버튼 클릭 시
            $(document).on("click", ".apply-btn", function () {
                const $row = $(this).closest("tr");
                const pdOrderNum = $row.find(".orderNum").data("pdordernum");
                console.log(pdOrderNum);
                const deleveryStatus = $row.find(".status-select").val();

                $.ajax({
                    url: "${contextPath}/updateProdTrackNum",
                    method: "POST",
                    dataType: "json",
                    data:{
                        pdOrderNum: pdOrderNum,
                        deleveryStatus: deleveryStatus
                    },
                    success: function (response) {
                        if (response.success === true) {
                            alert("배송상태가 변경되었습니다!");
                            location.reload();  // 새로고침으로 반영
                        } else {
                            alert("변경 실패!");
                        }
                    },
                    error: function () {
                        alert("서버 오류 발생!");
                    }
                });
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
        <input type="date" name="startDate" value="${empty param.startDate ? today : param.startDate}"> ~ 
        <input type="date" name="endDate" value="${empty param.endDate ? today : param.endDate}">
      </div>
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
      <input type="text" name="searchKeyword" id="searchKeyword" placeholder="검색어 입력" value="${param.searchKeyword}"   ${param.searchType == 'all' || empty param.searchType ? 'disabled' : ''}>
    </div>
        <!-- 가운데: 검색 버튼 -->
    <div class="filterSection centerSection">
      <button type="submit" class="searchBtn">검색</button>
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
		            <th style="font-weight: bold;">주문번호</th>
		            <th style="font-weight: bold;">품번</th>
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


		          	
		          </tr>
	            </thead>
	            <tbody>
					<c:forEach var="order" items ="${prodOrderList }">
					
					    <tr>
				        <td><div class="uiGridCell orderNum" data-pdordernum="${order.pdOrderNum}"><a href="#">${order.pdOrderNum}</a></div></td>
				        <td><div class="uiGridCell">${order.productNum}</div></td>
				        <td><div class="uiGridCell">${order.option}</div></td>
				        <td><div class="uiGridCell"><fmt:formatNumber value="${order.optionPrice}" type="number" groupingUsed="true" />원</div></td>
				        <td><div class="uiGridCell">${order.amount}</div></td>
				        <td><div class="uiGridCell"><fmt:formatNumber value="${order.totalPrice}" type="number" groupingUsed="true" />원</div></td>


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
							 <c:choose>
							            <c:when test="${order.deleveryStatus == '구매확정'}">
							                <span>구매확정</span> 
							            </c:when>
							            <c:otherwise>
							                <select class="status-select">
							                    <option ${order.deleveryStatus == '준비중' ? 'selected' : ''}>준비중</option>
							                    <option ${order.deleveryStatus == '배송중' ? 'selected' : ''}>배송중</option>
							                    <option ${order.deleveryStatus == '배송완료' ? 'selected' : ''}>배송완료</option>
							                </select>
							                <button class="apply-btn">적용</button>
							            </c:otherwise>
							        </c:choose>
				                
				            </div>
				        </td>
	
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
		    
		        <a href="?page=${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}&dateType=${param.dateType}&startDate=${param.startDate}&endDate=${param.endDate}&searchType=${param.searchType}&searchKeyword=${param.searchKeyword}"
		        	class="${currentPage <= pageGroupSize ? 'disabled' : ''}">&laquo;</a>
		    
		    
		    <!-- < 이전 페이지 -->
		        <a href="?page=${currentPage - 1}&dateType=${param.dateType}&startDate=${param.startDate}&endDate=${param.endDate}&searchType=${param.searchType}&searchKeyword=${param.searchKeyword}"
		         class="${currentPage == 1 ? 'disabled' : ''}">&lsaquo;</a>
		   
		    
		    <!-- 페이지 번호 -->
		    <c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
		        <a href="?page=${i}&dateType=${empty param.dateType ? '' : param.dateType}&startDate=${empty param.startDate ? '' : param.startDate}&endDate=${empty param.endDate ? '' : param.endDate}&searchType=${empty param.searchType ? '' : param.searchType}&searchKeyword=${empty param.searchKeyword ? '' : param.searchKeyword}"
		        class="${currentPage == i ? 'active' : ''}">${i}</a>
		    </c:forEach>
		    
		    <!-- > 다음 페이지 -->
		        <a href="?page=${currentPage + 1}&dateType=${param.dateType}&startDate=${param.startDate}&endDate=${param.endDate}&searchType=${param.searchType}&searchKeyword=${param.searchKeyword}"
		        	class="${currentPage >= totalPages ? 'disabled' : ''}">&rsaquo;</a>
		    
		    <!-- >> 현재 페이지 + 5 -->
		    <c:if test="${currentPage < totalPages}">
		        <a href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}&dateType=${param.dateType}&startDate=${param.startDate}&endDate=${param.endDate}&searchType=${param.searchType}&searchKeyword=${param.searchKeyword}"
		        	class="${groupEndPage >= totalPages ? 'disabled' : ''}">&raquo;</a>
		    </c:if>
		</div>
    </div>
    
    

 

    <div id="modal" class="modal">
        <div id="modalContent" class="modalContent">
            <span class="close">&times;</span>
            <p class="modalTitle">주문 상세 조회</p>
        </div>
    </div>

</body>
</html>