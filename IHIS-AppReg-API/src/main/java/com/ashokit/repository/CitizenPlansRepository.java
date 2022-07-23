package com.ashokit.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CitizenPlansEntity;

public interface CitizenPlansRepository extends JpaRepository<CitizenPlansEntity, Id> {

}
