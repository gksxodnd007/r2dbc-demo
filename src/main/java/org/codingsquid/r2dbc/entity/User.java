package org.codingsquid.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hubert.squid
 * @since 2020.04.18
 */
@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private int age;
    private String description;
}
