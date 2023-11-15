package hh.sof03.bootlegimdb.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hh.sof03.bootlegimdb.domain.GenreRepository;
import hh.sof03.bootlegimdb.domain.Movie;
import hh.sof03.bootlegimdb.domain.MovieRepository;
import jakarta.validation.Valid;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private GenreRepository genreRepo;

    // login

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    // list movies in the database

    @RequestMapping(value = "/movielist", method = RequestMethod.GET)
    public String movieList(Model model) {
        List<Movie> movies = (List<Movie>) movieRepo.findAll();
        model.addAttribute("movies", movies);

        return "movielist";
    }

    // add a new movie to the database

    @RequestMapping(value = "/add")
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("genres", genreRepo.findAll());
        return "addmovie";
    }

    // save a new movie entry into the database
   
    @PostMapping(value = "/savemovie")
    public String save(@ModelAttribute("movie") @Valid Movie movie, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("genres", genreRepo.findAll());
            return "addmovie"; // return to the addmovie page if validation fails
        }

        movieRepo.save(movie);
        return "redirect:movielist";
    }

    // edit a movie entry in the database
 
    @RequestMapping(value = "/edit/{id}")
    public String editMovie(@PathVariable("id") Long Id, Model model) {
        Optional<Movie> movieOptional = movieRepo.findById(Id);
        model.addAttribute("genres", genreRepo.findAll());
        Movie movie = movieOptional.orElse(new Movie());
        model.addAttribute("movie", movie);
        return "editmovie";
    }

    // delete a movie entry from the database

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteMovie(@PathVariable("id") Long id, Model model) {
        movieRepo.deleteById(id);

        return "redirect:../movielist";
    }
    
    
    // filter movies  
    
    @RequestMapping(value = "/movies/filtered", method = RequestMethod.GET)
    public String getFilteredMovies(Model model, @RequestParam(required = false) String query) {
        List<Movie> movies;
        if (query != null && !query.isEmpty()) {
            movies = movieRepo.findByTitleContainingIgnoreCase(query);
            // If no movies are found by title, search by director
            if (movies.isEmpty()) {
                movies = movieRepo.findByDirectorContainingIgnoreCase(query);
            }
        } else {
            movies = movieRepo.findAll();
        }
        model.addAttribute("movies", movies);
        return "movielist";
    }

	
}
