<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>꾸러미 문의 내역</title>
    <link rel="stylesheet" href="${contextPath }/buyer/packQuestionList.css">
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
    						
    						url: "${contextPath}/selectPackAnswer",
    						method:"GET",
    						data: {qaNum: qaNum},
    						success: function(data){
    	                        $questionReply.html(data.content); // data는 JSON
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


<div class="container">
    <div class="wrapper">

		<div class="content1">
			
	
		    <!-- 필터 섹션 -->
			<div class="FilterBox">
			  <label for="searchStartDate">조회 기간:</label>
			  <input type="date" id="searchStartDate" name="searchStartDate">
			  <span>~</span>
			  <input type="date" id="searchEndDate" name="searchEndDate">
			
			  <label for="deliveryStatus">배송 상태:</label>
			  <select id="deliveryStatus" name="deliveryStatus">
			    <option value="">전체</option>
			    <option value="준비중">준비중</option>
			    <option value="배송중">배송중</option>
			    <option value="배송완료">배송완료</option>
			    <option value="구매확정">구매확정</option>
			    <option value="취소완료">취소완료</option>
			  </select>
			
			  <button type="button" id="searchBtn">검색</button>
			</div>
		    
			    <c:if test="${empty questionList}">
			        <div class="emptyMessage">작성한 문의 내역이 없습니다.</div>
			    </c:if>
	
		    <!-- 문의 내역 반복 -->
		    <c:forEach var="question" items="${questionList }">
			    <div class="question">
			        <div class="imgBox"><img src="${contextPath }${question.imgUrl }" alt="imgsrc"></div>
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
