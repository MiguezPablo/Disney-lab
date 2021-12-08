package com.alkemy.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharactDTO {

    private Long id;
    private String image;
    private String name;
    private Long age;
    private Long weight ;
    private String history;
    private List<MovieDTO> movies;
}
