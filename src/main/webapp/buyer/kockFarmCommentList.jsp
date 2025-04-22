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
		
		        <form id="commentForm" name="commentForm">
		        
		        	<c:forEach var="comment" items="${kockCommentList}">
			        	 <div class="commentBox" data-kocknum="${comment.kockNum }">
			                <input type="checkbox"
					            id="commentCheck${status.index}" 
					            name="commentCheck" 
					            value="${comment.commentNum}">
					            
			                <div class="commentContent" >
			                    <div class="commentText">
			                    	${comment.content }
			                    </div>
			                    <div class="commentDate">${comment.createdAt }</div>
			                    <div class="commentNote"><a href="${contextPath}/detailKockFarm?kockNum=${comment.kockNum}">${comment.title }</a></div>
			                </div>
			            </div>
		        	</c:forEach>

		
		            <!-- 하단 전체 선택 및 버튼 -->
		            <div class="footer">
		                <div class="footerLeft">
		                    <input type="checkbox" id="selectAllCheck" name="selectAllCheck"> 전체선택
		                </div>
		                <div class="footerRight">
		                    <button type="button" id="deleteButton" name="deleteButton" class="btnDelete">선택 삭제</button>
		                    <button type="button" id="writeButton" name="writeButton" class="btnWrite writeButton">글쓰기</button>
		                </div>
		            </div>
		            <script type="text/javascript">
						const contextPath= '${contextPath}'

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
			                			window.location.href = `${contextPath}/detailKockFarm?kockNum=` + kockNum;
			                			console.log(window.location.href);
			                		
			                		}
			                	});
			                	
			                });
			                
			                const writeButton = document.getElementById("writeButton");
			                if(writeButton){
			                	writeButton.addEventListener("click", function(){
			                		window.location.href = `${contextPath}/insertKockFarm`;
			                	});
			                }
			                
			              });
		            </script>
		
		            <!-- 페이지네이션 -->
		            <div class="pagination">
		            	<c:if test="${currentPage>1 }">
		            		<a href="?page=${currentPage-1 }">〈 </a>
		            	</c:if>
		            	
					    <c:forEach var="i" begin="1" end="${maxPage}">
					        <c:choose>
					            <c:when test="${i == currentPage}">
					                <span class="currentPage">${i}</span>
					            </c:when>
					            <c:otherwise>
					                <a href="?page=${i}">${i}</a>
					            </c:otherwise>
					        </c:choose>
					    </c:forEach>
					    
					 	<c:if test="${currentPage < maxPage}">
					        <a href="?page=${currentPage + 1}">〉</a>
					    </c:if>					    
		            </div>
		        </form>
	        </div><!-- end of content -->
    	</div><!-- end of wrapper -->    
    </div><!-- end of container-->
</body>
</html>