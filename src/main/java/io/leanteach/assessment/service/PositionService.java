package io.leanteach.assessment.service;

import io.leanteach.assessment.domain.Position;
import io.leanteach.assessment.dto.PositionDto;

public interface PositionService {

    PositionDto save(PositionDto positionDto);

    Position findFirst(long id);

    PositionDto toDto(Position position);

}
