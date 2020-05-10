package org.codingsquid.r2dbc.repository;

import org.codingsquid.r2dbc.repository.projection.ItemWithUser;
import reactor.core.publisher.Flux;

/**
 * @author hubert.squid
 * @since 2020.05.11
 */
public interface ItemCustomRepository {

    Flux<ItemWithUser> findByUserId(long userId);
}
