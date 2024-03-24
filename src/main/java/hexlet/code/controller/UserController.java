package hexlet.code.controller;


import hexlet.code.dto.users.UserCreateDTO;
import hexlet.code.dto.users.UserUpdateDTO;
import hexlet.code.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api")
public final class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        var userDto = service.showOne(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> all() {
        var users = service.showAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody @Valid UserCreateDTO create) {
        var user = service.create(create);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UserUpdateDTO update, @PathVariable Long id) {
        var user = service.update(update, id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
