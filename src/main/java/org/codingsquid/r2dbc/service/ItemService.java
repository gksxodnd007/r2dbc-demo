package org.codingsquid.r2dbc.service;

import lombok.RequiredArgsConstructor;
import org.codingsquid.r2dbc.repository.ItemRepository;
import org.codingsquid.r2dbc.repository.projection.ItemWithUser;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public Flux<ItemWithUser> getByUserId(long userId) {
        return itemRepository.findByUserId(userId);
    }
}
