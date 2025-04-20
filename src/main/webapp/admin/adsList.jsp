<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="activeTab" value="${param.tab != null ? param.tab : 'in-progress'}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title>광고 관리</title>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/admin/adminAdsList.css" />
</head>
<body>
	<div class="container">
		<div class="wrapper">
			<jsp:include page="/header/adminHeader.jsp" />
			<div class="content">
			    <h1 class="title">배너 광고</h1>
<!-- 	        	<p class="subtitle">배너 광고 내역</p>
 -->	   	        <!-- 탭 버튼 -->
 					<div id="searchAndPagingWrapper" style="display: none;">
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
 
				<div class="tab-buttons">
				  <button class="tab-btn ${activeTab == 'in-progress' ? 'active' : ''}" data-tab="in-progress">진행 광고 목록</button>
				  <button class="tab-btn ${activeTab == 'application' ? 'active' : ''}" data-tab="application">종료된 광고 목록</button>
				</div>

		        <!-- 진행 광고 목록 탭 -->
				<div id="in-progress" class="tab-content ${activeTab == 'in-progress' ? 'active' : ''}">
		          <table id="banner-table" class="table">
		            <thead>
		              <tr>
		                <th>스토어명</th>
		                <th>제목</th>
		                <th>상품명</th>
		                <th>이미지파일</th>
		                <th>상품링크</th>
		                <th>광고시작일</th>
		                <th>광고종료일</th>
		                <th>상태</th>
		              </tr>
		            </thead>
		            <tbody>
		              <tr>
		                <td>강화도햇밤</td>
		                <td>abcd@naver.com</td>
		                <td>햇 고구마 1.5kg</td>
		                <td><img src="https://via.placeholder.com/50" alt="상품 이미지" /></td>
		                <td><a href="https://barofarm.com/product" target="_blank">https://barofarm.com/product</a></td>
		                <td>2025.04.04</td>
		                <td>2025.04.11</td>
		                <td>
		                  <select class="status-select">
		                    <option>승인대기</option>
		                    <option>승인</option>
		                    <option>반려</option>
		                  </select>
		                </td>
		              </tr>
		              <tr>
		                <td>강화도햇밤</td>
		                <td>abcd@naver.com</td>
		                <td>햇 고구마 1.5kgddddddda</td>
		                <td><img src="https://via.placeholder.com/50" alt="상품 이미지" /></td>
		                <td><a href="https://barofarm.com/product" target="_blank">https://barofarm.com/product</a></td>
		                <td>2025.04.04</td>
		                <td>2025.04.11</td>
		                <td>
		                  <select class="status-select">
		                    <option>상태</option>
		                    <option>승인</option>
		                    <option>반려</option>
		                    <option>대기중??</option>
		                  </select>
		                </td>
		              </tr>
		              <tr>
		                <td>강화도햇밤</td>
		                <td>abcd@naver.com</td>
		                <td>햇 고구마 1.5kgddddddda</td>
		                <td><img src="https://via.placeholder.com/50" alt="상품 이미지" /></td>
		                <td><a href="https://barofarm.com/product" target="_blank">https://barofarm.com/product</a></td>
		                <td>2025.04.04</td>
		                <td>2025.04.11</td>
		                <td>
		                  <select class="status-select">
		                    <option>상태</option>
		                    <option>승인</option>
		                    <option>반려</option>
		                    <option>대기중??</option>
		                  </select>
		                </td>
		              </tr>
		              <tr>
		                <td>강화도햇밤</td>
		                <td>abcd@naver.com</td>
		                <td>햇 고구마 1.5kgddddddda</td>
		                <td><img src="https://via.placeholder.com/50" alt="상품 이미지" /></td>
		                <td><a href="https://barofarm.com/product" target="_blank">https://barofarm.com/product</a></td>
		                <td>2025.04.04</td>
		                <td>2025.04.11</td>
		                <td>
		                  <select class="status-select">
		                    <option>상태</option>
		                    <option>승인</option>
		                    <option>반려</option>
		                    <option>대기중??</option>
		                  </select>
		                </td>
		              </tr>
		              <tr>
		                <td>강화도햇밤</td>
		                <td>abcd@naver.com</td>
		                <td>햇 고구마 1.5kgddddddda</td>
		                <td><img src="https://via.placeholder.com/50" alt="상품 이미지" /></td>
		                <td><a href="https://barofarm.com/product" target="_blank">https://barofarm.com/product</a></td>
		                <td>2025.04.04</td>
		                <td>2025.04.11</td>
		                <td>
		                  <select class="status-select">
		                    <option>상태</option>
		                    <option>승인</option>
		                    <option>반려</option>
		                    <option>대기중??</option>
		                  </select>
		                </td>
		              </tr>
		            </tbody>
		          </table>
		        </div>
			    <!-- 광고 신청 목록 탭 -->
				<div id="application" class="tab-content ${activeTab == 'application' ? 'active' : ''}">
		          <table id="banner-table2" class="table">
		            <thead>
		              <tr>
		                <th>스토어명</th>
		                <th>아이디</th>
		                <th>상품명</th>
		                <th>이미지파일</th>
		                <th>상품링크</th>
		                <th>광고시작일</th>
		                <th>광고종료일</th>
		                <th>광고종료일</th>
		              </tr>
		            </thead>
		            <tbody>
		              <c:forEach var="ad" items="${adsList}">
						  <tr>
						    <td>${ad.storeName}</td>
						    <td>${ad.title}</td>
						    <td>${ad.productName}</td>
						    <td><img src="kockImg?imgUrl=${ad.imgUrl }" alt="이미지" width="50" /></td>
						    <td><a href="${ad.productUrl}" target="_blank">${ad.productUrl}</a></td>
						    <td>${ad.startDate}</td>
						    <td>${ad.endDate}</td>
						    <td>
						      <select class="status-select">
						        <option ${ad.status == '승인대기' ? 'selected' : ''}>승인대기</option>
						        <option ${ad.status == '승인' ? 'selected' : ''}>승인</option>
						        <option ${ad.status == '반려' ? 'selected' : ''}>반려</option>
						      </select>
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
		        
				<div class="paging" id="pagingArea" style="text-align: center; margin-top: 20px;">
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
		        <div class="banner-section" id="bannerSection" style="margin-top: 60px;">
					<h2 class="title" style="margin-top: 60px;">메인 배너 관리</h2>
					
					<div class="banner-add-section"  id="openBannerModal" style="text-align: right; margin-bottom: 10px;">
					  <button class="banner-add-btn">배너 추가</button>
					</div>
					
					<table class="table banner-table">
					  <thead>
					    <tr>
					      <th>순번</th>
					      <th>이미지파일</th>
					      <th>배너명</th>
					      <th>등록일</th>
					    </tr>
					  </thead>
					  <tbody>
					    <tr>
					      <td>1</td>
					      <td><img src="https://via.placeholder.com/50" alt="배너 이미지" /></td>
					      <td>바로팜 지혜 특산</td>
					      <td>2025.04.04</td>
					    </tr>
					     <tr>
					      <td>1</td>
					      <td><img src="https://via.placeholder.com/50" alt="배너 이미지" /></td>
					      <td>바로팜 지혜 특산</td>
					      <td>2025.04.04</td>
					    </tr>
					     <tr>
					      <td>1</td>
					      <td><img src="https://via.placeholder.com/50" alt="배너 이미지" /></td>
					      <td>바로팜 지혜 특산</td>
					      <td>2025.04.04</td>
					    </tr>
					     <tr>
					      <td>1</td>
					      <td><img src="https://via.placeholder.com/50" alt="배너 이미지" /></td>
					      <td>바로팜 지혜 특산</td>
					      <td>2025.04.04</td>
					    </tr>
					     <tr>
					      <td>1</td>
					      <td><img src="https://via.placeholder.com/50" alt="배너 이미지" /></td>
					      <td>바로팜 지혜 특산</td>
					      <td>2025.04.04</td>
					    </tr>
					  </tbody>
					</table>
		        </div> <!-- 배너관리 -->
		        
			</div> <!-- content -->
		</div> <!-- wrapper -->
    </div> <!-- container -->
  <script>
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
	    pagingArea.style.display = "block";
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
	</script>
  
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
      <input type="text" name="bannerTitle" required>

      <label>파일첨부</label>
      <div class="file-upload-group">
        <input type="file" name="bannerImage" id="bannerImage" accept="image/*" required>
        <div class="preview-container" style="margin-top: 10px;">
	    <img id="imagePreview" src="#" alt="미리보기" style="display: none; width: 100%; max-width: 300px; border: 1px solid #ddd; padding: 5px;" />
	  </div>		
        <button type="submit" class="upload-btn">업로드</button>
      </div>
      <p class="notice">※ 메인 페이지 배너에 노출됩니다.</p>
    </form>
  </div>
</div>
  <script>
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
</script>
<script>
  const fileInput = document.getElementById('bannerImage');
  const form = document.getElementById('bannerForm');

  form.addEventListener('submit', function (e) {
    const file = fileInput.files[0];
    if (!file) return;

    const img = new Image();
    const objectUrl = URL.createObjectURL(file);

    img.onload = function () {
      const width = img.width;
      const height = img.height;

      URL.revokeObjectURL(objectUrl);

      const widthValid = width >= 1270 && width <= 1290;
      const heightValid = height >= 840 && height <= 860;

      if (!widthValid || !heightValid) {
        e.preventDefault();
        alert(`❌ 이미지 크기를 1280×850 ±10픽셀 이내로 업로드 해주세요.`);
      } else {
        form.submit(); // 크기 통과 시 제출
      }
    };

    img.onerror = function () {
      e.preventDefault();
      alert("이미지 파일을 불러올 수 없습니다.");
    };

    img.src = objectUrl;

    // 제출을 일시 중단, 이미지 크기 검사 후 통과하면 form.submit() 호출됨
    e.preventDefault();
  });
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

</script>
 
</body>
</html>
