package dev.gabiqassis.serasa.service.impl;

import dev.gabiqassis.serasa.domain.entity.User;
import dev.gabiqassis.serasa.domain.mapper.UserMapper;
import dev.gabiqassis.serasa.domain.request.UserCreaterRequest;
import dev.gabiqassis.serasa.domain.request.UserUpdateRequest;
import dev.gabiqassis.serasa.domain.response.UserResponse;
import dev.gabiqassis.serasa.domain.util.Validation;
import dev.gabiqassis.serasa.repository.UserRepository;
import dev.gabiqassis.serasa.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserResponse create(UserCreaterRequest userCreaterRequest) {
        logger.info("Iniciando a criação de um novo usuário");

        try {
            Validation.validationField(userCreaterRequest);
        } catch (IllegalArgumentException e) {
            logger.error("Erro de validação: {}", e.getMessage());
            throw e;
        }

        User user = userMapper.map(userCreaterRequest);
        user.setUpdatedAt(null);

        User savedUser = userRepository.save(user);
        logger.info("Usuário criado com sucesso. ID gerado {}", savedUser.getId());

        return userMapper.map(savedUser);
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse findById(Long id) {
        logger.info("Iniciando busca pelo usuário com ID {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Usuário com ID {} não encontrado", id);
                    return new EntityNotFoundException("Usuário não encontrado");
                });

        logger.info("Usuário encontrado");
        return userMapper.map(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        logger.info("Iniciando remoção do usuário com ID: {}", id);

        userRepository.findById(id)
                .ifPresentOrElse(
                        user -> {
                            if (hasOrder(user)) {
                                logger.error("Não foi possível deletar o usuário com ID {} porque ele tem pedidos associados", id);
                                throw new IllegalStateException("Não é possível deletar um usuário com pedidos");
                            }

                            logger.debug("Usuário encontrado para remoção");
                            userRepository.delete(user);
                            logger.info("Usuário com ID {} deletado com sucesso", id);
                        },
                        () -> {
                            logger.error("Usuário com ID {} não encontrado para deleção", id);
                            throw new EntityNotFoundException("Usuário não encontrado");
                        }
                );
    }

    @Transactional
    @Override
    public UserResponse update(Long id, UserUpdateRequest userUpdateRequest) {
        logger.info("Iniciando atualização do usuário com ID: {}", id);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Usuário com ID {} não encontrado para atualização", id);
                    return new EntityNotFoundException("Usuário não encontrado");
                });
        logger.debug("Usuário encontrado para atualização: {}", existingUser);

        try {
            Validation.validationField(userUpdateRequest);
        } catch (IllegalArgumentException e) {
            logger.error("Erro de validação: {}", e.getMessage());
            throw e;
        }

        userMapper.map(userUpdateRequest, existingUser);
        User updatedUser = userRepository.save(existingUser);

        logger.info("Usuário com ID {} atualizado com sucesso", updatedUser.getId());

        return userMapper.map(updatedUser);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse> findAll() {
        logger.info("Iniciando busca por todos os usuários");

        List<User> users = userRepository.findAll();
        logger.info("Total de usuários encontrados: {}", users.size());

        return users.stream()
                .map(userMapper::map)
                .toList();
    }

    private boolean hasOrder(User user) {
        return false;
    }
}
