<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>판매자 | 꾸러미 리뷰 관리</title>

<link rel="stylesheet" href="${contextPath}/seller/packReviewList.css" />

<link
	href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css"
	rel="stylesheet"
	integrity="sha384-jFvlDSY24z+oXMByOoX2Z1gM+M5NMd0uG7sDa4skv2mHXPuS0/RYXwPGLK0+Mgdc"
	crossorigin="anonymous" />

<script
	src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"
	integrity="sha384-FcKnveOKVsyQDhaxWTmHPNxY0wtv3QwEmOUwRZ5g+QqTQvSKKmnkT0NiFcDCCIvg"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	$(document).ready(
			function() {
				const modal = $("#modal");
				const modalContent = $("#modal .modalContent");

				// 전체 선택 체크박스 기능 추가
				$("thead input[type='checkbox']").on(
						"click",
						function() {
							let isChecked = $(this).prop("checked");
							$("tbody input[type='checkbox']").prop("checked",
									isChecked);
						});
				
				//리뷰 작성 버튼 클릭
				$(".btn.commentAdd").on("click",function() {
					let selectedReviews = [];
					// 체크된 체크박스가 있는 행에서 리뷰번호 가져오기
					$("input[type='checkbox']:checked").each(function() {
						let reviewNum = $(this).closest("tr").find(".reviewNum").text().trim();
						if (reviewNum) {
							selectedReviews.push(reviewNum);
						}
					});
					if(selectedReviews.length=== 0){
						alert("리뷰를 선택해주세요");
						return;
					}
					
				  	// 선택된 리뷰번호 표시
				  	$("#reviewNum").text(selectedReviews.join(","));
				    $.ajax({
				        url: "insertPackReviewComment",   // ✅ 서블릿 URL
				        method: "GET",
				        data: { pkReviewNums: selectedReviews.join(",") },  // ✅ 선택된 번호 넘기기
				        dataType: "html",
				        success: function (response) {
				            $("#modalContent").html(response);  // ✅ 모달 안에 내용 채우기
				            $("#modal").css("display", "flex"); // ✅ 모달 열기
				            $("body").addClass("modal-open");
				        },
				        error: function () {
				            alert("답글 작성 화면을 불러오는 데 실패했습니다.");
				        }
				    });	

							
				});
				
				   // ✅ 등록 버튼 클릭 시 답변 저장 요청 (JSON POST)
			    $(document).on("click", ".btn.addBtn", function () {
			        const reviewNums = $("#reviewNum").text().split(",");
			        const commentContent = $("#reviewComment").val().trim();
					console.log(reviewNums);
			        if (!commentContent) {
			            alert("답글을 작성해주세요");
			            return;
			        }
					
			        $.ajax({
			            url: "insertPackReviewComment",
			            type: "POST",
			            contentType: "application/json",
			            data: JSON.stringify({
			                pkReviewNums: reviewNums,
			                commentContent: commentContent
			            }),
			            success: function (response) {
			                alert("답변 등록이 완료되었습니다.");
			                location.reload();
			            },
			            error: function () {
			                alert("답변 등록에 실패했습니다.");
			            }
			        });
			    });


			    
				//리뷰내용 클릭시 상세 리뷰 창으로 이동
				$(".reviewDetail").on("click", function(event) {
					event.preventDefault(); // 기본 링크 이동 방지

					let row = $(this).closest("tr");
					let reviewNum = row.find(".reviewNum").text().trim();
					let orderNum = row.find(".orderNum a").text().trim();

					// Ajax로 detailReviewComment.html을 불러와서 모달에 표시
					$.ajax({
						url : "detailPackReviewComment", // 올바른 경로 확인 필수!
						method : "GET",
						data : {reviewNum: reviewNum},
						success : function(response) {
							$("#modalContent").html(response); // 모달 내부 변경
							$("#reviewNum").text(reviewNum);
							$("#orderNum").text(orderNum);
							$("#modal").css("display", "flex"); // 모달 표시
							$("body").addClass("modal-open");
						},
						error : function(xhr, status, error) {
							console.error("❌ AJAX 실패:", status, error);
							alert("❌ 리뷰 상세 정보를 불러오는 데 실패했습니다.");
						}
					});
				});

				// 모달 닫기 이벤트
				$(document).on("click", ".close, .closeBtn", function() {
					modal.css("display", "none");
					$("body").removeClass("modal-open");

				});

				// 모달 바깥 클릭 시 닫기
				$(window).on("click", function(event) {
					if ($(event.target).is("#modal")) {
						modal.css("display", "none");
						$("body").removeClass("modal-open");

					}
				});

				$(document).on("click", function(event) {
					if ($(event.target).is("#modal, #reviewModal")) {
						$(event.target).css("display", "none");
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
		<div class="noticeHeader">
			<span id="title">꾸러미 리뷰 관리</span>
		</div>

		<div class="filterWrapper">
			<div class="comment leftSection">
				<button class="btn commentAdd">답변 등록</button>
			</div>
			<div class="rightSection">
				<form id="sortForm" method="get" action="${contextPath}/sellerPackReviewList">
					<!-- 답변 여부 필터 -->
					<select name="commentStat" id="commentStat"
						onchange="this.form.submit()">
						<option value="all" ${param.commentStat == 'all' ? 'selected' : ''}>답변전체</option>
						<option value="answered"
							${param.commentStat == 'answered' ? 'selected' : ''}>답변작성</option>
						<option value="unanswered"
							${param.commentStat == 'unanswered' ? 'selected' : ''}>답변미작성</option>
					</select>
	
					<!-- 별점 필터 추가 -->
					<select name="ratingFilter" id="ratingFilter"
						onchange="this.form.submit()">
						<option value="all"
							${param.ratingFilter == 'all' ? 'selected' : ''}>평점전체</option>
						<option value="5" ${param.ratingFilter == '5' ? 'selected' : ''}>5점</option>
						<option value="4" ${param.ratingFilter == '4' ? 'selected' : ''}>4점</option>
						<option value="3" ${param.ratingFilter == '3' ? 'selected' : ''}>3점</option>
						<option value="2" ${param.ratingFilter == '2' ? 'selected' : ''}>2점</option>
						<option value="1" ${param.ratingFilter == '1' ? 'selected' : ''}>1점</option>

					</select>
	
					<!-- 정렬 -->
					<select name="sort" id="sortSelect" onchange="this.form.submit()">
						<option value="new" ${param.sort == 'new' ? 'selected' : ''}>최신등록순</option>
						<option value="old" ${param.sort == 'old' ? 'selected' : ''}>오래된등록순</option>
						<option value="rating" ${param.sort == 'rating' ? 'selected' : ''}>평점높은순</option>
						<option value="lowRating"
							${param.sort == 'lowRating' ? 'selected' : ''}>평점낮은순</option>
					</select>
				</form>
			</div>
		</div>
		<div class="tableWrapper">

			<table id="notie_table" class="table">
				<thead>
					<tr>
						<th><input type="checkbox"></th>
						<th style="font-weight: bold;">상품번호</th>
						<th style="font-weight: bold;">상품명</th>
						<th style="font-weight: bold;">상품옵션</th>
						<th style="font-weight: bold;">구매자평점</th>
						<th style="font-weight: bold;">리뷰내용</th>
						<th style="font-weight: bold;">포토</th>
						<th style="font-weight: bold;">등록자</th>
						<th style="font-weight: bold;">등록일자</th>
						<th style="font-weight: bold;">리뷰글번호</th>
						<th style="font-weight: bold;">답글여부</th>
						<th style="font-weight: bold;">상품주문번호</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="review" items="${reviewList }">
						<tr>
							<td>
								<div class="uiGridCell"><input type="checkbox"></div>
							</td>
							<td>
								<div class="uiGridCell packageName">
									<a href="detailPackage?packageNum=${review.packageNum}">${review.packageNum}</a>
								</div>
							</td>
							<td>
								<div class="uiGridCell packageName">
									<a href="detailPackage?packageNum=${review.packageNum}">${review.packageName}</a>
								</div>
							</td>
							<td>
								<div class="uiGridCell packageName">
									${review.packageUnit}
								</div>
							</td>
							<td>
								<c:forEach var="i" begin="1" end="5">
									<c:choose>
										<c:when test="${i <= review.pkRating}">★</c:when>
										<c:otherwise>☆</c:otherwise>
									</c:choose>
								</c:forEach><span>&nbsp;&nbsp;${review.pkRating}</span>
							</td>
							<td>
								<div class="uiGridCell reviewDetail">
									<a href="#">${review.pkReviewContent}</a>
								</div>
							</td>
							<td>
								<div class="uiGridCell">
									<img src="${contextPath}${review.pkReviewImgUrl}" width="50">
								</div>
							</td>


							<td>
								<div class="uiGridCell">${review.userId}</div>
							</td>
							<td>
								<div class="uiGridCell">${review.createdAt }</div>
							</td>
							<td>
								<div class="uiGridCell reviewNum">${review.pkReviewNum }</div>
							</td>
							<td>
								<div class="uiGridCell">
									<c:choose>
										<c:when test="${review.pkCommentStatus eq true }">
											<span>Y</span>
										</c:when>
										<c:otherwise><span>N</span></c:otherwise>
									</c:choose>
								</div>
							</td>

							<td>
								<div class="uiGridCell orderNum">
									<a href="#">${review.pkOrderNum }</a>
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
			<!-- << -->
				<a href="?page=${currentPage - pageGroupSize < 1 ? 1 : currentPage - pageGroupSize}&commentStat=${param.commentStat}&ratingFilter=${param.ratingFilter}&sort=${param.sort}"
				class="${currentPage <= pageGroupSize ? 'disabled' : ''}">&laquo;</a>
		
			<!-- < -->
				<a href="?page=${currentPage - 1}&commentStat=${param.commentStat}&ratingFilter=${param.ratingFilter}&sort=${param.sort}"
				class="${currentPage == pageGroupSize ? 'disabled' : ''}">&lsaquo;</a>
		
			<!-- 번호 -->
			<c:forEach begin="${groupStartPage}" end="${groupEndPage}" var="i">
				<a href="?page=${i}&commentStat=${param.commentStat}&ratingFilter=${param.ratingFilter}&sort=${param.sort}" class="${currentPage == i ? 'active' : ''}">${i}</a>
			</c:forEach>
		
			<!-- > -->
				<a href="?page=${currentPage + 1}&commentStat=${param.commentStat}&ratingFilter=${param.ratingFilter}&sort=${param.sort}"
				class="${currentPage >= totalPages ? 'disabled' : ''}">&rsaquo;</a>
		
			<!-- >> -->
				<a href="?page=${currentPage + pageGroupSize > totalPages ? totalPages : currentPage + pageGroupSize}&commentStat=${param.commentStat}&ratingFilter=${param.ratingFilter}&sort=${param.sort}"
				class="${groupEndPage >= totalPages ? 'disabled' : ''}">&raquo;</a>
		</div>

		<div id="modal" class="modal">
			<div class="modalContent" id="modalContent">

		  	</div>

		</div>
	</div>
</body>
</html>