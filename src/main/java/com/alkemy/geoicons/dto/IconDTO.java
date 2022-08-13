package com.alkemy.geoicons.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IconDTO {
    private Long id;
    private String name;
    private String image;
    private Double height;
    private String story;
    private String creationDate;
    private List<CountryBasicDTO> countries;
}
