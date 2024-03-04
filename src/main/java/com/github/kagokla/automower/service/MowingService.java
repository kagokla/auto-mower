package com.github.kagokla.automower.service;

import com.github.kagokla.automower.model.dto.CommandRequestDTO;
import com.github.kagokla.automower.model.dto.CommandResponseDTO;

public interface MowingService {

    CommandResponseDTO processCommand(CommandRequestDTO commandRequest);
}
