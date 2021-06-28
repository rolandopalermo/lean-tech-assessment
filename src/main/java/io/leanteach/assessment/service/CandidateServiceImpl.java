package io.leanteach.assessment.service;

import io.leanteach.assessment.domain.Candidate;
import io.leanteach.assessment.dto.CandidateDto;
import io.leanteach.assessment.exception.AlreadyExistsException;
import io.leanteach.assessment.exception.ResourceNotFoundException;
import io.leanteach.assessment.mapper.CandidateMapper;
import io.leanteach.assessment.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateMapper candidateMapper;
    private final CandidateRepository candidateRepository;

    @Override
    public CandidateDto save(CandidateDto candidateDto) {
        Optional<Candidate> optionalPosition = candidateRepository.findFirstByIdNumber(candidateDto.getIdNumber());
        if (optionalPosition.isPresent()) {
            throw new AlreadyExistsException(format("The candidate with the ID number [%s] already exists", candidateDto.getIdNumber()));
        }
        return candidateMapper.toDto(candidateRepository.save(candidateMapper.toDomain(candidateDto)));
    }

    @Override
    public Candidate update(CandidateDto candidateDto) {
        Optional<Candidate> optionalPosition = candidateRepository.findById(candidateDto.getId());
        optionalPosition.orElseThrow(
                () -> new ResourceNotFoundException(format("The candidate with the ID number [%s] does not exists", candidateDto.getIdNumber())));
        Candidate candidate = candidateMapper.toDomain(candidateDto);
        candidate.setId(candidateDto.getId());
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate findFirst(long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("The candidate [%s] does not exist", id)));
    }

    @Override
    public CandidateDto toDto(Candidate candidate) {
        return candidateMapper.toDto(candidate);
    }

}
