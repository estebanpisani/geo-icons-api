package com.alkemy.geoicons.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IconFilterDTO {
    private String name;
    private String creationDate;
    private List<Long> countries;
    private String order;

    public IconFilterDTO() {
    }

    public IconFilterDTO(String name, String creationDate, List<Long> countries, String order) {
        this.name = name;
        this.creationDate = creationDate;
        this.countries = countries;
        this.order = order;
    }
//
//    public boolean isAsc() {
//        return this.order.compareToIgnoreCase("asc") == 0;
//    }
//    public boolean isDesc() {
//        return this.order.compareToIgnoreCase("desc") == 0;
//    }
}
