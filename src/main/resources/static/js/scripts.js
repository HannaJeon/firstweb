String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(".answer-write button[type=submit]").click(addAnswer);

function addAnswer(e) {
    e.preventDefault();

    var queryString = $(".answer-write").serialize();
    console.log(queryString)
    var url = $(".answer-write").attr("action");
    console.log(url)
    $.ajax({
        type: 'post',
        url: url,
        data: queryString,
        dataType: 'json',
        error: onERROR,
        success: onSUCCESS});
}

function onERROR(error) {
    console.log(error)
}

function onSUCCESS(data, status) {
    var answerTemplete = $("#answerTemplate").html();
    var templete = answerTemplete.format(data.writer.name, data.convertTime, data.contents, data.id, data.id);
    $(".qna-comment-slipp-articles").prepend(templete);
    $(".answer-write textarea").val("");
}