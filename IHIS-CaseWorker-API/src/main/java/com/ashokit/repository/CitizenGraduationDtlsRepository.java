package com.ashokit.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CitizenGraduationDtlsEntity;

public interface CitizenGraduationDtlsRepository extends JpaRepository<CitizenGraduationDtlsEntity, Id> {

}
