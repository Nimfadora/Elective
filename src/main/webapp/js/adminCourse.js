$(document).ready(function() {

    $("tr").click(function (e) {
        var tr = e.currentTarget;
        var id = $(tr).attr('id');
        var name = $(id + " :nth-child(1)").value;
        console.log(name);
        $("#coursename").val($(tr).children().eq(0).text());
        $("#duration").val(parseInt($(tr).children().eq(1).text()));
        $("#topic :contains(\""+$(tr).children().eq(2).text()+"\")").attr("selected", "selected");
        $("#tutor :contains(\""+$(tr).children().eq(3).text()+"\")").attr("selected", "selected");
        $("#status").val($(tr).children().eq(4).text());
        $(".identifier").attr("value", id);
    });

    $("#create").click(function(){
        submitForm("/admin/courses/create");
    });
    $("#delete").click(function(){
        submitForm("/admin/courses/delete");
    });
    $("#update").click(function(){
        submitForm("/admin/courses");
    });

    function submitForm(url){
        $("#topicId").val($("#topic option:selected").attr('id'));
        $("#tutorId").val($("#tutor option:selected").attr('id'));
        $("form").attr("action", url).submit();
    }
});