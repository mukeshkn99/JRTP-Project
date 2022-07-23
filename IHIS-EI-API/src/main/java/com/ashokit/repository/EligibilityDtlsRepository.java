package com.ashokit.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.EligibilityDtlsEntity;

public interface EligibilityDtlsRepository extends JpaRepository<EligibilityDtlsEntity, Id> {
public EligibilityDtlsEntity findByCaseNum(Integer caseNum);
}
