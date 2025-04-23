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
        <span>${opt.option} (${opt.price}원)</span>
        <input type="number" name="quantities" value="${opt.quantity}" min="1" data-cart="${opt.cartNum}">
        <button type="button" class="delete-option-btn" data-cart="${opt.cartNum}">삭제</button>
      `;
      optionListArea.appendChild(div);
    });

    // 추가 옵션 목록 초기화
    const addRes = await fetch(`/barofarm/getOptions?productNum=${productNum}`);
    const addOptions = await addRes.json();

    const addSelect = document.getElementById('addOptionSelect');
    addSelect.innerHTML = '';
    addOptions.forEach(opt => {
      const optionEl = document.createElement('option');
      optionEl.value = opt.optionNum;
      optionEl.textContent = `${opt.option} (${opt.price}원)`;
      addSelect.appendChild(optionEl);
    });

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