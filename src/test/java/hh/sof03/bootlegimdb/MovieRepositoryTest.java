package hh.sof03.bootlegimdb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.bootlegimdb.domain.Genre;
import hh.sof03.bootlegimdb.domain.GenreRepository;
import hh.sof03.bootlegimdb.domain.Movie;
import hh.sof03.bootlegimdb.domain.MovieRepository;


@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepo;
    
    @Autowired
    private GenreRepository genreRepo;

    @Test
    public void findByTitleRetrieveMovie() {
    	
    	Genre genre = new Genre("Action");
    	genreRepo.save(genre);

    
    	Movie movie = new Movie();
    	movie.setTitle("Inception");
    	movie.setDirector("Christopher Nolan");
    	movie.setYear(2010);
    	movie.setRuntime(148);
    	movie.setGenre(genre);
    	movieRepo.save(movie);
    	
        // Test findByTitle
        List<Movie> movies = movieRepo.findByTitle("Inception");

        assertThat(movies).hasSize(1);
        assertThat(movies.get(0).getDirector()).isEqualTo("Christopher Nolan");
    }

    @Test
    public void createNewMovie() {
        Genre genre = new Genre("Drama");
        genreRepo.save(genre);

        Movie movie = new Movie();
        movie.setTitle("The Shawshank Redemption");
        movie.setDirector("Frank Darabont");
        movie.setYear(1994);
        movie.setRuntime(142);
        movie.setGenre(genre); 
        movieRepo.save(movie);

        assertThat(movie.getId()).isNotNull();
    }


    @Test
    public void testDeleteMovie() {
        // Test delete method
    	Genre genre = new Genre("Drama");
        genreRepo.save(genre);
    	
    	 Movie movie = new Movie();
         movie.setTitle("The Shawshank Redemption");
         movie.setDirector("Frank Darabont");
         movie.setYear(1994);
         movie.setRuntime(142);
         movie.setGenre(genre); 
         movieRepo.save(movie);

        movieRepo.delete(movie);

        List<Movie> foundMovies = movieRepo.findByTitle("The Shawshank Redemption");
        assertEquals(0, foundMovies.size());
    }
}
