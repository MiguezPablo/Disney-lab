package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAll() {
        List<MovieDTO> movieDTOS = this.movieService.getAll();
        return ResponseEntity.ok().body(movieDTOS);
    }

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getBasic() {
        List<MovieBasicDTO> movieBasicDTOList = this.movieService.getBasicMovies();
        return ResponseEntity.ok().body(movieBasicDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getDetailsById(@PathVariable Long id) {

        MovieDTO movieDTO = this.movieService.getDetailsById(id);

        return ResponseEntity.ok(movieDTO);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<MovieDTO>> getMovieByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String creationDate,
            @RequestParam(required = false) Long genderId,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieDTO> movieDTOS = this.movieService.getMovieByFilters(title, creationDate, genderId, order);
        return ResponseEntity.ok(movieDTOS);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO) {
        MovieDTO movieDTOfinal = this.movieService.save(movieDTO);
        return ResponseEntity.ok().body(movieDTOfinal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        MovieDTO result = this.movieService.updateMovie(id, movieDTO);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/{idMovie}/character/{idCharact}")
    public ResponseEntity<Void> addCharact(@PathVariable Long idMovie, @PathVariable Long idCharact) {
        this.movieService.addCharact(idMovie, idCharact);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{idMovie}/character/{idCharact}")
    public ResponseEntity<Void> removeCharact(@PathVariable Long idMovie, @PathVariable Long idCharact) {
        this.movieService.removeCharact(idMovie, idCharact);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
