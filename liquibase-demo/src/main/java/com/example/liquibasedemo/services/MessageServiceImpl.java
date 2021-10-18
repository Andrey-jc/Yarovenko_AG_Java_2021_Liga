package com.example.liquibasedemo.services;

import com.example.liquibasedemo.entity.Message;
import com.example.liquibasedemo.repository.MessageRepository;
import com.example.liquibasedemo.services.interfaces.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    @Override

    public List<Message> showAllMessagesDialog(int idDialog) {
        return null;
    }

    @Override
    public void addMessageToDialog(int idDialog, int idUser, String content) {

    }
}
