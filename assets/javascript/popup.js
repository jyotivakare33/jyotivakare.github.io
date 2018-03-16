$(".dropbtn").click(function(){
  $(".popup").fadeIn(300);
});

$('.close').click(function() {
   $(".content").fadeOut(300);
});

$('body').click(function() {
    $(".content").hide();
});

$('.content').click(function(e) {
    e.stopPropagation();
});
