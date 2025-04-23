<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    long startTime = System.currentTimeMillis();
    request.setAttribute("startTime", startTime);
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>배너 광고</title>

    <!-- ✅ jQuery & DataTables 라이브러리 -->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <link rel="stylesheet" href="${contextPath }/seller/adsList.css">
	<link rel="stylesheet" href="${contextPath }/common/modal.css">
</head>
<body>
	<div class="main">
		<div class="wrapper">
			<div class="sidebar">
					<jsp:include page="/header/sellerHeader.jsp" />
	        </div>	
	        <header id="header">
		        <div id="info">
		            <span id="email">kosta@kosta.com</span>
		            <span>내 정보</span>
		            <span>로그아웃</span>
		        </div>
		    </header>	    
	        <div class="content">
		        <div class="container-header">
		            <h2 class="title">배너 광고</h2>
		            <div class="subscribe-box">
		                <span class="question-icon">❓</span>
		                <c:if test="${bannerCnt <5 }">
	       	                <button class="btn-apply" onclick="location.href='${contextPath}/insertAdsBySeller'">📢 광고 신청하기</button>
		                </c:if>
		                <c:if test="${bannerCnt >=5 }">
		                	광고 마감입니다.
		                </c:if>
		            </div>
		        </div>
				<form method="get" action="${contextPath}/sellerAdsList" class="searchForm" >
				  <select name="searchType">
				    <option value="status" ${param.searchType == 'status' ? 'selected' : ''}>신청현황</option>
				    <option value="product" ${param.searchType == 'product' ? 'selected' : ''}>상품명</option>
				  </select>
				  <input type="text" name="keyword" value="${param.keyword}" placeholder="검색어 입력">
				  <input type="date" name="startDateFrom" value="${param.startDateFrom}" />
				  ~
				  <input type="date" name="startDateTo" value="${param.startDateTo}" />
				  <button type="submit">검색</button>
				</form>
		        <div class="history-section">
		            <h3>광고 신청 내역</h3>
		        <table id="banner-table" class="table">
		            <thead>
		                <tr>
	   	                    <th style="font-weight: bold;">순번</th>
		                    <th style="font-weight: bold;">신청 현황</th>
		                    <th style="font-weight: bold;">이미지파일</th>
		                    <th style="font-weight: bold;">상품명</th>
		                    <th style="font-weight: bold;">상품링크</th>
		                    <th style="font-weight: bold;">광고시작일</th>
		                    <th style="font-weight: bold;">광고종료일</th>
		                    <th style="font-weight: bold;">승인 여부</th>
		                </tr>
		            </thead>
		            <tbody>
	   	            	<c:forEach var="ads" items="${adsList }" varStatus="status">
			                <tr>
			                    <td>${status.count }</td>		                
			                    <td>${ads.status }</td>
			                    <td><img src="kockImg?imgUrl=${ads.imgUrl }"  width="100px" alt="광고배너" class="product-img"></td>
			                    <td>${ads.productName }</td>
			                    <td><a href="${ads.productUrl }" target="_blank">${ads.productUrl }</a></td>
			                    <td>${ads.startDate }</td>
			                    <td>${ads.endDate }</td>
			                    <td>
								  <c:choose>
								    <c:when test="${ads.status == '승인대기'}">
								      <button class="btn-cancel" data-adsnum="${ads.adsNum }">취소</button>
								      <button class="btn-edit" onclick="location.href='${contextPath}/updateAdsBySeller?adsNum=${ads.adsNum }'" >수정</button>							      
								    </c:when>
								    <c:when test="${ads.status == '이미지부적격' or ads.status == '상품링크오류'}">
										N							
								    </c:when>
								    <c:when test="${ads.status == '승인'}">
										Y						
								    </c:when>
								    <c:otherwise>
								      <!-- 게시중 / 종료 상태일 때는 버튼 없음 -->
								    </c:otherwise>
								  </c:choose>
								</td>
			                </tr>
		                </c:forEach>
		            </tbody>
		        </table>
		        </div>
		        <div class="paging" id="pagingArea" style="text-align: center; margin-top: 20px;">
				  <c:if test="${pi.startPage > 1}">
				    <a href="?page=${pi.startPage - 1}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}">&laquo;</a>
				  </c:if>
				
				  <c:forEach begin="${pi.startPage}" end="${pi.endPage}" var="p">
				    <a href="?page=${p}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}"
				       class="${p == pi.currentPage ? 'active' : ''}">${p}</a>
				  </c:forEach>
				
				  <c:if test="${pi.endPage < pi.maxPage}">
				    <a href="?page=${pi.endPage + 1}&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}">&raquo;</a>
				  </c:if>
				</div>
		    </div>
	    </div>
    </div>
<div class="modal-wrapper" id="cancelModal" style="display:none;">
	<div class="modal">
	    <p>광고를 취소하시겠습니다?</p>
	    <div class="buttons">
			<button class="btn btn-subscribe" id="confirmCancelBtn">확인</button>
	        <button class="btn btn-cancel" id="closeModalBtn">취소</button>
	    </div>
    </div>
</div>
<!-- 이미지 모달 -->
<div id="imgModal" class="modal-wrapper" style="display:none;">
	<div class="modal">
	  <div id="imgModalContent">
	    <span id="closeImgModal" style="cursor:pointer; float: right;">❌</span>
	    <img id="modalImage" src="" style="max-width: 100%; height: auto; margin-top: 20px;" />
	  </div>
	</div>
</div>

<script>
document.addEventListener("DOMContentLoaded", function () {
  let selectedAdsNum = null;

  // 삭제 버튼 클릭 → 모달 띄우기
  document.querySelectorAll(".btn-cancel").forEach(function(btn) {
    btn.addEventListener("click", function () {
    	console.log("cancel!!");
      selectedAdsNum = this.dataset.adsnum;
      document.getElementById("cancelModal").style.display = "flex";
    });
  });

  // 확인 → AJAX 호출
  document.getElementById("confirmCancelBtn").addEventListener("click", function () {
    if (!selectedAdsNum) return;

    fetch("${contextPath}/sellerCancelAds", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ adsNum: selectedAdsNum })
    })
    .then(res => res.json())
    .then(data => {
      if (data.success) {
        alert("광고가 취소되었습니다.");
        location.reload();
      } else {
        alert("광고 취소에 실패했습니다.");
      }
    })
    .catch(err => {
      console.error(err);
      alert("서버 오류가 발생했습니다.");
    })
    .finally(() => {
      document.getElementById("cancelModal").style.display = "none";
      selectedAdsNum = null;
    });
  });

  // 취소 → 모달 닫기
  document.getElementById("closeModalBtn").addEventListener("click", function () {
    document.getElementById("cancelModal").style.display = "none";
    selectedAdsNum = null;
  });
});
</script>
<script>
document.addEventListener("DOMContentLoaded", function () {
  // 이미지 클릭 → 모달에 원본 표시
  document.querySelectorAll(".product-img").forEach(function(img) {
    img.addEventListener("click", function () {
    	console.log("img!!");
      const modal = document.getElementById("imgModal");
      const modalImg = document.getElementById("modalImage");
      modalImg.src = this.src.replace("width=100px", ""); // 혹시 URL 파라미터가 있다면 제거
      modal.style.display = "flex";
    });
  });

  // 닫기
  document.getElementById("closeImgModal").addEventListener("click", function () {
    document.getElementById("imgModal").style.display = "none";
  });
});

document.getElementById("imgModal").addEventListener("click", function (e) {
	  const modalContent = document.getElementById("imgModalContent");

	  // 모달 콘텐츠 영역 외부를 클릭한 경우에만 닫기
	  if (!modalContent.contains(e.target)) {
	    this.style.display = "none";
	  }
	});


</script>

</body>
</html>
