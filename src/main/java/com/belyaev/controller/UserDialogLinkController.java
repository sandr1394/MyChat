package com.belyaev.controller;

import com.belyaev.controller.dto.UserDialogLinkDTO;
import com.belyaev.entity.UserDialogLink;
import com.belyaev.service.UserDialogLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(description = "Controller which provides methods for links between dialogs and users")
public class UserDialogLinkController {

    private UserDialogLinkService userDialogLinkService;
    private static Logger logger = Logger.getLogger(UserDialogLinkController.class);

    @Autowired
    public UserDialogLinkController(UserDialogLinkService userDialogLinkService) {
        this.userDialogLinkService = userDialogLinkService;
    }

    @ApiOperation(value = "Method for adding user into dialog", response = UserDialogLink.class)
    @RequestMapping(value = "chat/user-dialog-link/create", method = RequestMethod.POST)
    public UserDialogLink addUserInDialog(@Valid @RequestBody UserDialogLinkDTO userDialogLinkDTO) {
        logger.info("Method 'addUserInDialog' invocation. Input object - 'UserDialogLinkDTO' with properties( userID: " +
                userDialogLinkDTO.getUserId() + "; dialogID: " + userDialogLinkDTO.getDialogId() + " )");
        return userDialogLinkService.addNewUserIntoDialog(userDialogLinkDTO.getUserId(), userDialogLinkDTO.getDialogId());
    }
}
