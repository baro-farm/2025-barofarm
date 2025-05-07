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

  let clickedButton = null;

  document.getElementById('update_btn').addEventListener('click', () => {
    clickedButton = 'update';
  });

  document
    .getElementById('product_form')
    .addEventListener('submit', function (e) {
      e.preventDefault();
      if (clickedButton === 'stop') {
        // 판매 중단 처리
        this.submit();
        // 원하는 로직 추가
      } else {
        // 옵션 검사
        const list = document.getElementById('option_list');
        if (list.children.length < 1) {
          alert('옵션을 1개 이상 추가해주세요.');
          return;
        }

        // 에디터에서 HTML 내용 가져오기
        const htmlContent = editor.getHTML();
        const contentInput = document.getElementById('product_content');
        contentInput.value = htmlContent;

        // 조건 만족했으니 submit
        setTimeout(() => {
          this.submit(); // 여기서 진짜 submit 실행
        }, 0);
      }
    });

  const params = new URLSearchParams(location.search);
  if (params.get('error') === 'unauthorized') {
    alert('해당 상품의 판매자만 변경이 가능합니다.');
    //history.back(); // 또는 location.href = '/barofarm/원하는곳';
  }
  if (params.get('success') === 'true') {
    alert('상품 수정이 완료되었습니다!');
    // 주소 깔끔하게 정리
    history.replaceState({}, '', location.pathname);
  } else if (params.get('success') === 'false') {
    alert('상품 수정에 실패했습니다.');
    history.replaceState({}, '', location.pathname);
  }
  
  
});

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

  // 삭제
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
    const formattedStock = Math.abs(stock).toLocaleString();
    const sign = price >= 0 ? `+${formattedPrice}` : `-${formattedPrice}`;
    li.querySelector('span').innerText = `${name} (${sign}원, 재고: ${formattedStock}개)`;

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

// 상품 판매 상태 변경
document.getElementById('toggle_btn').addEventListener('click', async function () {
  const productNum = this.dataset.productNum;
  const currentStatus = this.dataset.status === 'true';

  const confirmMsg = currentStatus ? '판매를 중단하시겠습니까?' : '판매를 재개하시겠습니까?';
  if (!confirm(confirmMsg)) return;

  try {
    const res = await fetch('/barofarm/toggleProductStatus', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ productNum })
    });

    const result = await res.json();
    if (result.success) {
      alert(result.message);
      location.reload(); // 또는 버튼 텍스트/데이터만 교체
    } else {
      alert(result.message || '처리에 실패했습니다.');
    }
  } catch (err) {
    console.error('에러 발생:', err);
    alert('서버와의 통신 중 문제가 발생했습니다.');
  }
});