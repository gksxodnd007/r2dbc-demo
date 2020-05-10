package org.codingsquid.r2dbc.controller.model.request;

import lombok.Getter;
import lombok.Setter;
import org.codingsquid.r2dbc.entity.User;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
@Getter
@Setter
public class RegisterUserDTO {

    private String name;
    private int age;
    private String description;

    public User toEntity() {
        return new User(null, this.name, this.age, this.description);
    }
}
