package com.alkemy.geoicons.repository;

import com.alkemy.geoicons.entity.IconEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IconRepository extends JpaRepository<IconEntity, Long> {
}
