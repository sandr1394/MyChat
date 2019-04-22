package com.belyaev.service;

import com.belyaev.dao.DialogRepo;
import com.belyaev.entity.Dialog;
import com.belyaev.exception.DialogNotFoundException;
import com.belyaev.exception.UserNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DialogService {

    private DialogRepo dialogRepo;
    private UserDialogLinkService userDialogLinkService;
    static Logger logger = Logger.getLogger(DialogService.class);

    @Autowired
    public DialogService(DialogRepo dialogRepo, UserDialogLinkService userDialogLinkService) {
        this.dialogRepo = dialogRepo;
        this.userDialogLinkService = userDialogLinkService;
    }

    @Transactional
    public Dialog createDialog(Long userId, LocalDateTime timeDateOfCreation) {
        logger.info("Method 'createDialog' invocation");
        Dialog dialog = new Dialog();
        dialog.setTimeDateOfCreation(timeDateOfCreation);
        Dialog dialogLink = dialogRepo.save(dialog);
        userDialogLinkService.createLink(userId, dialogLink.getId());
        return dialogLink;
    }

    public Dialog getDialogById(Long dialogId) {
        logger.info("Method 'getDialogById' invocation");
        Dialog dialog = dialogRepo.findById(dialogId).orElse(null);
        if (dialog == null) {
            logger.warn("Can't find dialog with ID: " + dialogId);
            throw new DialogNotFoundException("Can't find dialog with ID: " + dialogId, dialogId);
        } else return dialog;
    }

    public void deleteDialog(Long dialogId) {
        logger.info("Method 'deleteDialog' invocation");
        dialogRepo.deleteById(dialogId);
    }

    @Transactional
    public List<Dialog> getAllDialogsOfUser(Long userId) {
        logger.info("Method 'getAllDialogsOfUser' invocation");
        List<BigInteger> listOfDialogsId = userDialogLinkService.getDialogIdByUserId(userId);
        if (listOfDialogsId.isEmpty()) {
            logger.warn("Can't find user with ID: " + userId);
            throw new UserNotFoundException("Can't find user with ID: " + userId, userId);
        } else {
            List<Dialog> listOfDialogs = new ArrayList<>();
            for (int i = 0; i < listOfDialogsId.size(); i++) {
                listOfDialogs.add(dialogRepo.findById(listOfDialogsId.get(i).longValue()).orElse(null));
            }
            return listOfDialogs;
        }
    }
}

