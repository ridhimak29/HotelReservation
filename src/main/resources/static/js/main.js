$(document).ready(function () {

    $(function() {
        var u = window.location.href.substr(window.location.href
            .lastIndexOf("/"));
        $("#ul-header li a").each(function() {
            $h = $(this).attr("href");
            if ($h == u || $h == '')
                $(this).addClass("active");
            else
                $(this).removeClass('active');
        })
    });

    $(function(){
        var dtToday = new Date();

        var month = dtToday.getMonth() + 1;
        var day = dtToday.getDate();
        var year = dtToday.getFullYear();
        if(month < 10)
            month = '0' + month.toString();
        if(day < 10)
            day = '0' + day.toString();

        var maxDate = year + '-' + month + '-' + day;

        $('#checkInDate').attr('min', maxDate);
        $('#checkOutDate').attr('min', maxDate);
    });

    $(function (){
        if (getCookie('loginHotelRid') != ''){
            const liTag = document.createElement('li');
            liTag.setAttribute('class', 'nav-item');
            liTag.setAttribute('id', 'logout_li')
            const aTag = document.createElement('a');
            aTag.setAttribute('href', '#');
            aTag.setAttribute('onclick', 'deleteLoginCookie()');
            aTag.setAttribute('class', 'nav-link');
            aTag.innerText = 'Logout';
            liTag.append(aTag);
            const ulTag = document.getElementById('ul-header');
            ulTag.append(liTag);
        }
    });

    $("#search-form").submit(function (event) {
        //stop submit the form, we will post it manually.logout
        event.preventDefault();
        fire_search_ajax_submit();

    });

    $("#login-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_login_ajax_submit();
    });

    $("#signup-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_signup_ajax_submit();
    });

    $("#reservation-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_reservation_ajax_submit();
    })

});

const show_login_form = function (id){
    $('#loginModal').modal({show:true});
    $('#selectedRoomId').val(id)
}

const hide_login_form = function () {
    $('#loginModal').modal('hide');
}

const fire_reservation_ajax_submit = function (){

    const guestInformation = {
        "firstName": $("#guest-firstname").val(),
        "lastName": $("#guest-lastname").val(),
        "email": $("#guest-email").val(),
        "phone": $("#guest-phone").val(),
        "country": $("#guest-country").val(),
        "address": $("#guest-address").val(),
        "address2": $("#guest-address2").val(),
        "zip": $("#guest-zip").val(),
        "city": $("#guest-city").val(),
        "state": $("#guest-state").val()
    }
    const expiryMonth = $("#ccexpirymonth").val()
    const expiryYear = $("#ccexpiryyear").val()
    const payment = {
        "cardName": $("#cardName").val(),
        "cardNumber": $("#cardNumber").val(),
        "expiryDate":`${expiryMonth}/${expiryYear}`,
        "amount": $("#total_amount").val()
    }
    var reservation = {}
    const detialsInStr = $("#details_value").val();
    const detials = detialsInStr.split(':')
    reservation['payment'] = payment;
    reservation['guest'] = guestInformation;
    reservation['customerId'] = detials[0]
    reservation['roomId'] = detials[1]
    reservation['checkInDate'] = detials[2]
    reservation['checkOutDate'] = detials[3]
    reservation['people'] = detials[4]

    $("#btn-confirm").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/reservation",
        data: JSON.stringify(reservation),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#btn-confirm").prop("disabled", false);
            window.location.href=`/confirm?confirmationId=${data.result[0].id}`
        }, error: function (e) {
            $("#btn-confirm").prop("disabled", false);
            $("#reservation-error").text(e.responseText)
        }
    });
}

const fire_signup_ajax_submit = function (){
    var signup = {}
    signup["email"] = $("#signup-email").val();
    signup["password"] = $("#signup-password").val();
    signup["firstName"] = $("#signup-firstname").val();
    signup["lastName"] = $("#signup-lastname").val();

    $("#btn-signup").prop("disabled", true);
    $("#error-messgae").text('')

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/signup",
        data: JSON.stringify(signup),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#btn-signup").prop("disabled", false);
            $("#success-message").text(data.msg)
        },
        error: function (e) {
            $("#btn-signup").prop("disabled", false);
            const json = JSON.parse(e.responseText)
            $("#error-messgae").text(json.message)
        }
    });
}

const fire_login_ajax_submit = function () {

    var login = {}
    login["email"] = $("#email").val();
    login["password"] = $("#password").val();

    $("#btn-login").prop("disabled", true);
    $("#error-messgae").text('')

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/login",
        data: JSON.stringify(login),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#btn-login").prop("disabled", false);
            if (data.msg != 'success'){
                $("#error-messgae").text(data.msg)
            } else {
                const roomId = $("#selectedRoomId").val();
                $("#error-messgae").text('');
                const email = data.result[0].email;
                setCookie('loginHotelRid', email, 1);
                sendToDetailsPage(email, roomId);
            }
        },
        error: function (e) {
            $("#btn-login").prop("disabled", false);
            const json = JSON.parse(e.responseText)
            if (json.error == 'Not Found') {
                $("#error-messgae").text('We donot have any account with this email, can you please create one using signup')
            } else {
                $("#error-messgae").text(json.message)
            }
        }
    });

}


const fire_search_ajax_submit = function () {

    var search = {}
    var people = parseInt($("#adult").val())
    if ($("#child").val() != '') {
        people = people + parseInt($("#child").val())
    }
    search["checkInDate"] = $("#checkInDate").val();
    search["checkOutDate"] = $("#checkOutDate").val();
    search["people"] = people;

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#error-messgae").text('')
            $('#search-result').html(createRooms(data));
            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);
        },
        error: function (e) {
            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
            $('#search-result').html(json);
            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);
        }
    });

}

const checkForms = function () {
    let checkInDate = document.getElementById("checkInDate");
    let checkOutDate = document.getElementById("checkOutDate");

    var checkInDateArray = checkInDate.value.split("-"),
        checkInDateDays = Number.parseInt(checkInDateArray[0]) * 365 + Number.parseInt(checkInDateArray[1]) * 31 + Number.parseInt(checkInDateArray[2]),
        dateNow = new Date(),
        nowDays = dateNow.getFullYear() * 365 + (dateNow.getMonth() + 1) * 31 + dateNow.getDate(),
        diffDate = checkInDateDays - nowDays;

    if (diffDate < 0) {
        window.alert( "Check in Date is invalid!");
        return false;
    }

    var checkOutDateArray = checkOutDate.value.split("-"),
        checkOutDateDays = Number.parseInt(checkOutDateArray[0]) * 365 + Number.parseInt(checkOutDateArray[1]) * 31 + Number.parseInt(checkOutDateArray[2]);
    diffDate = checkOutDateDays - checkInDateDays;

    if (diffDate < 0) {
        window.alert( "Check in date is after check out date, please correct!");
        return false;
    }

    return true;
}

const setCookie = function (cname, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    let expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

const getCookie = function (cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

const deleteLoginCookie = function (){
    document.cookie = 'loginHotelRid=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    $("#logout_li").remove();
    window.location.href = '/';
}

const sendToDetailsPage = function (email, roomId){
    const checkInDate = $("#checkInDate").val();
    const checkOutDate = $("#checkOutDate").val();
    var people = parseInt($("#adult").val())
    if ($("#child").val() != '') {
        people = people + parseInt($("#child").val())
    }
    window.location.href=`/details?checkInDate=${checkInDate}&checkOutDate=${checkOutDate}&people=${people}&roomId=${roomId}&email=${email}`
}

const createRooms = function (data){
    const outerDiv = document.createElement('div')
    outerDiv.setAttribute('class', ' row mt-4 margin-left-10px')
    data.result.forEach(room => outerDiv.append(createRoom(room, data.auth)))
    return outerDiv;
}

const createRoom = function (room, auth){
    const mainDiv = document.createElement('div')
    mainDiv.setAttribute('class', 'class="col-md-4 on-hover room-width margin-all')
    const roomDiv = document.createElement('div')
    roomDiv.setAttribute('class', 'card border-0 mb-4')

    const aTag = document.createElement('a')
    aTag.setAttribute('href', '#')
    const imgTag = document.createElement('img')
    imgTag.setAttribute('class', 'card-img-top event-img-height')
    imgTag.setAttribute('src', 'images/'+ room.image)
    imgTag.setAttribute('alt',  room.roomName)
    aTag.append(imgTag)

    const h5Tag = document.createElement('h5')
    h5Tag.setAttribute('class', 'font-weight-medium mt-3 margin-all')
    const h5ATag = document.createElement('a')
    h5ATag.setAttribute('href', '#')
    h5ATag.setAttribute('class', 'text-decoration-none link')
    h5ATag.innerHTML = room.roomName;
    h5ATag.setAttribute('id', 'room-name-'+ room.id)
    h5Tag.append(h5ATag)
    const spanTag = document.createElement('span')
    spanTag.setAttribute('class', 'float-right')
    spanTag.innerHTML = '$' + room.price;
    spanTag.setAttribute('id', 'room-price-'+ room.id)
    h5Tag.append(spanTag)

    const buttonDiv = document.createElement('div')
    const buttonTag = document.createElement('button')
    buttonTag.setAttribute('class', 'btn btn-primary btn-sm float-left margin-all')
    buttonTag.setAttribute('type', 'button')
    buttonTag.setAttribute('id', 'reserve-'+ room.id)
    buttonTag.innerHTML = "Reserve"
    const email = getCookie('loginHotelRid');
    if (email != '') {
        buttonTag.setAttribute('onclick', 'sendToDetailsPage("' + email + '", '+room.id+')')
    } else {
       buttonTag.setAttribute('onclick', 'show_login_form(' + room.id + ')')
    }
    buttonDiv.append(buttonTag)

    roomDiv.append(aTag)
    roomDiv.append(h5Tag)
    roomDiv.append(buttonDiv)

    mainDiv.append(roomDiv)
    return mainDiv;
}

