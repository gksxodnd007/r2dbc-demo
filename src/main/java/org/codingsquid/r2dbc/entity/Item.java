package org.codingsquid.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author hubert.squid
 * @since 2020.04.19
 */
@Data
@AllArgsConstructor
@Table(value = "items")
public class Item {

    @Id
    private Long id;
    @Column(value = "user_id")
    private Long userId;
    @Column(value = "name")
    private String name;
    @Column(value = "price")
    private double price;
}
