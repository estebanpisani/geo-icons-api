package com.alkemy.geoicons.mapper;

import com.alkemy.geoicons.dto.ContinentDTO;
import com.alkemy.geoicons.entity.ContinentEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContinentMapper {

    public ContinentEntity continentDTOToEntity(ContinentDTO dto) {
        ContinentEntity continentEntity = new ContinentEntity();
        continentEntity.setName(dto.getName());
        continentEntity.setImage((dto.getImage()));
        return continentEntity;
    }
    public ContinentDTO continentEntityToDTO(ContinentEntity continent) {
        ContinentDTO dto = new ContinentDTO();
        dto.setId(continent.getId());
        dto.setName(continent.getName());
        dto.setImage(continent.getImage());
        return dto;
    }

    public List<ContinentEntity> continentDTOListToEntityList(List<ContinentDTO> dtos) {
        List<ContinentEntity> continents = new ArrayList<>();
        for (ContinentDTO dto : dtos){
            continents.add(continentDTOToEntity(dto));
        }
        return continents;
    }
    public List<ContinentDTO> continentEntityListToDTOList(List<ContinentEntity> continents) {
        List<ContinentDTO> dtos = new ArrayList<>();
        for (ContinentEntity continent : continents){
            dtos.add(continentEntityToDTO(continent));
        }
        return dtos;
    }

}
