package io.leanteach.assessment.mapper;

import io.leanteach.assessment.domain.Position;
import io.leanteach.assessment.dto.PositionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class PositionMapper {

    public abstract PositionDto toDto(Position position);

    @Mapping(target = "id", ignore = true)
    public abstract Position toDomain(PositionDto positionDto);

}
