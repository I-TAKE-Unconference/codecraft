$(function() {
    var sessionsListContainer = $("#sessions-list-container table");

    $.getJSON( "/api/public/sessions/calendar", function( data ) {
        var items = [];


        $.each( data, function( idx, item ) {
            items.push(createTableRow(item));
        });

        console.log(items.join( "\n" ));

        sessionsListContainer.append(items.join( "" ));
    });


    function createTableRow(item) {

         var line = "<tr>";
         line += "<td>" + niceDate(item.atTime) + "</td>";
         line += "<td>" + item.duration + "</td>";
         line += "<td>" + item.location + "</td>";
         line += "<td>" + item.language + "</td>";
         line += "<td>" + item.practice + "</td>";
         line += "<td>" + item.creator.userName + "</td>";
         line += "<td>" + (item.participant ? item.participant.userName : "") + "</td>";
         line += "</tr>";

         return line;
    }
});

function niceDate(timestamp) {
    var myDate = new Date(timestamp);

    return myDate.toDateString();
}
