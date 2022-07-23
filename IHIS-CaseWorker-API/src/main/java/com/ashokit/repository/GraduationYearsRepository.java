package com.ashokit.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.GraduationYearsEntity;

public interface GraduationYearsRepository extends JpaRepository<GraduationYearsEntity, Id>{

}
