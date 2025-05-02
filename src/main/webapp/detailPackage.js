document.addEventListener('DOMContentLoaded', () => {
  const purchaseBtn = document.getElementById('purchase');

  if (purchaseBtn) {
    purchaseBtn.addEventListener('click', async (e) => {
      e.preventDefault();

      const packageNum = purchaseBtn.dataset.packageNum;

      console.log("🔥 구매하기 버튼의 packageNum:", packageNum);

      try {
        const res = await fetch('/barofarm/packagePayment', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            packageNum: parseInt(packageNum, 10)
          })
        });

        if (res.redirected) {
          window.location.href = res.url;
          return;
        }

        // fallback
        window.location.href = '/barofarm/packagePayment?packageNum=' + packageNum;
      } catch (err) {
        console.error("💥 네트워크 오류 또는 JSON 파싱 실패:", err);
        alert("요청 처리 중 오류가 발생했습니다.");
      }
    });
  }
});
