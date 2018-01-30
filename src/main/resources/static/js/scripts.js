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
    var templete = answerTemplete.format(data.writer.name, data.convertTime, data.contents, data.question.id, data.id);
    $(".qna-comment-count strong").text(parseInt($(".qna-comment-count strong").text()) + 1)
    $(".qna-comment-slipp-articles").prepend(templete);
    $(".answer-write textarea").val("");
}

$(".qna-comment-slipp-articles").on("click", ".link-delete-article", deleteAnswer)

function deleteAnswer(e) {
    e.preventDefault();
    let deleteButton = $(this);
    let url = $(this).attr("href");

    $.ajax({
        type: 'delete',
        url: url,
        dataType: 'json',
        error: function(xhr, error) {
            console.log(error)
        },
        success: function(data, status) {
            if (data.deleted) {
                deleteButton.closest("article").remove();
                $(".qna-comment-count strong").text(parseInt($(".qna-comment-count strong").text()) - 1)
            }
        }
    })
}