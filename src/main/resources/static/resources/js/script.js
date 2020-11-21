$(".dropdown-toggle").dropdown();

$('.dropdown-menu').on( 'click', 'a', function() {
    var text = $(this).html();
    var htmlText = text + ' <span class="caret"></span>';
    $(this).closest('.dropdown').find('.dropdown-toggle').html(htmlText);
});