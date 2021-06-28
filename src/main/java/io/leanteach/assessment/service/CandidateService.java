package io.leanteach.assessment.service;

import io.leanteach.assessment.domain.Candidate;
import io.leanteach.assessment.dto.CandidateDto;

public interface CandidateService {

    CandidateDto save(CandidateDto candidateDto);

    Candidate findFirst(long id);

    CandidateDto toDto(Candidate candidate);

}
