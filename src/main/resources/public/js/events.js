function addToCart(id) {
    $.ajax({
        method: 'POST',
        url: '/add-to-cart',
        data: {'id': id},
        success: function () {
            alert("Item added!");
            console.log(parseInt($("#cart-count").html()) + 1);
        }
    });

}