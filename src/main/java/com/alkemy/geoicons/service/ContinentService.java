package com.alkemy.geoicons.service;

import com.alkemy.geoicons.dto.ContinentDTO;
import com.alkemy.geoicons.entity.ContinentEntity;
import com.alkemy.geoicons.mapper.ContinentMapper;
import com.alkemy.geoicons.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContinentService {

    @Autowired
    private ContinentMapper mapper;
    @Autowired
    private ContinentRepository continentRepository;

    public List<ContinentDTO> getAllContinents(){
        List<ContinentEntity> continents = continentRepository.findAll();
        List<ContinentDTO> dtos = mapper.continentEntityListToDTOList(continents);
        return dtos;
    }

//    TODO public ContinentDTO getContinentById(Long id) {
//        ContinentDTO dto = new ContinentDTO();
//        //
//        return dto;
//    }
    public ContinentDTO save(ContinentDTO dto) {
        ContinentEntity entity = mapper.continentDTOToEntity(dto);
        ContinentEntity newContinent = continentRepository.save(entity);
        return mapper.continentEntityToDTO(newContinent);
    }
}
