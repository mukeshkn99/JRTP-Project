package com.ashokit.repository;

import java.util.Optional;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CitizenAppsEntity;

public interface CitizenAppsRepository extends JpaRepository<CitizenAppsEntity, Id> {
Optional<CitizenAppsEntity> findByCaseNum(Integer caseNum);

}
