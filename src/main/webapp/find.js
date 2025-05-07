$(document).ready(function() {
	$("#findIdBtn").click(function() {
		const email = $("#emailInput").val().trim();
		const phone = $("#phoneInput").val().trim();
	
		if (email === "" || phone === "") {
			alert("이메일과 전화번호를 모두 입력해주세요!");
			return;
		}
	
		$.ajax({
			url: `${contextPath}/findId`,
			method: "POST",
			contentType: "application/json",
			      //객체 → 문자열 (네트워크로 전송) → 다시 객체로 파싱
			data: JSON.stringify({ email: email, phone: phone }),
			success: function(data) {
		    if (data.success) {
		        $("#resultBox")
		            .removeClass('fail')
		            .addClass('success')
		            .html(`
		                <span>${data.userName}님의 아이디는 <strong>${data.userId}</strong> 입니다.</span>
		            `)
		            .fadeIn();
		    } else {
		        $("#resultBox")
		            .removeClass('success')
		            .addClass('fail')
		            .html(`<span style="color: red;"> 해당 정보로 등록된 아이디를 찾을 수 없습니다.</span>`)
		            .fadeIn();
		    }
		},
		error: function() {
			alert("서버 오류가 발생했습니다.");
		}
		});
	});
	/* 비밀번호 찾기 */
    $("#findPwdBtn").click(function() {
        const userId = $("#idInput").val().trim();
        const email = $("#emailInput").val().trim();

        if (!userId || !email) {
            alert("아이디와 이메일을 입력해주세요!");
            return;
        }

        $.ajax({
            url: `${contextPath}/findPwd`,
            type: "POST",
            data: JSON.stringify({ userId: userId, email: email }),
            contentType: "application/json",
            success: function(data) {
                if (data.success) {
                    $("#resultBox").html(`
                        <div class="successBox">
                            ✉️ 비밀번호 변경 메일이 발송되었습니다!<br>
                            메일함을 확인해 주세요.
                        </div>
                    `).fadeIn();
                } else {
                    $("#resultBox").html(`
                        <div class="failBox">
                            <span style="color: red;"> 해당 정보로 등록된 회원을 찾을 수 없습니다.</span>
                        </div>
                    `).fadeIn();
                }
            },
            error: function(xhr, status, error) {
                console.error("Ajax 오류:", error);
                $("#resultBox").html(`
                    <div class="failBox">
                        ⚠️ 서버 오류가 발생했습니다. 다시 시도해주세요.
                    </div>
                `).fade
			}
        });
    });
});

$(document).ready(function() {
    // URL에서 resetPwdToken 가져오기
    const urlParams = new URLSearchParams(window.location.search);
    const resetPwdToken = urlParams.get('token');
    

    if (!resetPwdToken) {
        $('#resultBox').text('유효하지 않은 접근입니다.').css('color', 'red');
        $('#resetPwdForm').hide();
        return;
    }

	$('#resetPwdBtn').click(function() {
	        const changePwd = $('#changePwd').val().trim();
	        const checkPwd = $('#checkPwd').val().trim();
	        const resetPwdToken = urlParams.get('token');
			console.log("find.js/",changePwd,"/",checkPwd,"/",resetPwdToken);
			
	        $.ajax({
	            url: `${contextPath}/resetPwd`,
	            type: 'POST',
	            contentType: 'application/json',
	            data: JSON.stringify({ 
		            changePwd: changePwd, 
		            checkPwd: checkPwd,
		            resetPwdToken: resetPwdToken 
		        }),
	            success: function(response) {
	                $('#resultBox').text(response.message).css('color', response.success ? 'green' : 'red');
	                if (response.success) {
	                    setTimeout(function() {
	                        window.location.href = `${contextPath}/login`;  // 성공 시 로그인 페이지로 이동
	                    }, 2000);
	                }
	            },
	            error: function() {
	                $('#resultBox').text('서버 오류가 발생했습니다.').css('color', 'red');
	            }
	        });
	    });
});

