document.addEventListener('DOMContentLoaded', () => {
  const paymentBtn = document.getElementById('payment');
  paymentBtn.addEventListener('click', async () => {
    // 1. 결제할 금액, 주문정보 준비 (JSP에서 값 가져오기)
    const amount = parseInt(document.querySelector('.all-total').innerText.replace(/[^\d]/g, ''), 10);
    const cartNums = Array.from(document.querySelectorAll('input[name="cartNums"]')).map(input => input.value);

    // 2. 포트원 결제창 호출
    const response = await PortOne.requestPayment({
      storeId: 'store-aa803013-fc3d-4cba-b60c-a1d5b8387f8a',  // 포트원 Store ID (아임포트 아닌 포트원 방식이면 이거!)
      channelKey: 'channel-key-83b8d92a-5618-4b71-b547-718a66b6e8df',
      paymentId: "" + Date.now(),  // 주문번호 (유니크하게)
      orderName: '상품 구매',
      totalAmount: amount,
      currency: 'CURRENCY_KRW',
      /*currency: 'KAKAOPAY',*/
      payMethod: 'EASY_PAY', // 카드, 간편결제 등
      /*pgProvider: 'inisis',*/
      /*pgProvider: 'INICIS',*/
      customer: {
        customerName: '구매자 이름',  // 로그인한 사용자 이름 가져오기
        customerEmail: '구매자 이메일'
      }
    });

	console.log(response);
    // 3. 결제 결과 처리
    if (response.status === 'paid') {
      // 성공 → 서버에 결제 정보 + cartNums 전송 (fetch로)
      const res = await fetch('/barofarm/paymentComplete', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
          /*impUid: response.paymentId,  // 결제 고유번호*/
          /*amount: response.totalAmount,*/
          /*impUid: response.imp_uid,  // paymentId → imp_uid로!
    	  amount: response.amount,   // totalAmount → amount로!*/
    	  paymentId: response.paymentId,
          cartNums: cartNums
        })
      });
      

      const result = await res.json();
      
      console.log(`결과: ${result}`)
      if (result.success) {
        alert('결제가 완료되었습니다!');
        location.href = '/barofarm/orderComplete';
      } else {
        alert('결제 검증에 실패했습니다.');
      }
    } else {
      alert('결제가 취소되었거나 실패했습니다.');
    }
  });
});
