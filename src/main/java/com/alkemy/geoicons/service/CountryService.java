package com.alkemy.geoicons.service;

import com.alkemy.geoicons.dto.CountryBasicDTO;
import com.alkemy.geoicons.dto.CountryDTO;
import com.alkemy.geoicons.entity.CountryEntity;
import com.alkemy.geoicons.mapper.CountryMapper;
import com.alkemy.geoicons.repository.CountryRepository;
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

    public CountryService() {
    }

    // CREATE
    public CountryDTO saveCountry(CountryDTO dto) {
        CountryEntity entity = mapper.countryDTOToEntity(dto);
        CountryEntity newCountry = countryRepository.save(entity);
        return mapper.countryEntityToDTO(newCountry);
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

    //UPDATE
    public CountryDTO updateCountry(CountryDTO dto, Long id) throws Exception {
        try {
            Optional<CountryEntity> result = countryRepository.findById(id);

            if (result.isPresent()) {
                CountryEntity country = result.get();
                country.setName(dto.getName());
                country.setImage(dto.getImage());
                countryRepository.save(country);
                CountryDTO dtoUpdated = mapper.countryEntityToDTO(country);
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
