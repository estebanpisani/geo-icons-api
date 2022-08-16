package com.alkemy.geoicons.dto;

import com.alkemy.geoicons.entity.ContinentEntity;
import com.alkemy.geoicons.entity.IconEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CountryDTO {
    private Long id;
    private String name;
    private String image;
    private Long population;
    private Long area;
    private Long continentId;
    private ContinentDTO continent;
    private List<IconBasicDTO> icons;
}
