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