package com.alkemy.geoicons.service;

import com.alkemy.geoicons.dto.CountryBasicDTO;
import com.alkemy.geoicons.dto.IconBasicDTO;
import com.alkemy.geoicons.dto.IconDTO;
import com.alkemy.geoicons.dto.IconFilterDTO;
import com.alkemy.geoicons.entity.CountryEntity;
import com.alkemy.geoicons.entity.IconEntity;
import com.alkemy.geoicons.mapper.CountryMapper;
import com.alkemy.geoicons.mapper.IconMapper;
import com.alkemy.geoicons.repository.CountryRepository;
import com.alkemy.geoicons.repository.IconRepository;
import com.alkemy.geoicons.repository.specifications.IconSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class IconService {
    @Autowired
    private IconMapper mapper;
    @Autowired
    private IconRepository iconRepository;
    @Autowired
    private IconSpecification iconSpecification;

    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private CountryRepository countryRepository;

    public IconService() {
    }

    // CREATE
    public IconDTO saveIcon(IconDTO dto) {
        IconEntity entity = mapper.iconDTOToEntity(dto);
        return mapper.iconEntityToDTO(entity);
    }

    // READ
    public List<IconBasicDTO> getAllIcons() throws Exception{
        try {
            List<IconEntity> icons = iconRepository.findAll();
            List<IconBasicDTO> dtos = mapper.iconEntityListToBasicDTOList(icons);
            return dtos;
        } catch (Exception e) {
            throw new Exception("Icons not found");
        }
    }
    public IconDTO getIconById(Long id) throws Exception {

        try {
            Optional<IconEntity> result = iconRepository.findById(id);
            if (result.isPresent()) {
                IconEntity icon = result.get();
                IconDTO dto = mapper.iconEntityToDTO(icon);
                return dto;
            } else {
                throw new Exception("Icon not found.");
            }
        } catch (Exception e) {
            throw new Exception("Icon not found.");
        }
    }

    public List<IconDTO> getByFilters(String name, String creationDate, List<Long> countries, String order){
        IconFilterDTO iconFilterDTO = new IconFilterDTO();
        if(!name.isEmpty() && name != null) {
            iconFilterDTO.setName(name);
        }
        if(creationDate != null && !creationDate.isEmpty()){
            iconFilterDTO.setCreationDate(creationDate);
        }
        if(countries != null && !countries.isEmpty() &&  countries.size()>=1) {
            iconFilterDTO.setCountries(countries);
        }
        iconFilterDTO.setOrder(order);

        List<IconEntity> icons = iconRepository.findAll(iconSpecification.getByFilters(iconFilterDTO));
        List<IconDTO> iconDTOs = mapper.iconEntityListToDTOList(icons);
        return iconDTOs;
    }

    //UPDATE
    public IconDTO updateIcon(IconDTO dto, Long id) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            Optional<IconEntity> result = iconRepository.findById(id);

            if (result.isPresent()) {
                IconEntity icon = result.get();
                icon.setName(dto.getName());
                icon.setImage(dto.getImage());
                icon.setHeight(dto.getHeight());
                icon.setStory(dto.getStory());
                if(dto.getCreationDate()!=null && !dto.getCreationDate().isEmpty()) {
                    LocalDate date = LocalDate.parse(dto.getCreationDate(), formatter );
                    icon.setCreationDate(date);
                }
                // TODO update setCountries
                iconRepository.save(icon);
                IconDTO dtoUpdated = mapper.iconEntityToDTO(icon);
                return dtoUpdated;
            } else {
                throw new Exception("Icon not found.");
            }
        } catch (Exception e) {
            throw new Exception("Icon not found.");
        }
    }

    // DELETE
    public void deleteIconById(Long id) {
        iconRepository.deleteById(id);
    }
}
