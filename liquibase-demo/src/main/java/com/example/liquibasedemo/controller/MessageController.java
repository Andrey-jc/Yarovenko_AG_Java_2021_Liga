package com.example.liquibasedemo.controller;


import com.example.liquibasedemo.dto.MessageDTO;
import com.example.liquibasedemo.services.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageServiceImpl messageService;

    @Autowired
    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages/dialog{id}")
    public void addMessage(@PathVariable("id") int idDialog, @RequestParam("idUser") int idUser, @RequestParam("content") String content) {
        messageService.addMessageToDialog(idDialog, idUser, content);
    }

    @GetMapping("/messages/dialog{id}")
    public List<MessageDTO> showAllMessageDialog(@PathVariable("id") int idDialog) {
        return messageService.showAllMessagesDialog(idDialog);
    }
}
