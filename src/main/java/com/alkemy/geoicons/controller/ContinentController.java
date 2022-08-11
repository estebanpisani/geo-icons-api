package com.alkemy.geoicons.controller;

import com.alkemy.geoicons.dto.ContinentDTO;
import com.alkemy.geoicons.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/continents")
public class ContinentController {
    @Autowired
    private ContinentService continentService;

    @PostMapping
    public ResponseEntity<ContinentDTO> saveContinent(@RequestBody ContinentDTO dto){
        ContinentDTO newContinent = continentService.saveContinent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newContinent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContinentDTO> updateContinent(@RequestBody ContinentDTO dto, @PathVariable Long id) throws Exception {
        ContinentDTO dtoUpdated = continentService.updateContinent(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoUpdated);
    }
    @GetMapping("/")
    public ResponseEntity<List<ContinentDTO>> getAllContinents() throws Exception {
        List<ContinentDTO> dtos = continentService.getAllContinents();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContinentDTO> getContinentById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(continentService.getContinentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContinentById(@PathVariable Long id){
        continentService.deleteContinentById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
