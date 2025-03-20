package br.com.GestaoVagas.company.controllers;

import br.com.GestaoVagas.dto.CreateJobDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(MockitoExtension.class)
public class CreateJobControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    @DisplayName("Should be able to create a new job")
    public void should_create_a_new_job(){

        var object = CreateJobDTO.builder().benefits("Benefits Test").description("Test").level("Test").build();

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            String contentObject = objectMapper.writeValueAsString(object);

            var result = mvc.perform(MockMvcRequestBuilders.post("/company/job")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(contentObject))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conversão do Json");
        }
    }
}
