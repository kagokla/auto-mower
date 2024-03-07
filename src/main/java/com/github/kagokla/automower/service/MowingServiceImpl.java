package com.github.kagokla.automower.service;

import com.github.kagokla.automower.model.LawnArea;
import com.github.kagokla.automower.model.MowerPosition;
import com.github.kagokla.automower.model.dto.CommandRequestDTO;
import com.github.kagokla.automower.model.dto.CommandResponseDTO;
import com.github.kagokla.automower.model.mapper.CommandRequestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MowingServiceImpl implements MowingService {

    @Override
    public CommandResponseDTO processCommand(final CommandRequestDTO commandRequest) {
        final var commandResponse = CommandRequestMapper.MAPPER.mapCommandRequestToCommandResponse(commandRequest);

        if (null != commandResponse && null != commandResponse.getArea()) {
            final var lawnArea = commandResponse.getArea();

            commandResponse.getMowers().stream()
                    .filter(mower -> null != mower && null != mower.getInitialPosition())
                    .forEach(mower -> {
                        if (isMowerInitialPositionOutsideArea(lawnArea, mower.getInitialPosition())) {
                            log.error(
                                    "The commands were not processed for the mower with the initial position: {}. Reason: Mower is outside the lawn",
                                    mower.getInitialPosition());
                            return;
                        }
                        final var currentPosition = new MowerPosition(mower.getInitialPosition());

                        mower.getInstructions().forEach(instruction -> {
                            switch (instruction) {
                                case FORWARD -> moveForward(lawnArea, currentPosition);
                                case LEFT -> currentPosition.rotateLeft();
                                case RIGHT -> currentPosition.rotateRight();
                            }
                        });

                        mower.setFinalPosition(currentPosition);
                    });
        }
        return commandResponse;
    }

    private boolean isMowerInitialPositionOutsideArea(final LawnArea lawnArea, final MowerPosition initialPosition) {
        return initialPosition.getX() < 0
                || initialPosition.getY() < 0
                || initialPosition.getX() > lawnArea.width()
                || initialPosition.getY() > lawnArea.height();
    }

    private void moveForward(final LawnArea area, final MowerPosition position) {
        switch (position.getOrientation()) {
            case NORTH -> {
                if (area.isLongerThanPosition(position.getY())) {
                    position.incrementY();
                }
            }
            case EAST -> {
                if (area.isWiderThanPosition(position.getX())) {
                    position.incrementX();
                }
            }
            case WEST -> {
                if (position.getX() > 0) {
                    position.decrementX();
                }
            }
            case SOUTH -> {
                if (position.getY() > 0) {
                    position.decrementY();
                }
            }
        }
    }
}
