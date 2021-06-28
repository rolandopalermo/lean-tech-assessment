package io.leanteach.assessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.leanteach.assessment.dto.CandidateDto;
import io.leanteach.assessment.exception.AlreadyExistsException;
import io.leanteach.assessment.service.CandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CandidateController.class)
public class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidateService candidateService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Given_ValidRequest_When_Save_Then_201() throws Exception {
        given(candidateService.save(any())).willReturn(new CandidateDto());
        this.mockMvc.perform(post("/api/v1/candidates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new CandidateDto())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success", is(true)));
    }

    @Test
    public void Given_ValidRequest_When_Save_Then_409() throws Exception {
        given(candidateService.save(any())).willThrow(new AlreadyExistsException(""));
        this.mockMvc.perform(post("/api/v1/candidates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new CandidateDto())))
                .andExpect(status().isConflict());
    }

}
