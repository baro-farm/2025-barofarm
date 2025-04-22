document.querySelectorAll('.open-modal').forEach(btn => {
	btn.addEventListener('click', async () => {
		const productNum = btn.dataset.product;

		document.getElementById('modalProductNum').value = productNum;

		// 기존 옵션 목록 가져오기
		const res = await fetch(`/barofarm/getCartOptions?productNum=${productNum}`);
		const optionList = await res.json();  // [{cartNum, optionNum, option, quantity, price}...]

console.log("초기 옵션 목록:");
optionList.forEach(opt => console.log(opt.cartNum, opt.quantity));

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

		// 옵션 추가용 select도 초기화
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
	setTimeout(() => { }, 0);
});

document.getElementById('optionListArea').addEventListener('click', (e) => {
	if (e.target.classList.contains('delete-option-btn')) {
		e.target.classList.add('deleted');  // 삭제 표시 (submit 시 넘길 값)
		e.target.closest('.option-row').style.display = 'none';  // 화면에서 숨김
	}

	if (e.target.classList.contains('remove-temp-option-btn')) {
		e.target.closest('.option-row').remove();  // 새 추가 옵션은 그냥 삭제
	}
});


document.getElementById('optionChangeForm').addEventListener('submit', function(e) {
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

  // Hidden input에 값 세팅
  document.getElementById('hiddenCartNums').value = cartNums.join(',');
  document.getElementById('hiddenQuantities').value = quantities.join(',');
  document.getElementById('hiddenNewOptionNums').value = newOptionNums.join(',');
  document.getElementById('hiddenNewQuantities').value = newQuantities.join(',');
  document.getElementById('hiddenDeleteCartNums').value = deleteCartNums.join(',');

  console.log('== 최종 전송 값 ==');
  console.log('cartNums:', cartNums);
  console.log('quantities:', quantities);
  console.log('newOptionNums:', newOptionNums);
  console.log('newQuantities:', newQuantities);
  console.log('deleteCartNums:', deleteCartNums);
console.log('hiddenQuantities:', document.getElementById('hiddenQuantities').value);

  this.submit();
});


function closeModal() {
	document.getElementById('optionModal').style.display = 'none';
	document.getElementById('modalOverlay').style.display = 'none';

	// submitted 플래그 초기화
	document.getElementById('optionChangeForm').submitted = false;
}

document.getElementById('modalOverlay').addEventListener('click', () => {
	closeModal();
});

