package io.leanteach.assessment.repository;

import io.leanteach.assessment.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {

    Optional<Position> findFirstByName(String name);

}
