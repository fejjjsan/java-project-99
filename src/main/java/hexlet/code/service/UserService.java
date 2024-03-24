package hexlet.code.service;


import hexlet.code.dto.users.UserDTO;
import hexlet.code.dto.users.UserCreateDTO;
import hexlet.code.dto.users.UserUpdateDTO;
import hexlet.code.repository.UserRepository;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.UserMapper;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class UserService {
    private final UserRepository repository;

    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDTO showOne(Long id) {
        var model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        var dto = mapper.map(model);
        return dto;
    }
    public List<UserDTO> showAll() {
        var users = repository.findAll();
        return users.stream().map(mapper::map).toList();
    }

    public UserDTO create(UserCreateDTO create) {
        var model = mapper.map(create);
        repository.save(model);
        var dto = mapper.map(model);
        return dto;
    }
    public UserDTO update(UserUpdateDTO update, Long id) {
        var model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        mapper.update(update, model);
        repository.save(model);
        var dto = mapper.map(model);
        return dto;
    }
    public void delete(Long id) {
        var user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        repository.deleteById(user.getId());
    }
}
