document.addEventListener('DOMContentLoaded', () => {
	const editor = new toastui.Editor({
		  el: document.querySelector('#editor'),
		  height: '500px',
		  initialEditType: 'wysiwyg',
		  previewStyle: 'vertical',
		  initialValue: document.getElementById('origin_content').innerHTML.trim(),
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
		        callback(result.url, '이미지'); // 이미지가 에디터에 삽입됨
				} catch (e) {
					console.log('이미지 업로드 실패: ', e);
				}
     	},
      },
	});
	
	document
	  .getElementById('product_form')
	  .addEventListener('submit', function (e) {
	    e.preventDefault();
	
	    // 옵션 검사
	    const list = document.getElementById('option_list');
	    if (list.children.length < 1) {
	      alert('옵션을 1개 이상 추가해주세요.');
	      return;
	    }
	
	    // 에디터에서 HTML 내용 가져오기
	    const htmlContent = editor.getHTML();
	    console.log('에디터 내용:', htmlContent); // 디버깅용
	    const contentInput = document.getElementById('product_content');
	    contentInput.value = htmlContent;
	    console.log(contentInput.value);
	
	    // 조건 만족했으니 submit
	    setTimeout(() => {
	      this.submit(); // 여기서 진짜 submit 실행
	    }, 0);
	  });

	const params = new URLSearchParams(location.search);
	  if (params.get('success') === 'true') {
	    alert('상품 등록이 완료되었습니다!');
	    // 주소 깔끔하게 정리
	    history.replaceState({}, '', location.pathname);
	  } else if (params.get('success') === 'false') {
	    alert('상품 등록에 실패했습니다.');
	    history.replaceState({}, '', location.pathname);
  }
});

console.log('test')

const addBtn = document.getElementById('add_option_btn');
const nameInput = document.getElementById('option_name');
const priceInput = document.getElementById('option_price');
const list = document.getElementById('option_list');

addBtn.addEventListener('click', () => {
  const name = nameInput.value.trim();
  const price = priceInput.value.trim();

  if (!name || isNaN(price)) {
    alert('옵션명과 가격을 제대로 입력해주세요.');
    return;
  }

  const li = document.createElement('li');
  li.innerHTML = `
    <span>${name} (+${price}원)</span>
    <input type="hidden" name="option_name" value="${name}">
    <input type="hidden" name="option_price" value="${price}">
    <button type="button" class="delete-option-btn">삭제</button>
  `;
  list.appendChild(li);

  nameInput.value = '';
  priceInput.value = '';
});

document.getElementById('option_list').addEventListener('click', function (e) {
  if (e.target.classList.contains('delete-option-btn')) {
    e.target.parentElement.remove(); // li 요소 삭제
  }
});

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