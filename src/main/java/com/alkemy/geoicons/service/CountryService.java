package com.alkemy.geoicons.service;

import com.alkemy.geoicons.dto.*;
import com.alkemy.geoicons.entity.CountryEntity;
import com.alkemy.geoicons.entity.IconEntity;
import com.alkemy.geoicons.mapper.CountryMapper;
import com.alkemy.geoicons.repository.CountryRepository;
import com.alkemy.geoicons.repository.specifications.CountrySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private CountryMapper mapper;
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountrySpecification countrySpecification;

    public CountryService() {
    }

    // CREATE
    public CountryDTO saveCountry(CountryDTO dto) {
        CountryEntity entity = mapper.countryDTOToEntity(dto);
        CountryEntity newCountry = countryRepository.save(entity);
        CountryDTO newDTO = mapper.countryEntityToDTO(newCountry);
        return newDTO;
    }

    // READ
    public List<CountryBasicDTO> getAllCountries() throws Exception{
        try {
            List<CountryEntity> countries = countryRepository.findAll();
            List<CountryBasicDTO> dtos = mapper.countryEntityListToBasicDTOList(countries);
            return dtos;
        } catch (Exception e) {
            throw new Exception("Countries not found");
        }
    }
    public CountryDTO getCountryById(Long id) throws Exception {
        try {
            Optional<CountryEntity> result = countryRepository.findById(id);

            if (result.isPresent()) {
                CountryEntity country = result.get();
                CountryDTO dto = mapper.countryEntityToDTO(country);
                return dto;
            } else {

                throw new Exception("Country not found.");
            }
        } catch (Exception e) {
            throw new Exception("Country not found.");
        }
    }
    public List<CountryDTO> getByFilters(String name, Long continent, String order){
        CountryFilterDTO countryFilterDTO = new CountryFilterDTO();

        if(name != null && !name.isEmpty()) {
            countryFilterDTO.setName(name);
        }

        if(continent != null) {
            countryFilterDTO.setContinent(continent);
        }
        countryFilterDTO.setOrder(order);

        List<CountryEntity> countries = countryRepository.findAll(countrySpecification.getByFilters(countryFilterDTO));
        List<CountryDTO> countryDTOs = mapper.countryEntityListToDTOList(countries);
        return countryDTOs;
    }

    //UPDATE
    public CountryDTO updateCountry(CountryDTO dto, Long id) throws Exception {
        try {
            Optional<CountryEntity> result = countryRepository.findById(id);
            if (result.isPresent()) {
                CountryEntity country = result.get();
                if (dto.getName() != null){
                country.setName(dto.getName());
                }
                if (dto.getImage() != null) {
                    country.setImage(dto.getImage());
                }
                if (dto.getArea() != null) {
                    country.setArea(dto.getArea());
                }
                if (dto.getPopulation() != null) {
                    country.setPopulation(dto.getPopulation());
                }
                if (dto.getContinentId() != null) {
                    country.setContinentId(dto.getContinentId());
                }
                CountryEntity countryUpdated = countryRepository.save(country);
                CountryDTO dtoUpdated = mapper.countryEntityToDTO(countryUpdated);
                return dtoUpdated;
            } else {
                throw new Exception("Country not found.");
            }
        } catch (Exception e) {
            throw new Exception("Country not found.");
        }
    }

    // DELETE
    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }
}