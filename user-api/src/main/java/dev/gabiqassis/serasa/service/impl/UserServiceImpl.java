package dev.gabiqassis.serasa.service.impl;

import dev.gabiqassis.serasa.client.OrderHttpClient;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderHttpClient httpClient;

    private final UserMapper userMapper;

    @Transactional
    @Override
    @Caching(evict = {
            @CacheEvict(value = "userCache", key = "#result.id"),
            @CacheEvict(value = "userCache", key = "'userList'")
    })
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
    @Cacheable(value = "userCache", key = "#id")
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
    @Caching(evict = {
            @CacheEvict(value = "userCache", key = "#id"),
            @CacheEvict(value = "userCache", key = "'userList'")
    })
    @Override
    public void deleteById(Long id) {
        logger.info("Iniciando remoção do usuário com ID: {}", id);

        userRepository.findById(id)
                .ifPresentOrElse(
                        user -> {
                            if (hasOrder(id)) {
                                logger.error("O usuário com ID {} possui pedidos e não pode ser excluído", id);
                                throw new IllegalStateException("O usuário possui pedidos e não pode ser excluído");
                            }
                            processUserDeletion(user, id);
                        },
                        () -> {
                            logger.error("Usuário com ID {} não encontrado para deleção", id);
                            throw new EntityNotFoundException("Usuário não encontrado");
                        }
                );
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "userCache", key = "#id"),
            @CacheEvict(value = "userCache", key = "'userList'")
    })
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
    @Cacheable(value = "userCache", key = "'userList'")
    @Override
    public List<UserResponse> findAll() {
        logger.info("Iniciando busca por todos os usuários");

        List<User> users = userRepository.findAll();
        logger.info("Total de usuários encontrados: {}", users.size());

        return users.stream()
                .map(userMapper::map)
                .toList();
    }

    private void processUserDeletion(User user, Long id) {
        logger.debug("Usuário encontrado para remoção");
        userRepository.delete(user);
        logger.info("Usuário com ID {} deletado com sucesso", id);
    }

    private boolean hasOrder(Long id){

        return httpClient.hasOrders(String.valueOf(id));
    }
}
