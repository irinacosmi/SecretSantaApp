$('document').ready(function(){
    $('a[data-toggle="tooltip"]').tooltip({
        animated: 'fade',
        placement: 'bottom',
        html: true
    });

    $(document).ready(function(){
        $('[data-toggle="popover"]').popover();
    })
});

