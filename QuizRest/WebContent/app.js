$(document).ready(function(){
    console.log('Document Loaded');
    loadQuizes();
});

var loadQuizes = function(){
    $('#content').empty();
    $.ajax({
        type: 'GET',
        url: 'api/quizzes',
        dataType: "json"
    })
    .done(function(data, status){
        buildDom(data);
    })
    .fail(function(xhr, status, error){
        console.error("Error");
    });
};

var buildDom = function(data){
	console.log(data);
    //set up table header
    var table = $('<table>');
    var head = $('<thead>');
    var trh = $('<tr>');
    var th1 = $('<th>');
    var th2 = $('<th>');
    th1.text('Quiz Name');
    th2.text('View');
    trh.append(th1);
    trh.append(th2);
    head.append(trh);
    table.append(head);
    var body = $('<tbody>');
    data.forEach(function(value, index, arr){
        var trb = $('<tr>');
        var td1 = $('<td>');
        var td2 = $('<td>');
        td1.text(value.name);
        td2.text('View');
        td2.attr('id',value.id);
        td2.click(function(){
            var id = $(this).attr('id');
            console.log(id);
        });
        trb.append(td1);
        trb.append(td2);
        body.append(trb);
        });
    table.append(body);
    $('#content').append(table);
};
