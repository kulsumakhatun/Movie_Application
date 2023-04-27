package com.niit.userService.UserService.services;

import com.niit.userService.UserService.exception.UserAlreadyExistsException;
import com.niit.userService.UserService.exception.UserNotFoundException;
import com.niit.userService.UserService.models.Favourite;
import com.niit.userService.UserService.models.User;

public interface UserService {

    User addUser(User user) throws UserAlreadyExistsException;

    User updateUser( User user) throws UserNotFoundException;

    boolean deleteUser(String email) throws UserNotFoundException;

    User getUserById(String email);

}
