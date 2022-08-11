package com.alkemy.geoicons.repository;

import com.alkemy.geoicons.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
}
