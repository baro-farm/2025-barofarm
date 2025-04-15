$(document).ready(function () {
  $('#service_table').DataTable({
    colReorder: true,
    responsive: true,
    scrollX: true,
  });
});

document.addEventListener('DOMContentLoaded', () => {
  const rows = document.querySelectorAll('#service_table tbody tr');

  rows.forEach((row) => {
    const answerStatus = row.children[7].textContent.trim();
    const insertBtn = row.querySelector('.insertBtn');
    const updateBtn = row.querySelector('.updateBtn');

    if (answerStatus === '미답변') {
      insertBtn.disabled = false;
      updateBtn.disabled = true;
    } else if (answerStatus === '답변완료') {
      insertBtn.disabled = true;
      updateBtn.disabled = false;
    }
  });

  const insertModal = document.getElementById('insertModal');
  const updateModal = document.getElementById('updateModal');

  async function openInsertModal(row) {
    if (!insertModal || !row) return;

    const title = insertModal.querySelector('.customer_title');
    const content = insertModal.querySelector('.customer_content');
    const num = row.querySelector('.insertBtn').dataset.num;

    try {
      const response = await fetch(`/barofarm/csDetail?questionNum=${num}`);
      const data = await response.json();

      title.innerText = `제목: ${data.title}`;
      content.innerText = `내용: ${data.content}`;
      insertModal.style.display = 'flex';
    } catch (error) {
      console.log('Insert 모달 에러:', error);
    }

    const insertAnswerBtn = document.getElementById('insertAnswerBtn');
    insertAnswerBtn.addEventListener('click', async () => {
      const answer = insertModal.querySelector('.answer_content').value;

      try {
        const response = await fetch(`/barofarm/insertCSAnswer`, {
          method: "POST",
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            questionNum: num,
            answer: answer
          })
        });

        const result = await response.json();
        if (result.success) {
          alert('답변을 등록하였습니다.');
          insertModal.style.display = 'none';
        } else {
          alert('답변 등록에 실패했습니다.');
        }
      } catch (error) {
        console.log('에러 발생', error);
      }
    });
  }

  async function openUpdateModal(row) {
    if (!updateModal || !row) return;

    const title = updateModal.querySelector('.customer_title');
    const content = updateModal.querySelector('.customer_content');
    const textarea = updateModal.querySelector('.answer_content');
    const num = row.querySelector('.updateBtn').dataset.num;

    try {
      const response = await fetch(`/barofarm/csDetail?questionNum=${num}`);
      const data = await response.json();

      title.innerText = `제목: ${data.title}`;
      content.innerText = `내용: ${data.content}`;
      updateModal.style.display = 'flex';
    } catch (error) {
      console.log('Insert 모달 에러:', error);
    }

    try {
      const response = await fetch(`/barofarm/updateCSAnswer?questionNum=${num}`);
      const data = await response.json();

      textarea.value = data.content;
    } catch (error) {
      console.log(error);
    }

    const updateAnswerBtn = document.getElementById('updateAnswerBtn');
    updateAnswerBtn.addEventListener('click', async () => {
      const answer = updateModal.querySelector('.answer_content').value;

      try {
        const response = await fetch(`/barofarm/updateCSAnswer`, {
          method: "POST",
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            questionNum: num,
            answer: answer
          })
        });

        const result = await response.json();
        if (result.success) {
          alert('답변을 수정하였습니다.');
          updateModal.style.display = 'none';
        } else {
          alert('답변 수정에 실패했습니다.');
        }
      } catch (error) {
        console.log('에러 발생', error);
      }
    });
  }

  function closeModalOnOutsideClick(modal) {
    window.addEventListener('click', (event) => {
      if (event.target === modal) {
        modal.style.display = 'none';
      }
    });
  }

  // 버튼 클릭 이벤트 (이벤트 위임)
  document.querySelector('#service_table').addEventListener('click', (event) => {
    const target = event.target;
    const row = target.closest('tr');

    if (target.classList.contains('insertBtn')) {
      openInsertModal(row);
    } else if (target.classList.contains('updateBtn')) {
      openUpdateModal(row);
    }
  });

  // 모달 바깥 클릭 시 닫기
  closeModalOnOutsideClick(insertModal);
  closeModalOnOutsideClick(updateModal);
});
