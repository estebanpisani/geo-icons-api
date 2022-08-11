package com.alkemy.geoicons.controller;

import com.alkemy.geoicons.dto.IconBasicDTO;
import com.alkemy.geoicons.dto.IconDTO;
import com.alkemy.geoicons.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/icons")
public class IconController {
    @Autowired
    private IconService iconService;

    @PostMapping
    public ResponseEntity<IconDTO> saveIcon(@RequestBody IconDTO dto){
        IconDTO newIcon = iconService.saveIcon(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newIcon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IconDTO> updateIcon(@RequestBody IconDTO dto, @PathVariable Long id) throws Exception {
        IconDTO dtoUpdated = iconService.updateIcon(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoUpdated);
    }
    @GetMapping()
    public ResponseEntity<List<IconBasicDTO>> getAllIcons() throws Exception {
        List<IconBasicDTO> dtos = iconService.getAllIcons();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<IconDTO> getIconById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(iconService.getIconById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIconById(@PathVariable Long id){
        iconService.deleteIconById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
