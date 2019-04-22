package com.belyaev.service;

import com.belyaev.dao.UserRepo;
import com.belyaev.entity.User;
import com.belyaev.exception.DialogNotFoundException;
import com.belyaev.exception.PasswordNotMatchException;
import com.belyaev.exception.UserNotFoundException;
import com.belyaev.utils.PasswordUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepo userRepo;
    private UserDialogLinkService userDialogLinkService;
    private static Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepo userRepo, UserDialogLinkService userDialogLinkService) {
        this.userRepo = userRepo;
        this.userDialogLinkService = userDialogLinkService;
    }

    public User createUser(String name, String surname, String nikName, String password) {
        logger.info("Method 'createUser' invocation");
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setNik(nikName);
        try {
            user.setPassword(PasswordUtils.hash(password));
            userRepo.save(user);
            return user;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(Long userId) {
        logger.info("Method 'getUserByID' invocation");
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            logger.warn("UserNotFoundException: Can't find user with ID: " + userId);
            throw new UserNotFoundException("Can't find user with ID: " + userId, userId);
        }
        return user;
    }

    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    @Transactional
    public List<User> getParticipantsOfDialog(Long dialogId) {
        logger.info("Method 'getParticipantsOfDialog' invocation");
        List<BigInteger> listOfUsersId = userDialogLinkService.getUserIdByDialogId(dialogId);
        if (listOfUsersId.isEmpty()) {
            logger.warn("Can't find dialog with ID: " + dialogId);
            throw new DialogNotFoundException("Can't find dialog with ID: " + dialogId, dialogId);
        } else {
            List<User> listOfUsers = new ArrayList<>();
            for (int j = 0; j < listOfUsersId.size(); j++) {
                listOfUsers.add(userRepo.findById(listOfUsersId.get(j).longValue()).orElse(null));
            }
            return listOfUsers;
        }
    }

    public String checkAuthentication(String nik, String password) {
        logger.info("Method 'checkAuthentication' invocation");
        User user = userRepo.findUserByNik(nik);
        if (user == null) {
            logger.warn("Can't find user with nik: " + nik);
            throw new UserNotFoundException("Can't find user with nik: " + nik);
        } else {
            try {
                if (user.getPassword().equals(PasswordUtils.hash(password))) {
                    return "Ok";
                } else {
                    logger.error("Entered password does not matched for nik: " + nik);
                    throw new PasswordNotMatchException("Entered password does not matched for nik: " + nik);
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
