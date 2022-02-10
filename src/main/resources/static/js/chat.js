$(function (){
    let initApplication = function ()
    {
        $('.messages_and_users').css({display: 'flex'});
        $('.controls').css({display: 'flex'});
    //    TODO: init events
    };

    let registerUser = function (name)
    {
        $.post('/auth', {name: name}, function (response) {
            if (response.result){
                initApplication();
            }

        });
    };
    $.get('/init', {}, function (response) {
        if (!response.result) {
            let name = prompt('Insert your name: ');
            registerUser(name);
        }
        else {
            initApplication();
        }
    });
});