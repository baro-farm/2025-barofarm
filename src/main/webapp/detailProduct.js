document.addEventListener('DOMContentLoaded', function () {
	const selectBox = document.getElementById('optionSelect');
	const selectedOptionsContainer = document.getElementById('selectedOptions');
	const totalPriceEl = document.getElementById('totalPrice');
	
	const selectedOptions = {};
	
	function updateTotalPrice() {
		let total = 0;
		for (const key in selectedOptions) {
            total += selectedOptions[key].price * selectedOptions[key].qty;
        }
        totalPriceEl.textContent = total.toLocaleString();
        }

        function renderOptions() {
        selectedOptionsContainer.innerHTML = '';

        Object.entries(selectedOptions).forEach(([key, { name, price, qty }]) => {
            const wrapper = document.createElement('div');
            wrapper.className = 'selectedOptionItem';

            wrapper.innerHTML = `
            <div class="optionName">${name}</div>
            <div class="optionControls">
                <button class="decrease" data-key="${key}">-</button>
                <span class="qty">${qty}</span>
                <button class="increase" data-key="${key}">+</button>
            </div>
            <div class="optionPrice">${(price * qty).toLocaleString()}원</div>
            <div class="removeBtn" data-key="${key}"> X </div>
            `;

            selectedOptionsContainer.appendChild(wrapper);
        });

        updateTotalPrice();
        }

        selectBox.addEventListener('change', function () {
        const selectedValue = this.value;
        if (!selectedValue) return;

        const [name, priceStr] = selectedValue.split('|');
        const selectedOptionElement = selectBox.selectedOptions[0];
        const optionNum = parseInt(selectedOptionElement.dataset.optionnum, 10);
        
        const key = name;
        const price = parseInt(priceStr, 10);

        if (!selectedOptions[key]) {
            selectedOptions[key] = { name, price, qty: 1, optionNum: optionNum };
        } else {
            selectedOptions[key].qty += 1;
        }

        // 선택 초기화
        selectBox.selectedIndex = 0;
        renderOptions();
        });

        selectedOptionsContainer.addEventListener('click', function (e) {
        const key = e.target.dataset.key;
        if (!key) return;

        if (e.target.classList.contains('increase')) {
            selectedOptions[key].qty += 1;
        }

        if (e.target.classList.contains('decrease')) {
            selectedOptions[key].qty -= 1;
            if (selectedOptions[key].qty <= 0) {
            delete selectedOptions[key];
            }
        }

        if (e.target.classList.contains('removeBtn')) {
            delete selectedOptions[key];
        }

        renderOptions();
        });
        
        const basketBtn = document.getElementById('basket');
  
  basketBtn.addEventListener('click', async (e) => {
    e.preventDefault();

    // 선택된 옵션이 있는지 확인
    const selectedKeys = Object.keys(selectedOptions);
    if (selectedKeys.length === 0) {
      alert('옵션을 선택하세요!');
      return;
    }

    // 선택된 옵션들 하나씩 담기
    for (const key of selectedKeys) {
      const option = selectedOptions[key];
      const optionNum = option.optionNum;  // optionNum은 어디에 저장해두는지 확인!
      const quantity = option.qty;
      
      
	  const productNum = basketBtn.dataset.productnum;
	  console.log(productNum)
      const payload = {
        productNum: parseInt(productNum),
        optionNum: optionNum,
        quantity: quantity
      };

      console.log('보낼 데이터:', payload);  // 확인용

      try {
        const response = await fetch('/barofarm/insertCart', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(payload)
        });

        const result = await response.json();
        if (response.ok) {
          console.log('장바구니에 담김:', result);
          alert(result.message);
        } else {
          alert(result.message || '오류가 발생했습니다.');
        }
      } catch (error) {
        console.error('Fetch Error:', error);
        alert('네트워크 오류가 발생했습니다.');
      }
    }
});
});

/* 리뷰 페이징 */
$(document).ready(function(){ 
    $(document).on("click", "a.reviewPageLink", function(e){
        e.preventDefault();
        console.log("클릭한 a 태그:", this.tagName);
        const url = $(e.currentTarget).attr("href");

        const scrollPos = $(window).scrollTop();
        console.log("요청 URL:", url);

        $.ajax({
            url: url,
            type: "get",
            success: function(data){
                $("#review").html(data);
                $(window).scrollTop(scrollPos);
            },
            error: function(xhr, status, error){
                console.log("Ajax 오류:", status, error);
                console.log("서버 응답:", xhr.responseText);
            }
        });
    });
});