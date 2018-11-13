package org.superbiz.moviefun.movies;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {
    private MoviesRepository movieRepository;

    public MoviesController(MoviesRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @PostMapping
    public void add(@RequestBody Movie movie) {
        movieRepository.addMovie(movie);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        movieRepository.deleteMovieId(id);
    }

    @GetMapping("count")
    public int countAll(@RequestParam(required = false) String field, @RequestParam(required = false) String key) {
        if (field != null && key != null) {
            return movieRepository.count(field, key);
        }
        return movieRepository.countAll();
    }

    @GetMapping
    public List<Movie> getAll(@RequestParam(required = false) Integer start, @RequestParam(required = false) Integer pageSize,
                              @RequestParam(required = false) String field,
                              @RequestParam(required = false) String key) {
        if (field != null && key != null) {
            return movieRepository.findRange(field, key, start, pageSize);
        } else if (start != null & pageSize != null) {
            return movieRepository.findAll(start, pageSize);
        }
        return movieRepository.getMovies();
    }


}
