package org.codingsquid.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author hubert.squid
 * @since 2020.04.18
 */
@Data
@AllArgsConstructor
@Table(value = "users")
public class User {

    @Id
    private Long id;
    @Column(value = "name")
    private String name;
    @Column(value = "age")
    private int age;
    @Column(value = "description")
    private String description;
}
