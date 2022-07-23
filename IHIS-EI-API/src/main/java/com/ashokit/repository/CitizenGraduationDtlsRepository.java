package com.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.CitizenGraduationDtlsEntity;
import com.ashokit.entity.GraduationYearsEntity;

public interface CitizenGraduationDtlsRepository extends JpaRepository<CitizenGraduationDtlsEntity, Integer> {
	CitizenGraduationDtlsEntity findByCaseNum(Integer caseNum);
	@Query("Select ditinct(year) from GRADUATION_YEARS")
	List<GraduationYearsEntity> getyear();
}
