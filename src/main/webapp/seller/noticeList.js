$(document).ready(function () {
  $('#notie_table').DataTable({
    // columns: [
    //   { data: 'name', title: 'Name' },
    //   { data: 'position', title: 'Position' },
    //   { data: 'office', title: 'Office' },
    //   { data: 'extn', title: 'Extn.' },
    //   { data: 'startDate', title: 'Start date' },
    //   { data: 'salary', title: 'Salary' },
    // ],
    colReorder: true,
    responsive: true,
    scrollX: true,
  });
});
