package com.alkemy.geoicons.service;

import com.alkemy.geoicons.dto.ContinentDTO;
import com.alkemy.geoicons.entity.ContinentEntity;
import com.alkemy.geoicons.mapper.ContinentMapper;
import com.alkemy.geoicons.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContinentService {

    @Autowired
    private ContinentMapper mapper;
    @Autowired
    private ContinentRepository continentRepository;

    // CREATE
    public ContinentDTO save(ContinentDTO dto) {
        ContinentEntity entity = mapper.continentDTOToEntity(dto);
        ContinentEntity newContinent = continentRepository.save(entity);
        return mapper.continentEntityToDTO(newContinent);
    }

    // READ
    public List<ContinentDTO> getAllContinents() throws Exception{
        try {
            List<ContinentEntity> continents = continentRepository.findAll();
            List<ContinentDTO> dtos = mapper.continentEntityListToDTOList(continents);
            return dtos;
        } catch (Exception e) {
            throw new Exception("Continents not found");
        }
    }
    public ContinentDTO getContinentById(Long id) throws Exception {
        try {
            Optional<ContinentEntity> result = continentRepository.findById(id);

            if (result.isPresent()) {
                ContinentEntity continent = result.get();
                ContinentDTO dto = mapper.continentEntityToDTO(continent);
                return dto;
            } else {

                throw new Exception("Continent not found.");
            }
        } catch (Exception e) {
            throw new Exception("Continent not found.");
        }
    }

    //UPDATE
    public ContinentDTO update(ContinentDTO dto, Long id) throws Exception {
        try {
            Optional<ContinentEntity> result = continentRepository.findById(id);

            if (result.isPresent()) {
                ContinentEntity continent = result.get();
                continent.setName(dto.getName());
                continent.setImage(dto.getImage());
                continentRepository.save(continent);
                ContinentDTO dtoUpdated = mapper.continentEntityToDTO(continent);
                return dtoUpdated;
            } else {
                throw new Exception("Continent not found.");
            }
        } catch (Exception e) {
            throw new Exception("Continent not found.");
        }
    }

    // DELETE
    public void deleteContinentById(Long id) {
        continentRepository.deleteById(id);
    }

}
