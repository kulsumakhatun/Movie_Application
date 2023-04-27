package com.niit.authentication.repositorylayer;

import com.niit.authentication.domain.User;
import com.niit.authentication.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(scripts = "/MysqlQuery.sql")
@Sql(scripts = "/MysqlDelete.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired(required=true)
    private UserRepository userRepository ;

    private User user;

    @BeforeEach
    void setUp() {

        user = new User("kko1@gmail.com","12");
    }

    @AfterEach
    void tearDown() {
        user= null;
    }

    @Test
    public void addUser(){
        userRepository.save(user);
        User user1 = userRepository.findById(user.getEmail()).get();
        assertNotNull(user1);
        assertEquals(user.getEmail(),user1.getEmail());
    }

    @Test
    public void loginUser(){
        User user1 = userRepository.findById(user.getEmail()).get();
        assertNotNull(user1);
        assertEquals(user.getEmail(),user1.getEmail());
        assertEquals(user.getPassword(),user1.getPassword());
    }
}
