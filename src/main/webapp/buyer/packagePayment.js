function openAddressModal() {
    document.getElementById('addressModal').style.display = 'flex';
}

function closeAddressModal() {
    document.getElementById('addressModal').style.display = 'none';
}

window.payMonths = 0;
window.unitPrice = 0;

document.addEventListener('DOMContentLoaded', () => {
    const addressItems = document.querySelectorAll('.address-item');
    addressItems.forEach(item => {
        item.addEventListener('click', () => {
            const name = item.dataset.name;
            const nickname = item.dataset.nickname;
            const phone = item.dataset.phone;
            const addr1 = item.dataset.addr1;
            const addr2 = item.dataset.addr2;

            document.getElementById('receiverName').innerText = `${name} (${nickname})`;
            document.getElementById('receiverPhone').innerText = phone;
            document.getElementById('receiverAddress').innerText = `${addr1} ${addr2}`;

            closeAddressModal();
        });
    });

    const start = new Date(document.querySelector('[data-start-date]').dataset.startDate);
	const end = new Date(document.querySelector('[data-end-date]').dataset.endDate);
	const today = new Date();
	const unitPrice = parseInt(document.getElementById('unitPrice').dataset.price, 10);
	
	const todayText = document.getElementById('today');
	const msg = document.getElementById('subscriptionMsg');
	const priceText = document.getElementById('finalPriceText');
	
	todayText.innerText = today.toISOString().slice(0, 10);
	
	// 총 구독 개월 수 계산
	let totalMonths =
	    (end.getFullYear() - start.getFullYear()) * 12 +
	    (end.getMonth() - start.getMonth());
	
	if (end.getDate() >= start.getDate()) {
	  totalMonths += 1; // 종료일이 시작일 이상일 경우 그 달 포함
	}
	let payMonths = totalMonths;
	let startMessage = "";
	
	const isSameMonth = today.getFullYear() === start.getFullYear() &&
	                    today.getMonth() === start.getMonth();
	const deliveryStartDay = start.getDate();
	
	if (isSameMonth && today.getDate() >= deliveryStartDay) {
	    // 이번 달은 이미 지나서 제외
	    payMonths -= 1;
	    startMessage = "※ 현재 날짜 기준 이번 달 배송은 종료되어 다음 달부터 시작됩니다.";
	} else {
	    startMessage = "※ 이번 달부터 배송이 시작됩니다.";
	}
	
	msg.innerText = `${startMessage} 총 ${payMonths}개월분(${payMonths} x ${unitPrice.toLocaleString()}원)이 결제됩니다.`;
	priceText.innerText = (payMonths * unitPrice).toLocaleString() + "원";
	document.querySelector('.all-total').innerText = `총 결제 금액: ${(payMonths * unitPrice).toLocaleString()}원`;
	
	// 👉 payMonths 전역 변수로 expose
	window.payMonths = payMonths;
	window.unitPrice = unitPrice;

});
