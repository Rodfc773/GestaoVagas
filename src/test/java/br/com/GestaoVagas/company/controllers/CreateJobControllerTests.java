package br.com.GestaoVagas.company.controllers;

import br.com.GestaoVagas.Utils.TestUtils;
import br.com.GestaoVagas.dto.CreateJobDTO;
import br.com.GestaoVagas.models.CompanyEntity;
import br.com.GestaoVagas.repositories.interfaces.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static br.com.GestaoVagas.Utils.TestUtils.ObjectToJSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTests {

    private MockMvc mvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup(){
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }
    @Test
    public void shouldCreateJobSuccessfully(){

        var company = CompanyEntity.builder()
                .description("Company Test Integration")
                .email("teste@test.com")
                .password("teste123456")
                .name("teste").build();


        company = companyRepository.saveAndFlush(company);

        var object = CreateJobDTO.builder().benefits("Benefits Test").description("Test").level("Test").build();

        String contentObject = ObjectToJSON(object);

        try {

            var result = mvc.perform(MockMvcRequestBuilders.post("/company/jobs/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", TestUtils.generateToken(company.getId(), "JAVACOURSe@$5asl"))
                    .content(contentObject))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException("Erro no teste");
        }
    }
    @Test
    @DisplayName("Should not be able to crate a job if company doesn't exists ")
    public void shouldNotBeAbleToCreateAJob(){

        var object = CreateJobDTO.builder().benefits("Benefits Test").description("Test").level("Test").build();

        String contentObject = ObjectToJSON(object);

        try {

            mvc.perform(MockMvcRequestBuilders.post("/company/jobs/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), "JAVACOURSe@$5asl"))
                            .content(contentObject))
                            .andExpect(MockMvcResultMatchers.status().is(404));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
