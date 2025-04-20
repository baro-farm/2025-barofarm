document.addEventListener('DOMContentLoaded', () => {
  const editor = new toastui.Editor({
    el: document.querySelector('#editor'),
    height: '500px',
    initialEditType: 'markdown',
    previewStyle: 'vertical',
    hooks: {
      addImageBlobHook: async (blob, callback) => {
        const formData = new FormData();
        formData.append('uploadFile', blob);

        try {
          const res = await fetch('/barofarm/imageUpload', {
            method: 'POST',
            body: formData,
          });
          const result = await res.json();
          callback(result.url, '이미지');
        } catch (e) {
          console.log('이미지 업로드 실패: ', e);
        }
      },
    },
  });

  document
    .getElementById('package_form')
    .addEventListener('submit', function (e) {
      e.preventDefault();

      const htmlContent = editor.getHTML().trim();
      const markdownContent = editor.getMarkdown().trim();
      const contentInput = document.getElementById('package_content');

      if (!markdownContent) {
        alert('상품 상세설명을 입력해주세요!');
        return;
      }

      contentInput.value = htmlContent;
      console.log('전송할 HTML:', htmlContent);

      this.submit();
    });

  const params = new URLSearchParams(location.search);
  if (params.get('success') === 'true') {
    alert('상품 등록이 완료되었습니다!');
    history.replaceState({}, '', location.pathname);
  } else if (params.get('success') === 'false') {
    alert('상품 등록에 실패했습니다.');
    history.replaceState({}, '', location.pathname);
  }

  // 이미지 미리보기
  const fileInput = document.getElementById('product_image');
  const preview = document.getElementById('preview');

  fileInput.addEventListener('change', (event) => {
    const file = event.target.files[0];
    if (file && file.type.startsWith('image/')) {
      const reader = new FileReader();
      reader.onload = (e) => {
        preview.innerHTML = `<img src="${e.target.result}" alt="미리보기" style="max-width: 200px; margin-top: 10px;" />`;
      };
      reader.readAsDataURL(file);
    } else {
      preview.innerHTML = '';
    }
  });

  // 패키지 인원 변경 시 가격 필드 자동 수정
  document
    .getElementById('package_unit')
    .addEventListener('change', function () {
      const container = document.getElementById('options_container');
      container.innerHTML = '';
      const selectedValue = this.value;

      const div = document.createElement('div');
      div.classList.add('input_div');
      div.innerHTML = `
      <label>${selectedValue}인 가격</label>
      <input type="number" name="package_price" id="package_price" placeholder="${selectedValue}인 가격 (원)" required>
    `;
      container.appendChild(div);
    });
});
