package com.example.liquibasedemo.services;

import com.example.liquibasedemo.dto.MessageDTO;
import com.example.liquibasedemo.entity.Dialog;
import com.example.liquibasedemo.entity.Message;
import com.example.liquibasedemo.repository.DialogRepository;
import com.example.liquibasedemo.repository.MessageRepository;
import com.example.liquibasedemo.repository.UserRepository;
import com.example.liquibasedemo.services.interfaces.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final DialogRepository dialogRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, DialogRepository dialogRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.dialogRepository = dialogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<MessageDTO> showAllMessagesDialog(int idDialog) {
        Dialog dialog = dialogRepository.getById(idDialog);
        List<Message> messages = dialog.getMessage();
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message :
                messages) {
            messageDTOS.add(new MessageDTO(message));
        }
        return messageDTOS;
    }

    @Transactional
    @Override
    public void addMessageToDialog(int idDialog, int idUser, String content) {
        Message message = new Message(
                content, userRepository.getById(idUser), dialogRepository.getById(idDialog));
        messageRepository.save(message);
    }
}
