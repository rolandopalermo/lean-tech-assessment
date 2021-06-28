package io.leanteach.assessment.repository;

import io.leanteach.assessment.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Optional<Candidate> findFirstByIdNumber(String idNumber);

}
