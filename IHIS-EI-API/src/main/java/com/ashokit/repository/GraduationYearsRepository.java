package com.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.GraduationYearsEntity;

public interface GraduationYearsRepository extends JpaRepository<GraduationYearsEntity, Integer> {

	@Query("Select year from GRADUATION_YEARS")
	List<GraduationYearsEntity> getyear();
}
