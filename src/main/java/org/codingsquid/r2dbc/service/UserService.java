package org.codingsquid.r2dbc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codingsquid.r2dbc.entity.User;
import org.codingsquid.r2dbc.exception.NotFoundEntityException;
import org.codingsquid.r2dbc.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> getByName(String name) {
        return userRepository.findByName(name)
            .switchIfEmpty(Mono.error(new NotFoundEntityException()));
    }

    @Transactional
    public Mono<User> save(User entity) {
        return userRepository.save(entity);
    }
}
