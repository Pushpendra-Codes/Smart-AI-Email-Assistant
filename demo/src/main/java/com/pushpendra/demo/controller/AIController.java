package com.pushpendra.demo.controller;

import com.pushpendra.demo.model.EmailRequest;
import com.pushpendra.demo.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")


public class AIController{

    @Autowired
    private AIService service;


    @PostMapping("/generate-reply")
    public String getReply(@RequestBody EmailRequest request,EmailRequest tone){
        return service.getReply(request.getEmailText(),tone.getTone());
    }
}


