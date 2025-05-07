<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
$(document).ready(function () {
    // 펼침 상태 복원
    const openMenu = localStorage.getItem("openMenu");
    if (openMenu) {
        const target = $(`.has-submenu[data-menu="${openMenu}"]`);
        target.addClass("open");
        target.find(".submenu").show();
    }

    // 클릭 시 저장
    $(".has-submenu > a").click(function () {
        const parent = $(this).parent();
        const submenu = parent.find(".submenu");

        if (parent.hasClass("open")) {
            parent.removeClass("open");
            submenu.slideUp();
            localStorage.removeItem("openMenu");
        } else {
            $(".has-submenu").removeClass("open").find(".submenu").slideUp();
            parent.addClass("open");
            submenu.slideDown();
            localStorage.setItem("openMenu", parent.data("menu"));
        }
    });
});
</script>
<link rel="stylesheet" href="${contextPath}/header/sellerHeader.css">
<div class="inner_body">
	<div class="sidebar">
		<div class="logo">
			<div>
				<a href="${contextPath}/main"> <img
					src="${contextPath }/img/barologo2.png" alt="barologo1" border="0"
					width="100">
				</a>
			</div>
		</div>
	<ul>
		<!-- 일반 상품 아코디언 -->
		<li class="has-submenu"><a href="javascript:void(0)"
			data-menu="normal-product">일반 상품</a>
			<ul class="submenu">
				<li><a href="${contextPath }/sellerProductList">상품 관리</a></li>
				<li><a href="${contextPath }/sellerProdOrderList">주문 관리</a></li>
				<li><a href="${contextPath }/sellerProdReviewList">리뷰 관리</a></li>
				<li><a href="${contextPath }/sellerCancelList">취소 내역</a></li>
			</ul>
		</li>

		<!-- 꾸러미 아코디언 -->
		<li class="has-submenu"><a href="javascript:void(0)"
			data-menu="package">꾸러미</a>
			<ul class="submenu">
				<li><a href="${contextPath}/sellerPackageList">판매 관리</a></li>
				<li><a href="${contextPath}/sellerPackOrderList">주문 관리</a></li>
				<li><a href="${contextPath }/sellerPackReviewList">리뷰 관리</a></li>
				<li><a href="${contextPath}/sellerPackSubscribeList">구독관리</a></li>
			</ul>
		</li>

		<!-- 기타 -->
		<li><a href="${contextPath }/farmPointList">팜 포인트</a></li>
	    <li><a href="${contextPath}/sellerKCList">콕팜 댓글</a></li>
		<li><a href="${contextPath }/sellerAlarmList">알림 내역</a></li>
		<li><a href="${contextPath }/detailStoreInfo">스토어 정보</a></li>
		<li><a href="${contextPath }/sellerAdsList">배너 광고</a></li>
		</ul>
	</div>
</div>
