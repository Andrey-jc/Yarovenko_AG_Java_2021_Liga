package com.example.liquibasedemo.services.interfaces;

import com.example.liquibasedemo.dto.DialogDTO;
import com.example.liquibasedemo.entity.Dialog;

import java.util.List;

public interface DialogService {

    List<DialogDTO> showAllDialog();

    DialogDTO showCurrentDialog(int idDialog);

    void createDialog(int idUserFor, int idUserTo);

    void deleteDialog(int idDialog);
}
