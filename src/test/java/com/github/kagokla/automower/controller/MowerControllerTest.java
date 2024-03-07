package com.github.kagokla.automower.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.github.kagokla.automower.model.MowerPosition;
import com.github.kagokla.automower.model.Orientation;
import com.github.kagokla.automower.model.dto.CommandRequestDTO;
import com.github.kagokla.automower.model.dto.CommandResponseDTO;
import com.github.kagokla.automower.model.mapper.CommandRequestMapper;
import com.github.kagokla.automower.service.MowingService;
import com.github.kagokla.automower.spring.JacksonConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {MowerController.class, JacksonConfig.class})
class MowerControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MowingService mowingService;

    @BeforeAll
    static void setUp() {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    @Test
    void shouldReturn200OkWhenRequestIsValid() throws Exception {
        // Given
        final var area = "55";
        final var initialPosition = "12N";
        final var instructions = "LFLFLFLFF";
        final var commandRequest = buildCommandRequestDTOForOneMower(area, initialPosition, instructions);
        final var finalPosition = new MowerPosition();
        finalPosition.setX(1);
        finalPosition.setY(3);
        finalPosition.setOrientation(Orientation.NORTH);
        final var commandResponse = buildCommandResponseDTOForOneMower(commandRequest, finalPosition);

        Mockito.when(mowingService.processCommand(any())).thenReturn(commandResponse);

        // When
        this.mockMvc
                .perform(post("/auto-mowers")
                        .content(objectMapper.writeValueAsString(commandRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.area").value(area))
                .andExpect(jsonPath("$.mowers", hasSize(1)))
                .andExpect(jsonPath("$.mowers[0].initial_position").value(initialPosition))
                .andExpect(jsonPath("$.mowers[0].instructions").value(instructions))
                .andExpect(jsonPath("$.mowers[0].final_position").value("13N"));
    }

    @Test
    void shouldReturn400BadRequestWhenRequestIsInvalid() throws Exception {
        // Given
        final var area = "area";
        final var initialPosition = "33E";
        final var instructions = "FFRFFRFRRF";
        final var commandRequest = buildCommandRequestDTOForOneMower(area, initialPosition, instructions);
        final var finalPosition = new MowerPosition();
        finalPosition.setX(5);
        finalPosition.setY(1);
        finalPosition.setOrientation(Orientation.EAST);
        final var commandResponse = buildCommandResponseDTOForOneMower(commandRequest, finalPosition);

        Mockito.when(mowingService.processCommand(any())).thenReturn(commandResponse);

        // Then
        this.mockMvc
                .perform(post("/auto-mowers")
                        .content(objectMapper.writeValueAsString(commandRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    private CommandRequestDTO buildCommandRequestDTOForOneMower(
            final String area, final String initialPosition, final String instructions) {
        final var mower = new CommandRequestDTO.Mower();
        mower.setInitialPosition(initialPosition);
        mower.setInstructions(instructions);
        final var commandRequest = new CommandRequestDTO();
        commandRequest.setArea(area);
        commandRequest.setMowers(List.of(mower));

        return commandRequest;
    }

    private CommandResponseDTO buildCommandResponseDTOForOneMower(
            final CommandRequestDTO commandRequest, final MowerPosition finalPosition) {
        final var commandResponse = CommandRequestMapper.MAPPER.mapCommandRequestToCommandResponse(commandRequest);
        commandResponse.getMowers().forEach(mower -> mower.setFinalPosition(finalPosition));

        return commandResponse;
    }
}