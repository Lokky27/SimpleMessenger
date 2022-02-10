package com.lokky.SimpleMessenger.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MessageController
{

    //TODO:
    @GetMapping
    public HashMap<String, Boolean> init()
    {
        HashMap<String, Boolean> response = new HashMap<>();
        response.put("result", true);
        //TODO: check sessionId. If found => true, if not => false
        return response;
    }

    @PostMapping("/auth")
    public HashMap<String, Boolean> auth(String name)
    {
        HashMap<String, Boolean> response = new HashMap<>();
//        TODO:
//        -create user with name? sessionId
//        -save user to DB
        response.put("result", true);
        return response;
    }
    @PostMapping("/message")
    public Boolean sendMessage(@RequestParam String message)
    {
        return true;
    }

    @GetMapping("/message")
    public List<String> getMessagesList()
    {
        return null;
    }

    @GetMapping("/user")
    public HashMap<Long, String> getUsersList()
    {
        return null;
    }
}
