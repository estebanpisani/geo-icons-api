package com.alkemy.geoicons.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountryBasicDTO {
    private Long id;
    private String name;
    private String image;
    private Long population;
}
