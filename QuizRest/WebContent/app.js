$(document).ready(function(){
    console.log('Document Loaded');
    //$('#content').append("Testing");
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
    data.forEach(function(value, index, arr){
        $('#content').append(value.quiz);
    });
};
