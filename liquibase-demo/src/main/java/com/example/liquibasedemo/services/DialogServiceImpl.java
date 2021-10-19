package com.example.liquibasedemo.services;

import com.example.liquibasedemo.dto.DialogDTO;
import com.example.liquibasedemo.entity.Dialog;
import com.example.liquibasedemo.entity.Message;
import com.example.liquibasedemo.entity.User;
import com.example.liquibasedemo.repository.DialogRepository;
import com.example.liquibasedemo.repository.UserRepository;
import com.example.liquibasedemo.services.interfaces.DialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class DialogServiceImpl implements DialogService {

    private final DialogRepository dialogRepository;
    private final UserRepository userRepository;

    @Autowired
    public DialogServiceImpl(DialogRepository dialogRepository, UserRepository userRepository) {
        this.dialogRepository = dialogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<DialogDTO> showAllDialog() {
        List<Dialog> dialogList = dialogRepository.findAll();
        List<DialogDTO> dialogDTO = new ArrayList<>();
        for (Dialog dialog :
                dialogList) {
            dialogDTO.add(new DialogDTO(dialog));
        }
        return dialogDTO;
    }

    @Override
    public DialogDTO showCurrentDialog(int idDialog) {
        Dialog byId = dialogRepository.getById(idDialog);
        DialogDTO dialogDTO = new DialogDTO(byId);
        return dialogDTO;
    }

    @Override
    public void createDialog(int idUserFor, int idUserTo) {
        User userFor = userRepository.getById(idUserFor);
        User userTo = userRepository.getById(idUserTo);
        List<Message> messageList = new ArrayList<>();
        Dialog dialog = new Dialog(userFor, userTo, messageList);
        dialogRepository.save(dialog);

    }

    @Override
    public void deleteDialog(int idDialog) {
        dialogRepository.deleteById(idDialog);
    }
}
