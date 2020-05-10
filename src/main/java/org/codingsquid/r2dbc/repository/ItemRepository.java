package org.codingsquid.r2dbc.repository;

import org.codingsquid.r2dbc.entity.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
public interface ItemRepository extends ReactiveCrudRepository<Item, Long>, ItemCustomRepository {

}
