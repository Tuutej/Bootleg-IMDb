package hh.sof03.bootlegimdb;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.bootlegimdb.domain.User;
import hh.sof03.bootlegimdb.domain.UserRepository;

@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;

    @Test
    public void findByUsernameRetrieveUser() {
       
        String usernameToSearch = "user";
        String password = "password";
        String role = "USER"; 

        User user = new User();
        user.setUsername(usernameToSearch);
        user.setPasswordHash(password);
        user.setRole(role);
        userRepo.save(user); // Save the user to the database

        User retrievedUser = userRepo.findByUsername(usernameToSearch);

        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getUsername()).isEqualTo(usernameToSearch);
    }


}