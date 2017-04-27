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
        url: '/add-to-cart',
        data: {'id': id},
        success: function () {
            $("#cart-count").text(parseInt($("#cart-count").html()) - 1);
        }
    });
}

function plusAmount(id) {
    $("#amount-count").val(parseInt($("#amount-count").val()) + 1);
    addToCart(id);
}

function minusAmount(id) {
    
}

function showLoginModal() {
    $(".modal-body").empty();

    $(".modal-body").append(loginHmtl());

}

$(function() {
  $( "#login" ).submit(function( event ) {
  event.preventDefault();
  showLoginModal();
  return false;
});
});

function loginHmtl() {
    var html='\
    <form id="loginForm" method="post" action="/login"> \
        <input type="text" name="username" value="" required>\
        <input type="password" name="password" value="" required>\
        <input type="submit" value="LOGIN">\
    </form>';
    return html;

}
