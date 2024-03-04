package com.github.kagokla.automower.service;

import com.github.kagokla.automower.model.dto.CommandRequestDTO;
import com.github.kagokla.automower.model.dto.CommandResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MowingServiceImpl implements MowingService {

    @Override
    public CommandResponseDTO processCommand(final CommandRequestDTO commandRequest) {
        return null;
    }
}
