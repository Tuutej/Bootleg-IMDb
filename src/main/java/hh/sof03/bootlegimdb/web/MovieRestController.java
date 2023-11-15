package hh.sof03.bootlegimdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof03.bootlegimdb.domain.Movie;
import hh.sof03.bootlegimdb.domain.MovieRepository;

@CrossOrigin
@Controller
public class MovieRestController {

    @Autowired
    private MovieRepository movieRepository;

    // RESTful service to list all movies
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public @ResponseBody List<Movie> movieListRest() {
    
        return (List<Movie>) movieRepository.findAll();
    }

    // RESTful service to get a movie by ID
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Movie> findMovieRest(@PathVariable("id") Long movieId) {
      
        return movieRepository.findById(movieId);
    }
    
    // RESTful service to create a new movie
    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public @ResponseBody Movie createMovie(@RequestBody Movie movie) {
       
        return movieRepository.save(movie);
    }

    // RESTful service to update a movie by ID
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    public @ResponseBody Movie updateMovie(@PathVariable("id") Long movieId, @RequestBody Movie updatedMovie) {
       
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
           
            return movieRepository.save(movie);
        } else {
           
            return null;
        }
    }

    // RESTful service to delete a movie by ID
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public void deleteMovie(@PathVariable("id") Long movieId) {
       
        movieRepository.deleteById(movieId);
    }
}
