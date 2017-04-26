/**
 * Created by peter on 2017.04.26..
 */
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
    $.post("/getCategoryListSize", function (data) {
        for (i = 1; i < data+1; i++) {
            if (i != categoryid) {
                $("#category"+i).hide();
            }
        }
    });
}

function openNav() {
    document.getElementById("mySidenav").style.width = "200px";
    //document.getElementById("boards_main").style.marginLeft = "250px";
}
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    //document.getElementById("boards_main").style.marginLeft= "150px";
}

