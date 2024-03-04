package com.github.kagokla.automower;

import com.github.kagokla.automower.controller.MowerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AutomowerApplicationTest {

    @Autowired
    private Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer;

    @Autowired
    private MowerController controller;

    @Test
    void shouldLoadContext() {
        assertThat(jacksonCustomizer).isNotNull();
        assertThat(controller).isNotNull();
    }
}
