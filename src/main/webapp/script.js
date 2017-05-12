$(document).ready(function() {
    $('#todoList').hide();
    getTodos();
    getAll();
    getActive();
    getCompleted();
    deleteCompleted();

    });

function addTodo() {
        var todoName = document.getElementById("todo");
        var todoValue = todoName.value;
        $.ajax({
            url: "/todo",
            type: 'POST',
            data: todoValue,
            success: function (data) {
                var newTodo = JSON.parse(data);
                $("#todoList").append("<div id= 'div" + newTodo.id + "' class='active'><input type='checkbox' class='squaredFour' name = 'checkbox' value = '" + newTodo.active + "' id='" + newTodo.id + "'>" + newTodo.name + "</div>");
                $('#div' + newTodo.id).hide();
                $('#div' + newTodo.id).fadeIn(500);
                checkBox();
            }
        });

}

function getTodos(){
    $('.fadeOnLoad').hide();
    $.ajax({
        url: "/todo",
        type: 'GET',
        success: function (data) {
            $.each(JSON.parse(data), function (index, element) {
                if (element.active == true) {
                    $("#todoList").append("<div id= 'div" + element.id + "' class='active'><input type='checkbox' name = 'checkbox' value = '" + element.active + "' id='" + element.id + "'>" + element.name + " </div>");
                }
                else {
                    $("#todoList").append("<div id= 'div" + element.id + "' class='completed'><input type='checkbox' checked=checked name = 'checkbox' value = '" + element.active + "' id='" + element.id + "'>" + element.name + "</div>");
                }
            });
            checkBox();
        }

    });
    $('.fadeOnLoad').fadeIn(500);
}

function getAll(){
    $('#all').click(function () {
        $('#todoList').empty();
        getTodos();

    });
}

function getActive(){
    $('#active').click(function () {
        $('.completed').hide();
        $('.active').hide();
        $('.active').fadeIn(500);

    });

}

function getCompleted(){
    $('#completed').click(function () {
        $('.active').hide();
        $('.completed').hide();
        $('.completed').fadeIn(500);

    });

}

function deleteCompleted(){
    $('#delete').click(function () {
        $.ajax({
            url: "/todo",
            type: 'GET',
            success: function (data) {
                var deleteList = [];
                $.each(JSON.parse(data), function (index, element) {
                    if (element.active == false) {

                        var deleted = new Object();
                        deleted.name = this.name;
                        deleted.id = this.id;
                        deleted.active = false;
                        deleteList.push(deleted);

                    }
                });
                $.ajax({
                    url: "/delete",
                    type: 'POST',
                    data: JSON.stringify(deleteList)

                });
                location.reload();
            }
        });

    });
}

function checkBox() {
    $('input[type="checkbox"]').click(function () {
        var checked = new Object();
        checked.name = this.name;
        checked.id = this.id;
        checked.active = this.checked;
        if(this.checked == true){

            $('#div' + this.id).removeClass("active").addClass("completed") ;
            $('#div' + this.id).show();
        }
        if (this.checked == false){
            $('#div' + this.id).removeClass("completed").addClass("active");
            $('#div' + this.id).show();
        }

        $.ajax({
            url: "/upstatus",
            type: 'POST',
            data: JSON.stringify(checked)

        });

    });
}
