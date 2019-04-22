package com.belyaev.service;

import com.belyaev.dao.UserDialogLinkRepo;
import com.belyaev.entity.UserDialogLink;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserDialogLinkService {

    private UserDialogLinkRepo userDialogLinkRepo;
    static Logger logger = Logger.getLogger(UserDialogLinkService.class);

    @Autowired
    public void setUserDialogLinkRepo(UserDialogLinkRepo userDialogLinkRepo) {
        this.userDialogLinkRepo = userDialogLinkRepo;
    }

    public void createLink(Long userId, Long dialogId) {
        logger.info("Method 'createLink' invocation");
        UserDialogLink link = new UserDialogLink();
        link.setUserId(userId);
        link.setDialogId(dialogId);
        userDialogLinkRepo.save(link);
    }

    public UserDialogLink addNewUserIntoDialog(Long userId, Long dialogId) {
        logger.info("Method 'addNewUserIntoDialog' invocation");
        UserDialogLink userDialogLink = new UserDialogLink();
        userDialogLink.setDialogId(dialogId);
        userDialogLink.setUserId(userId);
        userDialogLinkRepo.save(userDialogLink);
        return userDialogLink;
    }

    public List<BigInteger> getUserIdByDialogId(Long DialogId) {
        logger.info("Method 'getUserIdByDialogId' invocation");
        return userDialogLinkRepo.findUsersIdByDialogId(DialogId);
    }

    public List<BigInteger> getDialogIdByUserId(Long UserId) {
        logger.info("Method 'getDialogIdByUserId' invocation");
        return userDialogLinkRepo.findDialogsByUserId(UserId);
    }

}
