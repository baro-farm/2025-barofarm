<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <link rel="stylesheet" href="${contextPath}/seller/insertReviewComment.css">
    <script>
    	$(document).ready(function() {
    	
    		$("#reviewComment").on("input", function () {
				  const currentLength = $(this).val().length;
				  $("#charCount").text(currentLength + " / 1000");
			});
    	});
        				
    </script>

        <span class="close">&times;</span>
        <h2 class="title">판매자 일괄 답글 작성</h2>

        <div class="reviewComment">
            <h3></h3>
            <table>
                <tr>
                    <th>리뷰<br>글 번호</th>
                    <td id="reviewNum">${reviewNums }</td>
                </tr>
                <tr>
                    <th>답글 내용</th>
          			<td>
			  			<textarea id="reviewComment" name="reviewComment" rows="10" cols="50" maxlength="1000"></textarea>
			  			<div id="charCount" style="text-align: right; font-size: 12px; color: gray;">0 / 1000</div>
					</td>
                </tr>

            </table>
            <div class="actions">

                <button class="btn addBtn">등록</button>
                <button class="btn closeBtn">닫기</button>

            </div>
        </div>



    <script>
        // 주문번호 동적 설정
        const urlParams = new URLSearchParams(window.location.search);
        const reviewNum = urlParams.get("reviewNum");
        if (reviewNum) {
            document.getElementById("reviewNum").textContent = reviewNum;
        }
    </script>
