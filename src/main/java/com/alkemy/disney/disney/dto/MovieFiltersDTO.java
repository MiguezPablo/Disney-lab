package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFiltersDTO {

    private String title;
    private String creationDate;
    private Long genderId;
    private String order;

    public MovieFiltersDTO(String title, String creationDate, Long genderId, String order) {
        this.title = title;
        this.creationDate = creationDate;
        this.genderId = genderId;
        this.order = order;
    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
