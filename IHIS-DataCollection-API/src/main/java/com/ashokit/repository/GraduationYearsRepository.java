package com.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.GraduationYearsEntity;

public interface GraduationYearsRepository extends JpaRepository<GraduationYearsEntity, Integer> {
	@Query(value="select distinct(year) from GRADUATION_YEARS")
    public List<GraduationYearsEntity> findyear();
}
