package org.codingsquid.r2dbc.controller;

import lombok.RequiredArgsConstructor;
import org.codingsquid.r2dbc.controller.model.request.RegisterUserDTO;
import org.codingsquid.r2dbc.entity.User;
import org.codingsquid.r2dbc.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/users")
    public Mono<User> doGet(@RequestParam("name") String name) {
        return userService.getByName(name);
    }

    @PostMapping(path = "/users")
    public Mono<User> doPost(@RequestBody RegisterUserDTO dto) {
        return userService.save(dto.toEntity());
    }
}
