package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharactDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    MovieDTO save(MovieDTO movieDTO);

    MovieDTO updateMovie (Long id,MovieDTO movieDTO);

    void deleteMovie(Long id);

    List<MovieBasicDTO> getBasicMovies();

    List<MovieDTO> getMovieByFilters(String title, String creationDate, Long genderId, String order);

    List<MovieDTO> getAll();

    void addCharact(Long idMovie, Long idCharact);

    void removeCharact(Long idMovie, Long idCharact);

    MovieDTO getDetailsById(Long id);
}
