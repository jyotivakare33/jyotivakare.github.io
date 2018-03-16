for(var i = 0; i < 36; ++i)
    $('#myTable > tbody').append('<tr><td>' + i + '</td><td>' + i.toString(16) + '</td></tr>');
  $('#myTable').paginate({
    limit: 10,
    initialPage: 2
  });
