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
    $("#amount-count").val(parseInt($("#amount-count").val()) + 1);
    addToCart(id);
    $("#total-price").load();
}

function minusAmount(id) {
    $("#amount-count").val(parseInt($("#amount-count").val()) - 1);
    if (parseInt($("#amount-count").val()) <= 0) {
        deleteItem(id);
    }
    removeFromCart(id);
    $("#total-price").load();
}

function deleteItem(id) {
    $("#shopping-cart-"+id.toString()).empty().remove();
    $("#shopping-cart-row-"+id.toString()).empty().remove();
    deleteFromCart(id);
    $("#total-price").load();
}