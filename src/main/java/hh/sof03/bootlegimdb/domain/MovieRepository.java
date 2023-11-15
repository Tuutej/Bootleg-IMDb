package hh.sof03.bootlegimdb.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByDirector(String director);
    List<Movie> findByDirectorContainingIgnoreCase(String director);
    List<Movie> findByYear(int year);
    List<Movie> findByGenre(Genre genre);
    List<Movie> findByRuntimeGreaterThan(int runtime);

}