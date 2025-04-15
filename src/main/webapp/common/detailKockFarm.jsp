<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>콕팜 상세보기</title>
  	<link rel="stylesheet" href="${contextPath}/common/detailKockFarm.css" />
</head>
<body>
<div class="main">
	<c:import url="/header/mainHeader.jsp" />
	
	<div class="container">
	    <div class="title_head">
	        <div class="title">콕팜</div>
	        <p>거래를 제안하세요!</p>
	    </div>
	
	    <div class="post-info">
	        <p><strong class="p-strong">제목</strong>${kock.title } </p>
	        <p><strong class="p-strong">카테고리</strong>${kock.name }</p>
	        <p><strong class="p-strong">수량</strong>${kock.quantity } </p>
	        <p><strong class="p-strong">희망 배송일자</strong>${kock.shipDate }</p>
	        <p><strong class="p-strong">가격</strong>${kock.price }원</p>
  	        <p><strong class="p-strong">이미지</strong>  
        	<c:if test="${kock.imgUrl ne null}">
				<img src="kockImg?imgUrl=${kock.imgUrl }" width="100px" />
			</c:if>
			</p>     
	    </div>
	
	    <div class="content-box">
	        ${kock.content }
	    </div>
	
	    <div class="buttons">
	        <button class="btn btn-list" onclick="location.href='kockFarmList'">글 목록</button>
	        <button class="btn btn-edit" onclick="location.href='updateKockFarm?kockNum=${kock.kockNum }'">글 수정</button>
	        <form action="deleteKockFarm" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
	            <input type="hidden" name="kockNum" value="${kock.kockNum }">
	        	<button class="btn btn-delete" type="submit">글 삭제</button>
	        </form>
	    </div>
	
	    <!-- 댓글 목록 -->
 	    <div class="comments">
	        <div class="message-box seller">
	            <div class="icon">판매자</div>
	            <div class="text-box">
	                <div class="user-info">작성자 | 아이디 | 등록일</div>
	                <div class="message">네, 물론입니다~ 고객님 수락 버튼 클릭 후 결제를 진행해주세요~!</div>
	            </div>
	            <button class="btn-reply">답글</button>
	        </div>
	
	        <div class="message-box buyer-sec reply">
	            <div class="re-icon">↪</div>
	            <div class="icon">구매자</div>
	            <div class="text-box">
	                <div class="user-info">구매자 | 아이디 | 등록일</div>
	                <div class="message">20만원 진행하시죠! 금요일 배송 가능은 확실한가요?</div>
	            </div>
	            <span class="delete-icon">❌</span>
	        </div>
	    </div>
	
	    <div class="comment-box">
	    	<form action="insertKockComment" method="post">
	    		<input type="hidden" name="userName" value=${kock.userName } />
    			<input type="hidden" name="kockNum" value=${kock.kockNum } />
	        	<input type="text" class="comment-input" placeholder="댓글을 입력해주세요.">
	        	<button type="submit" class="btn-submit">등록</button>
	        </form>
	    </div>
	</div>
</div>  
</body>
</html>