/**
 * Created by peter on 2017.04.26..
 */

$(document).ready(function () {
    setShoppingCartCount();

    $('#cart-items').fadeIn("slow");
    $(".thumbnail").fadeIn("slow");


    $('#checkb').change(function () {
        if (this.checked) {
            $('#shipping').css("display", "none");
            $('.sh1').removeAttr("required");
        }
        else
            $('#shipping').css("display", "inline");

    });
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    $("#filterbutton").click(function (e) {
        var elem = document.getElementById('filterList');
        if (elem.style.visibility !== "visible") {
            elem.style.visibility = 'visible';
        } else {
            elem.style.visibility = 'hidden';
        }

    });

    if ($(".logout").length) {
        console.log("van");
        $(".loginPart").removeAttr("data-toggle")
    } else {
        $(".loginPart").attr("data-toggle", "modal")
    }


    $("#shoppingchartbutton").click(function (e) {
        hideFilter();
    });

    handleLoggedUser();


});

function handleLoggedUser() {
    $.ajax({
        method: 'POST',
        url: '/isuserlogged',
        success: function (data) {
            if (data != "nulluser") {
                $(".loginPart").empty();
                $(".loginPart").append("<a href='/logout'><span class='btn filtermenu loginPart logout' >LogOut</span></a>");
                $(".registerPart").empty();
                $(".registerPart").append("<div>" + data + "</div>");
                $(".loginPart").removeAttr("data-toggle")
            }
        }
    });
}

function setFilterCategoryChecked(param1) {

    $('input:checkbox.' + param1).each(function () {
        $(this).prop('checked', true);
    });
}

function setFilterCategoryUnchecked(param1) {
    $('input:checkbox.' + param1).each(function () {
        $(this).prop('checked', false);
    });
}

function setAmount(id) {
    var num = parseInt($("#amount-count" + id).val());
    $.ajax({
        method: 'POST',
        url: '/set-amount',
        data: {'id': id, 'num': num},
        success: function () {
            $("#cart-count").text(parseInt($("#cart-count").html()) - 1);
        }
    });
}

function filter(categoryid) {
    hideFilter();
    $.post("/getCategoryListSize", function (data) {
        for (i = 1; i < data + 1; i++) {
            if (i != categoryid) {
                $("#category" + i).hide();
            }
        }
    });
}

function hideFilter() {
    var elem = document.getElementById('filterList');
    elem.style.visibility = 'hidden';
}

function addToCart(id) {
    $.ajax({
        method: 'POST',
        url: '/add-to-cart',
        data: {'id': id},
        success: function () {
            $("#cart-count").text(parseInt($("#cart-count").html()) + 1);
        }
    });
}

function removeFromCart(id) {
    $.ajax({
        method: 'POST',
        url: '/remove-from-cart',
        data: {'id': id},
        success: function () {
            $("#cart-count").text(parseInt($("#cart-count").html()) - 1);
        }
    });
}


function deleteFromCart(id) {
    $.ajax({
        method: 'POST',
        url: '/delete-from-cart',
        data: {'id': id}
    });
}

function plusAmount(id) {
    $("#amount-count" + id).val(parseInt($("#amount-count" + id).val()) + 1);
    addToCart(id);
    setTotalPrice();
}

function minusAmount(id) {
    $("#amount-count" + id).val(parseInt($("#amount-count" + id).val()) - 1);
    if (parseInt($("#amount-count" + id).val()) <= 0) {
        deleteItem(id);
    }
    removeFromCart(id);
    setTotalPrice();
}

function setShoppingCartCount() {
    $.ajax({
        method: 'POST',
        url: '/get-shoppingcart-size',
        success: function (number) {
            $("#cart-count").text(number);
        }
    })
}


function deleteItem(id) {
    $("#shopping-cart-" + id.toString()).empty().remove();
    $("#shopping-cart-row-" + id.toString()).empty().remove();
    setTotalPrice();
    deleteFromCart(id);
}

function setTotalPrice() {
    var total = 0;
    $('#cart-items tr').each(function () {
        //the value of sum needs to be reset for each row, so it has to be set inside the row loop
        //find the combat elements in the current row and sum it
        var price = parseFloat($(this).find('#price').text());
        var amount = parseFloat($(this).find('.counter').val());
        if (!isNaN(price) && !isNaN(amount)) {
            total += parseFloat(price) * parseFloat(amount);
        }
    });
    //set the value of currents rows sum to the total-combat element in the current row
    $('#total-price-price').text(total + ' USD');
}

function validateForm() {
    var fisrt_name = document.forms["customerInput"]["first_name"].value;
    var last_name = document.forms["customerInput"]["last_name"].value;
    var email = document.forms["customerInput"]["email"].value;
    var phone = document.forms["customerInput"]["phone"].value;
    var billingCountry = document.forms["customerInput"]["billingCountry"].value;
    var billingAddress = document.forms["customerInput"]["billingAddress"].value;
    var billingCity = document.forms["customerInput"]["billingCity"].value;
    var billingZipcode = document.forms["customerInput"]["billingZipcode"].value;
    var shippingCountry = document.forms["customerInput"]["shippingCountry"].value;
    var shippingAddress = document.forms["customerInput"]["shippingAddress"].value;
    var shippingCity = document.forms["customerInput"]["shippingCity"].value;
    var shippingZipcode = document.forms["customerInput"]["shippingZipcode"].value;

    var letters = /^[A-Za-z]+$/;
    var numbers = /^[0-9]+$/;
    var number_and_letter = /^[A-Za-z0-9]+$/;
    var email_regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var phone_regex = /^[\+]?[0-9]+$/;

    if (!fisrt_name.match(letters)) {
        alert("Invalid First Name");
        return false;
    }
    if (!last_name.match(letters)) {
        alert("Invalid Last Name");
        return false;
    }
    if (!email.match(email_regex)) {
        alert("Invalid Email.");
        return false;
    }
    if (!phone.match(phone_regex)) {
        alert("Invalid phone number.");
        return false;
    }
    if (!billingCountry.match(letters)) {
        alert("Invalid billing Country name");
        return false;
    }
    if (!billingAddress.match(number_and_letter)) {
        alert("Invalid billing Address");
        return false;
    }
    if (!billingCity.match(letters)) {
        alert("Invalid billing City name");
        return false;
    }
    if (!billingZipcode.match(number_and_letter)) {
        alert("Invalid billing Zipcode");
        return false;
    }
    if (!shippingCountry.match(letters) && shippingCountry !== "") {
        alert("Invalid shipping Country");
        return false;
    }
    if (!shippingAddress.match(number_and_letter) && shippingAddress !== "") {
        alert("Invalid shipping Address");
        return false;
    }
    if (!shippingCity.match(letters) && shippingCity !== "") {
        alert("Invalid shipping City");
        return false;
    }
    if (!shippingZipcode.match(number_and_letter) && shippingZipcode !== "") {
        alert("Invalid shippping Zipcode");
        return false;
    }
}
