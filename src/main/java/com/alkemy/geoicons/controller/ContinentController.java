package com.alkemy.geoicons.controller;

import com.alkemy.geoicons.dto.ContinentDTO;
import com.alkemy.geoicons.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("continents")
public class ContinentController {
    @Autowired
    private ContinentService continentService;

    @GetMapping()
    public ResponseEntity<List<ContinentDTO>> getAllContinents(){
        List<ContinentDTO> dtos = continentService.getAllContinents();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping
    public ResponseEntity<ContinentDTO> save(@RequestBody ContinentDTO dto){
        ContinentDTO newContinent = continentService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newContinent);
    }

}
