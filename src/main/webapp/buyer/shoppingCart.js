document.querySelectorAll('.open-modal').forEach(btn => {
  btn.addEventListener('click', async () => {
    const cartNum = btn.dataset.cart;
    const productNum = btn.dataset.product;

    document.getElementById('modalCartNum').value = cartNum;

    // 옵션 불러오기
    const res = await fetch(`/barofarm/getOptions?productNum=${productNum}`);
    const optionList = await res.json(); // [{optionNum, option, price}, ...]

    const select = document.getElementById('modalOptionSelect');
    select.innerHTML = ''; // 초기화
    optionList.forEach(opt => {
      const op = document.createElement('option');
      op.value = opt.optionNum;
      op.textContent = `${opt.option} (${opt.price}원)`;
      select.appendChild(op);
    });

    document.getElementById('optionModal').style.display = 'block';
  });
});

function closeModal() {
  document.getElementById('optionModal').style.display = 'none';
}
