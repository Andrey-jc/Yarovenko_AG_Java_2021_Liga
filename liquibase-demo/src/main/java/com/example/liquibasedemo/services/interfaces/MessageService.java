package com.example.liquibasedemo.services.interfaces;

import com.example.liquibasedemo.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> showAllMessagesDialog(int idDialog);

    void addMessageToDialog(int idDialog, int idUser, String content);
}
