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
    <title>판매자|꾸러미 주문 관리</title>

    <link rel="stylesheet" href="${contextPath }/seller/packageSubcribeList.css" />

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
    <div class="inner_body">
        <div class="sidebar">
            <div class="logo">
                <div>
                    <img src="https://i.ibb.co/B5ssbkwV/barologo2.png" alt="barologo1" border="0" width="100" />
                </div>
            </div>
            <ul>
                <li><a href="#">상품관리</a></li>
                <li><a href="#">상품 주문관리</a></li>
                <li><a href="#">꾸러미 판매 관리</a></li>
                <li><a href="#">꾸러미 주문 관리</a></li>
                <li><a href="#">리뷰</a></li>
                <li><a href="#">콕팜</a></li>
                <li><a href="#">알림 내역</a></li>
                <li><a href="#">문의 내역</a></li>
                <li><a href="#">스토어 정보</a></li>
            </ul>
        </div>
    </div>
    <header id="header">
        <div id="info">
            <span id="email">kosta@kosta.com</span>
            <span>내 정보</span>
            <span>로그아웃</span>
        </div>
    </header>
    <div id="content">
        <div class="notice-header">
            <span id="title">꾸러미 구독 관리</span>
        </div>

        <select id="order-date">
            <option>주문 날짜 조회</option>

        </select>
        <table id="notie_table" class="table" width="100%">
            <thead>
                <tr>
                    <th>상품번호</th>
                    <th>아이디</th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th>전화번호</th>
                    <th>배송지</th>
                    <th>구독일</th>
                    <th>수량</th>
                    <th>회차</th>
                </tr>
            </thead>
            <tbody>

                <td>2025110111</td>
                <td>hong1234</td>
                <td>홍길동</td>
                <td>hong1234</td>
                <td>010-1234-5678</td>
                <td>서울특별시 강남구</td>
                <td>2025.03.11</td>
                <td>2</td>
                <td>1</td>

                <tr>
                    <td>
                        <div class="uiGridCell productNum"><a href="#">2025110111</a></div>
                    </td>
                    <td>
                        <div class="uiGridCell id" name="id">hong1234</div>
                    </td>
                    <td>
                        <div class="uiGridCell">홍길동</div>
                    </td>

                    <td>
                        <div class="uiGridCell" name="email">hong123@naver.com</div>
                    </td>
                    <td>
                        <div class="uiGridCell">010-1234-5678</div>
                    </td>
                    <td>
                        <div class="uiGridCell">서울특별시 강남구</div>
                    </td>
                    <td>
                        <div class="uiGridCell">2025.03.11</div>
                    </td>
                    <td>
                        <div class="uiGridCell">2</div>
                    </td>
                    <td>
                        <div class="uiGridCell">1 <span style="font-weight: bold;">1</span></div>
                    </td>

                </tr>

            </tbody>

        </table>

    </div>

    <div id="modal" class="modal">
        <div id="modalContent" class="modalContent">
            <span class="close">&times;</span>
            <p class="modalTitle">주문 상세 조회</p>
        </div>
    </div>

</body>
</html>