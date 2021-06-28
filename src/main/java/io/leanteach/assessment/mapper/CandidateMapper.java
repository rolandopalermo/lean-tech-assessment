package io.leanteach.assessment.mapper;

import io.leanteach.assessment.domain.Candidate;
import io.leanteach.assessment.dto.CandidateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class CandidateMapper {

    public abstract CandidateDto toDto(Candidate candidate);

    @Mapping(target = "id", ignore = true)
    public abstract Candidate toDomain(CandidateDto candidateDto);

}
