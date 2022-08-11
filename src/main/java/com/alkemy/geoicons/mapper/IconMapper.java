package com.alkemy.geoicons.mapper;

import com.alkemy.geoicons.dto.IconBasicDTO;
import com.alkemy.geoicons.dto.IconDTO;
import com.alkemy.geoicons.entity.IconEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class IconMapper {

    @Autowired
    CountryMapper countryMapper;
    public IconEntity iconDTOToEntity(IconDTO dto) {
        IconEntity iconEntity = new IconEntity();
        iconEntity.setName(dto.getName());
        iconEntity.setImage((dto.getImage()));
        iconEntity.setHeight(dto.getHeight());
        iconEntity.setStory(dto.getStory());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(dto.getCreationDate()!=null && !dto.getCreationDate().isEmpty()) {
            LocalDate date = LocalDate.parse(dto.getCreationDate(), formatter );
            iconEntity.setCreationDate(date);
        }
        //TODO icon setCountries
        // iconEntity.setCountries(countryMapper.countryDTOListToEntityList(dto.getCountries()));
        return iconEntity;
    }
    public IconDTO iconEntityToDTO(IconEntity icon) {
        IconDTO dto = new IconDTO();
        dto.setId(icon.getId());
        dto.setName(icon.getName());
        dto.setImage(icon.getImage());
        dto.setCreationDate(icon.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dto.setStory(icon.getStory());
        dto.setHeight(icon.getHeight());
        //TODO iconDTO setCountries
        // dto.setCountries(icon.getCountries());
        return dto;
    }

    public IconBasicDTO iconEntityToBasicDTO(IconEntity icon) {
        IconBasicDTO basicDTO = new IconBasicDTO();
        basicDTO.setId(icon.getId());
        basicDTO.setName(icon.getName());
        basicDTO.setImage(icon.getImage());
        return basicDTO;
    }

    public List<IconEntity> iconDTOListToEntityList(List<IconDTO> dtos) {
        List<IconEntity> countries = new ArrayList<>();
        for (IconDTO dto : dtos){
            countries.add(iconDTOToEntity(dto));
        }
        return countries;
    }
    public List<IconDTO> iconEntityListToDTOList(List<IconEntity> countries) {
        List<IconDTO> dtos = new ArrayList<>();
        for (IconEntity icon : countries){
            dtos.add(iconEntityToDTO(icon));
        }
        return dtos;
    }

    public List<IconBasicDTO> iconEntityListToBasicDTOList(List<IconEntity> countries) {
        List<IconBasicDTO> basicDTOs = new ArrayList<>();
        for (IconEntity icon : countries){
            basicDTOs.add(iconEntityToBasicDTO(icon));
        }
        return basicDTOs;
    }
}