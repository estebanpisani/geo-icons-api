package com.alkemy.geoicons.controller;

import com.alkemy.geoicons.dto.CountryBasicDTO;
import com.alkemy.geoicons.dto.CountryDTO;
import com.alkemy.geoicons.dto.IconDTO;
import com.alkemy.geoicons.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/cities")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping
    public ResponseEntity<CountryDTO> saveCountry(@RequestBody CountryDTO dto){
        CountryDTO newCountry = countryService.saveCountry(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCountry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO dto, @PathVariable Long id) throws Exception {
        CountryDTO dtoUpdated = countryService.updateCountry(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoUpdated);
    }
    @GetMapping()
    public ResponseEntity<List<CountryBasicDTO>> getAllCountries() throws Exception {
        List<CountryBasicDTO> dtos = countryService.getAllCountries();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(countryService.getCountryById(id));
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long continent,
            @RequestParam(required = false, defaultValue = "asc") String order
    ){
        List<CountryDTO> countries = countryService.getByFilters(name, continent, order);
        return ResponseEntity.ok(countries);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountryById(@PathVariable Long id){
        countryService.deleteCountryById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
