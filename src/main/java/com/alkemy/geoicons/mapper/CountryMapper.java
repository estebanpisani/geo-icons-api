package com.alkemy.geoicons.mapper;

import com.alkemy.geoicons.dto.CountryBasicDTO;
import com.alkemy.geoicons.dto.CountryDTO;
import com.alkemy.geoicons.entity.CountryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CountryMapper {

    @Autowired
    ContinentMapper continentMapper;
    public CountryEntity countryDTOToEntity(CountryDTO dto) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setName(dto.getName());
        countryEntity.setImage((dto.getImage()));
        countryEntity.setArea(dto.getArea());
        countryEntity.setPopulation(dto.getPopulation());
        countryEntity.setContinentId(dto.getContinentId());
//        if (dto.getContinent() != null) {
//            countryEntity.setContinent(continentMapper.continentDTOToEntity(dto.getContinent()));
//        }
        return countryEntity;
    }
    public CountryDTO countryEntityToDTO(CountryEntity country) {
        CountryDTO dto = new CountryDTO();
        dto.setId(country.getId());
        dto.setName(country.getName());
        dto.setImage(country.getImage());
        dto.setArea(country.getArea());
        dto.setPopulation(country.getPopulation());
        dto.setContinentId(country.getContinentId());
        if (country.getContinent() != null) {
            dto.setContinent(continentMapper.continentEntityToDTO(country.getContinent()));
        }
//        TODO setIcons
//        dto.setIcons(country.getIcons());
        return dto;
    }

    public CountryBasicDTO countryEntityToBasicDTO(CountryEntity country) {
        CountryBasicDTO basicDTO = new CountryBasicDTO();
        basicDTO.setId(country.getId());
        basicDTO.setName(country.getName());
        basicDTO.setImage(country.getImage());
        basicDTO.setPopulation(country.getPopulation());
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
