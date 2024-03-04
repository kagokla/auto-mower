package com.github.kagokla.automower.controller;

import com.github.kagokla.automower.model.dto.CommandRequestDTO;
import com.github.kagokla.automower.model.dto.CommandResponseDTO;
import com.github.kagokla.automower.service.MowingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MowerController {

    private final MowingService mowingService;

    @PostMapping(
            path = "/auto-mowers",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public CommandResponseDTO handleMowerCommand(@RequestBody @Valid CommandRequestDTO requestDTO) {

        return mowingService.processCommand(requestDTO);
    }
}
