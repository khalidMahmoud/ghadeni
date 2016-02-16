$(document).ready(function(){
    $(".navItem").click(function(){
       $(this).addClass("borderBottom").siblings().removeClass("borderBottom");
    });
    $(".reg").click(function(){
       $(".logIn") .fadeOut(1000);
       $(".register").fadeIn(1000);
       
    });
    $(".log").click(function(){
       $(".register").fadeOut(1000);
        $(".logIn") .fadeIn(1000);
    });
    $(".redBack").click(function (){
       $(".hiddenApplyOffer").slideToggle();
    });
});