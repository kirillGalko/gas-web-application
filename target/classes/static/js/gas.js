$(function(){

    let initApplication = function()
    {
        $('.post-data').on('click', function()
        {
            let current = $('.create-item').val();
            $.post('/api/new', {current: current}, function(response){
                if(response.result) {
                    $('.create-item').val('');
                } else {
                    alert('Ошибка :( Повторите попытку позже');
                }
            });
        });
    };

});