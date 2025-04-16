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

  const modals = {
    insert: insertModal,
    update: updateModal
  };

  async function getModalContent(type, row) {
    const modal = modals[type];
    if (!modal || !row) return;

    const title = modal.querySelector('.customer_title');
    const content = modal.querySelector('.customer_content');
    const num = row.querySelector(`.${type}Btn`).dataset.num;

    try {
      const response = await fetch(`/barofarm/csDetail?questionNum=${num}`);
      const data = await response.json();

      title.innerText = `제목: ${data.title}`;
      content.innerText = `내용: ${data.content}`;
      modal.style.display = 'flex';

      return num;
    } catch (error) {
      console.log(`${type} 모달 에러:`, error);
      return null;
    }
  }

  async function submitAnswer(url, questionNum, answer) {
    try {
      const response = await fetch(url, {
        method: "POST",
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ questionNum, answer })
      });
      return await response.json();
    } catch (error) {
      console.error("에러 발생", error);
      return { success: false };
    }
  }

  function resetButtonListener(buttonId, handler) {
    const oldBtn = document.getElementById(buttonId);
    const newBtn = oldBtn.cloneNode(true);
    oldBtn.replaceWith(newBtn);
    newBtn.addEventListener('click', handler);
  }

  async function openInsertModal(row) {
    const num = await getModalContent("insert", row);

    resetButtonListener('insertAnswerBtn', async () => {
      const answer = insertModal.querySelector('.answer_content').value;
      const result = await submitAnswer(`/barofarm/insertCSAnswer`, num, answer);

      if (result.success) {
        alert('답변을 등록하였습니다.');
        insertModal.style.display = 'none';
      } else {
        alert('답변 등록에 실패했습니다.');
      }
    });
  }

  async function openUpdateModal(row) {
    const num = await getModalContent("update", row);
    const textarea = updateModal.querySelector('.answer_content');

    try {
      const response = await fetch(`/barofarm/updateCSAnswer?questionNum=${num}`);
      const data = await response.json();
      textarea.value = data.content;
    } catch (error) {
      console.log(error);
    }

    resetButtonListener('updateAnswerBtn', async () => {
      const answer = textarea.value;
      const result = await submitAnswer(`/barofarm/updateCSAnswer`, num, answer);

      if (result.success) {
        alert('답변을 수정하였습니다.');
        updateModal.style.display = 'none';
      } else {
        alert('답변 수정에 실패했습니다.');
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

  document.querySelector('#service_table').addEventListener('click', (event) => {
    const target = event.target;
    const row = target.closest('tr');

    if (target.classList.contains('insertBtn')) {
      openInsertModal(row);
    } else if (target.classList.contains('updateBtn')) {
      openUpdateModal(row);
    }
  });

  closeModalOnOutsideClick(insertModal);
  closeModalOnOutsideClick(updateModal);
});