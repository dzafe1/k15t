package com.k15t.pat.service;

import com.k15t.pat.exception.ItemFoundException;
import com.k15t.pat.exception.ItemNotFoundException;
import com.k15t.pat.model.User;
import com.k15t.pat.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;


    /**
     * Method creates new user who registered for Java meetup
     *
     * @param user user to be created
     * @return created user
     */
    public User createUserForRegistration(User user) {
        log.info("Creating user with email {}", user.getEmail());
        if (this.checkIfUserExists(user.getEmail()))
            throw new ItemFoundException("User already exists in database!");

        /*I would encode password here*/
        userRepository.saveAndFlush(user);

        return userRepository.saveAndFlush(user);
    }

    /**
     * Method checks does user exists with given email
     *
     * @param email which is used to check database
     * @return null or user
     */
    public Boolean checkIfUserExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    /**
     * Bellow are implemented additional methods
     * just to show my knowledge
     *
     * Method gets all registered users
     *
     * @return List of users
     */
    protected List<User> getAllRegisteredUsers() {
        log.info("Getting all users.");
        return userRepository.findAll();
    }

    /**
     * Method gets registered user by id
     *
     * @param id of user
     * @return user from database, throws exception if not found
     */
    User getRegisteredUserById(Long id) {
        log.info("Getting user by id {}", id);
        User user = userRepository.findById(id);
        if (user == null)
            throw new ItemNotFoundException("User is not found!");
        return user;
    }

    /**
     * Deletes registered user
     *
     * @param id of user
     *           throws ItemNotFoundException if not found
     */
    void deleteRegisteredUserById(Long id) {
        log.info("Deleting user by id {}", id);
        User user = this.getRegisteredUserById(id);
        userRepository.delete(user);
    }
}
