package com.ashokit.repository;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CitizenChildDtlsEntity;
import com.ashokit.entity.CitizenGraduationDtlsEntity;

public interface CitizenGraduationDtlsRepository extends JpaRepository<CitizenGraduationDtlsEntity, Integer> {
	
}
