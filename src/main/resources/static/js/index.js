$( document ).ready(function() {

    var sessionsListContainer = $("#sessions-list-container");

    $.getJSON( "/api/public/sessions", function( data ) {
      var items = [];
        $.each( data, function( idx, item ) {
            items.push( "<li class='list-group-item' id='" + item.id + "'>" +
            "<a href='#' class='btn btn-info pull-right btn-join' data-id='"+item.id+"'>Join</a>" +
            "<strong>" + item.sessionName + "</strong> created by <strong>" + item.creator.userName + "</strong> <br/> " +
            "<strong>"+ niceDate(item.atTime) +"</strong>" + " <span>for</span> " +
            "<strong>"+item.duration+"</strong>" + " <span>in</span> " +
            "<strong>"+item.location+"</strong>" + " <span>coding in </span>  " +
            "<strong>"+item.language+"</strong>" + " <span>and practicing</span> " +
            "<strong>"+item.practice+"</strong>" +
            "</li>" );
        });

        $( "<ul/>", {
            "class": "list-group",
            html: items.join( "" )
        }).appendTo( sessionsListContainer );
    });

    $("#sessions-list-container").on("click", ".btn-join", function() {
        var joinBtn = this;

        $.post("/api/public/session/" + $(this).attr("data-id") + "/", function() {
            $(joinBtn).remove();
        });
    });

});

function niceDate(timestamp) {
    var myDate = new Date(timestamp);

    return myDate.toDateString();
}