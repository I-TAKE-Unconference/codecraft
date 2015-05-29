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
    });

    $("#sessions-list-container").on("click", ".btn-join", function() {
        var joinBtn = this;

        $.ajax({
            type: "POST",
            url: "/api/public/session/" + $(this).attr("data-id") + "/join",
            beforeSend: function(xhr) {xhr.setRequestHeader('X-CSRF-Token', $('#csrfToken').val())},
            success: function() {
                $(joinBtn).remove();
            },
            contentType: 'application/json'
        });
    });

    function createDescription(item) {
        var description = "<strong>" + item.sessionName + "</strong> created by <strong>" + item.creator.userName + "</strong>";

        if(typeof item.participant == "object")
            description += " and " + item.participant.userName + " is participating";

        description += " <br/> "

        return description;
    }

    function createJoinButton(item) {
        if(!item.participant)
            return "<a href='/join/" + item.id + "' class='btn btn-info pull-right btn-join'>Join</a>"
        else
            return "";
    }

});

function niceDate(timestamp) {
    var myDate = new Date(timestamp);

    return myDate.toDateString();
}
