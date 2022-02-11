package com.lokky.SimpleMessenger.controllers;

import com.lokky.SimpleMessenger.dto.MessageDto;
import com.lokky.SimpleMessenger.models.Message;
import com.lokky.SimpleMessenger.models.User;
import com.lokky.SimpleMessenger.repositories.MessageRepo;
import com.lokky.SimpleMessenger.repositories.UserRepo;
import com.lokky.SimpleMessenger.service.ConverterDtoToModel;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MessageController
{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MessageRepo messageRepo;
    //TODO:
    @GetMapping
    public HashMap<String, Boolean> init()
    {
        HashMap<String, Boolean> response = new HashMap<>();
        Map<String, Boolean> result = new HashMap<>();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        Optional<User> userOptional = userRepo.findUserBySessionId(sessionId);
        response.put("result", userOptional.isPresent());
        return response;
    }

    @PostMapping("/auth")
    public HashMap<String, Boolean> auth(String name)
    {
        HashMap<String, Boolean> response = new HashMap<>();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = new User();
        user.setName(name);
        user.setSessionId(sessionId);
        userRepo.save(user);
        response.put("result", true);
        return response;
    }
    @PostMapping("/message")
    public Map<Object, Object> sendMessage(@RequestParam String message)
    {
        if (Strings.isEmpty(message))
        {
            return Map.of("result", false);
        }
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = userRepo.findUserBySessionId(sessionId).get();
        Message message1 = new Message();
        message1.setDateTime(LocalDateTime.now());
        message1.setUser(user);
        message1.setMessage(message);
        messageRepo.save(message1);
        return Map.of("result", true);
    }

    @GetMapping("/message")
    public List<MessageDto> getMessagesList()
    {
        return messageRepo.findAll(Sort.by(Sort.Direction.ASC, "datetime")).
                stream().
                map(message -> ConverterDtoToModel.convertMessageToDto(message)).
                collect(Collectors.toList());
    }

    @GetMapping("/user")
    public HashMap<Long, String> getUsersList()
    {
        return null;
    }
}
