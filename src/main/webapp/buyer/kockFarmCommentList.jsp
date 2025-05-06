<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>콕팜 작성 댓글</title>
    <link rel="stylesheet" href="${contextPath}/buyer/kockFarmCommentList.css">
</head>

<body>
	<jsp:include page="/header/mainHeader.jsp"/>

    <div class="container">
		<div class="wrapper">
			<div class="sideMenu">
				<jsp:include page="/header/buyerMenu.jsp" />
			</div>
			<div class="content">    
		        <div class="title">콕팜 작성 댓글</div>
			    <c:if test="${empty kockCommentList}">
			        <div class="emptyMessage">작성한 콕팜 댓글이 없습니다.</div>
			    </c:if>		
		        <form id="commentForm" name="commentForm" >
		        
		        	<c:forEach var="comment" items="${kockCommentList}">
			        	 <div class="commentBox" data-kocknum="${comment.kockNum }">
							<div class="commentAll">
				               <div class="commentNote"><a href="${contextPath}/detailKockFarm?kockNum=${comment.kockNum}">제목: ${comment.title }</a></div>
						            
				                <div class="commentContent" >
				                    <div class="commentText">
				                    	${comment.content }
				                    </div>
				                    <div class="commentDate">${comment.createdAt }</div>
				                </div>
			                </div>
			            </div>
		        	</c:forEach>

		
		            <!-- 하단 전체 선택 및 버튼 -->
		            <div class="footer">

		                <div class="footerRight">
		                    <button type="button" id="writeButton" name="writeButton" class="btnWrite writeButton">글쓰기</button>
		                </div>
		            </div>
		            
		

		        </form>
<c:set var="groupStartPage" value="${(currentPage - 1) / pageGroupSize * pageGroupSize + 1}" />
<c:set var="groupEndPage" value="${groupStartPage + pageGroupSize - 1}" />

<c:if test="${groupEndPage > totalPages}">
    <c:set var="groupEndPage" value="${totalPages}" />
</c:if>

<div class="pagination">
    <!-- << : 이전 페이지 그룹으로 -->
    <c:choose>
        <c:when test="${currentPage > pageGroupSize}">
            <c:set var="prevGroupPage" value="${currentPage - pageGroupSize}" />
            <a href="?page=${prevGroupPage}">&laquo;</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">&laquo;</a>
        </c:otherwise>
    </c:choose>

    <!-- < : 이전 페이지 -->
    <c:choose>
        <c:when test="${currentPage > 1}">
            <a href="?page=${currentPage - 1}">&lsaquo;</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">&lsaquo;</a>
        </c:otherwise>
    </c:choose>

    <!-- 페이지 번호 -->
    <c:forEach var="i" begin="${groupStartPage}" end="${groupEndPage}">
        <a href="?page=${i}" class="${currentPage == i ? 'active' : ''}">${i}</a>
    </c:forEach>

    <!-- > : 다음 페이지 -->
    <c:choose>
        <c:when test="${currentPage < totalPages}">
            <a href="?page=${currentPage + 1}">&rsaquo;</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">&rsaquo;</a>
        </c:otherwise>
    </c:choose>

    <!-- >> : 다음 페이지 그룹으로 -->
    <c:choose>
        <c:when test="${groupEndPage < totalPages}">
            <c:set var="nextGroupPage" value="${currentPage + pageGroupSize}" />
            <c:if test="${nextGroupPage > totalPages}">
                <c:set var="nextGroupPage" value="${totalPages}" />
            </c:if>
            <a href="?page=${nextGroupPage}">&raquo;</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">&raquo;</a>
        </c:otherwise>
    </c:choose>
</div>
	        </div><!-- end of content -->
    	</div><!-- end of wrapper -->    
    </div><!-- end of container-->
</body>
<script type="text/javascript">
						

			            document.addEventListener("DOMContentLoaded", function () {
			                const selectAllCheck = document.getElementById("selectAllCheck");
			                const checkboxes = document.querySelectorAll('input[name="commentCheck"]');
			                selectAllCheck.addEventListener("change", function (e) {
			                  checkboxes.forEach(function (checkbox) {
			                    checkbox.checked = selectAllCheck.checked;
			                  });
			                });
			                
			                const commentBoxes = document.querySelectorAll(".commentBox");
			                commentBoxes.forEach(box =>{
			                	box.addEventListener("click",function(e){
			                		//체크박스 무시
			                		if (e.target.tagName.toLowerCase() === "input") return;
			                		
			                		const kockNum=this.dataset.kocknum;
			                		if(kockNum){
			                			console.log();
			                			window.location.href = `${contextPath}/detailKockFarm?kockNum=\${kockNum}`;
			                			console.log(window.location.href);
			                		
			                		}
			                	});
			                	
			                });
			                
			                const writeButton = document.getElementById("writeButton");
			                if(writeButton){
			                	writeButton.addEventListener("click", function(){
			                		console.log("click");
			                		window.location.href = `\${contextPath}/insertKockFarm`;
			                	});
			                }
			                
			              });
		            </script>
</html>