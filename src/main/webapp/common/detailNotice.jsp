<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항</title>
	<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.min.css">
	<link rel="stylesheet" href="${contextPath}/reset.css">
	<link rel="stylesheet" href="${contextPath}/common/detailNotice.css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div class="container">
		<jsp:include page="/header/mainHeader.jsp" />
		<div class="wrapper">
			<jsp:include page="/header/sideMenu.jsp" />
			<div class="content">
        		<h1 class="title">공지사항</h1>
        		<p class="subtitle">관리자가 쓰는 서비스 관련 공지</p>
		            <table class="table">
		                <tr class="a">
		                    <td class=""><label>제목</label></td>
		                    <td class=""><span>${notice.title}</span></td>
		                </tr>
		                <tr class="a">
		                    <td class=""><label>작성자</label></td>
		                    <td class=""><span>관리자</span></td>
		                    <td class="" style="float: right;"><span>${notice.createdAt}</span></td>
		                </tr>
		                <tr>
		                    <td class=""><div id="viewer" class="text"></div></td>
		                </tr>
		            </table>
        		<br>
        		<a href="userNoticeList" class="backBtn">목록으로</a>
         	</div>
    	</div>
		<jsp:include page="/header/footer.jsp" />
	</div>
	<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
	<script>
	const viewer = toastui.Editor.factory({
		  el: document.querySelector('#viewer'),
		  viewer: true,
		  initialValue: `${notice.content}`
		});
	</script>
</body>
</html>