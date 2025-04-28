<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<jsp:include page="/header/sellerHeader.jsp" />
	<header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
	</header>	    
    <div id="content">
     <div class="container-header">
         <h2 class="title">알림 내역</h2>
         <div class="subscribe-box">
         	당신의 알람을 확인하세용~
          </div>
      </div>
		<form method="get" action="${contextPath}/sellerAlarmList" class="searchForm" >
		 <select name="searchType">
		   <option value="content" ${param.searchType == 'content' ? 'selected' : ''}>내용</option>
		 </select>
		 <input type="text" name="keyword" value="${param.keyword}" placeholder="검색어 입력">
		 <input type="date" name="startDateFrom" value="${param.startDateFrom}" />
		 ~
		 <input type="date" name="startDateTo" value="${param.startDateTo}" />
		  <button type="submit">검색</button>
		</form>
      <div class="history-section">
          <h3>알림 내역</h3>
      <table id="banner-table" class="table">
          <thead>
              <tr>
                 <th style="font-weight: bold;">순번</th>
                 <th style="font-weight: bold;">보낸 사람</th>
                 <th style="font-weight: bold;">알림 제목</th>
                 <th style="font-weight: bold;">알림 내용</th>
                 <th style="font-weight: bold;">유형</th>
                 <th style="font-weight: bold;">날짜</th>
                 <th style="font-weight: bold;">확인 여부</th>
             </tr>
         </thead>
         <tbody>
              <tr>
                  <td>1</td>		                
                  <td>김당근</td>
                  <td>콕팜 제안글</td>
                  <td>고구마 제안 글 도착!</td>
                  <td>콕팜링</td>
                  <td>2025.04.25</td>
                  <td><button>확인</button></td>
              </tr>
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
