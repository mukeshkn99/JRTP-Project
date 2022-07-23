package com.ashokit.repository;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CitizenChildDtlsEntity;

public interface CitizenChildDtlsRepository extends JpaRepository<CitizenChildDtlsEntity, Id> {
List<CitizenChildDtlsEntity> findByCaseNum(Integer caseNum);
}
