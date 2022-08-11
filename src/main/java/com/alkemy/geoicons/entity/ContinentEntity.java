package com.alkemy.geoicons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "continent")
@Getter
@Setter
public class ContinentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String image;

    public ContinentEntity() {
    }
    public ContinentEntity(String name, String image) {
        this.name = name;
        this.image = image;
    }
}
