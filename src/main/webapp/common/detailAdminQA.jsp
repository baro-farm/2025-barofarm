<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>문의하기 상세조회</title>
	<link rel="stylesheet" href="${contextPath}/reset.css">
	<link rel="stylesheet" href="${contextPath}/common/detailAdminQA.css">
</head>
<body>
	<div class="container">
		<jsp:include page="/header/mainHeader.jsp" />
		<div class="wrapper">
			<jsp:include page="/header/sideMenu.jsp" />
			<div class="content">
		        <h1 class="title">문의하기</h1>
		        <p class="subtitle">서비스와 관련하여 관리자에게 문의</p>
		        <table class="table">
		        	<tr class="a">
		            	<td class=""><label>글쓴이</label></td>
		                <td class=""><span>${adminq.userId}</span></td>
		            </tr>
		            <tr class="a">
		            	<td class=""><label>제목</label></td>
		                <td class=""><span>${adminq.title}</span></td>
		                <td class="date"><span>${adminq.createdAt}</span></td>
		            </tr>
		        </table>
		        <div class="textbox">
			        <div id="" class="question text">${adminq.content}</div>
			        <c:if test="${not empty adminq.aContent}">
			           	<div id="" class="answer text">${adminq.aContent}</div>
			        </c:if>
		        </div>
		        <br>
        		<a href="adminQAList" class="backBtn">목록으로</a>
        		<button id="deleteBtn">삭제하기</button>
        	</div>
        </div>
        <jsp:include page="/header/footer.jsp" />
    </div>
    <script>
	    document.getElementById('deleteBtn').onclick = function() {
	    	const urlParams = new URLSearchParams(window.location.search);
	        const questionNum = urlParams.get('questionNum');
	        
	        if (confirm('정말 삭제하시겠습니까?')) {
	            window.location.href = 'deleteAdminQA?questionNum=' + questionNum;
	        }
	    };
	</script>
</body>
</html>