package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.Charact;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.CharactRepository;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieSpecification movieSpecification;
    @Autowired
    private CharactRepository charactRepository;



    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        Movie movie = this.movieMapper.movieDTO2Entity(movieDTO);
        Movie entitySaved = this.movieRepository.save(movie);
        MovieDTO result = this.movieMapper.movieEntity2DTO(entitySaved, true);
        return result;
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        Optional<Movie> movie = this.movieRepository.findById(id);
        if (!movie.isPresent()) {
            throw new ParamNotFound("Id movie no valid");
        }
        this.movieMapper.movieEntityRefreshValues(movie.get(), movieDTO);
        Movie movieSaved = this.movieRepository.save(movie.get());
        MovieDTO result = this.movieMapper.movieEntity2DTO(movieSaved, false);
        return result;
    }

    @Override
    public void deleteMovie(Long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public List<MovieBasicDTO> getBasicMovies() {
        List<Movie> movies = this.movieRepository.findAll();
        List<MovieBasicDTO> result = this.movieMapper.movieEntitySet2BasicDTOList(movies);
        return result;
    }

    @Override
    public List<MovieDTO> getMovieByFilters(String title, String creationDate, Long genderId, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(title, creationDate, genderId, order);
        List<Movie> movies = this.movieRepository.findAll(
                this.movieSpecification.getByFilters(filtersDTO)
        );
        List<MovieDTO> movieDTOS = this.movieMapper.movieEntityList2DTOList(movies, true);
        return movieDTOS;
    }

    @Override
    public List<MovieDTO> getAll() {
        List<Movie> movies = this.movieRepository.findAll();
        List<MovieDTO> result = this.movieMapper.movieEntityList2DTOList(movies, true);
        return result;
    }

    @Override
    public MovieDTO getDetailsById(Long id) {
        Optional<Movie> movie = this.movieRepository.findById(id);

        if (!movie.isPresent()){
            throw new ParamNotFound("Id movie no valid");
        }

        MovieDTO movieDTO = this.movieMapper.movieEntity2DTO(movie.get(),true);
        return movieDTO;
    }

    @Override
    public void addCharact(Long idMovie, Long idCharact) {
        Charact charact = this.charactRepository.getById(idCharact);
        Movie movie = movieRepository.getById(idMovie);
        movie.addCharact(charact);
        this.movieRepository.save(movie);
    }

    @Override
    public void removeCharact(Long idMovie, Long idCharact) {
        Charact charact = this.charactRepository.getById(idCharact);
        Movie movie = movieRepository.getById(idMovie);
        movie.removeCharact(charact);
        this.movieRepository.save(movie);
    }


}
