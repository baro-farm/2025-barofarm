<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<meta charset="UTF-8">
	<title>콕팜 상세보기</title>
  	<link rel="stylesheet" href="${contextPath}/common/detailKockFarm.css" />
	<script type="text/javascript">
	/* 부모댓글 */
	$(function () {
	    $("#commentForm").on("submit", function (e) {
	        e.preventDefault(); // 폼 기본 동작 막기
	
	        const formData = $(this).serialize();
	
	        $.ajax({
	            url: '${contextPath}/insertKockComment',
	            type: 'post',
	            data: formData,
	            dataType: 'json',
	            success: function (response) {
	                let parsed = typeof response === "string" ? JSON.parse(response) : response;

	                if (parsed.success) {
	                    const c = parsed.comment;
	                    var storeName = c.storeName;
	                    var userName = c.userName;
	                    var content = c.content;
	                    var kcNum = c.kcNum;
	                    
	                    // 값이 제대로 할당되었는지 확인
	                    if (storeName && userName && content && kcNum) {
	                    	var comment = "";
	                    	comment += "<div class='comments' id='comment-" + kcNum + "'>";
	                    	comment +=    "<div class='message-box seller'>";
	                    	comment +=        "<div class='icon'>판매자</div>";
	                    	comment +=        "<div class='text-box'>";
	                    	comment +=            "<div class='user-info'>" + storeName + "</div>";
	                    	comment +=            "<div class='message'>" + content + "</div>";
	                    	comment +=        "</div>";
	                    	comment +=        "<button class='btn-reply' data-comment-id='" + kcNum + "'>답글</button>";
	                    	comment +=    "</div>";
	                    	comment += "</div>";

	                    	$("#commentList").append(comment);  // 댓글 리스트에 추가
	                    	$(".comment-input").val("");  // 입력창 비우기
	                    } else {
	                        console.error("댓글 데이터에 필요한 값이 누락되었습니다.");
	                    }
	                } else {
	                    alert("댓글 등록 실패: " + parsed.message);
	                }
	            }
,
	            error: function (err) {
	                console.log("에러 발생:", err);
	                alert("서버 오류로 댓글 등록에 실패했습니다.");
	            }
	        });
	    });
	});
	
	/* 아기 댓글 */
$(document).on("submit", ".babyForm", function (e) {
    e.preventDefault(); // 폼 기본 동작 막기

    const formData = $(this).serialize();

    $.ajax({
        url: '${contextPath}/insertBabyComment',
        type: 'post',
        data: formData,
        dataType: 'json',
        success: function (response) {
            let parsed = typeof response === "string" ? JSON.parse(response) : response;

            if (parsed.success) {
                const c = parsed.comment;
                var storeName = c.storeName;
                var userName = c.userName;
                var content = c.content;
                var reNum = c.reNum;
                var kcNum = c.kcNum;
                
                // 값이 제대로 할당되었는지 확인
                if (userName && content) {
                    var comment = "";
                    comment += "<div class='comments' id='comment-" + reNum + "'>";
                    comment += "<div class='message-box buyer-sec reply'>";
                    comment += "<div class='re-icon'>↪</div>";
                    comment += "<div class='icon'>구매자</div>";
                    comment += "<div class='text-box'>";
                    comment += "<div class='user-info'>" + userName + "</div>";
                    comment += "<div class='message'>" + content + "</div>";
                    comment += "</div>";
                    comment += "</div>";
                    comment += "</div>";

                    $("#babyList-"+kcNum).append(comment);  // 댓글 리스트에 추가
                    $(".comment-input2").val("");  // 입력창 비우기
                } else {
                    console.error("댓글 데이터에 필요한 값이 누락되었습니다.");
                }
            } else {
                alert("댓글 등록 실패: " + parsed.message);
            }
        },
        error: function (err) {
            console.log("에러 발생:", err);
            alert("서버 오류로 댓글 등록에 실패했습니다.");
        }
    });
});
	</script>
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
		<div id="commentList">
       	<c:forEach var="comment" items="${commentList }" varStatus="status">
   			<div class="comments" id="comment-${comment.kcNum}">
		        <div class="message-box seller" >
		            <div class="icon">판매자</div>
		            <div class="text-box">
		                <div class="user-info">${comment.storeName }</div>
		                <div class="message">${comment.content }</div>
		            </div>
		            <button class="btn-reply" data-comment-id="${comment.kcNum}">답글</button>
		        </div>
				<div id="babyList-${comment.kcNum }">
			       
		        </div>
		    </div>
		</c:forEach>
	    </div>
	    <!-- 구매자의 댓글 폼 => 무조건 아기댓글 -->
	    <div id="reply-form-template"  style="display: none;">
	    	<div class="babyDiv">
		    <form id="babyForm" method="post" class="reply-form comment-box">
		        <input type="hidden" name="userNum" value="1" />
		        <input type="hidden" name="kcNum" value="" />
		        <input type="text" name="content" class="comment-input2" placeholder="대댓글을 입력해주세요." required="required">
		        <button type="submit" class="btn-submit">등록</button>
		    </form>
		    </div>
		</div>
		
    	<!-- 판매자의 첫번째 댓글 폼 => 무조건 부모댓글 -->
	    <div>
		    <form id="commentForm" method="post" class="reply-form comment-box">
		        <input type="hidden" name="userNum" value="4" />
		        <input type="hidden" name="kockNum" value="${kock.kockNum}" />
		        <input type="hidden" name="parentCommentNum" value="" />
		        <input type="text" name="content" class="comment-input" placeholder="댓글을 입력해주세요." required="required">
		        <button type="submit" class="btn-submit">등록</button>
		    </form>
		</div>
	</div>
</div>
<script>
document.addEventListener("DOMContentLoaded", function () {
    const replyButtons = document.querySelectorAll(".btn-reply");
    const replyFormTemplate = document.querySelector("#reply-form-template");
    let activeForm = null;

    replyButtons.forEach(btn => {
        btn.addEventListener("click", function () {
            const kcNum = this.dataset.commentId; // 부모 댓글의 kcNum
            const targetDiv = document.querySelector("#comment-" + kcNum);

            // 기존 폼이 있다면 제거
            if (activeForm) {
                activeForm.remove();
            }

            // 템플릿 복제해서 삽입
            const newForm = replyFormTemplate.firstElementChild.cloneNode(true);
            newForm.querySelector("input[name='kcNum']").value = kcNum;

            // 여기가 중요! 폼에 class 추가
            newForm.querySelector("form").classList.add("babyForm");

            targetDiv.appendChild(newForm);
            activeForm = newForm;
        });
    });
});
</script>  
</body>
</html>