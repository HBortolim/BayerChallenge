package com.grupoM.BayerChallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupoM.BayerChallenge.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
