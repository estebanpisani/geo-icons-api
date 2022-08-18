package com.alkemy.geoicons.repository;

import com.alkemy.geoicons.entity.CountryEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long>, JpaSpecificationExecutor<CountryEntity> {
    List<CountryEntity> findAll(Specification<CountryEntity> spec);
}
