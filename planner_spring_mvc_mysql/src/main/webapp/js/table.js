$(function () {
    $("#dragdiv li").draggable({
        helper: "clone",
        cursor: "move",

    });
    $("#dropdiv table tbody td").draggable({
        cursor: "move",
        appendTo: "body",
        helper: "clone",

    });

    $("#dragdiv a").droppable({
        cursor: "move",
        drop: function (event, ui) {
            //ui: element being dragged
            //this: element being dropped into

            cleanContent(ui);

        },
        over: function (event, ui) {
            var $this = $(this);

        },
    });

    initDroppable($("#dropdiv table td"));

    function initDroppable($elements) {
        $elements
            .droppable({
                activeClass: "ui-state-default",
                hoverClass: "ui-drop-hover",
                over: function (event, ui) {
                    var $this = $(this);
                },
                drop: function (event, ui) {
                    var goalName = ui.draggable.text();
                    var id = $(ui.draggable).children()[1].innerHTML;
                    goalName = goalName.replace(id, "").trim();

                    $("<td></td>").text(goalName).appendTo(this);
                    $(this)[0].children[0].innerHTML = id;

                    cleanContent(ui);
                }
            });
    }

    function cleanContent(ui) {
        var id = $(ui.draggable).prop('id');
        id = '#' + id;

        var draggedElement = $(id);
        if (draggedElement.is("td")) {
            $(id).html("");
        }
    }
});


