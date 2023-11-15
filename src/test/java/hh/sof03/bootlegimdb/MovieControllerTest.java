package hh.sof03.bootlegimdb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.bootlegimdb.web.MovieController;

@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MovieControllerTest {

    @Autowired
    private MovieController movieController;
    
    @Test
    public void contextLoads() {
        assertThat(movieController).isNotNull();
    }
}
