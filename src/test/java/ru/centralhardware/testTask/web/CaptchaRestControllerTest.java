package ru.centralhardware.testTask.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CaptchaRestControllerTest {

    @Autowired
    private CaptchaRestController controller;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getCaptcha() throws Exception {
        mockMvc.perform(get("/get-captcha")).
                andExpect(status().isOk()).
                andExpect(header().exists("X-captcha_string")).
                andExpect(header().string("Cache-Control", "no-cache")).
                andExpect(header().exists("X-request-id")).
                andExpect(content().contentType("image/png"));
    }
}