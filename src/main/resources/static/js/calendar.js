$(function() {
    var sessionsListContainer = $("#sessions-list-container table tbody");

    $.getJSON( "/api/public/sessions/calendar", function( data ) {
      var items = [];
        $.each( data, function( idx, item ) {
            items.push(createTableRow(item);
        });

        sessionsListContainer.html(items.join( "" ));
    });


    function createTableRow(item) {

         var line = "<tr>";
         line += "<td>" + niceDate(item.atTime) + "</td>";
         line += "<td>" + item.duration + "</td>";
         line += "<td>" + item.language + "</td>";
         line += "<td>" + item.practice + "</td>";
         line += "<td>" + partner(item) + "</td>";
         line += "</tr>";
    }
});

function partner(item) {
    return + "/" + ;
}

function niceDate(timestamp) {
    var myDate = new Date(timestamp);

    return myDate.toDateString();
}
