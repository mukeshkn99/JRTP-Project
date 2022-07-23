package com.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CoTriggersEntity;

public interface CoTriggersRepository extends JpaRepository<CoTriggersEntity, Integer> {
public List<CoTriggersEntity> findByTrgStatus(String trgStatus);
}
