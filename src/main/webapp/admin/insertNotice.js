document.addEventListener('DOMContentLoaded', () => {
  const editor = new toastui.Editor({
    el: document.querySelector('#editor'),
    height: '500px',
    initialEditType: 'markdown',
    previewStyle: 'vertical',
  });

  const cancelBtn = document.getElementById('cancelBtn');
  cancelBtn.addEventListener('click', (e) => {
    e.preventDefault();
    location.href = '/barofarm/noticeList';
  });

  document
    .getElementById('notice_form')
    .addEventListener('submit', function (e) {
      e.preventDefault();

      // 에디터에서 HTML 내용 가져오기
      const htmlContent = editor.getHTML();
      console.log('에디터 내용:', htmlContent); // 디버깅용
      const contentInput = document.getElementById('notice_content');
      contentInput.value = htmlContent;
      console.log(contentInput.value);
      
      
      setTimeout(() => {
      this.submit(); // 여기서 진짜 submit 실행
    }, 0);
    });
});
