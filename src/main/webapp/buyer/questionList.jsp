<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 문의 내역</title>
    <link rel="stylesheet" href="${contextPath }/buyer/questionList.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <script>
    	$(function(){
    		$(".replyBtn").click(function(){
    			console.log("click");
    			const $btn = $(this);
    			const $replyBox = $btn.next(".replyBox");
    			const $questionReply = $replyBox.find(".questionReply");
    			const $questionReplyDate = $replyBox.find(".questionReplyDate");
    			const qaNum = $btn.data("qanum");
    			
    			if($replyBox.is(":visible")){
    				$replyBox.hide();
    				$btn.text($btn.text().replace("▲", "▼"));
    			}
    			else{
    				if($questionReply.is(":empty")){
    					$.ajax({
    						
    						url: "${contextPath}/selectProdAnswer",
    						method:"GET",
    						data: {qaNum: qaNum},
    						success: function(data){
    	                        $questionReply.html(data.content); // data는 JSON 형태라 가정
    	                      	$questionReplyDate.html("답변일: "+data.createdAt)
    	                        $replyBox.show();
    	                        $btn.text($btn.text().replace("▼", "▲"));
    						},
    						error: function(err){
    	                        alert("답변을 불러오는 데 실패했습니다.");
    	                        console.log(err);
    						}
    						
    					});
    				}
    				else{
    	                $replyBox.show();
    	                $btn.text($btn.text().replace("▼", "▲"));
    				}
    			}
    		});
    		
    	});
    </script>
</head>
<body>
<jsp:include page="/header/mainHeader.jsp"/>

<div class="container">
    <div class="wrapper">
		<div class="sideMenu">
			<jsp:include page="/header/buyerMenu.jsp" />
		</div>
		<div class="content">
			
	    	<div class="header">상품 문의 내역</div>
	
		    <!-- 필터 섹션 -->
		    <div class="filterSection">
		        <select>
		            <option>6개월</option>
		            <option>3개월</option>
		            <option>1개월</option>
		        </select>
		        <select>
		            <option>전체</option>
		            <option>답변 완료</option>
		            <option>답변 미완료</option>
		        </select>
		    </div>
	
		    <!-- 문의 내역 반복 -->
		    <c:forEach var="question" items="${questionList }">
			    <div class="question">
			        <div class="imgBox"><img src="${question.imgUrl }" alt="imgsrc"></div>
			        <div class="questionContent">
			            <div class="questionTitle"><a href="#">${question.productName }</a></div>
			            <div class="questionInfo">${question.storeName } | 문의일:${question.createdAt }</div>
			            <div class="questionText" name="content">${question.content}</div>
			            <c:choose>
			            
			            	<c:when  test="${question.answerCount>0 }">
					            <button class="replyBtn"  data-qanum="${question.qaNum}">답변 ${question.answerCount} ▼</button>
					            
					            <div class="replyBox">
					                <strong>답변</strong>
					                <div class="questionReply"></div>
					                <div class="questionReplyDate"></div>
					            </div>
				            </c:when>
				            
				            <c:otherwise>
					            <button class="replyBtn">답변 0</button>
				            </c:otherwise>
				            
			            </c:choose>
			        </div>
			    </div>
		    </c:forEach>
		</div>
	</div>
</div>
<!-- toggleReply 토클버튼 ajax안쓰는 버전 -->
<!-- 
<script>
	onclick="toggleReply(this)"
    function toggleReply(button) {
        let replyBox = button.nextElementSibling;
        if (replyBox.style.display === "block") {
            replyBox.style.display = "none";
            button.textContent = button.textContent.replace("▲", "▼");
        } else {
            replyBox.style.display = "block";
            button.textContent = button.textContent.replace("▼", "▲");
        }
    }
</script>
 -->
</body>
</html>