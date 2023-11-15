package hh.sof03.bootlegimdb;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.bootlegimdb.domain.Genre;
import hh.sof03.bootlegimdb.domain.GenreRepository;

@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepo;

    @Test
    public void findByNameRetrieveGenre() {
    	
    	Genre genre = new Genre("Action");
    	genreRepo.save(genre);
    	
        List<Genre> genres = genreRepo.findByName("Action");

        assertThat(genres).hasSize(1);
        assertThat(genres.get(0).getName()).isEqualTo("Action");
    }

    @Test
    public void createNewGenre() {
        Genre genre = new Genre("Horror");
        genreRepo.save(genre);
        assertThat(genre.getGenreid()).isNotNull();
    }
}
