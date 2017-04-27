/**
 * Created by peter on 2017.04.27..
 */
$( document ).ready(function() {
    var creditcardmodal = document.getElementById('creditcardModal');
    var paypalmodal = document.getElementById('paypalModal');
    var creditcardbtn = document.getElementById("creditcard");
    var paypalbtn = document.getElementById("paypal");
    var closecreditcard = document.getElementById("closecredit");
    var closepaypal = document.getElementById("closepaypal");
    creditcardbtn.onclick = function () {
        creditcardmodal.style.display = "block";
    }
    paypalbtn.onclick = function () {
        paypalmodal.style.display = "block";
    }
    closecreditcard.onclick = function () {
        creditcardmodal.style.display = "none";
    }
    closepaypal.onclick = function () {
        paypalmodal.style.display = "none";
    }
});
