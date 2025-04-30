function openAddressModal() {
    document.getElementById('addressModal').style.display = 'flex';
}

function closeAddressModal() {
    document.getElementById('addressModal').style.display = 'none';
}

// 배송지 클릭 시 화면에 반영
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
});
           