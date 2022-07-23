package com.ashokit.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.ashokit.entity.GraduationYearsEntity;

public interface GraduationYearsRepository extends JpaRepository<GraduationYearsEntity, Integer> {
	
	}
