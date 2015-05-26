$( document ).ready(function() {

    var sessionsListContainer = $("#sessions-list-container");

    $.getJSON( "/api/public/sessions", function( data ) {
      var items = [];
        $.each( data, function( idx, item ) {
            items.push( "<li class='list-group-item' id='" + item.id + "'><a href='#' class='btn btn-info pull-right'>Join</a><strong>" + item.sessionName + "</strong> <br/> <span>Session details...</span></li>" );
        });

        $( "<ul/>", {
            "class": "list-group",
            html: items.join( "" )
        }).appendTo( sessionsListContainer );
    });

});