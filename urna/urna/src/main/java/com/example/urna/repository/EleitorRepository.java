package com.example.urna.repository;

import com.example.urna.model.EleitorElegivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EleitorRepository extends JpaRepository<EleitorElegivel, Long> {
    Optional<EleitorElegivel> findByToken(String token);
}
