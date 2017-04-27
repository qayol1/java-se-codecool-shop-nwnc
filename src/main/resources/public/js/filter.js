/**
 * Created by peter on 2017.04.26..
 */

$( document ).ready(function() {
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    $("#filterbutton").click(function (e) {
        var elem = document.getElementById('filterList');
        if (elem.style.visibility !== "visible") {
            elem.style.visibility= 'visible';
        } else {
            elem.style.visibility= 'hidden';
        }

    });
    $("#shoppingchartbutton").click(function (e) {
        hideFilter();
    });
});

function setSuppliersChecked() {
    $('input:checkbox.supplier').each(function(){
        $(this).prop('checked',true);
    });
}

function setSuppliersUnchecked() {
    $('input:checkbox.supplier').each(function(){
        $(this).prop('checked',false);
    });
}

function setCatergoryChecked() {
    $('input:checkbox.category').each(function(){
        $(this).prop('checked',true);
    });
}

function setCategoryUnchecked() {
    $('input:checkbox.category').each(function(){
        $(this).prop('checked',false);
    });
}

function filter(categoryid) {
    hideFilter();
    $.post("/getCategoryListSize", function (data) {
        for (i = 1; i < data+1; i++) {
            if (i != categoryid) {
                $("#category"+i).hide();
            }
        }
    });
}

function hideFilter() {
    var elem = document.getElementById('filterList');
    elem.style.visibility= 'hidden';
}

