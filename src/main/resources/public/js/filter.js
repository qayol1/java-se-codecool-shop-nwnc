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

