package hh.sof03.bootlegimdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Director is required")
    private String director;

    @NotNull(message = "Release year is required")
    @Digits(integer = 4, fraction = 0, message = "Year must be a 4-digit whole number")
    @Min(value = 1900, message = "Year must be at least 1900")
    @Column(name = "release_year")
    private Integer year;

    @NotNull(message = "Runtime is required")
    @Digits(integer = 3, fraction = 0, message = "Runtime must be a max 3-digit whole number")
    @Min(value = 1, message = "Runtime must be at least 1 minute")
    private Integer runtime;

    @DecimalMin(value = "0.0", inclusive = true, message = "Box office should be a positive number or zero")
    private Double boxoffice;

    @ManyToOne
    @JsonIgnoreProperties("movies")
    @JoinColumn(name = "genreid")
    @NotNull(message = "Genre is required")
    private Genre genre;

    // constructors

    public Movie() {
        super();
        this.title = null;
        this.director = null;
        this.year = null;
        this.runtime = null;
        this.boxoffice = null;
        this.genre = null;
    }

    public Movie(String title, String director, Integer year, Integer runtime, Double boxoffice, Genre genre) {
        super();
        this.title = title;
        this.director = director;
        this.year = year;
        this.runtime = runtime;
        this.boxoffice = boxoffice;
        this.genre = genre;
    }

    // setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public void setBoxoffice(Double boxoffice) {
        this.boxoffice = boxoffice;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    // getters

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public Double getBoxoffice() {
        return boxoffice;
    }

    public Genre getGenre() {
        return genre;
    }

    // toString

    @Override
    public String toString() {
        if (this.genre != null)
            return "ID: " + id + ", TITLE: " + title + ", DIRECTOR: " + director + ", YEAR: " + year + ", RUNTIME: " + runtime + ", boxoffice: " + boxoffice + ", GENRE: " + this.getGenre();
        else
            return "ID: " + id + ", TITLE: " + title + ", DIRECTOR: " + director + ", YEAR: " + year + ", RUNTIME: " + runtime + ", boxoffice: " + boxoffice;
    }
}