package io.leanteach.assessment.service;

import io.leanteach.assessment.domain.Position;
import io.leanteach.assessment.dto.PositionDto;
import io.leanteach.assessment.exception.AlreadyExistsException;
import io.leanteach.assessment.exception.ResourceNotFoundException;
import io.leanteach.assessment.mapper.PositionMapper;
import io.leanteach.assessment.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionMapper positionMapper;
    private final PositionRepository positionRepository;

    @Override
    public PositionDto save(PositionDto positionDto) {
        Optional<Position> optionalPosition = positionRepository.findFirstByName(positionDto.getName());
        if (optionalPosition.isPresent()) {
            throw new AlreadyExistsException(format("The position [%s] already exists", positionDto.getName()));
        }
        return positionMapper.toDto(positionRepository.save(positionMapper.toDomain(positionDto)));
    }

    @Override
    public Position findFirst(long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("The position [%s] does not exist", id)));
    }

    @Override
    public PositionDto toDto(Position position) {
        return positionMapper.toDto(position);
    }

}
