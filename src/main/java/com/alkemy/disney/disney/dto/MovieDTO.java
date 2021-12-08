package com.alkemy.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String image;
    private String title;
    private String creationDate;
    private Long rating; // from 1 to 5
    private Long genderId;
    private List<CharactDTO> characters;
}
