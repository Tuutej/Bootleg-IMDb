package hh.sof03.bootlegimdb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.bootlegimdb.web.GenreController;

@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GenreControllerTest {

    @Autowired
    private GenreController genreController;

    @Test
    public void contextLoads() {
        assertThat(genreController).isNotNull();
    }
}
