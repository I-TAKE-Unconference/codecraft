package com.itakeunconf.codecraft.repository;

import com.itakeunconf.codecraft.model.PairingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PairingSessionRepository extends JpaRepository<PairingSession, Long> {

    public List<PairingSession> findAllByOrderByIdDesc();
}
