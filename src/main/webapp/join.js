let isIdChecked = false;
let isStoreChecked = false;

$(function() {
	/* 아이디 중복 확인 */
	$("#doubleId").click(function(e) {
		e.preventDefault();
		
		const userid = $("#userId").val();
		if (!userid) {
        	alert("아이디를 입력해주세요.");
           	return;
        }
			
		$.ajax({
			url:'doubleIdCheck',
			type:'post',
			async:true,
			dataType:'text',
			data:{userid:$("#userId").val()},
			success:function(result) {
				if (result === "true") {
					$("#idCheckResult").text("이미 사용 중인 아이디입니다.");
					isIdChecked = false;
				} else {
				    $("#idCheckResult").text("사용 가능한 아이디입니다!");
				    isIdChecked = true;
				}
			},
			error:function(err) {
				console.log(err);
				alert("중복 확인 요청 실패.");
			}	
		})
	})
  	/* 스토어명 중복 확인 */
  	$("#doubleStoreName").click(function(e) {
			e.preventDefault();
			
			const storeName = $("#storeName").val();
			
			if (!storename) {
            	alert("스토어명을 입력해주세요.");
            	return;
            }
			
			$.ajax({
				url:'doubleStoreNameCheck',
				type:'post',
				async:true,
				dataType:'text',
				data:{storeName: storeName},
				success:function(result) {
					if (result === "true") {
				      $("#storeNameCheckResult").text("이미 사용 중인 스토어명입니다.");
				      isStoreChecked = false;
				    } else {
				      $("#storeNameCheckResult").text("사용 가능한 스토어명입니다!");
				      isStoreChecked = true;
				    }
				},
				error:function(err) {
					console.log(err);
					alert("중복 확인 요청 실패.");
				}
			})
	})
})

$('#joinForm').submit(function (e) {
  if (!isIdChecked) {
    alert("아이디 중복확인을 해주세요.");
    e.preventDefault();
    return false;
  }
  const isSeller = $("input[name='isSeller']:checked").val() === "true";
  if (isSeller && !isStoreChecked) {
    alert("스토어명 중복확인을 해주세요.");
    e.preventDefault();
    return false;
  }
  return true;
});


/* 판매자 가입 시 스토어명, 사업자번호 필수 입력 */
document.addEventListener("DOMContentLoaded", function () {
    const isSellerRadios = document.querySelectorAll('input[name="isSeller"]');
    const sellerFields = document.getElementById("sellerFields");
    const sellerInputs = sellerFields.querySelectorAll("input");

    isSellerRadios.forEach(function (radio) {
        radio.addEventListener("change", function () {
            if (radio.value === "true") {
                sellerFields.classList.add("visible");
                sellerInputs.forEach(function (input) {
                    input.required = true;
                });
            } else {
                sellerFields.classList.remove("visible");
                sellerInputs.forEach(function (input) {
                    input.required = false;
                });
            }
        });
    });

    if (isSellerRadios[0].checked) {
        sellerFields.classList.remove("visible");
        sellerInputs.forEach(function (input) {
            input.required = false;
        });
    }
});

/*주소 입력 api*/
function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }