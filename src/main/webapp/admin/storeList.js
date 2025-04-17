$(document).ready(function () {
  $('#store_table').DataTable({
    colReorder: true,
    responsive: true,
    scrollX: true,
  });
});

$(document).ready(function () {
  const table = $('#store_table').DataTable();

  // 검색 조건 추가
  $.fn.dataTable.ext.search.push(function (settings, data, dataIndex) {
    const subscribe = data[7]; // 8번째 열
    const filterOn = $('#farm-sub-only').is(':checked');

    if (!filterOn) return true; // 필터 안 쓰면 전체 보여줌
    return subscribe === 'O';
  });

  // 체크박스에 이벤트 연결
  $('#farm-sub-only').on('change', function () {
    table.draw();
  });
});

$(document).ready(function () {
  const table = $('#store_table').DataTable();

  // 검색 조건 추가
  $.fn.dataTable.ext.search.push(function (settings, data, dataIndex) {
    const subscribe = data[8];
    const filterValue = $('#search_by_column').val();

    if (!filterValue) return true; // 선택 안 했으면 전체 표시

    if (filterValue === 'sub') return subscribe === 'O'; // 구독자만
    if (filterValue === 'not_sub') return subscribe !== 'O'; // 미구독자만

    return true; // 그 외는 전체
  });

  // 체크박스에 이벤트 연결
  $('#search_by_column').on('change', function () {
    table.draw();
  });
});
