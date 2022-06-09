package com.grupoM.BayerChallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupoM.BayerChallenge.entities.Symptom;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Long> {

}
