package org.codingsquid.r2dbc.controller;

import lombok.RequiredArgsConstructor;
import org.codingsquid.r2dbc.controller.model.response.ItemInfoDTO;
import org.codingsquid.r2dbc.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(path = "/items")
    public Flux<ItemInfoDTO> doGet(@RequestParam("userId") long userId) {
        return itemService.getByUserId(userId)
            .map(ItemInfoDTO::from);
    }
}
