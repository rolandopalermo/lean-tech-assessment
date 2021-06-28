package io.leanteach.assessment.service;

import io.leanteach.assessment.domain.Candidate;
import io.leanteach.assessment.dto.CandidateDto;
import io.leanteach.assessment.exception.AlreadyExistsException;
import io.leanteach.assessment.mapper.CandidateMapper;
import io.leanteach.assessment.repository.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

    @Mock
    CandidateRepository candidateRepository;

    @Mock
    CandidateMapper candidateMapper;

    @InjectMocks
    CandidateServiceImpl candidateService;

    @Test
    public void Given_ValidCandidate_When_Save_Then_Success() {
        when(candidateRepository.findFirstByIdNumber(any())).thenReturn(Optional.empty());
        when(candidateMapper.toDto(any())).thenReturn(new CandidateDto());
        candidateService.save(new CandidateDto());
        verify(candidateRepository, times(1)).save(any());
    }

    @Test
    public void Given_ExistingCandidate_When_Save_Then_ThrowException() {
        when(candidateRepository.findFirstByIdNumber(any())).thenReturn(Optional.of(new Candidate()));
        assertThrows(AlreadyExistsException.class, () -> candidateService.save(new CandidateDto()));
    }

}
