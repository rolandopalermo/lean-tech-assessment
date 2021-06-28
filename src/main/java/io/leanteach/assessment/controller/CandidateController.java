package io.leanteach.assessment.controller;

import io.leanteach.assessment.dto.CandidateDto;
import io.leanteach.assessment.dto.HttpResponseDto;
import io.leanteach.assessment.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"api/v1/candidates"})
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<HttpResponseDto<CandidateDto>> save(@RequestBody CandidateDto candidateDto) {
        return new ResponseEntity<>(new HttpResponseDto(true, candidateService.save(candidateDto)), HttpStatus.CREATED);
    }

}
