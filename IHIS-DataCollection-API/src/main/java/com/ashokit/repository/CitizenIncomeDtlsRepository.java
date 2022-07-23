package com.ashokit.repository;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CitizenChildDtlsEntity;
import com.ashokit.entity.CitizenIncomeDtlsEntity;

public interface CitizenIncomeDtlsRepository extends JpaRepository<CitizenIncomeDtlsEntity, Id> {
	public List<CitizenIncomeDtlsEntity> findByCaseNum(Integer caseNum);
}
