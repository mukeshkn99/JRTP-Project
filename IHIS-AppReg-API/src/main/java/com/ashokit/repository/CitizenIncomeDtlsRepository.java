package com.ashokit.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CitizenIncomeDtlsEntity;

public interface CitizenIncomeDtlsRepository extends JpaRepository<CitizenIncomeDtlsEntity, Id> {

}
