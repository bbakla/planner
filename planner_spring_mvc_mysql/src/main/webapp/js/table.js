$(function () {
    $("#dragdiv a").draggable({
//		appendTo : "body",
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
                //accept : ":not(.ui-sortable-helper)",

                over: function (event, ui) {
                    var $this = $(this);
                },
                drop: function (event, ui) {
                    $("<td></td>").text(ui.draggable.text()).appendTo(this)

                    cleanContent(ui);
                },
            });
    }

    function cleanContent(ui){
        var id = $(ui.draggable).attr('id');
        id = '#' + id;

        var draggedElement = $(id);
        if (draggedElement.is("td")) {
            $(id).html("");
        }
    }
});


