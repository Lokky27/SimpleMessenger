package com.lokky.SimpleMessenger.service;

import com.lokky.SimpleMessenger.dto.MessageDto;
import com.lokky.SimpleMessenger.models.Message;

public class ConverterDtoToModel
{
    public static MessageDto convertMessageToDto(Message message)
    {
        return MessageDto.builder().text(message.getMessage()).
                datetime(message.getDateTime().toString()).
                username(message.getUser().getName()).
                build();
    }
}
