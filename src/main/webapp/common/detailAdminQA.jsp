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
		        <table>
		        	<tr>
		            	<td class=""><label>글쓴이</label></td>
		                <td class=""><span>${adminq.userId}</span></td>
		            </tr>
		            <tr>
		            	<td class=""><label>제목</label></td>
		                <td class=""><span>${adminq.title}</span></td>
		                <td class=""><span>${adminq.createdAt}</span></td>
		            </tr>
		            <tr>
		            	<td><label>내용</label></td>
		                <td class="">
		                	<div id="" class="question">${adminq.content}</div>
		                </td>
		           	</tr>
		            <tr>
		            	<td class=""><label>답변</label></td>
		                <td class=""><div id="" class="answer">dd</div></td>
		            </tr>
				</table>
		        <br>
        		<a href="adminQAList" class="yellowBtnSb">목록으로</a>
        	</div>
        </div>
        <jsp:include page="/header/footer.jsp" />
    </div>
</body>
</html>