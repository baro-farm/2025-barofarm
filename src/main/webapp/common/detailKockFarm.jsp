<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<meta charset="UTF-8">
	<title>콕팜 상세보기</title>
    <link rel="stylesheet" href="${contextPath}/common/modalKockMatching.css">
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
	                    var userNum=c.userNum;
	                    var kockNum=c.kockNum;
	                    
	                    // 값이 제대로 할당되었는지 확인
	                    if (storeName && userName && content && kcNum) {
	                    	var comment = "";
	                    	comment += "<div class='comments' id='comment-" + kcNum + "'>";
	                    	comment +=    "<div class='message-box seller'>";
	                    	comment +=        "<div class='sell-com'>";
	                    	comment +=        	"<div class='text-box'>";
	                    	comment +=            "<div class='user-info'>" + storeName + "</div>";
	                    	comment +=            "<div class='message'>" + content + "</div>";
	                    	comment +=       	 "</div>";
	                    	comment +=        "<div>";
	                    	comment +=            "<button class='btn-reply' data-comment-id='" + kcNum + "'>답글</button>";
	                    	comment +=        "</div>";
	                    	comment +=        "</div>";
	                    	comment +=    "</div>";
	                    	comment +=    "<div id='babyList-" + kcNum + "'></div>";
	                    	comment += "</div>";
	                    	
	                    	// ✅ 댓글 폼 사라지게 하기
	                        $("#commentForm").hide(); // 또는 .remove();
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

                console.log(typeof storeName);
                
                
                // 값이 제대로 할당되었는지 확인
                if ((storeName==="null") && userName && content) {
                    var comment = "";
                    comment += "<div class='comments' id='comment-" + reNum + "'>";
                    comment += "<div class='message-box buyer-sec reply'>";
                    comment += "<div class='re-icon'>↪</div>";
                    comment += "<div class='text-box'>";
                    comment += "<div class='user-info'>" + userName + "</div>";
                    comment += "<div class='message'>" + content + "</div>";
                    comment += "</div>";
                    comment += "</div>";
                    comment += "</div>";

                    $("#babyList-"+kcNum).append(comment);  // 댓글 리스트에 추가
                    $(".comment-input2").val("");  // 입력창 비우기
                } else if(storeName && userName && content) {
                	var comment = "";
                    comment += "<div class='comments' id='comment-" + reNum + "'>";
                    comment += "<div class='message-box seller-sec reply'>";
                    comment += "<div class='re-icon'>↪</div>";
                    comment += "<div class='text-box'>";
                    comment += "<div class='user-info'>" + storeName + "</div>";
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
		<jsp:include page="/header/mainHeader.jsp" />
			<div class="wrapper">
			<jsp:include page="/header/sideMenu.jsp" />
	
			<div class="content">
			    <div>
			        <h1 class="title">콕팜</h1>
			        <p class="subtitle">거래를 제안하세요!</p>
			    </div>
			
			    <div class="post-info">
			        <p class="p-value"><strong class="p-strong">제목</strong>${kock.title } </p>
			        <p class="p-value"><strong class="p-strong">카테고리</strong>${kock.name }</p>
			        <p class="p-value"><strong class="p-strong">수량</strong>${kock.quantity } </p>
			        <p class="p-value"><strong class="p-strong">희망 배송일자</strong>${kock.shipDate }</p>
			        <p class="p-value"><strong class="p-strong">가격</strong>${kock.price }원</p>
		  	        <p class="image"><strong class="p-strong">이미지</strong>  
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
			        <c:if test="${isWriter && fn:length(commentList) == 0 }">
			        <button class="btn btn-edit" onclick="location.href='updateKockFarm?kockNum=${kock.kockNum }'">글 수정</button>
			        <form action="deleteKockFarm" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
			            <input type="hidden" name="kockNum" value="${kock.kockNum }">
			        	<button class="btn btn-delete" type="submit">글 삭제</button>
			        </form>
			        </c:if>
			    </div>
			
			    <!-- 댓글 목록 -->
				<div id="commentList">
		       	<c:forEach var="comment" items="${commentList }" varStatus="status">
		   			<div class="comments" id="comment-${comment.kcNum}">
				        <div class="message-box seller" >
				            <div class="sell-com">
				            	<div class="text-box">
				                	<div class="user-info">${comment.storeName }</div>
				                	<div class="message">${comment.content }</div>
					            </div>
					            <div>
					            	<c:if test="${user.userNum eq comment.userNum || isWriter  }">
					            	<button class="btn-reply" data-comment-id="${comment.kcNum}">답글</button>
					            	</c:if>
					            	<c:if test="${!isMatched and isWriter}">
					            	<button 
									  class="btn-match" 
									  data-kock-num="${comment.kockNum}" 
									  data-kc-num="${comment.kcNum}" 
									  data-seller-num="${comment.userNum}"
									  data-store-name="${comment.storeName}" 
									  data-buyer-num="1">
									  매칭
									</button>
									</c:if>
									<c:if test="${isMatched && matching.sellerNum eq comment.userNum}">
					            	<button class="btn-matched"  disabled="disabled">
									  매칭 완료
									</button>
									</c:if>
					            </div>		
				            </div>
				        </div>
						<div id="babyList-${comment.kcNum }">
					       <c:forEach var="baby" items="${comment.babyComments }">
					       <c:if test="${baby.storeName eq null }">
						       <div class="comments" id="comment-${baby.reNum}">
				                    <div class="message-box buyer-sec reply">
				                        <div class="re-icon">↪</div>
				                        <div class="text-box">
				                            <div class="user-info">${baby.userName}</div>
				                            <div class="message">${baby.content}</div>
				                        </div>
				                    </div>
				                </div>
			                </c:if>
			                <c:if test="${baby.storeName ne null }">
						       <div class="comments" id="comment-${baby.reNum}">
				                    <div class="message-box seller-sec reply">
				                        <div class="re-icon">↪</div>
				                        <div class="text-box">
				                            <div class="user-info">${baby.storeName}</div>
				                            <div class="message">${baby.content}</div>
				                        </div>
				                    </div>
				                </div>
			                </c:if>
					       </c:forEach>
				        </div>
				    </div>
				</c:forEach>
			    </div>
			    <!-- 구매자의 댓글 폼 => 무조건 아기댓글 -->
			    <div id="reply-form-template"  style="display: none;">
			    	<div class="babyDiv">
				    <form id="babyForm" method="post" class="reply-form comment-box">
				        <input type="hidden" name="userNum" value="${user.userNum }" />
				        <input type="hidden" name="kcNum" value="" />
				        <input type="text" name="content" class="comment-input2" placeholder="대댓글을 입력해주세요." required="required">
				        <button type="submit" class="btn-submit">등록</button>
				    </form>
				    </div>
				</div>
				
		    	<!-- 판매자의 첫번째 댓글 폼 => 무조건 부모댓글 -->
		    	<c:if test="${!isMatched and user.isSeller and not hasComment }">
			    <div>
				    <form id="commentForm" method="post" class="reply-form comment-box">
				        <input type="hidden" name="userNum" value="${user.userNum }" />
				        <input type="hidden" name="kockNum" value="${kock.kockNum}" />
				        <input type="hidden" name="parentCommentNum" value="" />
				        <input type="text" name="content" class="comment-input" placeholder="댓글을 입력해주세요." required="required">
				        <button type="submit" class="btn-submit">등록</button>
				    </form>
				</div>
				</c:if>
			</div>
			</div>
		<jsp:include page="/header/footer.jsp" />
	</div>
<script>
 document.addEventListener("DOMContentLoaded", function () {
	    const replyFormTemplate = document.querySelector("#reply-form-template");
	    let activeForm = null;

	    document.addEventListener("click", function (e) {
	        if (e.target && e.target.classList.contains("btn-reply")) {
	            const kcNum = e.target.dataset.commentId;

	            // 현재 클릭된 답글 버튼이 속한 댓글 div 찾기
	            const commentBox = e.target.closest(".comments");

	            // 기존 폼 제거
	            if (activeForm) {
	                activeForm.remove();
	                // 같은 위치면 토글처럼 끄고 끝내기
	                if (activeForm.parentElement === commentBox) {
	                    activeForm = null;
	                    return;
	                }
	            }

	            // 템플릿 복제 및 값 설정
	            const newForm = replyFormTemplate.firstElementChild.cloneNode(true);
	            newForm.querySelector("input[name='kcNum']").value = kcNum;
	            newForm.querySelector("form").classList.add("babyForm");

	            // 해당 댓글 박스 안에 폼 추가
	            commentBox.appendChild(newForm);
	            activeForm = newForm;
	        }
	    });
	});

</script>  

<div class="modal" id="matchModal" >
    <p id="modalMessageMain"></p>
    <p>이후 변경,취소가 불가합니다.</p>
    <div class="buttons">
	    <!-- matchModal 내부 form (템플릿 문법 제거!) -->
		<form id="matchForm" action="${contextPath}/kockMatching" method="post">
		  <input type="hidden" name="kockNum" id="kockNumInput">
		  <input type="hidden" name="kcNum" id="kcNumInput">
		  <input type="hidden" name="sellerNum" id="sellerNumInput">
		  <input type="hidden" name="buyerNum" id="buyerNumInput"> <!-- JS에서 로그인 유저 넘버 넣기 -->
		  <button class="btn btn-subscribe" id="confirmMatchBtn" type="submit">거래 체결</button>
		</form>
        <button class="btn btn-cancel" id="cancelMatchBtn">거래 취소</button>
    </div>
</div>

<script>

let selectedMatchInfo = {};
const currentBuyerNum = "${user.userNum}";

document.querySelectorAll('.btn-match').forEach(btn => {
  btn.addEventListener('click', function () {
    console.log(this.dataset); 
    selectedMatchInfo = {
      kockNum: this.dataset.kockNum,
      kcNum: this.dataset.kcNum,
      sellerNum: this.dataset.sellerNum,
      storeName: this.dataset.storeName,
      buyerNum: this.dataset.buyerNum
    };

    console.log(selectedMatchInfo); 
    console.log("Data type of storeName:", typeof selectedMatchInfo.storeName); // 문자열이어야 해요
    console.log("Store Name before:", selectedMatchInfo.storeName);  // 공백 전 상태 확인
    console.log("Store Name after trim:", selectedMatchInfo.storeName.trim());  // 공백 제거 후 상태 확인
    console.log("Trimmed storeName:", selectedMatchInfo.storeName.trim());
    // 텍스트가 잘 나오는지 최종 확인
    const modalMessageElement = document.getElementById('modalMessageMain');
	if (modalMessageElement) {
	  modalMessageElement.textContent = `\${selectedMatchInfo.storeName.trim()}님과 매칭하시겠습니까?`;
	}
	
	// form의 hidden input 요소들에 값 세팅
	document.getElementById('kockNumInput').value = selectedMatchInfo.kockNum;
	document.getElementById('kcNumInput').value = selectedMatchInfo.kcNum;
	document.getElementById('sellerNumInput').value = selectedMatchInfo.sellerNum;
	document.getElementById('buyerNumInput').value = currentBuyerNum; // 로그인 사용자 번호 JS에서 가져와야 함


    // 모달을 표시하는 부분
    const matchModal = document.getElementById('matchModal');
    if (matchModal) {
      matchModal.style.display = 'block';
    } else {
      console.log("Modal not found!");
    }
  });
}); 

// 아니오 버튼은 단순히 모달 닫기
document.getElementById('cancelMatchBtn').addEventListener('click', function () {
  document.getElementById('matchModal').style.display = 'none';
});


document.getElementById('matchForm').addEventListener('submit', function (e) {
    e.preventDefault(); // 기본 제출 막기

    const form = e.target;
    const formData = $(form).serialize();

    $.ajax({
        url: form.action,
        method: 'POST',
        data: formData,
        success: function (response) {
            // ✅ 매칭 성공 처리

            // 1. 모든 매칭 버튼 비활성화
            document.querySelectorAll(".btn-match").forEach(btn => {
                btn.disabled = true;
                btn.classList.add('btn-matched');
                btn.textContent = "매칭 완료"; // 버튼 텍스트 바꾸기
            });

            // 2. 댓글 폼 제거
            const commentForm = document.getElementById("commentForm");
            if (commentForm) {
                commentForm.remove();
            }

            // 3. 모달 닫기
            document.getElementById('matchModal').style.display = 'none';
        },
        error: function () {
            alert("거래 체결에 실패했습니다.");
        }
    });
});

</script>
</body>
</html>