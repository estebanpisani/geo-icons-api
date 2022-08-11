package com.alkemy.geoicons.service;

import com.alkemy.geoicons.dto.IconBasicDTO;
import com.alkemy.geoicons.dto.IconDTO;
import com.alkemy.geoicons.entity.IconEntity;
import com.alkemy.geoicons.mapper.IconMapper;
import com.alkemy.geoicons.repository.IconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IconService {
    @Autowired
    private IconMapper mapper;
    @Autowired
    private IconRepository iconRepository;

    // CREATE
    public IconDTO save(IconDTO dto) {
        IconEntity entity = mapper.iconDTOToEntity(dto);
        IconEntity newIcon = iconRepository.save(entity);
        return mapper.iconEntityToDTO(newIcon);
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

    //UPDATE
    public IconDTO update(IconDTO dto, Long id) throws Exception {
        try {
            Optional<IconEntity> result = iconRepository.findById(id);

            if (result.isPresent()) {
                IconEntity icon = result.get();
                icon.setName(dto.getName());
                icon.setImage(dto.getImage());
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
