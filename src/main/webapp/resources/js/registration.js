$('.form').find('input, textarea').on('keyup blur focus', function (e) {
    var $this = $(this),
        label = $this.prev('label');

    if (e.type === 'keyup') {
        if ($this.val() === '') {
            label.removeClass('active highlight');
        } else {
            label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
        if ($this.val() === '') {
            label.removeClass('active highlight');
        } else {
            label.removeClass('highlight');
        }
    } else if (e.type === 'focus') {

        if ($this.val() === '') {
            label.removeClass('highlight');
        }
        else if ($this.val() !== '') {
            label.addClass('highlight');
        }
    }

});

$('.tab').on('click', function (e) {

    e.preventDefault();

    $(this).addClass('active');
    $(this).siblings().removeClass('active');

    var target = $(this).attr('href');

    $('.tab-switcher > div').not(target).hide();


    $(target).fadeIn(600);
});

$('#extra').on('click', function (e) {
    var moreValue = "More";
    var lessValue = "Less";

    target = $('#extra-fields');
    var value = $(this).text().toLowerCase();

    switch (value) {
        case moreValue.toLowerCase():
            $(target).fadeIn(600);
            $(this).text(lessValue)
            break;
        case lessValue.toLowerCase():
            $(this).text(moreValue)
            $(target).fadeOut(500);
            break;
    }
});

$('#registerBtn').on('click', function (e) {
    var login = $.trim($('#regLogin').val());
    var password = $.trim($('#regPassword').val());
    var name = $.trim($('#regName').val());

    var email = $.trim($('#regEmail').val());
    var address = $.trim($('#regAddress').val());
    var notes = $.trim($('#regNotes').val());
    var organization = $.trim($('#regOrganization').val());
    var phone = $.trim($('#regPhone').val());

    if (login === '') {
        checkEmpty($('#regLogin'));
        return;
    }
    if (password === '') {
        checkEmpty($('#regPassword'));
        return;
    }

    if (name === '') {
        checkEmpty($('#regName'));
        return;
    }
    var userData = {
        "userLogin": login,
        "userPassword": password,
        "userName": name,
        "userPhoneField": phone,
        "userEmailField": email,
        "userOrganizationField": organization,
        "userAddressField": address,
        "userNotesField": notes
    };
    $.ajax({
        contentType: 'application/json',
        url: '/user/',
        type: 'post',
        success: function (data) {
            $('#loginTab').click();
        },
        error: function (data) {
            show2sec($('#alreadyExists'))
        },
        data: JSON.stringify(userData)
    });
});

$('#loginForm').on('submit', function (e) {
    e.preventDefault();
    var login = $.trim($('#logLogin').val());
    var password = $.trim($('#logPassword').val());


    if (login === '') {
        checkEmpty($('#logLogin'));
        return;
    }
    if (password === '') {
        checkEmpty($('#logPassword'));
        return;
    }

    var userData = {
        "j_username": login,
        "j_password": password
    };
    $.ajax({
        contentType: 'application/x-www-form-urlencoded',
        url: '/j_spring_security_check',
        type: 'post',
        statusCode: {
            200: function() {
                window.location.replace("/main");
            },
            401: function() {
                show2sec($('#incorrectCredentials'));
            }
        },
        data: $('#loginForm').serialize()
    });
});

function checkEmpty(el) {
    var $el = el,
        x = 450,
        originalColor = $el.css("background");

    $el.css("background", "#ff1a1a");
    setTimeout(function () {
        $el.css("background", originalColor);
    }, x);
}

function show2sec(el){
    if (el) {
        el.show().delay(2000).fadeOut();
    }
}


