<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성한 리뷰</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<style>
  .tabMenu {
    display: flex;
    gap: 8px;
    margin-bottom: 20px;
    padding-bottom:10px;
    border-bottom: 2px solid black;
  }
  .tabBtn {
    padding: 10px 20px;
    background-color: #eee;
    border: none;
    cursor: pointer;
    font-size: 15px;

  }
  .tabBtn.active {
    background-color: #65B741;
    color: white;
  }
  .tabContent {
    display: none;
  }
  .tabContent.active {
    display: block;
  }
  
  .wrapper {
	display: flex;
	flex-direction: row;
	min-height: calc(100vh - 200px); /* 헤더 높이만큼 빼기 */
}
.sideMenu {
	position: relative;
}
.container {
	max-width: 1300px;
	margin: 0 auto;
}

.content {
	flex: 1;
	align-self: flex-start;
	padding: 30px 0;
	max-width: 1000px;
	min-width: 0;
	margin-left: 20px;
	margin-right: auto;
	position: relative;
}
.header {
    font-size: 30px;
    color:#5cb85c;
    font-weight: bold;
    margin:20px;

}

</style>
<script>
  $(document).ready(function() {
    // 탭 Ajax 로딩 함수
    function loadTab(url, targetId) {
      $.ajax({
        url: url,
        type: "GET",
        success: function(data) {
          $("#" + targetId).html(data);
        },
        error: function() {
          $("#" + targetId).html("<p>불러오기에 실패했습니다.</p>");
        }
      });
    }

    // 첫 번째 탭 자동 로딩
    var firstBtn = $(".tabBtn.active");
    loadTab(firstBtn.data("url"), firstBtn.data("target"));

    // 탭 클릭 이벤트
    $(".tabBtn").click(function() {
      $(".tabBtn").removeClass("active");
      $(this).addClass("active");

      $(".tabContent").removeClass("active");
      $("#" + $(this).data("target")).addClass("active");

      loadTab($(this).data("url"), $(this).data("target"));
    });
  });
</script>
</head>
<body>
<jsp:include page="/header/mainHeader.jsp" />

<div class="container">
	<div class="wrapper">
	  <div class="sideMenu">
	    <jsp:include page="/header/buyerMenu.jsp" />
	  </div>
	
	  <div id="content" style="width:100%;">
	    <div class="header">작성한 리뷰 내역</div>
	
	    <!-- 탭 메뉴 -->
	    <div class="tabMenu">
	      <button class="tabBtn active" data-url="${contextPath}/prodWrittenReviewList" data-target="tab1">일반 상품</button>
	      <button class="tabBtn" data-url="${contextPath}/packWrittenReviewList" data-target="tab2">꾸러미</button>
	    </div>
	
	
	    <!-- 탭 콘텐츠 영역 -->
	    <div id="tab1" class="tabContent active"></div>
	    <div id="tab2" class="tabContent"></div>
	  </div>
	</div><!-- end of wrapper -->
</div><!-- end of container -->


</body>
</html>