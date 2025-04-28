// 모달 열기 및 옵션 목록 초기화
const optionChangeForm = document.getElementById('optionChangeForm');
const handleSubmit = function(e) {
  e.preventDefault();

  if (this.submitted) return;
  this.submitted = true;

  const cartNums = [];
  const quantities = [];
  document.querySelectorAll('#optionListArea input[name="quantities"]').forEach(input => {
    const cartNum = input.dataset.cart;
    const quantity = input.value.trim();
    const isDeleted = input.closest('.option-row').querySelector('.delete-option-btn')?.classList.contains('deleted');

    if (cartNum && quantity && !isDeleted) {
      cartNums.push(cartNum);
      quantities.push(quantity);
    }
  });

  const newOptionNums = [];
  const newQuantities = [];
  document.querySelectorAll('#optionListArea input[name="newQuantities"]').forEach(input => {
    const optionNum = input.dataset.option;
    const quantity = input.value.trim();

    if (optionNum && quantity) {
      newOptionNums.push(optionNum);
      newQuantities.push(quantity);
    }
  });

  const deleteCartNums = [];
  document.querySelectorAll('.delete-option-btn.deleted').forEach(btn => {
    deleteCartNums.push(btn.dataset.cart);
  });

  document.getElementById('hiddenCartNums').value = cartNums.join(',');
  document.getElementById('hiddenQuantities').value = quantities.join(',');
  document.getElementById('hiddenNewOptionNums').value = newOptionNums.join(',');
  document.getElementById('hiddenNewQuantities').value = newQuantities.join(',');
  document.getElementById('hiddenDeleteCartNums').value = deleteCartNums.join(',');

  console.log('== 최종 전송 값 ==');
  console.log('cartNums:', cartNums.join(','));
  console.log('quantities:', quantities.join(','));
  console.log('newOptionNums:', newOptionNums.join(","));
  console.log('newQuantities:', newQuantities.join(','));
  console.log('deleteCartNums:', deleteCartNums.join(','));

  setTimeout(() => {
    this.submit();
  }, 0);
};

// 이벤트 리스너 등록
optionChangeForm.addEventListener('submit', handleSubmit);

// 모달 열기 (옵션 초기화)
document.querySelectorAll('.open-modal').forEach(btn => {
  btn.addEventListener('click', async () => {
    // 기존 submit 리스너 제거 후 재등록
    optionChangeForm.removeEventListener('submit', handleSubmit);
    optionChangeForm.addEventListener('submit', handleSubmit);

    const productNum = btn.dataset.product;
    document.getElementById('modalProductNum').value = productNum;

    // 옵션 목록 불러오기
    // 해당 유저의 상품 옵션!!
    const res = await fetch(`/barofarm/getCartOptions?productNum=${productNum}`);
    const optionList = await res.json();

    const optionListArea = document.getElementById('optionListArea');
    optionListArea.innerHTML = '';

    optionList.forEach(opt => {
      const div = document.createElement('div');
      div.classList.add('option-row');
      div.innerHTML = `
        <span>${opt.option} (${opt.price.toLocaleString()}원)</span>
        <input type="number" class="number-input" name="quantities" value="${opt.quantity}" min="1" data-cart="${opt.cartNum}">
        <button type="button" class="delete-option-btn" data-cart="${opt.cartNum}">삭제</button>
      `;
      optionListArea.appendChild(div);
    });

    // 추가 옵션 목록 초기화
    const addRes = await fetch(`/barofarm/getOptions?productNum=${productNum}`);
    const addOptions = await addRes.json();

    const addSelect = document.getElementById('addOptionSelect');
    addSelect.innerHTML = '';
    
    // 🟢 1. 이미 장바구니에 담긴 옵션들 번호 뽑기
	const existingOptionNums = optionList.map(opt => opt.optionNum);
	
	// 🟢 2. 전체 옵션 중에서 기존에 없는 옵션만 select에 추가
	addOptions.forEach(opt => {
	  if (!existingOptionNums.includes(opt.optionNum)) {  // 담긴 옵션 제외!
	    const optionEl = document.createElement('option');
	    optionEl.value = opt.optionNum;
	    optionEl.textContent = `${opt.option} (${opt.price.toLocaleString()}원)`;
	    addSelect.appendChild(optionEl);
	  }
	})
    /*addOptions.forEach(opt => {
      const optionEl = document.createElement('option');
      optionEl.value = opt.optionNum;
      optionEl.textContent = `${opt.option} (${opt.price.toLocaleString()}원)`;
      addSelect.appendChild(optionEl);
    });*/

    document.getElementById('optionModal').style.display = 'block';
    document.getElementById('modalOverlay').style.display = 'block';
  });
});

// 옵션 추가
document.getElementById('addOptionBtn').addEventListener('click', () => {
  const select = document.getElementById('addOptionSelect');
  const quantity = document.getElementById('addOptionQuantity').value;

  const div = document.createElement('div');
  div.classList.add('option-row');
  div.innerHTML = `
    <span>${select.options[select.selectedIndex].text}</span>
    <input type="number" name="newQuantities" value="${quantity}" min="1" data-option="${select.value}">
    <button type="button" class="remove-temp-option-btn">취소</button>
  `;
  document.getElementById('optionListArea').appendChild(div);
});

// 옵션 삭제 및 취소
const optionListArea = document.getElementById('optionListArea');
optionListArea.addEventListener('click', (e) => {
  if (e.target.classList.contains('delete-option-btn')) {
    e.target.classList.add('deleted');
    e.target.closest('.option-row').style.display = 'none';
  }
  if (e.target.classList.contains('remove-temp-option-btn')) {
    e.target.closest('.option-row').remove();
  }
});

// 모달 닫기
function closeModal() {
  document.getElementById('optionModal').style.display = 'none';
  document.getElementById('modalOverlay').style.display = 'none';
  optionChangeForm.submitted = false;  // 플래그 초기화
}

document.getElementById('modalOverlay').addEventListener('click', closeModal);

function updateSelectAllCheck() {
  const totalProducts = document.querySelectorAll('.product-checkbox').length;
  const totalChecked = document.querySelectorAll('.product-checkbox:checked').length;
  const selectAll = document.getElementById('selectAll');
  selectAll.checked = totalProducts > 0 && totalProducts === totalChecked;
}

// 체크박스 및 합계 업데이트 로직

document.addEventListener('DOMContentLoaded', () => {
  // 스토어 체크박스 클릭 시 해당 스토어 상품 체크박스 모두 선택/해제
  document.querySelectorAll('.store-checkbox').forEach(storeCheck => {
  storeCheck.addEventListener('change', () => {
    const storeGroup = storeCheck.closest('.store-group');
    const productChecks = storeGroup.querySelectorAll('.product-checkbox');
    
    productChecks.forEach(prodCheck => prodCheck.checked = storeCheck.checked);
    
    updateStoreTotal(storeGroup);  // 스토어 합계 업데이트
    updateSelectAllCheck();        // 전체 체크박스 업데이트
  });
});


  // 각 상품 체크박스 클릭 시 합계 업데이트
  document.querySelectorAll('.product-checkbox').forEach(prodCheck => {
  prodCheck.addEventListener('change', () => {
    const storeGroup = prodCheck.closest('.store-group');
    const productChecks = storeGroup.querySelectorAll('.product-checkbox');
    const productChecked = storeGroup.querySelectorAll('.product-checkbox:checked');
    
    // 스토어 체크박스 상태 업데이트
    const storeCheckbox = storeGroup.querySelector('.store-checkbox');
    storeCheckbox.checked = productChecks.length === productChecked.length;

    updateStoreTotal(storeGroup);  // 스토어 합계 업데이트
    updateSelectAllCheck();        // 전체 체크박스 업데이트
  });
});

});

// 선택된 상품들의 건수와 총합을 스토어별로 업데이트
function updateStoreTotal(storeGroup) {
  let total = 0;
  let count = 0;
  storeGroup.querySelectorAll('.product-checkbox:checked').forEach(check => {
    const productTotal = parseInt(check.dataset.totalPrice) || 0;
    total += productTotal;
    count++;
  });
  const totalBox = storeGroup.querySelector('.store-total');
  totalBox.textContent = `선택된 상품 ${count}건 / 총합 ${total.toLocaleString()}원`;

  updateAllTotal();  // 전체 총합 + 건수도 같이 업데이트
}

function updateAllTotal() {
  let total = 0;
  let count = 0;
  document.querySelectorAll('.product-checkbox:checked').forEach(check => {
    const productTotal = parseInt(check.dataset.totalPrice) || 0;
    total += productTotal;
    count++;
  });
  const totalBox = document.querySelector('.all-total');
  totalBox.textContent = `전체 선택된 상품 ${count}건 / 총합 ${total.toLocaleString()}원`;
}

function getSelectedCartNums(storeGroup = null) {
  const selected = [];
  const checkboxes = storeGroup 
    ? storeGroup.querySelectorAll('.product-checkbox:checked') 
    : document.querySelectorAll('.product-checkbox:checked');

  checkboxes.forEach(check => {
    const cartNumsStr = check.dataset.cartNums;  // 여기서 cartNums 가져옴
    const cartNums = cartNumsStr
      .split(',')
      .map(num => num.trim())  // 여기 추가!
      .filter(num => num);
    selected.push(...cartNums);
  });

  return selected;
}

document.getElementById('selectAll').addEventListener('change', (e) => {
  const checked = e.target.checked;

  // 스토어 체크박스, 상품 체크박스 전부 선택/해제
  document.querySelectorAll('.store-checkbox').forEach(storeCb => storeCb.checked = checked);
  document.querySelectorAll('.product-checkbox').forEach(prodCb => prodCb.checked = checked);

  // 전체/스토어 합계 업데이트
  document.querySelectorAll('.store-group').forEach(group => {
    updateStoreTotal(group);
  });
});

document.querySelectorAll('.store-order-btn').forEach(btn => {
  btn.addEventListener('click', () => {
    const storeGroup = btn.closest('.store-group');
    const cartNums = getSelectedCartNums(storeGroup);
    if (cartNums.length === 0) {
      alert('상품을 선택하세요!');
      return;
    }

    fetch('/barofarm/payment', {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: new URLSearchParams(cartNums.map(num => ['cartNums[]', num]))
    }).then(response => response.text())
      .then(html => {
        document.open();
        document.write(html);
        document.close();
		window.location.href = '/barofarm/payment';
		window.location.href = '/barofarm/payment?cartNums=' + cartNums.join(',');
      });
  });
});

document.getElementById('all-order-btn').addEventListener('click', () => {
  const cartNums = getSelectedCartNums();
  if (cartNums.length === 0) {
    alert('상품을 선택하세요!');
    return;
  }
//console.log('cartNums:', cartNums);  // 선택된 cartNums 찍어보기
//console.log('fetch 요청 시작!');

  fetch('/barofarm/payment', {
    method: 'POST',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: new URLSearchParams(cartNums.map(num => ['cartNums[]', num]))
  }).then(response => {
	  // console.log('서버 응답:', response); 
	  return response.text();
	 })
    .then(html => {
	//console.log('받아온 HTML:', html);  // 여기서 HTML 내용 확인
    // 받아온 HTML을 새로운 페이지로 열기
    //const parser = new DOMParser();
    //const doc = parser.parseFromString(html, 'text/html');
    document.open();
    //document.write(doc.documentElement.innerHTML);
    document.write(html);
    document.close();
    window.location.href = '/barofarm/payment';
    window.location.href = '/barofarm/payment?cartNums=' + cartNums.join(',');
  });
});
