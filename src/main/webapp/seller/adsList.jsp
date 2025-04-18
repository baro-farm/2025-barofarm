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

    </script>
    <link
    href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css"
    rel="stylesheet"
    integrity="sha384-jFvlDSY24z+oXMByOoX2Z1gM+M5NMd0uG7sDa4skv2mHXPuS0/RYXwPGLK0+Mgdc"
    crossorigin="anonymous"
    />
    <script
    src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"
    integrity="sha384-FcKnveOKVsyQDhaxWTmHPNxY0wtv3QwEmOUwRZ5g+QqTQvSKKmnkT0NiFcDCCIvg"
    crossorigin="anonymous"
    ></script>
    <!-- ✅ Custom CSS -->
    <link rel="stylesheet" href="${contextPath }/seller/adsList.css">
	<link rel="stylesheet" href="${contextPath }/common/modal.css">
    <script>
        $(document).ready(function () {
            $('#banner-table').DataTable({
                colReorder: true,  
                responsive: true,  
                scrollX: true,  
                paging: true,  
                searching: true,  
                ordering: true,  // 🔥 정렬 기능 활성화
                lengthMenu: [5, 10, 20],
                columnDefs: [
                    { targets: 0, orderable: false }, // 0번째 열(구분) 정렬 비활성화
                ],    
                language: {
                    "lengthMenu": "페이지당 _MENU_ 개씩 보기",
                    "zeroRecords": "조회된 포인트 내역이 없습니다.",
                    "info": "총 _TOTAL_ 개의 내역 중 _START_ - _END_ 표시",
                    "infoEmpty": "데이터가 없습니다.",
                    "infoFiltered": "(총 _MAX_ 개 데이터에서 필터링됨)",
                    "search": "검색: ",
                    "paginate": {
                        "first": "<<",
                        "last": ">>",
                        "next": ">",
                        "previous": "<"
                    }
                }
            });
        });
    </script>
</head>
<body>
	<div class="main">
		<c:import url="/header/mainHeader.jsp" />
	    <div class="container">
	        <div class="container-header">
	            <h2 class="title">배너 광고</h2>
	
	            <div class="subscribe-box">
	                <span class="question-icon">❓</span>
	                <button class="btn-apply" onclick="location.href='${contextPath}/insertAdsBySeller'">📢 광고 신청하기</button>
	            </div>
	        </div>
	
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
							    <c:when test="${ads.status == '승인반려'}">
							      <button class="btn-edit" onclick="location.href='${contextPath}/updateAdsBySeller?adsNum=${ads.adsNum }'" >수정</button>
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
	    </div>
    </div>
<div class="modal" id="cancelModal" style="display:none;">
    <p>광고를 취소하시겠습니다?</p>
    <div class="buttons">
		<button class="btn btn-subscribe" id="confirmCancelBtn">확인</button>
        <button class="btn btn-cancel" id="closeModalBtn">취소</button>
    </div>
</div>
<!-- 이미지 모달 -->
<div id="imgModal" class="modal" style="display:none;">
  <div id="imgModalContent">
    <span id="closeImgModal" style="cursor:pointer; float: right;">❌</span>
    <img id="modalImage" src="" style="max-width: 100%; height: auto; margin-top: 20px;" />
  </div>
</div>

<script>
document.addEventListener("DOMContentLoaded", function () {
  let selectedAdsNum = null;

  // 삭제 버튼 클릭 → 모달 띄우기
  document.querySelectorAll(".btn-cancel").forEach(function(btn) {
    btn.addEventListener("click", function () {
      selectedAdsNum = this.dataset.adsnum;
      document.getElementById("cancelModal").style.display = "block";
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
      const modal = document.getElementById("imgModal");
      const modalImg = document.getElementById("modalImage");
      modalImg.src = this.src.replace("width=100px", ""); // 혹시 URL 파라미터가 있다면 제거
      modal.style.display = "block";
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
