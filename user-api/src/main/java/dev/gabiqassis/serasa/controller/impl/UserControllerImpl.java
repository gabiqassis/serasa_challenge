package dev.gabiqassis.serasa.controller.impl;

import dev.gabiqassis.serasa.controller.UserController;
import dev.gabiqassis.serasa.domain.request.UserCreaterRequest;
import dev.gabiqassis.serasa.domain.request.UserUpdateRequest;
import dev.gabiqassis.serasa.domain.response.UserResponse;
import dev.gabiqassis.serasa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<UserResponse> create(UserCreaterRequest userCreaterRequest) {
        return ResponseEntity.status(CREATED).body(userService.create(userCreaterRequest));
    }

    @Override
    public ResponseEntity<UserResponse> findById(Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Override
    public ResponseEntity<UserResponse> update(Long id, UserUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok(userService.update(id, userUpdateRequest));
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>("Usuário deletado com sucesso", HttpStatus.OK); }
        catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar o usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
