<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>판매자|리뷰관리</title>
<link rel="stylesheet" href="${contextPath}/reset.css" />
<link rel="stylesheet" href="${contextPath}/noticeList.css" />
<link rel="stylesheet" href="${contextPath}/seller/reviewList.css" />

<link
	href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css"
	rel="stylesheet"
	integrity="sha384-jFvlDSY24z+oXMByOoX2Z1gM+M5NMd0uG7sDa4skv2mHXPuS0/RYXwPGLK0+Mgdc"
	crossorigin="anonymous" />

<script
	src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"
	integrity="sha384-FcKnveOKVsyQDhaxWTmHPNxY0wtv3QwEmOUwRZ5g+QqTQvSKKmnkT0NiFcDCCIvg"
	crossorigin="anonymous"></script>
<script src="${contextPath}/noticeList.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
        $(document).ready(function () {
            const modal = $("#modal");
            const modalContent = $("#modal .modalContent");
           
           
            // 전체 선택 체크박스 기능 추가
            $("thead input[type='checkbox']").on("click", function () {
                let isChecked = $(this).prop("checked");
                $("tbody input[type='checkbox']").prop("checked", isChecked);
            });

            $(".btn.commentAdd").on("click", function () {
                let selectedReviews = [];
                // 체크된 체크박스가 있는 행에서 리뷰번호 가져오기
                $("input[type='checkbox']:checked").each(function () {
                    let reviewNum = $(this).closest("tr").find(".reviewNum").text().trim();
                    if (reviewNum) {
                        selectedReviews.push(reviewNum);
                    }
                });

                $.ajax({
                    url: "insertReviewComment.html",
                    method: "GET",
                    dataType: "html",
                    success: function (response) {
                        modalContent.html(response); // 모달 내부에 HTML 삽입
                        modal.css("display", "flex"); // 모달 표시
                        $("body").addClass("modal-open");
                        // 선택된 리뷰번호가 있을 때만 모달 표시
                        if (selectedReviews.length > 0) {

                            $("#modal #reviewNum").text(selectedReviews);

                            modal.css("display", "flex");
                        } else {
                            alert("리뷰를 선택해주세요!");
                        }

                    },
                    error: function () {
                        alert("주문 상세 정보를 불러오는 데 실패했습니다.");
                    }
                });
            });



            //리뷰내용 클릭시 상세 리뷰 창으로 이동
            $(".reviewDetail").on("click", function (event) {
                event.preventDefault(); // 기본 링크 이동 방지

                let row = $(this).closest("tr");
                let reviewNum = row.find(".reviewNum").text().trim();
                let orderNum = row.find(".orderNum a").text().trim();

                // Ajax로 detailReviewComment.html을 불러와서 모달에 표시
                $.ajax({
                    url: "detailReviewComment.html", // 올바른 경로 확인 필수!
                    method: "GET",
                    dataType: "html",
                    success: function (response) {
                        $("#modalContent").html(response); // 모달 내부 변경
                        $("#reviewNum").text(reviewNum);
                        $("#orderNum").text(orderNum);
                        $("#modal").css("display", "flex"); // 모달 표시
                        $("body").addClass("modal-open");
                    },
                    error: function (xhr, status, error) {
                        console.error("❌ AJAX 실패:", status, error);
                        alert("❌ 리뷰 상세 정보를 불러오는 데 실패했습니다.");
                    }
                });
            });



            // 모달 닫기 이벤트
            $(document).on("click", ".closeBtn", function () {
                modal.css("display", "none");
                $("body").removeClass("modal-open");

            });

            // 모달 바깥 클릭 시 닫기
            $(window).on("click", function (event) {
                if ($(event.target).is("#modal")) {
                    modal.css("display", "none");
                    $("body").removeClass("modal-open");

                }
            });

            $(document).on("click", function (event) {
                if ($(event.target).is("#modal, #reviewModal")) {
                    $(event.target).css("display", "none");
                    $("body").removeClass("modal-open");
                }
            });

  

        });
    </script>

</head>

<body>
	<div class="inner_body">
		<div class="sidebar">
			<jsp:include page="/header/sellerHeader.jsp" />
		</div>
	</div>

	<div id="content">
		<div>
			<header id="header">
				<div id="info">
					<span id="email">kosta@kosta.com</span> <span>내 정보</span> <span>로그아웃</span>
				</div>
			</header>
		</div>
		<div class="notice-header">
			<span id="title">리뷰 관리</span>
		</div>


		<table id="notie_table" class="table" style="width: 950px;">
			<thead>
				<tr>
					<th><input type="checkbox"></th>
					<th>상품번호</th>
					<th>상품명</th>
					<th>카테고리</th>
					<th>구매자평점</th>
					<th>포토</th>
					<th>리뷰내용</th>
					<th>등록자</th>
					<th>등록일자</th>
					<th>리뷰글번호</th>
					<th>답글여부</th>
					<th>상품주문번호</th>
					<th>구독유형</th>
				</tr>
			</thead>
			<tbody>
				<div class="comment">
					<button class="btn commentAdd">답변 등록</button>
				</div>

				<tr>
					<td>
						<div class="uiGridCell">
							<input type="checkbox">
						</div>
					</td>
					<td>
						<div class="uiGridCell productNum">
							<a href="#">2025030415668</a>
						</div>
					</td>
					<td>
						<div class="uiGridCell productName">
							<a href="#">파프리카</a>
						</div>
					</td>
					<td>
						<div class="uiGridCell">고추/피망/파프리카/허브채소</div>
					</td>
					<td>
						<div class="uiGridCell">⭐⭐⭐⭐ 4</div>
					</td>
					<td>
						<div class="uiGridCell">
							<img src="../../img/thumbnail.png" width="50">
						</div>
					</td>
					<td>
						<div class="uiGridCell reviewDetail">
							<a href="#">파프리카가 싱싱</a>
						</div>
					</td>

					<td>
						<div class="uiGridCell">hong****</div>
					</td>
					<td>
						<div class="uiGridCell">2025.03.28</div>
					</td>
					<td>
						<div class="uiGridCell reviewNum">2222</div>
					</td>
					<td>
						<div class="uiGridCell">N</div>
					</td>

					<td>
						<div class="uiGridCell orderNum">
							<a href="#">2025030415668</a>
						</div>
					</td>
					<td>
						<div class="uiGridCell">-</div>
					</td>

				</tr>
				<tr>
					<td>
						<div class="uiGridCell">
							<input type="checkbox">
						</div>
					</td>
					<td>
						<div class="uiGridCell productNum">
							<a href="#">2025030415668</a>
						</div>
					</td>
					<td>
						<div class="uiGridCell productName">
							<a href="#">파프리카</a>
						</div>
					</td>
					<td>
						<div class="uiGridCell">고추/피망/파프리카/허브채소</div>
					</td>
					<td>
						<div class="uiGridCell">⭐⭐⭐⭐ 4</div>
					</td>
					<td>
						<div class="uiGridCell">
							<img src="../../img/thumbnail.png" width="50">
						</div>
					</td>
					<td>
						<div class="uiGridCell reviewDetail">
							<a href="#">파프리카가 싱싱</a>
						</div>
					</td>

					<td>
						<div class="uiGridCell">hong****</div>
					</td>
					<td>
						<div class="uiGridCell">2025.03.28</div>
					</td>
					<td>
						<div class="uiGridCell reviewNum">1111</div>
					</td>
					<td>
						<div class="uiGridCell">N</div>
					</td>

					<td>
						<div class="uiGridCell orderNum">
							<a href="#">2025030415668</a>
						</div>
					</td>
					<td>
						<div class="uiGridCell">-</div>
					</td>

				</tr>
			</tbody>
		</table>
		<div id="modal" class="modal">
			<div id="modalContent" class="modalContent"></div>

		</div>
	</div>
</body>
</html>