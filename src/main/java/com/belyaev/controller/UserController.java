package com.belyaev.controller;

import com.belyaev.controller.dto.AuthenticationRequestDTO;
import com.belyaev.controller.dto.UserDTO;
import com.belyaev.entity.User;
import com.belyaev.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Api(description = "Controller which provides methods for operations with users")
public class UserController {

    private UserService userService;
    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/chat/user", method = RequestMethod.POST)
    @ApiOperation(value = "Method for user creation", response = User.class)
    public User createNewUser(@Valid @RequestBody UserDTO userDTO) {
        logger.info("Method 'createNewUser' invocation. Input object - 'User' with properties( name: " + userDTO.getName() + "; surname: " +
                userDTO.getSurname() + "; nikname: " + userDTO.getNik() + " )");
        return userService.createUser(userDTO.getName(), userDTO.getSurname(), userDTO.getNik(), userDTO.getPassword());
    }

    @ApiOperation(value = "Method for getting user by ID", response = User.class)
    @RequestMapping(value = "/chat/user/{id}", method = RequestMethod.GET)
    public User getUserById(@NotNull @PathVariable Long id) {
        logger.info("Method 'getUserById' invocation. Input parameter - 'UserID' with value: " + id);
        return userService.getUserById(id);
    }

    @ApiOperation(value = "Method getting list of users in one dialog", response = User.class)
    @RequestMapping(value = "/chat/get-users-by-dialogid/{id}", method = RequestMethod.GET)
    public List<User> getUsersByDialogId(@NotNull @PathVariable Long id) {
        logger.info("Method 'getUsersByDialogId' invocation. Input parameter - 'DialogID' with value: " + id);
        return userService.getParticipantsOfDialog(id);
    }

    @ApiOperation(value = "Method returns result of authentication", response = String.class)
    @RequestMapping(value = "/chat/authentication", method = RequestMethod.POST)
    public String checkAuthentication(@Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        logger.info("Method 'checkAuthentication' invocation. Input object - 'AuthenticationRequestDTO' with properties( nikname: "
                + authenticationRequestDTO.getNik() + ")");
        return userService.checkAuthentication(authenticationRequestDTO.getNik(), authenticationRequestDTO.getPassword());
    }

    @ApiOperation(value = "User deletion method")
    @RequestMapping(value = "/chat/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@NotNull @PathVariable Long id) {
        logger.info("Method 'deleteUser' invocation. Input parameter - 'UserID' with value: " + id);
        userService.deleteUser(id);
    }
}
