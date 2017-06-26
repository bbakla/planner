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
                    var goalId = ui.draggable.text();
                    var goalName = $(ui.draggable).children()[1].innerHTML;
                    goalName = goalName.replace(goalId, "").trim();
                    var id = goalId.replace(goalName, "").trim();

                    $(this)[0].children[0].innerHTML = id;
                    $(this)[0].children[1].innerHTML = goalName;
                    
                    var hiddenLabelId = ($(ui.draggable).children()[0]).id;
                    var nameLabelId = ($(ui.draggable).children()[1]).id;
                  
                    var dragged = $($(ui.draggable).children()[1]).is("a.ui-droppable");

                    cleanContent(dragged, hiddenLabelId, nameLabelId);
                }
            });
    }

    function cleanContent(dragged, hiddenLabelId, nameLabelId) {
        
    	if(!dragged){
        	hiddenLabelId = '#' + hiddenLabelId;
        	nameLabelId = '#' + nameLabelId;
        	
        	$(nameLabelId)[0].innerHTML = "";
        	$(hiddenLabelId)[0].innerHTML = "";

    	}
    }
});


