package com.grupoM.BayerChallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupoM.BayerChallenge.entities.Disease;



@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long>{

}
