package com.alkemy.geoicons.mapper;

import com.alkemy.geoicons.dto.CountryBasicDTO;
import com.alkemy.geoicons.dto.CountryDTO;
import com.alkemy.geoicons.entity.CountryEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CountryMapper {
    public CountryEntity countryDTOToEntity(CountryDTO dto) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setName(dto.getName());
        countryEntity.setImage((dto.getImage()));
        return countryEntity;
    }
    public CountryDTO countryEntityToDTO(CountryEntity country) {
        CountryDTO dto = new CountryDTO();
        dto.setId(country.getId());
        dto.setName(country.getName());
        dto.setImage(country.getImage());
        return dto;
    }

    public CountryBasicDTO countryEntityToBasicDTO(CountryEntity country) {
        CountryBasicDTO basicDTO = new CountryBasicDTO();
        basicDTO.setId(country.getId());
        basicDTO.setName(country.getName());
        basicDTO.setImage(country.getImage());
        return basicDTO;
    }

    public List<CountryEntity> countryDTOListToEntityList(List<CountryDTO> dtos) {
        List<CountryEntity> countries = new ArrayList<>();
        for (CountryDTO dto : dtos){
            countries.add(countryDTOToEntity(dto));
        }
        return countries;
    }
    public List<CountryDTO> countryEntityListToDTOList(List<CountryEntity> countries) {
        List<CountryDTO> dtos = new ArrayList<>();
        for (CountryEntity country : countries){
            dtos.add(countryEntityToDTO(country));
        }
        return dtos;
    }

    public List<CountryBasicDTO> countryEntityListToBasicDTOList(List<CountryEntity> countries) {
        List<CountryBasicDTO> basicDTOs = new ArrayList<>();
        for (CountryEntity country : countries){
            basicDTOs.add(countryEntityToBasicDTO(country));
        }
        return basicDTOs;
    }
}
