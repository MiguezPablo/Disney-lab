package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharactDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Charact;
import com.alkemy.disney.disney.entity.Gender;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Component
public class MovieMapper {

    @Autowired
    private CharactMapper charactMapper;
    @Autowired
    private GenderRepository genderRepository;

    private LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    public Movie movieDTO2Entity(MovieDTO dto) {
        Gender gender = genderRepository.findById(dto.getGenderId()).orElseThrow();
        Movie movie = new Movie();
        movie.setImage(dto.getImage());
        movie.setTitle(dto.getTitle());
        movie.setCreationDate(this.string2LocalDate(dto.getCreationDate()));
        movie.setRating(dto.getRating());
        movie.setGender(gender);
        Set<Charact> chara = this.charactMapper.charactDTOList2Entity(dto.getCharacters());
        movie.setCharacts(chara);
        return movie;
    }

    public MovieDTO movieEntity2DTO(Movie movie, boolean loadCharacters) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setImage(movie.getImage());
        dto.setTitle(movie.getTitle());
        dto.setCreationDate(movie.getCreationDate().toString());
        dto.setRating(movie.getRating());
        dto.setGenderId(movie.getGender().getId());
        if (loadCharacters) {
            List<CharactDTO> charactDTOS = this.charactMapper.charactEntitySet2DTOList(movie.getCharacts(), false);
            dto.setCharacters(charactDTOS);
        }
        return dto;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<Movie> movies, boolean loadCharacters){
        List<MovieDTO> movieDTOS = new ArrayList<>();
        for (Movie movie:movies) {
            movieDTOS.add(this.movieEntity2DTO(movie, loadCharacters));
        }
        return movieDTOS;
    }

    public void movieEntityRefreshValues(Movie movie, MovieDTO movieDTO){
        movie.setImage(movieDTO.getImage());
        movie.setTitle(movieDTO.getTitle());
        movie.setCreationDate(this.string2LocalDate(movieDTO.getCreationDate()));
        movie.setRating(movieDTO.getRating());
        Gender gender = genderRepository.getById(movieDTO.getGenderId());
        movie.setGender(gender);
        Set<Charact> characts = this.charactMapper.charactDTOList2Entity(movieDTO.getCharacters());
        movie.setCharacts(characts);
    }

    public List<MovieBasicDTO> movieEntitySet2BasicDTOList(Collection<Movie> movies) {
        List<MovieBasicDTO> dtos = new ArrayList<>();
        MovieBasicDTO movieBasicDTO;
        for (Movie movie:movies) {
            movieBasicDTO = new MovieBasicDTO();
            movieBasicDTO.setId(movie.getId());
            movieBasicDTO.setImage(movie.getImage());
            movieBasicDTO.setTitle(movie.getTitle());
            movieBasicDTO.setCreationDate(movie.getCreationDate().toString());
            dtos.add(movieBasicDTO);
        }
        return dtos;
    }
}
