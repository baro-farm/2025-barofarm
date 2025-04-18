<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>콕팜 목록</title>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <link
    href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css"
    rel="stylesheet"
    integrity="sha384-jFvlDSY24z+oXMByOoX2Z1gM+M5NMd0uG7sDa4skv2mHXPuS0/RYXwPGLK0+Mgdc"
    crossorigin="anonymous"
    />
    <script
    src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"
    integrity="sha384-FcKnveOKVsyQDhaxWTmHPNxY0wtv3QwEmOUwRZ5g+QqTQvSKKmnkT0NiFcDCCIvg"
    crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="${contextPath}/common/kockFarmList.css">
    <link rel="stylesheet" href="${contextPath}/content.css">
    <link rel="stylesheet" href="${contextPath}/header/reset.css">
    <script>
        $(document).ready(function () {
            $('#notice_table').DataTable({
                colReorder: true,  
                responsive: true,  
                scrollX: true,  
                paging: true,  
                searching: true,  
                ordering: true,  // 🔥 정렬 기능 활성화
                lengthMenu: [10, 20],
                columnDefs: [
                    { targets: 0, orderable: false }, // 0번째 열(구분) 정렬 비활성화
                ],    
                language: {
                    "lengthMenu": "페이지당 _MENU_ 개씩 보기",
                    "zeroRecords": "조회된 포인트 내역이 없습니다.",
                    "info": "총 _TOTAL_ 개의 내역 중 _START_ - _END_ 표시",
                    "infoEmpty": "데이터가 없습니다.",
                    "infoFiltered": "(총 _MAX_ 개 데이터에서 필터링됨)",
                    "search": "검색: ",
                    "paginate": {
                        "first": "<<",
                        "last": ">>",
                        "next": ">",
                        "previous": "<"
                    }
                }
            });
        });
    </script>
</head>

<body>
	<div class="main">
		<c:import url="/header/mainHeader.jsp" />
	
	    <div class="kock-container">
	        <h1 class="title">콕팜</h1>
	        <p class="subtitle">원하는 농작물을 직접 신청하세요!</p>
	
	        <table id="notice_table" class="table" width="100%">
	            <thead class="">
	                <tr>
	                    <th style="font-weight: bold;">순번</th>
	                    <th style="font-weight: bold;">제목</th>
	                    <th style="font-weight: bold;">작성자</th>
	                    <th style="font-weight: bold;">작성일</th>
	                </tr>
	            </thead>
	            <tbody>
	            	<c:forEach var="kock" items="${kocks }" varStatus="status">
		                <tr id="kock-tr">
		                    <td>${status.count }</td>
		                    <td><a href="detailKockFarm?kockNum=${kock.kockNum }">${kock.title }</a></td>
		                    <td>${kock.userName }</td>
		                    <fmt:parseDate value="${kock.createdAt}" pattern="yyyy-MM-dd" var="createdDate"/>
		                    <td><fmt:formatDate value="${createdDate }" pattern="yyyy-MM-dd"/></td>
		                </tr>
	                </c:forEach>
	            </tbody>
	        </table>
			<c:if test="${!isSeller }">
	        <div class="buttons">
	            <button class="btn btn-list" onclick="location.href='insertKockFarm'">글 등록</button>
	        </div>
	        </c:if>
	    </div>
	    
	</div>
</body>

</html>