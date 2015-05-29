function validateField(id) {
    if ($("#" + id).val() == null || $("#" + id).val() == "") {
        var errorDiv = $("#" + id).closest("div");
        errorDiv.addClass("has-error");
        return false;
    }
    else {
        var successDiv = $("#" + id).closest("div");

        successDiv.removeClass("has-error");
        return true;
    }
}

function checkFormValidity(){

    var invalidItems = 0;

    if (!validateField("sessionName")) {
        $("#sessionName").focus();
        invalidItems++;
    }
    if (!validateField("atTime")) {
        $("#atTime").focus();
        invalidItems++;
    }
    if (!validateField("sessionDuration")) {
        $("#sessionDuration").focus();
        invalidItems++;
    }
    if (!validateField("sessionLocation")) {
        $("#sessionLocation").focus();
        invalidItems++;
    }
    if (!validateField("sessionProgrammingLanguage")) {
        $("#sessionProgrammingLanguage").focus();
        invalidItems++;
    }
    if (!validateField("whatToPractice")) {
        $("#whatToPractice").focus();
        invalidItems++;
    }

    return invalidItems <= 0;
}

function saveSession() {
    if(checkFormValidity()) {
        var pairingSession = {};
        pairingSession.sessionName = $("#sessionName").val();
        pairingSession.dateAsString = $("#atTime").val();
        pairingSession.duration = $("#sessionDuration").val();
        pairingSession.location = $("#sessionLocation").val();
        pairingSession.language = $("#sessionProgrammingLanguage").val();
        pairingSession.practice = $("#whatToPractice").val();

        $.ajax({
            type: "POST",
            url: '/api/session/add',
            data: JSON.stringify(pairingSession),
            beforeSend: function (xhr) {
                xhr.setRequestHeader('X-CSRF-Token', $('#csrfToken').val())
            },
            success: function () {
                window.location.href = "/";
            },
            contentType: 'application/json'
        });
    }
}




