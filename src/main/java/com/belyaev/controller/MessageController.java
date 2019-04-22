package com.belyaev.controller;

import com.belyaev.controller.dto.MessageDTO;
import com.belyaev.entity.Message;
import com.belyaev.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Api(description = "Controller which provides methods for operations with messages")
public class MessageController {

    private MessageService messageService;
    private static Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ApiOperation(value = "Method for message creation", response = Message.class)
    @RequestMapping(value = "/chat/message", method = RequestMethod.POST)
    public Message createNewMessage(@Valid @RequestBody MessageDTO messageDTO) {
        logger.info("Method 'createNewMessage' invocation. Input object - 'Message' with properties( dialogID: " + messageDTO.getDialogId() +
                "; userID: " + messageDTO.getText() + "; timeDateOfCreation: " + messageDTO.getTimeDateOfMessageCreation() + " )");
        return messageService.createMessage(messageDTO.getDialogId(), messageDTO.getText(), messageDTO.getUserId(), messageDTO.getTimeDateOfMessageCreation());
    }

    @ApiOperation(value = "Method for getting all messages", response = Message.class)
    @RequestMapping(value = "chat/message/get-by-dialog-id/{dialogId}", method = RequestMethod.GET)
    public List<Message> getAllMessages(@NotNull @PathVariable Long dialogId) {
        logger.info("Method 'getAllMessages' invocation. Input parameter - 'DialogID' with value: " + dialogId);
        return messageService.getAllMessagesByDialogId(dialogId);
    }

}
