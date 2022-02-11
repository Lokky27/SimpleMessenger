$(function (){
    let getMessageElement = function (message) {
        let item = $('<div class="message-item"></div>');
        let header = $('<div class="message_header"></div>');
        header.append($('<div class="datetime">' + message.dateTime + '</div>'));
        header.append($('<div class="username">' + message.username + '</div>'));
        let textElement = $('<div class="message_text"></div>')
        item.append(header, textElement.text(message.text));
        return item;
    };

    let updateMessages = function () {
        $.get('/message', {}, function (response) {
            if (response.length == 0) {
                return;
            }
            $('.messages_list').html('');
            for (i in response)
           {
               let element = getMessageElement(response[i])
               $('.messages_list').append(element);
           }
        });
    };

    let initApplication = function ()
    {
        $('.messages_and_users').css({display: 'flex'});
        $('.controls').css({display: 'flex'});

        $('.send_message').on('click', function () {
            let message = $('.new_message').val();
            $.post('/message', {message: message}, function (response) {
                if (response.result)
                {
                    $('.new_message').val('');
                }
                else
                {
                    alert('Error. Try again')
                }
            });
        });

        setInterval(updateMessages, 1000);
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