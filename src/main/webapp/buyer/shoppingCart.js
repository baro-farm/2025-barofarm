document.addEventListener('DOMContentLoaded', () => {
	// 옵션 변경 버튼 클릭 시 모달 열기 + 옵션 불러오기
	document.querySelectorAll('.open-modal').forEach(btn => {
		btn.addEventListener('click', async () => {
			const cartNum = btn.dataset.cart;
			const productNum = btn.dataset.product;

			// 모달에 cartNum 세팅
			document.getElementById('modalCartNum').value = cartNum;

			// 옵션 셀렉트 초기화 및 로딩
			const res = await fetch(`/barofarm/getOptions?productNum=${productNum}`);
			const optionList = await res.json();

			const select = document.getElementById('modalOptionSelect');
			select.innerHTML = '';
			optionList.forEach(opt => {
				const op = document.createElement('option');
				op.value = opt.optionNum;
				op.textContent = `${opt.option} (${opt.price}원)`;
				select.appendChild(op);
			});

			// 모달 + 오버레이 표시
			document.getElementById('optionModal').style.display = 'block';
			document.getElementById('modalOverlay').style.display = 'block';
		});
	});

	// 배경 눌렀을 때 모달 닫고 새로고침
	document.getElementById('modalOverlay').addEventListener('click', () => {
		closeModal();
	});
	
	// 모달 닫기
	window.closeModal = function() {
		document.getElementById('optionModal').style.display = 'none';
		document.getElementById('modalOverlay').style.display = 'none';
	};
});


