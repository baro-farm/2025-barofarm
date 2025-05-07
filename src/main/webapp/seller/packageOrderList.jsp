<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>판매자 | 꾸러미 주문 관리</title>

    <link rel="stylesheet" href="${contextPath }/seller/packageOrderList.css" />

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
                const pkOrderNum = $row.find(".orderNum").data("pkordernum");
                console.log(pkOrderNum);
                const deleveryStatus = $row.find(".status-select").val();

                $.ajax({
                    url: "${contextPath}/updatePkDeliveryStatus",
                    method: "POST",
                    dataType: "json",
                    data:{
                    	pkOrderNum: pkOrderNum,
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
            <span id="title">꾸러미 주문 관리</span>
        </div>
        
<div class="filterBox">
  <form method="get" action="${contextPath}/sellerPackOrderList" style="display: flex; width: 100%; justify-content: space-between;">
	 <!-- 왼쪽: 조회기간 -->
	  <div class="filterSection leftSection">
	    <div class="filterGroup">
	      <label>조회기간</label>
	      <select name="dateType">
	        <option value="paymentDate" ${param.dateType == 'paymentDate' ? 'selected' : ''}>결제일</option>
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
	
	  <!-- 가운데: 배송요일 -->
	  <div class="filterSection centerSection">
	    <div class="filterGroup">
	      <label>배송요일</label>
	      <select name="deliveryDay" id="deliveryDay">
	        <option value="" ${empty param.deliveryDay ? 'selected' : ''}>전체</option>
	        <option value="월" ${param.deliveryDay == '월' ? 'selected' : ''}>월요일</option>
	        <option value="화" ${param.deliveryDay == '화' ? 'selected' : ''}>화요일</option>
	        <option value="수" ${param.deliveryDay == '수' ? 'selected' : ''}>수요일</option>
	        <option value="목" ${param.deliveryDay == '목' ? 'selected' : ''}>목요일</option>
	        <option value="금" ${param.deliveryDay == '금' ? 'selected' : ''}>금요일</option>
	        <option value="토" ${param.deliveryDay == '토' ? 'selected' : ''}>토요일</option>
	        <option value="일" ${param.deliveryDay == '일' ? 'selected' : ''}>일요일</option>
	      </select>
	    </div>

	  </div>
	
	  <!-- 오른쪽: 상세조건 -->
	  <div class="filterSection rightSection">
	    <div class="filterGroup">
	      <label>상세조건</label>
	      <select name="searchType" id="searchType">
	        <option value="all" ${param.searchType == 'all' ? 'selected' : ''}>전체</option>
	        <option value="userName" ${param.searchType == 'userName' ? 'selected' : ''}>구매자명</option>
	        <option value="pkOrderNum" ${param.searchType == 'pkOrderNum' ? 'selected' : ''}>주문번호</option>
	        <option value="packageNum" ${param.searchType == 'packageNum' ? 'selected' : ''}>상품번호</option>
	        <option value="trackingNum" ${param.searchType == 'trackingNum' ? 'selected' : ''}>송장번호</option>
	      </select>
	    </div>
	    <input type="text" name="searchKeyword" placeholder="검색어 입력" value="${param.searchKeyword}">
	  </div>
    	    <!-- 검색 버튼 -->
	    <div class="filterGroup">
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
		            <th style="font-weight: bold;">주문<br>번호</th>
		            <th style="font-weight: bold;">품번</th>
		            <th style="font-weight: bold;">제품명</th>
		            <th style="font-weight: bold;">가격</th>
		            <th style="font-weight: bold;">결제일</th>		            
		            <th style="font-weight: bold;">구매자ID</th>
		            <th style="font-weight: bold;">수령인</th>
		            <th style="font-weight: bold;">주소</th>
		            <th style="font-weight: bold;">전화번호</th>
		            <th style="font-weight: bold;">발송<br>요일</th>
		            <th style="font-weight: bold;">구독<br>시작일</th>
		            <th style="font-weight: bold;">회차정보</th>
		            <th style="font-weight: bold;">배송상태</th>
		          </tr>
	            </thead>
	            <tbody>
					<c:forEach var="order" items ="${packOrderList }">
					
					    <tr>
				        <td><div class="uiGridCell orderNum" data-pkordernum="${order.pkOrderNum}">${order.pkOrderNum}</div></td>
				        <td><div class="uiGridCell packageNum" data-packagenum="${order.packageNum }"><a href="${contextPath }/detailPackage?packageNum=${order.packageNum}">${order.packageNum}</a></div></td>
				        <td><div class="uiGridCell packageNum"><a href="${contextPath }/detailPackage?packageNum=${order.packageNum}">${order.packageName}</a></div></td>
				        
				        <td><div class="uiGridCell"><fmt:formatNumber value="${order.pkTotalPrice}" type="number" groupingUsed="true" />원</div></td>

				        <td><div class="uiGridCell">${order.orderedAt}</div></td>
				        <td><div class="uiGridCell id">${order.userId}</div></td>

				        <td><div class="uiGridCell id">${order.rname}</div></td>
				        <td><div class="uiGridCell">${order.addr}</div></td>
				        <c:set var="rphone" value="${order.rphone}" />
							<c:if test="${not empty rphone and fn:length(rphone) == 11}">
							  <td><div class="uiGridCell id">
							    ${fn:substring(rphone, 0, 3)}-${fn:substring(rphone, 3, 7)}-${fn:substring(rphone, 7, 11)}
							  </div></td>
							</c:if>
							<c:if test="${empty rphone or fn:length(rphone) != 11}">
							  <td><div class="uiGridCell id">${order.rphone}</div></td>
							</c:if>
				        <td><div class="uiGridCell">${order.deleveryDate}</div></td>
				        <td><div class="uiGridCell">${order.subStartDate}</div></td>
				        <td><div class="uiGridCell">${order.subRound}</div></td>
							 

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
	    <!-- << 그룹 처음 -->
	    <a href="?page=${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}&dateType=${param.dateType}&startDate=${param.startDate}&endDate=${param.endDate}&searchType=${param.searchType}&searchKeyword=${param.searchKeyword}"
	       class="${currentPage <= pageGroupSize ? 'disabled' : ''}">&laquo;</a>
	
	    <!-- < 이전 -->
	    <a href="?page=${currentPage - 1}&dateType=${param.dateType}&startDate=${param.startDate}&endDate=${param.endDate}&searchType=${param.searchType}&searchKeyword=${param.searchKeyword}"
	       class="${currentPage == 1 ? 'disabled' : ''}">&lsaquo;</a>
	
	    <!-- 페이지 번호 -->
	    <c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
	        <a href="?page=${i}&dateType=${param.dateType}&startDate=${param.startDate}&endDate=${param.endDate}&searchType=${param.searchType}&searchKeyword=${param.searchKeyword}"
	           class="${currentPage == i ? 'active' : ''}">${i}</a>
	    </c:forEach>
	
	    <!-- > 다음 -->
	    <a href="?page=${currentPage + 1}&dateType=${param.dateType}&startDate=${param.startDate}&endDate=${param.endDate}&searchType=${param.searchType}&searchKeyword=${param.searchKeyword}"
	       class="${currentPage >= totalPages ? 'disabled' : ''}">&rsaquo;</a>
	
	    <!-- >> 그룹 끝 -->
	    <a href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}&dateType=${param.dateType}&startDate=${param.startDate}&endDate=${param.endDate}&searchType=${param.searchType}&searchKeyword=${param.searchKeyword}"
	       class="${groupEndPage >= totalPages ? 'disabled' : ''}">&raquo;</a>
	</div>


    <div id="modal" class="modal">
        <div id="modalContent" class="modalContent">
            <span class="close">&times;</span>
            <p class="modalTitle">주문 상세 조회</p>
        </div>
    </div>
</div>
</body>
</html>