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
		            .html(`<span> 해당 정보로 등록된 아이디를 찾을 수 없습니다.</span>`)
		            .fadeIn();
		    }
		},
		error: function() {
			alert("서버 오류가 발생했습니다.");
		}
		});
	});
	
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
                            ✉️ 메일이 발송되었습니다!<br>
                            메일함을 확인해 주세요.
                        </div>
                    `).fadeIn();
                } else {
                    $("#resultBox").html(`
                        <div class="failBox">
                            <span> 해당 정보로 등록된 회원을 찾을 수 없습니다.</span>
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
