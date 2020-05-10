package org.codingsquid.r2dbc.repository;

import org.codingsquid.r2dbc.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    Mono<User> findByName(String name);
}
