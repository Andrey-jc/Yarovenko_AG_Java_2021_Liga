package com.example.liquibasedemo.services.interfaces;

import com.example.liquibasedemo.dto.MessageDTO;

import java.util.List;

public interface MessageService {

    List<MessageDTO> showAllMessagesDialog(int idDialog);

    void addMessageToDialog(int idDialog, int idUser, String content);

}
