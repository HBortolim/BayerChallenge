package com.grupoM.BayerChallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupoM.BayerChallenge.entities.Ocurrence;

@Repository
public interface OcurrenceRepository extends JpaRepository<Ocurrence,Long>{

}
