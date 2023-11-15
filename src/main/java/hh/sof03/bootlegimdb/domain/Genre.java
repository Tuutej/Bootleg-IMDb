package hh.sof03.bootlegimdb.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;



@Entity(name = "genres")
public class Genre { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreid; 
    
    @NotBlank(message = "Name is required")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    @JsonIgnoreProperties("genre")
    private List<Movie> movies;

    // constructors

    public Genre() {
        super();
        this.name = null;
    }

    public Genre(String name) {
        super();
        this.name = name;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setGenreid(Long genreid) {
        this.genreid = genreid;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    // getters

    public String getName() {
        return name;
    }

    public Long getGenreid() {
        return genreid;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    // toString

    @Override
    public String toString() {
        return "GENRE ID: " + genreid + ", NAME: " + name;
    }
}
