package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharactFiltersDTO {

    private String name;
    private Long age;
    private Long weight ;
    private List<Long> movies;

    public CharactFiltersDTO(String name, Long age, Long weight, List<Long> movies) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movies = movies;
    }
}
