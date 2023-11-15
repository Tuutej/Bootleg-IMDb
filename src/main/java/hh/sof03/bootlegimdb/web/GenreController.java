package hh.sof03.bootlegimdb.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof03.bootlegimdb.domain.Genre;
import hh.sof03.bootlegimdb.domain.GenreRepository;
import jakarta.validation.Valid;

@Controller
public class GenreController {

	
	@Autowired
	private GenreRepository genreRepo;

	// list genres in database
	
	@RequestMapping(value = "/genrelist", method = RequestMethod.GET)
	public String genreList(Model model) {
		List<Genre> genres = (List<Genre>) genreRepo.findAll();
		model.addAttribute("genres", genres);
		
		
		return "genrelist";
	}
	
	// add a new genre to database
	
	@RequestMapping(value = "/addgenre")
	public String addgenre(Model model) {
		model.addAttribute("genre", new Genre());
		
		return "addgenre";
	}
			
	// save a new genre entry into the database
	
	@PostMapping(value = "/savegenre")
    public String savegenre(@Valid Genre genre, BindingResult result) {
        if (result.hasErrors()) {
            return "addgenre"; 
        }

        genreRepo.save(genre);
        return "redirect:genrelist";
    }
	
	// delete a genre entry from the database
	
	@PostMapping(value = "/deletegenre/{genreid}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteGenre(@PathVariable("genreid") Long genreId) {
	    genreRepo.deleteById(genreId);
	    return "redirect:/genrelist";
	}

			
}
