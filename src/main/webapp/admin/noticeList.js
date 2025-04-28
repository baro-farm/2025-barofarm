document.addEventListener('DOMContentLoaded', () => {	 
  const deleteModal = document.getElementById('deleteModal');
  const deleteButtons = document.querySelectorAll('.deleteBtn');
  const confirmDelete = document.getElementById('confirmDelete');
  const cancelDelete = document.getElementById('cancelDelete');
  const title = document.getElementById('selected_title');
  const content = document.getElementById('selected_content');
  let targetRow; // 삭제할 행을 저장할 변수
  let targetNum;
  

  // 삭제 버튼 클릭 이벤트 추가
  deleteButtons.forEach((button) => {
    button.addEventListener('click', (event) => {
      targetRow = event.target.closest('tr'); // 클릭된 버튼의 부모 행(tr) 저장
      deleteModal.style.display = 'flex'; // 모달 열기
   	  targetNum = button.dataset.num;
      title.innerText = "제목: " + targetRow.children[1].innerText;
      content.innerText = "내용: " + targetRow.children[2].innerText;
    });
  });

  // 삭제 확인 버튼 클릭 시
  confirmDelete.addEventListener('click', async () => {
	const params = new URLSearchParams();
	params.append('noticeNum', targetNum);

	const res = await fetch('/barofarm/deleteNotice', {
	  method: 'POST',
	  headers: {
	    'Content-Type': 'application/x-www-form-urlencoded',
	  },
	  body: params.toString()
	});
	
	const message = await res.text(); // ← 응답 텍스트 받아옴
    alert(message); // ← alert 띄움

	deleteModal.style.display = 'none'; // 모달 닫기
	location.reload();
  });

  // 취소 버튼 클릭 시 모달 닫기
  cancelDelete.addEventListener('click', () => {
    deleteModal.style.display = 'none';
  });

  // 모달 바깥 클릭 시 닫기
  window.addEventListener('click', (event) => {
    if (event.target === deleteModal) {
      deleteModal.style.display = 'none';
    }
  });
})