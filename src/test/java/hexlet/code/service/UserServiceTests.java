package hexlet.code.service;

import hexlet.code.dto.users.UserCreateDTO;
import hexlet.code.dto.users.UserDTO;
import hexlet.code.dto.users.UserUpdateDTO;
import hexlet.code.mapper.UserMapper;
import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public final class UserServiceTests {
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;
    @InjectMocks
    private UserService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowOne() {
        User user = new User("Artur", "Lastovskiy", "hexlet@email.com", "1234567890");
        user.setId(1L);
        user.setCreatedAt(new Date());

        UserDTO dto = UserDTO.builder().id(user.getId()).email(user.getEmail())
                .firstName(user.getFirstName()).lastName(user.getLastName())
                .createdAt(user.getCreatedAt()).build();

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(mapper.map(user)).thenReturn(dto);
        var actual = service.showOne(1L);
        assertThat(actual).isEqualTo(dto);
    }


    @Test
    public void testShowAll() {
        User one = new User("Artur", "Lastovskiy", "hexlet@email.com", "1234567890");
        User two = new User("Peter", "Parker", "parker@email.com", "0987654321");
        List<User> users = List.of(one, two);
        Mockito.when(repository.findAll()).thenReturn(users);
        var usersDto = service.showAll();
        assertThat(usersDto.size()).isEqualTo(users.size());
    }

    @Test
    public void testCreate() {
        UserCreateDTO createDto = new UserCreateDTO("Woody", "Griffin", "griffin@email.com", "0987654321");
        User model = new User("Woody", "Griffin", "griffin@email.com", "0987654321");
        UserDTO dto = UserDTO.builder().id(model.getId()).email(model.getEmail())
                .firstName(model.getFirstName()).lastName(model.getLastName())
                        .createdAt(model.getCreatedAt()).build();
        model.setId(1L);
        model.setCreatedAt(new Date());
        Mockito.when(mapper.map(createDto)).thenReturn(model);
        Mockito.when(repository.save(model)).thenReturn(model);
        Mockito.when(mapper.map(model)).thenReturn(dto);
        var actual = service.create(createDto);
        assertThat(actual).isEqualTo(dto);
    }

    @Test
    public void testUpdate() {
        User model = new User("Peter", "Parker", "parker@email.com", "0987654321");
        model.setId(1L);
        model.setCreatedAt(new Date());

        UserUpdateDTO update = UserUpdateDTO.builder().lastName(JsonNullable.of("Skywaker"))
                .email(JsonNullable.of("skywaker@email.com")).password(JsonNullable.of("12345")).build();

        UserDTO dto = UserDTO.builder().id(model.getId()).email(update.getEmail().get())
                        .firstName(model.getFirstName()).lastName(update.getEmail().get())
                        .createdAt(model.getCreatedAt()).build();

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(model));
        Mockito.doAnswer(invocation -> {
                    Object[] args = invocation.getArguments();
                    ((User) args[1]).setLastName(((UserUpdateDTO) args[0]).getLastName().get());
                    ((User) args[1]).setEmail(((UserUpdateDTO) args[0]).getEmail().get());
                    ((User) args[1]).setPassword(((UserUpdateDTO) args[0]).getPassword().get());
                    return null;
                }
        ).when(mapper).update(update, model);
        Mockito.when(repository.save(model)).thenReturn(model);
        Mockito.when(mapper.map(model)).thenReturn(dto);
        var actual = service.update(update, 1L);
        assertThat(actual).isEqualTo(dto);
    }

    @Test
    public void delete() {
        User model = new User("Peter", "Parker", "parker@email.com", "0987654321");
        model.setId(1L);
        model.setCreatedAt(new Date());
        List<User> list = new ArrayList<>();
        list.add(model);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(model)).thenReturn(Optional.empty());
        Mockito.doAnswer(invocation -> {
            list.remove(model);
            return null;
        }).when(repository).deleteById(1L);
        service.delete(1L);
        assertThat(list).isEmpty();
    }
 }
