$(document).ready(function() {

    var locationPathname = $(location).attr('pathname');

    if (locationPathname == "/task-1") {

    };
    if (locationPathname == "/task-2") {
        submitUUID();
    };
    if (locationPathname == "/task-3") {
        submitBrowserRestet();
    };
    if (locationPathname == "/task-4") {
        popularUsers();
    };
    if (locationPathname == "/task-5") {
        alikeBook();
        alikeUser();
    };
});

function submitUUID() {
    $('#submit-uuid').click(function() {
        getAjaxBookUUID($('#input-uuid').val());
    });
}

function submitBrowserRestet() {
    $('#submit-browser').click(function() {
        getAjaxBrowsers();
    });
}

function popularUsers() {
    $('#submit-number').click(function() {
        getAjaxPopularUsers($('#numbers').val());
    });
}

function alikeBook() {
    $('#submit-book').click(function() {
        book_uuid = $('input#book_uuid').val();
        getAjaxBookUUIDAlike(book_uuid);
    });
}
function alikeUser() {
    $('#submit-user').click(function() {
        user_uuid = $('input#user_uuid').val();
        getAjaxUserUUIDAlike(user_uuid);
    });
}

var prevAjaxReturnedUUID = true;
var xhrUUID = null;

function getAjaxBookUUID(uuid) {

    if (prevAjaxReturnedUUID) {
        prevAjaxReturnedUUID = false;
    } else if (xhrUUID) {
        xhrUUID.abort();
    }

    xhrUUID = $.ajax({
        type: "POST",
        data: {
            uuid: uuid,
        },
        url: '/api/task-2',
        beforeSend: function() {
            $('.image-ajax').attr('src', '/static/images/ajax-loader.gif');
        },
        dataType: 'json',
        success: function(json) {
            $('#error-message-uuid').text("");
            if (json.hasOwnProperty('exception')) {
                $('#error-message-uuid').text(json.exception + " Try Again, with diffrent UUID");
            } else {
                $('#book-country-plot').attr('src', '/static/results/countries_to_book_' + uuid + '.png');
                $('#book-continent-plot').attr('src', '/static/results/continent_to_book_' + uuid + '.png');
            }
            prevAjaxReturnedUUID = true;
        }
    });
};



var prevAjaxBrowsers = true;
var xhrBrowsers = null;

function getAjaxBrowsers() {

    if (prevAjaxBrowsers) {
        prevAjaxBrowsers = false;
    } else if (xhrBrowsers) {
        xhrBrowsers.abort();
    }

    xhrBrowsers = $.ajax({
        type: "POST",
        url: '/api/task-3',
        beforeSend: function() {
            $('.image-ajax').attr('src', '/static/images/ajax-loader.gif');
        },
        dataType: 'json',
        success: function(json) {
            console.log(json)
            $('#error-message').text("");
            if (json.hasOwnProperty('exception')) {
                $('#error-message').text(json.exception);
            } else {
                $('#browser-usage').removeAttr("src").attr("src", "static/results/simple_browser_usage.png");
                $('#browser-general').removeAttr("src").attr("src", "static/results/general_browser_usage.png");
            }
            prevAjaxBrowsers = true;
        }

    });
};


var prevAjaxUserPopular = true;
var xhrUsers = null;

function getAjaxPopularUsers(numbers) {

    if (prevAjaxUserPopular) {
        prevAjaxUserPopular = false;
    } else if (xhrUsers) {
        xhrUsers.abort();
    }
    xhrUsers = $.ajax({
        type: "POST",
        data: {
            "numbers": numbers
        },
        url: '/api/task-4',
        beforeSend: function() {
            $('.image-ajax').attr('src', '/static/images/ajax-loader.gif');
        },
        dataType: 'json',
        complete: function(json) {
            $('#error-message').text("");
            console.log(json);
            if (json.responseText.indexOf('exception') > 1) {
                $('#error-message').text(json.responseText);
            } else {
                makeUserTable(json);
            }
            prevAjaxUserPopular = true;
        }
    });
};

function makeUserTable(users) {
    userData = JSON.parse(users.responseText.replace(/\bNaN\b/g, "null")).succesfully;

    $('tbody').html("");
    $.each(userData, function(key, val) {

        $('tbody').append('<tr id=' + key + '>');

        $.each(val.visitor_ip, function(prop, val) {
            $('#' + key).append('<th>' + val + '</th>');
        });
        $.each(val.visitor_country, function(prop, val) {
            $('#' + key).append('<th>' + val + '</th>');
        });
        $.each(val.visitor_device, function(prop, val) {
            $('#' + key).append('<th>' + val + '</th>');
        });
        $.each(val.visitor_useragent, function(prop, val) {
            $('#' + key).append('<th>' + val.substring(0,20) + '</th>');
        });
        $.each(val.visitor_uuid, function(prop, val) {
            $('#' + key).append('<th>' + val + '</th>');
        });
        $('#' + key).append('<th>' + val.total_time + '</th>');

        $('tbody').append('</tr>');
    });
}


var prevAjaxReturnedUUID = true;
var xhrUUID = null;

function getAjaxBookUUIDAlike(uuid) {
    if (prevAjaxReturnedUUID) {
        prevAjaxReturnedUUID = false;
    } else if (xhrUUID) {
        xhrUUID.abort();
    }

    xhrUUID = $.ajax({
        type: "POST",
        data: {
            book_uuid: uuid,
        },
        url: '/api/task-5a',
        dataType: 'json',
        success: function(json) {
            $('#error-message-book').text("");
            $('#book_data-like').text("")

            if (json.hasOwnProperty('exception') || json.alike_sorted.length == 0 ) {
                $('#error-message-book').text(json.exception + " Try Again, with diffrent UUID");
            } else {
                var jsonPrettyLike = JSON.stringify(json.book_alike, null, 2);
                var jsonPrettySort = JSON.stringify(json.alike_sorted, null, 2);
            
                $('#book-data-like').text(jsonPrettyLike);
                $('#book-data-sorted').text(jsonPrettySort);
            }
            prevAjaxReturnedUUID = true;
        }
    });
};



var prevAjaxReturnedUUID = true;
var xhrUUID = null;

function getAjaxUserUUIDAlike(uuid) {
    if (prevAjaxReturnedUUID) {
        prevAjaxReturnedUUID = false;
    } else if (xhrUUID) {
        xhrUUID.abort();
    }

    xhrUUID = $.ajax({
        type: "POST",
        data: {
            user_uuid: uuid,
        },
        url: '/api/task-5b',
        dataType: 'json',
        success: function(json) {
            $('#error-message-user').text("");
            if (json.hasOwnProperty('exception') || json.alike_sorted.length == 0 ) {
                $('#error-message-user').text(json.exception + " Try Again, with diffrent UUID");
            } else {
                var jsonPrettyLike = JSON.stringify(json.user_alike, null, 2);
                var jsonPrettySort = JSON.stringify(json.alike_sorted, null, 2);
            
                $('#user-data-like').text(jsonPrettyLike);
                $('#user-data-sorted').text(jsonPrettySort);

            }
            prevAjaxReturnedUUID = true;
        }
    });
};
