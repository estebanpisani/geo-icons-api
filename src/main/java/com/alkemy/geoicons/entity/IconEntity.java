package com.alkemy.geoicons.entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "icon")
@Getter
@Setter
public class IconEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String image;
    private Double height;
    private String story;
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @ManyToMany(mappedBy = "icons", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CountryEntity> countries = new ArrayList<>();

}
