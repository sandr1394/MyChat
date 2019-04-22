package com.belyaev.service;

import com.belyaev.dao.MessageRepo;
import com.belyaev.entity.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private MessageRepo messageRepo;
    static Logger logger = Logger.getLogger(MessageService.class);

    @Autowired
    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Message createMessage(Long dialogId, String text, Long userId, LocalDateTime timeDateOfMessageCreation) {
        logger.info("Method 'createMessage' invocation");
        Message message = new Message();
        message.setDialogId(dialogId);
        message.setText(text);
        message.setUserId(userId);
        message.setTimeDateOfMessageCreation(timeDateOfMessageCreation);
        messageRepo.save(message);
        return message;
    }

    public List<Message> getAllMessagesByDialogId(Long dialogId) {
        logger.info("Method 'getAllMessagesByDialogId' invocation");
        return messageRepo.findByDialogId(dialogId);
    }
}
