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
        const key = name;
        const price = parseInt(priceStr, 10);

        if (!selectedOptions[key]) {
            selectedOptions[key] = { name, price, qty: 1 };
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
