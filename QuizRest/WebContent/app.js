$(document).ready(function(){
    console.log('Document Loaded');
    loadQuizes();
});

var loadQuizes = function(){
    $.ajax({
        type: 'GET',
        url: 'api/quizzes',
        dataType: "json"
    })
    .done(function(data, status){
        buildDomTable(data);
    })
    .fail(function(xhr, status, error){
        console.error("Error");
    });
};

var buildDomTable = function(data){
    $('#content').empty();
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
        var button = $('<button>');
        button.text('View');
        button.attr('id',value.id);
        button.click(function(){
            var id = $(this).attr('id');
            displaySingleQuiz(id);
        });
        td2.append(button);
        trb.append(td1);
        trb.append(td2);
        body.append(trb);
        });
    table.append(body);
    $('#content').append(table);
    var newButton = $('<button>');
    newButton.text('New Quiz');
    newButton.click(function(){
        console.log('in New Quiz');
        buildNewQuizForm();
    });
    $('#content').append(newButton);
};

var displaySingleQuiz = function(id){
    $.ajax({
        type: 'GET',
        url: 'api/quizzes/' + id,
        dataType: "json"
    })
    .done(function(data, status){
        buildDomOneQuiz(data);
    })
    .fail(function(xhr, status, error){
        console.error("Error");
    });
};

var buildDomOneQuiz = function(data){
    console.log(data);
    $('#content').empty();
    var h1 = $('<h1>');
    h1.text(data.name);
    $('#content').append(h1);
    var ul = $('<ul>');
    var questions = data.questions;
    console.log(questions);
    questions.forEach(function(question, index, arr){
        var li = $('<li>');
        console.log(question);
        li.text(question.questionText);
        ul.append(li);
    });
    $('#content').append(ul);
    var qbutton = $('<button>');
    qbutton.attr('id', 'listQuizzes');
    qbutton.text('List Quizzes');
    qbutton.click(loadQuizes);
    $('#content').append(qbutton);
    $('#content').append('<br>');
    var ebutton = $('<button>');
    ebutton.attr('id', data.id);
    ebutton.text('Edit Quiz');
    ebutton.click(function(){
        editQuiz(this.id)
    });
    $('#content').append(ebutton);
    $('#content').append('<br>');
    var dbutton = $('<button>');
    dbutton.attr('id', data.id);
    dbutton.text('Delete Quiz');
    dbutton.click(function(){
        deleteQuiz(this.id)
    });
    $('#content').append(dbutton);
};

var editQuiz = function(id){
    console.log("in editQuiz");
    editQuizForm(id);
};

var deleteQuiz = function(id){
    console.log("in deleteQuiz:" + id);
    $.ajax({
        type: 'DELETE',
        url: 'api/quizzes/' + id,
        })
        .done(function(data, status){
            loadQuizes();
        })
        .fail(function(xhr, status, error){
            console.error("Error");
        });
};

var buildNewQuizForm = function(){
    $('#content').append('<br>');
    var form = $('<form>');
    form.attr('name', 'newQuiz');
    var input = $('<input>');
    input.attr('type', 'text');
    input.attr('name', 'quizName');
    input.attr('placeholder', 'New Quiz Name');
    var submit = $('<input>');
    submit.attr('type', 'submit');
    submit.attr('name', 'submit');
    submit.attr('value', 'Create');
    submit.click(function(e) {
        e.preventDefault();
        createNewQuiz();
    });
    form.append(input);
    form.append(submit);
    $('#content').append(form);
};

var createNewQuiz = function(){
    var newOne = {
        name: $(newQuiz.quizName).val()
    }
    $.ajax({
        type: 'POST',
        url: 'api/quizzes',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(newOne)
        })
        .done(function(data, status){
            loadQuizes();
        })
        .fail(function(xhr, status, error){
            console.error("Error");
        });
};

var editQuizForm = function(id){
    $('#content').append('<br>');
    var form = $('<form>');
    form.attr('name', 'editQuiz');
    var input = $('<input>');
    input.attr('type', 'text');
    input.attr('name', 'quizName');
    input.attr('id', 'quizName');
    input.attr('placeholder', 'Quiz Name');
    var submit = $('<input>');
    submit.attr('type', 'submit');
    submit.attr('name', 'submit');
    submit.attr('value', 'Submit');
    submit.click(function(e) {
        e.preventDefault();
        submitEditQuiz(id);
    });
    form.append(input);
    form.append(submit);
    $('#content').append(form);
};

var submitEditQuiz = function(id) {
    console.log(id);
    console.log($('#quizName').val());
    var editedOne = {
        id: id,
        name: $('#quizName').val()
    };
    console.log(editedOne);
    $.ajax({
        type: 'PUT',
        url: 'api/quizzes/' + id,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(editedOne)
    })
    .done(function(data, status){
        displaySingleQuiz(id);
    })
    .fail(function(xhr, status, error){
        console.error("Error");
    });
};
