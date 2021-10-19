package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.dto.DialogDTO;
import com.example.liquibasedemo.services.DialogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DialogController {

    private final DialogServiceImpl dialogService;

    @Autowired
    public DialogController(DialogServiceImpl dialogService) {
        this.dialogService = dialogService;
    }

    @GetMapping("/dialogs")
    public List<DialogDTO> showAllDialog() {
        return dialogService.showAllDialog();
    }

    @PostMapping("/dialogs/user{id}")
    public void createDialog(@PathVariable("id") int idUserFor, @RequestParam int idUserTo, @PathVariable String id) {
        dialogService.createDialog(idUserFor, idUserTo);
    }

    @GetMapping("/dialogs/{id}")
    public DialogDTO getDialog(@PathVariable("id") int idDialog, @PathVariable String id) {
        return dialogService.showCurrentDialog(idDialog);
    }

    @DeleteMapping("/dialogs/{id}")
    public void deleteDialog(@PathVariable("id") int idDialog) {
        dialogService.deleteDialog(idDialog);
    }
}
