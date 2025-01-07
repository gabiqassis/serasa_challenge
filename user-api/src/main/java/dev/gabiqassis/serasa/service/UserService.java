package dev.gabiqassis.serasa.service;

import dev.gabiqassis.serasa.domain.request.UserCreaterRequest;
import dev.gabiqassis.serasa.domain.request.UserUpdateRequest;
import dev.gabiqassis.serasa.domain.response.UserResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    UserResponse create(UserCreaterRequest userCreaterRequest);

    UserResponse update(Long id, UserUpdateRequest userUpdateRequest);

    UserResponse findById(Long id);

    void deleteById(Long id);

    List<UserResponse> findAll();
}

