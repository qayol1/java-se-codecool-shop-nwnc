/**
 * Created by Péter on 2017.04.26..
 */
$("#checkAllCategory").click(function(){
    console.log("Here")
    $('input:checkbox').not(this).prop('checked', this.checked);
});
