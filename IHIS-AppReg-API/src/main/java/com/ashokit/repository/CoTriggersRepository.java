package com.ashokit.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CoTriggersEntity;

public interface CoTriggersRepository extends JpaRepository<CoTriggersEntity, Id> {

}
