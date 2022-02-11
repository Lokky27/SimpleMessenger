package com.lokky.SimpleMessenger.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MessageDto
{
    private String datetime;

    private String text;

    private String username;
}
