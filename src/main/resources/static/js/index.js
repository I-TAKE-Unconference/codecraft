$( document ).ready(function() {

    var sessionsListContainer = $("#sessions-list-container");

    $.getJSON( "/api/public/sessions", function( data ) {
      var items = [];
        $.each( data, function( idx, item ) {
            items.push( "<li class='list-group-item' id='" + item.id + "'>" +
            createJoinButton(item) +
            createDescription(item) +
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

        if(items.length == 0){
            $("<div/>", {
                        "class" : "alert alert-info",
                        "html" : "There are no open sessions to join"
                        }).appendTo( sessionsListContainer );
        }
    });

    function createDescription(item) {
        var description = "<strong>" + item.sessionName + "</strong> created by <strong>" + item.creator.userName + "</strong>";

        if(typeof(item.participant) != "undefined" && item.participant != null)
            description += " and <strong>" + item.participant.userName + "</strong> is participating";

        description += " <br/> "

        return description;
    }

    function createJoinButton(item) {
        if(!item.participant)
            return "<a href='/join/" + item.id + "' class='btn btn-info pull-right btn-join'>Join</a>"
        else
            return "<span class='pull-right'><i class='glyphicon glyphicon-lock'></i></span>";
    }

});

function niceDate(timestamp) {
    var myDate = new Date(timestamp);

    return myDate.toDateString();
}
