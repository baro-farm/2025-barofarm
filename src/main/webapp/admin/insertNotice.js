const cancelBtn = document.getElementById("cancelBtn");
cancelBtn.addEventListener('click', (e)=> {
	e.preventDefault();
    location.href = "/barofarm/noticeList";
})

document.getElementById('noticeForm').addEventListener('submit', function(e) {
  // 에디터에서 HTML 내용 가져오기
  const htmlContent = editor.getHTML();
  document.getElementById('content').value = htmlContent;
});