package hexlet.code.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.dto.users.UserCreateDTO;
import hexlet.code.dto.users.UserUpdateDTO;
import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.instancio.Select.field;
import org.instancio.Instancio;
import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public final class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository repository;
    @Autowired
    private Faker faker;
    @Autowired
    private ObjectMapper mapper;
    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = Instancio.of(User.class)
                .supply(field(User::getEmail), () -> faker.internet().emailAddress())
                .supply(field(User::getFirstName), () -> faker.name().firstName())
                .supply(field(User::getLastName), () -> faker.name().lastName())
                .supply(field(User::getPassword), () -> faker.internet().password())
                .create();
    }

    @Test
    public void oneTest() throws Exception {
        repository.save(testUser);
        this.mockMvc.perform(get("/api/users/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void allTest() throws Exception {
        repository.save(testUser);
        this.mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        var createDto = Instancio.of(UserCreateDTO.class)
                .supply(field(UserCreateDTO::getEmail), () -> faker.internet().emailAddress())
                .supply(field(UserCreateDTO::getFirstName), () -> faker.name().firstName())
                .supply(field(UserCreateDTO::getLastName), () -> faker.name().lastName())
                .supply(field(UserCreateDTO::getPassword), () -> faker.internet().password())
                .create();

        var request = post("/api/users")
                .content(mapper.writeValueAsString(createDto))
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    public void updateTest() throws Exception {
        repository.save(testUser);
        var updateDto = Instancio.of(UserUpdateDTO.class)
                .supply(field(UserUpdateDTO::getEmail), () -> JsonNullable.of(faker.internet().emailAddress()))
                .supply(field(UserUpdateDTO::getFirstName), () -> JsonNullable.of(faker.name().firstName()))
                .supply(field(UserUpdateDTO::getPassword), () -> JsonNullable.of(faker.internet().password()))
                .create();

        var request = put("/api/users/{id}", 1L)
                .content(mapper.writeValueAsString(updateDto))
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isOk());

        var user = repository.findById(1L).get();
        assertThat(user.getEmail()).isEqualTo(updateDto.getEmail().get());
        assertThat(user.getFirstName()).isEqualTo(updateDto.getFirstName().get());
        assertThat(user.getPassword()).isEqualTo(updateDto.getPassword().get());
    }

    @Test
    public void deleteTest() throws Exception {
        repository.save(testUser);
        var request = delete("/api/users/{id}", 1L);
        this.mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
}


