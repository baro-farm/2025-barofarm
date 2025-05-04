document.addEventListener('DOMContentLoaded', () => {	 
  const deleteModal = document.getElementById('deleteModal');
  const deleteButtons = document.querySelectorAll('.deleteBtn');
  const cancelDelete = document.getElementById('cancelDelete');

  deleteButtons.forEach((button) => {
  button.addEventListener('click', async (event) => {
    const noticeNum = button.dataset.num;
    const title = event.target.closest('tr').children[1].innerText;
    const confirmed = confirm(`정말로 삭제하시겠습니까?\n\n글번호: ${noticeNum}\n제목: ${title}`);
    
    if (!confirmed) return;

    const params = new URLSearchParams();
    params.append('noticeNum', noticeNum);

    const res = await fetch('/barofarm/deleteNotice', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: params.toString()
    });

    location.reload();
  });
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