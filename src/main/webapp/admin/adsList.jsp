<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="activeTab" value="${param.tab != null ? param.tab : 'in-progress'}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title>광고 관리</title>
		<link rel="stylesheet" href="${contextPath}/header/reset.css">
	
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/admin/adminAdsList.css" />
    	<link rel="stylesheet" href="${contextPath }/common/modal.css">
 
</head>
<body>
	<jsp:include page="/header/adminHeader.jsp" />
	<header id="header">
			<jsp:include page="/header/adminSellerTop.jsp" />
    </header>
    <div id="content">
				<div class="hh">
					<div class="pkHeader">
						<span id="title">배너 관리</span>
					</div>
 					<div id="searchAndPagingWrapper" class="selectBox" style="display: none;">
					 <form method="get" action="${contextPath}/adminAdsList" style="margin-bottom: 20px;">
						  <select name="searchType">
						    <option value="store" ${param.searchType == 'store' ? 'selected' : ''}>스토어명</option>
						    <option value="product" ${param.searchType == 'product' ? 'selected' : ''}>상품명</option>
						  </select>
						  <input type="hidden" name="tab" value="${activeTab}" />
						  <input type="text" name="keyword" value="${param.keyword}" placeholder="검색어 입력">
						  <input type="date" name="startDateFrom" value="${param.startDateFrom}" />
						  ~
						  <input type="date" name="startDateTo" value="${param.startDateTo}" />
						  <button type="submit">검색</button>
						</form>
					</div>
				</div>
				<div class="tab-buttons">
				  <button class="tab-btn ${activeTab == 'in-progress' ? 'active' : ''}" data-tab="in-progress">진행 광고 목록</button>
				  <button class="tab-btn ${activeTab == 'application' ? 'active' : ''}" data-tab="application">종료된 광고 목록</button>
				</div>

		        <!-- 진행 광고 목록 탭 -->
				<div id="in-progress" class="tab-content ${activeTab == 'in-progress' ? 'active' : ''}">
		          <table id="banner-table" class="table">
		            <thead>
		              <tr>
   		                <th>순번</th>
		                <th>스토어명</th>
		                <th>이미지파일</th>
		                <th>광고시작일</th>
		                <th>광고종료일</th>
		                <th>상태</th>
		              </tr>
		            </thead>
		            <tbody>
		            	<c:if test="${empty postingList}">
						  <tr>
						    <td colspan="8" style="text-align: center;">조회된 결과가 없습니다.</td>
						  </tr>
						</c:if>
      	            	<c:forEach var="post" items="${postingList }" varStatus="status">
							<tr>
			                    <td>${status.count }</td>
							    <td>${post.storeName}</td>
							    <td><img src="kockImg?imgUrl=${post.imgUrl }" alt="이미지" width="50px" class="product-img" /></td>
			                    <fmt:parseDate value="${post.startDate}" pattern="yyyy-MM-dd" var="startDate"/>
			                    <fmt:parseDate value="${post.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
			                    <td class="startD"><fmt:formatDate value="${startDate }" pattern="yyyy-MM-dd"/></td>		                    
			                    <td class="endD"><fmt:formatDate value="${endDate }" pattern="yyyy-MM-dd"/></td>		                    
							    <td>
									<c:if test="${post.status eq '승인'}">
										승인
									</c:if>
									<c:if test="${post.status eq '승인대기'}">
									    <select class="status-select" data-ads-num="${post.adsNum}">
									      <option ${post.status eq '승인대기' ? 'selected' : ''}>승인대기</option>
									      <option ${post.status eq '승인' ? 'selected' : ''}>승인</option>
									      <option>이미지부적격</option>
									      <option>상품링크오류</option>
									    </select>
									</c:if>
		                  		</td>
							</tr>
			            </c:forEach>
		            </tbody>
		          </table>
		        </div>
			    <!-- 광고 신청 목록 탭 -->
				<div id="application" class="tab-content ${activeTab == 'application' ? 'active' : ''}">
		          <table id="banner-table2" class="table">
		            <thead>
		              <tr>
		                <th>순번</th>
		                <th>스토어명</th>
		                <th>내용</th>
		                <th>이미지파일</th>
		                <th>광고시작일</th>
		                <th>광고종료일</th>
		                <th>상태</th>
		              </tr>
		            </thead>
		            <tbody>
		              <c:forEach var="ad" items="${adsList}" varStatus="status">
						  <tr>
		                    <td>${status.count }</td>
						    <td>${ad.storeName}</td>
						    <td>${ad.title}</td>
						    <td><img src="kockImg?imgUrl=${ad.imgUrl }" alt="이미지" width="50px" class="product-img"  /></td>
		                    <fmt:parseDate value="${ad.startDate}" pattern="yyyy-MM-dd" var="startDate"/>
		                    <fmt:parseDate value="${ad.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
		                    <td><fmt:formatDate value="${startDate }" pattern="yyyy-MM-dd"/></td>		                    
		                    <td><fmt:formatDate value="${endDate }" pattern="yyyy-MM-dd"/></td>		                    
						    <td>${ad.status }
						    </td>
						  </tr>
						</c:forEach>
						<c:if test="${empty adsList}">
						  <tr>
						    <td colspan="8" style="text-align: center;">조회된 결과가 없습니다.</td>
						  </tr>
						</c:if>
		            </tbody>
		          </table>
		        </div>
		        
				<div class="pagination" id="pagingArea" >
				  <c:if test="${pi.startPage > 1}">
				    <a href="?page=${pi.startPage - 1}&tab=application&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}">&laquo;</a>
				  </c:if>
				
				  <c:forEach begin="${pi.startPage}" end="${pi.endPage}" var="p">
				    <a href="?page=${p}&tab=application&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}"
				       class="${p == pi.currentPage ? 'active' : ''}">${p}</a>
				  </c:forEach>
				
				  <c:if test="${pi.endPage < pi.maxPage}">
				    <a href="?page=${pi.endPage + 1}&tab=application&searchType=${param.searchType}&keyword=${param.keyword}&startDateFrom=${param.startDateFrom}&startDateTo=${param.startDateTo}">&raquo;</a>
				  </c:if>
				</div>
		        <!-- 🔽 메인 배너 관리 -->
		        <div class="banner-section" id="bannerSection">
					<div class="pkHeader" style="margin-top: 60px;">
						<span id="title">메인 배너 관리</span>
					</div>
					<div id="limitBannerMsg" class="banner-add-section" style="text-align: right; margin-bottom: 10px; display: ${fn:length(bannerList) >= 5 ? 'block' : 'none'};">
					  <p>배너는 최대 5개 게시할 수 있습니다.</p>
					</div>
					
					<div class="banner-add-section" id="openBannerModal" style="text-align: right; margin-bottom: 10px; display: ${fn:length(bannerList) < 5 ? 'block' : 'none'};">
					    <button class="banner-add-btn">배너 추가</button>
					  </div>
				  <div class="">
					<table class="table banner-table">
					  <thead>
					    <tr>
					      <th>순번</th>
   					      <th>이미지파일</th>
					      <th>배너명</th>
					      <th>등록일</th>
   					      <th>삭제</th>
					    </tr>
					  </thead>
					  <tbody>
					  <c:if test="${empty bannerList}">
						  <tr>
						    <td colspan="8" style="text-align: center;">조회된 결과가 없습니다.</td>
						  </tr>
						</c:if>
		            	<c:forEach var="bn" items="${bannerList }" varStatus="status">
			                <tr>
			                    <td>${status.count }</td>
   			                    <td><img src="kockImg?imgUrl=${bn.imgUrl }" alt="이미지" width="50px" class="product-img" /></td>
			                    <td>${bn.title }</td>
			                    <fmt:parseDate value="${bn.createdAt}" pattern="yyyy-MM-dd" var="createdDate"/>
			                    <td><fmt:formatDate value="${createdDate }" pattern="yyyy-MM-dd"/></td>
   			                    <td><button class="btn-cancel" data-banner-num="${bn.bannerNum }">삭제</button></td>
			                </tr>
		                </c:forEach>
					  </tbody>
					</table>
				</div>
		        </div> <!-- 배너관리 -->
		        
    </div> <!-- container -->
  <script> /* 탭 전환 스크립트 */
  document.addEventListener("DOMContentLoaded", () => {
	  const tabButtons = document.querySelectorAll(".tab-btn");
	  const tabContents = document.querySelectorAll(".tab-content");
	  const searchArea = document.getElementById("searchAndPagingWrapper");
	  const bannerSection = document.getElementById("bannerSection");
	  const pagingArea = document.getElementById("pagingArea");

	  // ✅ 현재 탭 파라미터 확인
	  const urlParams = new URLSearchParams(window.location.search);
	  const currentTab = urlParams.get("tab") || "in-progress";

	  // ✅ 페이지 진입 시 초기 상태 세팅
	  if (currentTab === "application") {
	    searchArea.style.display = "block";
	    //pagingArea.style.display = "block";
	    if (bannerSection) bannerSection.style.display = "none";
	  } else {
	    searchArea.style.display = "none";
	    pagingArea.style.display = "none";
	    if (bannerSection) bannerSection.style.display = "block";
	  }

	  // ✅ 탭 클릭 시 처리
	  tabButtons.forEach(button => {
	    button.addEventListener("click", () => {
	      const tabId = button.getAttribute("data-tab");

	      // 버튼 스타일
	      tabButtons.forEach(btn => btn.classList.remove("active"));
	      button.classList.add("active");

	      // 탭 컨텐츠 표시
	      tabContents.forEach(content => content.classList.remove("active"));
	      document.getElementById(tabId).classList.add("active");

	      // ✅ 페이지 이동 (탭 유지)
	      const url = new URL(window.location.href);
	      url.searchParams.set("tab", tabId);
	      window.location.href = url.toString();
	    });
	  });
	});
	</script>  /* 탭 전환 스크립트 */
  
<!-- 🟢 배너 추가 버튼 -->
<!-- 🟢 모달 구조 -->
<div class="modal-overlay" id="bannerModal">
  <div class="modal-content">
    <form id="bannerForm" method="post" enctype="multipart/form-data" action="${contextPath}/insertBanner">
      <div class="modal-header">
        <h2>배너 추가하기</h2>
        <button type="button" class="close-btn" id="closeBannerModal">×</button>
      </div>

      <label>배너명</label>
      <input type="text" name="title" required>

      <label>파일첨부</label>
      <div class="file-upload-group">
      <input type="hidden" id="contextPath2" value="${pageContext.request.contextPath}" />
         
        <input type="file" name="imgUrl" id="bannerImage" accept="image/*" required>
        <div class="preview-container" style="margin-top: 10px;">
	    <img id="imagePreview" src="#" alt="미리보기" style="display: none; width: 100%; max-width: 300px; border: 1px solid #ddd; padding: 5px;" />
	  </div>		
        <button type="submit" class="upload-btn">업로드</button>
      </div>
      <p class="notice">※ 메인 페이지 배너에 노출됩니다.</p>
    </form>
  </div>
</div>
  <script> <!-- 모달 보이기 -->
  const openModalBtn = document.getElementById('openBannerModal');
  const closeModalBtn = document.getElementById('closeBannerModal');
  const modal = document.getElementById('bannerModal');

  openModalBtn.addEventListener('click', () => {
    modal.style.display = 'flex';
  });

  closeModalBtn.addEventListener('click', () => {
    modal.style.display = 'none';
  });

  // ESC 키로 닫기
  window.addEventListener('keydown', (e) => {
    if (e.key === "Escape") modal.style.display = 'none';
  });
</script> <!-- 🟢 모달 보이기 -->

<script> <!-- 🟢 배너 추가 + 리스트 비동기갱신 -->
  const fileInput = document.getElementById('bannerImage');
  const form = document.getElementById('bannerForm');

  form.addEventListener('submit', function (e) {
    e.preventDefault(); // 기본 제출 막기

    const file = fileInput.files[0];
    if (!file) return;

    const img = new Image();
    const objectUrl = URL.createObjectURL(file);

    img.onload = async function () {
      const width = img.width;
      const height = img.height;
      URL.revokeObjectURL(objectUrl);

      const widthValid = width >= 1270 && width <= 1290;
      const heightValid = height >= 840 && height <= 860;

      if (!widthValid || !heightValid) {
        alert(`❌ 이미지 크기를 1280×850 ±10픽셀 이내로 업로드 해주세요.`);
        return;
      }

      // ✅ 이미지 크기 통과 후 fetch로 전송
      const formData = new FormData(form);

      try {
        const res = await fetch(`${contextPath}/insertBanner`, {
          method: 'POST',
          body: formData
        });

        const result = await res.json();

        if (result.success) {
          modal.style.display = 'none';
          form.reset();
          document.getElementById("imagePreview").style.display = "none";
          await refreshBannerTable();
        } else {
          alert('등록 실패: ' + (result.message || '알 수 없는 오류'));
        }
      } catch (err) {
        console.error('배너 등록 에러:', err);
        alert('서버 오류가 발생했습니다.');
      }
    };

    img.onerror = function () {
      alert("이미지 파일을 불러올 수 없습니다.");
    };

    img.src = objectUrl;
  });

  // 🖼 미리보기
  fileInput.addEventListener("change", function () {
    const file = this.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = function (e) {
      const preview = document.getElementById("imagePreview");
      preview.src = e.target.result;
      preview.style.display = "block";
    };
    reader.readAsDataURL(file);
  });
  const contextPath = document.getElementById('contextPath2').value;

  // ✅ 배너 테이블 갱신 함수
	async function refreshBannerTable() {
	  try {
	    const res = await fetch(`${contextPath}/getBannerList`);
	    const bannerList = await res.json();

	    const tbody = document.querySelector(".banner-table tbody");
	    tbody.innerHTML = ""; // 기존 내용 제거
	
	    if (bannerList.length === 0) {
	      const tr = document.createElement("tr");
	      tr.innerHTML = `<td colspan="5" style="text-align:center;">등록된 배너가 없습니다.</td>`;
	      tbody.appendChild(tr);
	      return;
	    }
	
	    bannerList.forEach((banner, index) => {
	    	  const tr = document.createElement("tr");

	    	  let createdAt;

	    	  if (banner.createdAt && banner.createdAt.date) {
	    	    const { year, month, day } = banner.createdAt.date;
	    	    createdAt = `\${year}-\${String(month).padStart(2, '0')}-\${String(day).padStart(2, '0')}`;
	    	  } else {
	    	    const today = new Date();
	    	    createdAt = `\${today.getFullYear()}-\${String(today.getMonth() + 1).padStart(2, '0')}-\${String(today.getDate()).padStart(2, '0')}`;
	    	  }
	    	  
	    	  const rowHtml = `
	    	  <td>\${index + 1}</td>
	    	  <td><img src="\${contextPath}/kockImg?imgUrl=\${banner.imgUrl }" alt="이미지" width="50" /></td>
	    	  <td>\${banner.title}</td>
	    	  <td>\${createdAt}</td>
	    	  <td><button class="btn-cancel" data-banner-num="\${banner.bannerNum }">삭제</button></td>
	    	  `;

	    	  tr.innerHTML = rowHtml;
	    	  tbody.appendChild(tr);
	    	});

	 	//버튼과 제한 문구 표시 제어
	    const addBtnWrapper = document.getElementById("openBannerModal");
	    const limitMsgWrapper = document.getElementById("limitBannerMsg");

	    if (bannerList.length >= 5) {
	      if (addBtnWrapper) addBtnWrapper.style.display = "none";
	      if (limitMsgWrapper) limitMsgWrapper.style.display = "block";
	    } else {
	      if (addBtnWrapper) addBtnWrapper.style.display = "block";
	      if (limitMsgWrapper) limitMsgWrapper.style.display = "none";
	    }

	
	  } catch (err) {
	    console.error("배너 테이블 갱신 실패", err);
	  }
	}
</script>  <!-- 🟢 배너 추가 + 리스트 비동기갱신 -->
<script> /* 배너 삭제 */
document.addEventListener("DOMContentLoaded", () => {
	  const table = document.querySelector(".banner-table");

	  // 🔥 테이블 전체에 클릭 이벤트 위임
	  table.addEventListener("click", async (e) => {
	    if (e.target.classList.contains("btn-cancel")) {
	      const button = e.target;
	      const bannerNum = button.dataset.bannerNum;
	      const row = button.closest("tr");

	      if (!confirm("정말 이 배너를 삭제하시겠습니까?")) return;

	      try {
	        const res = await fetch(`${contextPath}/deleteBanner`, {
	          method: "POST",
	          headers: {
	            "Content-Type": "application/json"
	          },
	          body: JSON.stringify({ bannerNum })
	        });

	        const result = await res.json();

	        if (result.success) {
	          row.remove(); // 행 제거
	          await refreshBannerTable(); 
	        } else {
	          alert("❌ 삭제 실패: " + (result.message || "알 수 없는 오류"));
	        }
	      } catch (err) {
	        console.error("삭제 요청 실패", err);
	        alert("❌ 서버 오류가 발생했습니다.");
	      }
	    }
	  });
	});

</script> <!-- 배너 삭제 -->
<script> <!-- 진행광고 상태변경 -->
document.addEventListener("DOMContentLoaded", () => {
	  document.getElementById("banner-table").addEventListener("change", async (e) => {
	    if (e.target.classList.contains("status-select")) {
	      const selectBox = e.target;
	      const newStatus = selectBox.value;
	      const adsNum = selectBox.dataset.adsNum;
	      const td = selectBox.closest("td");
	      const row = selectBox.closest("tr");

	      try {
	        const res = await fetch(`${contextPath}/updateAdStatus`, {
	          method: "POST",
	          headers: {
	            "Content-Type": "application/json"
	          },
	          body: JSON.stringify({
	            adsNum,
	            status: newStatus
	          })
	        });

	        const result = await res.json();

	        if (result.success) {

	          if (newStatus === "승인") {
	            // ✅ 셀렉트를 "승인" 텍스트로 교체
	            td.innerHTML = "승인";
	            
	            //오늘 날짜 + 7일 뒤 날짜
	            const today = new Date();
	            const nextWeek = new Date(today);
	            nextWeek.setDate(today.getDate()+7);
	            
	            const formatDate = (date) => 
	            	date.getFullYear() + "-" +
	            	String(date.getMonth()+1).padStart(2,'0')+"-"+
	            	String(date.getDate()).padStart(2,'0');
	            
                row.querySelector(".startD").innerText = formatDate(today);
                row.querySelector(".endD").innerText = formatDate(nextWeek);
	          } else if (newStatus !== "승인대기") {
	            // ✅ 승인/승인대기가 아닌 상태면 행 삭제
	            row.remove();
	          }

	        } else {
	          alert("실패: " + (result.message || "알 수 없는 오류"));
	        }
	      } catch (err) {
	        console.error("에러:", err);
	        alert("서버 오류 발생");
	      }
	    }
	  });
	});

</script> <!-- 진행광고 상태변경 -->

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
  // 이미지 클릭 → 모달에 원본 표시
  document.querySelectorAll(".product-img").forEach(function(img) {
    img.addEventListener("click", function () {
      const modal = document.getElementById("imgModal");
      const modalImg = document.getElementById("modalImage");
      modalImg.src = this.src.replace("width=100px", "");
      modal.style.display = "flex";
    });
  });

  // 닫기 버튼 클릭 시 모달 닫기
  document.getElementById("closeImgModal").addEventListener("click", function () {
    document.getElementById("imgModal").style.display = "none";
  });

  // 모달 외부 클릭 시 닫기
  document.getElementById("imgModal").addEventListener("click", function (e) {
    const modalContent = document.getElementById("imgModalContent");
    if (!modalContent.contains(e.target)) {
      this.style.display = "none";
    }
  });
});
</script>

</body>
</html>
