package io.leanteach.assessment.controller;

import io.leanteach.assessment.dto.HttpResponseDto;
import io.leanteach.assessment.dto.PositionDto;
import io.leanteach.assessment.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"api/v1/positions"})
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping
    public ResponseEntity<HttpResponseDto<PositionDto>> save(@RequestBody PositionDto positionDto) {
        return new ResponseEntity<>(new HttpResponseDto(true, positionService.save(positionDto)), HttpStatus.CREATED);
    }

}
