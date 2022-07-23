package com.ashokit.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CaseWorkersAcctEntity;

public interface CaseWorkersAcctRepository extends JpaRepository<CaseWorkersAcctEntity, Integer> {
//public CaseWorkersAcctEntity findByEmailAndPazzword(String email,String pazzword);
//public CaseWorkersAcctEntity findByEmail(String email);
}
