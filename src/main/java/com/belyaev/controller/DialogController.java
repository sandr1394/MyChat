package com.belyaev.controller;

import com.belyaev.controller.dto.DialogDTO;
import com.belyaev.entity.Dialog;
import com.belyaev.service.DialogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Api(description = "Controller which provides methods for operations with dialogs")
public class DialogController {

    private DialogService dialogService;
    private static Logger logger = Logger.getLogger(DialogController.class);

    @Autowired
    public DialogController(DialogService dialogService) {
        this.dialogService = dialogService;
    }

    @ApiOperation(value = "Method for dialog creation", response = Dialog.class)
    @RequestMapping(value = "/chat/dialog", method = RequestMethod.POST)
    public Dialog createNewDialog(@Valid @RequestBody DialogDTO dialogDTO) {
        logger.info("Method 'createNewDialog' invocation. Input object - 'Dialog' with properties( userID: " + dialogDTO.getUserId() +
                "; timeDateOfCreation: " + dialogDTO.getTimeDateOfCreation() + " )");
        return dialogService.createDialog(dialogDTO.getUserId(), dialogDTO.getTimeDateOfCreation());
    }

    @ApiOperation(value = "Method for getting dialog by ID", response = Dialog.class)
    @RequestMapping(value = "/chat/dialog/{id}", method = RequestMethod.GET)
    public Dialog getDialogById(@NotNull @PathVariable Long id) {
        logger.info("Method 'getDialogById' invocation. Input parameter - 'DialogID' with value: " + id);
        return dialogService.getDialogById(id);
    }

    @ApiOperation(value = "Method for getting all dialogs of one user", response = Dialog.class)
    @RequestMapping(value = "/chat/get-dialogs-by-userid/{id}", method = RequestMethod.GET)
    public List<Dialog> getAllDialogsByOneUser(@NotNull @PathVariable Long id) {
        logger.info("Method 'getAllDialogsByOneUser' invocation. Input parameter - 'UserID' with value: " + id);
        return dialogService.getAllDialogsOfUser(id);
    }

    @ApiOperation(value = "Method for dialog deleting")
    @RequestMapping(value = "/chat/dialog/delete/{id}", method = RequestMethod.DELETE)
    public void deleteDialog(@NotNull @PathVariable Long id) {
        logger.info("Method 'deleteDialog' invocation. Input parameter - 'DialogID' with value: " + id);
        dialogService.deleteDialog(id);
    }
}