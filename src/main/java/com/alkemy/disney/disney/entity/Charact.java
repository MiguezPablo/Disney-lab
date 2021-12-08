package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE charact SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Charact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;
    private String name;
    private Long age;
    private Long weight ;
    private String history;
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "characts", cascade = CascadeType.ALL)
    private List<Movie> movies = new ArrayList<>();

}
