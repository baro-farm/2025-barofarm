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
const stockInput = document.getElementById('option_stock');
const list = document.getElementById('option_list');

addBtn.addEventListener('click', () => {
  const name = nameInput.value.trim();
  const price = priceInput.value.trim();
  const stock = stockInput.value.trim();
  
  if (!name || isNaN(price) || isNaN(stock)) {
    alert('옵션명, 가격, 재고를 제대로 입력해주세요.');
    return;
  }

  const formattedPrice = Math.abs(price).toLocaleString(); 
  const sign = price >= 0 ? `+${formattedPrice}` : `-${formattedPrice}`;

  const li = document.createElement('li');
  li.innerHTML = `
    <span>${name} (${sign}원, 재고: ${stock}개)</span>
    <input type="hidden" name="option_name" value="${name}">
    <input type="hidden" name="option_price" value="${price}">
    <input type="hidden" name="option_stock" value="${stock}">
    <button type="button" class="edit-option-btn">수정</button>
    <button type="button" class="delete-option-btn">삭제</button>
  `;
  list.appendChild(li);

  nameInput.value = '';
  priceInput.value = '';
  stockInput.value = '';
});

document.getElementById('option_list').addEventListener('click', function (e) {
  const li = e.target.closest('li');
  
  if (e.target.classList.contains('delete-option-btn')) {
    li.remove();
    return;
  }
  
  // 수정 모드 진입
  if (e.target.classList.contains('edit-option-btn')) {
    const span = li.querySelector('span');
    const nameInput = li.querySelector('input[name="option_name"]');
    const priceInput = li.querySelector('input[name="option_price"]');
    const stockInput = li.querySelector('input[name="option_stock"]');
    const optionNumInput = li.querySelector('input[name="option_num"]');

    // 기존 값
    const currentName = nameInput.value;
    const currentPrice = priceInput.value;
	const currentStock = stockInput.value;
	
    // span 영역을 인풋 필드로 대체
    span.innerHTML = `
      <input type="text" class="inline-edit name-edit" value="${currentName}" />
      <input type="number" class="inline-edit price-edit" value="${currentPrice}" />
      <input type="number" class="inline-edit stock-edit" value="${currentStock}" />
      <button type="button" class="save-option-btn">저장</button>
      <button type="button" class="cancel-option-btn">취소</button>
    `;

    // 기존 버튼 숨기기
    li.querySelector('.edit-option-btn').style.display = 'none';
    li.querySelector('.delete-option-btn').style.display = 'none';
  }

  // 저장
  if (e.target.classList.contains('save-option-btn')) {
    const li = e.target.closest('li');
    const nameField = li.querySelector('.name-edit');
    const priceField = li.querySelector('.price-edit');
    const stockField = li.querySelector('.stock-edit');
    const name = nameField.value.trim();
    const price = priceField.value.trim();
	const stock = stockField.value.trim();
	  
  	if (!name || isNaN(price) || isNaN(stock)) {
	    alert('옵션명, 가격, 재고를 제대로 입력해주세요.');
	    return;
	}
    

    // 값 업데이트
    li.querySelector('input[name="option_name"]').value = name;
    li.querySelector('input[name="option_price"]').value = price;
    li.querySelector('input[name="option_stock"]').value = stock;
    
    const formattedPrice = Math.abs(price).toLocaleString();
    const sign = price >= 0 ? `+${formattedPrice}` : `-${formattedPrice}`;
    li.querySelector('span').innerText = `${name} (${sign}원, 재고: ${stock}개)`;

    // 버튼 복구
    li.querySelector('.edit-option-btn').style.display = '';
    li.querySelector('.delete-option-btn').style.display = '';
  }

  // 취소
  if (e.target.classList.contains('cancel-option-btn')) {
    const li = e.target.closest('li');
    const name = li.querySelector('input[name="option_name"]').value;
    const price = li.querySelector('input[name="option_price"]').value;
    const stock = li.querySelector('input[name="option_stock"]').value;

    // 내용 복구
    const formattedPrice = Math.abs(price).toLocaleString();
    const sign = price >= 0 ? `+${formattedPrice}` : `-${formattedPrice}`;
    li.querySelector('span').innerText = `${name} (${sign}원, 재고: ${stock}개)`;

    // 버튼 복구
    li.querySelector('.edit-option-btn').style.display = '';
    li.querySelector('.delete-option-btn').style.display = '';
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

