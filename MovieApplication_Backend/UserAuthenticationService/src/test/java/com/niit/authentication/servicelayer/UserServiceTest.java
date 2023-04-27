package com.niit.authentication.servicelayer;

import com.niit.authentication.domain.User;
import com.niit.authentication.exceptions.UserAlreadyExistsException;
import com.niit.authentication.repository.UserRepository;
import com.niit.authentication.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("mail@123", "pass@123");
    }

    @Test
    public void saveUser() throws UserAlreadyExistsException {

        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(null));
        assertEquals(user,userService.addUser(user));
        verify(userRepository,times(1)).save(user);
        verify(userRepository,times(1)).findById(user.getEmail());
    }

    @Test
    public void saveUserFailure(){
        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        assertThrows(UserAlreadyExistsException.class,()->userService.addUser(user));
        verify(userRepository,times(0)).save(user);
        verify(userRepository,times(1)).findById(user.getEmail());
    }

}