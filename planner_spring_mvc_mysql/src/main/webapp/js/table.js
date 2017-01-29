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
        	console.log("dfdsf");
            cleanContent(ui);
        },
        over: function (event, ui) {
            var $this = $(this);
            console.log("over droppable as");
        },
    });
    
    	


        initDroppable($("#dropdiv table td"));
//	backDroppable($("#dragdiv li"));

    function initDroppable($elements) {
        $elements
            .droppable({
                activeClass: "ui-state-default",
                hoverClass: "ui-drop-hover",
                //accept : ":not(.ui-sortable-helper)",

                over: function (event, ui) {
                    var $this = $(this);
                    console.log("over droppable table")
                },
                drop: function (event, ui) {
                	console.log("droppable of table");
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


