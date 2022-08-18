package com.alkemy.geoicons.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountryFilterDTO {
    private String name;
    private Long continent;
    private String order;

    public CountryFilterDTO() {
    }

    public CountryFilterDTO(String name, Long continent, String order) {
        this.name = name;
        this.continent = continent;
        this.order = order;
    }
}
