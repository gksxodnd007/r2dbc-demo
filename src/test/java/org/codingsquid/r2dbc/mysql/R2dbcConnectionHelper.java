package org.codingsquid.r2dbc.mysql;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;

import java.time.Duration;

/**
 * @author hubert.squid
 * @since 2020.04.18
 */
public class R2dbcConnectionHelper {

    public static MySqlConnectionConfiguration getConfiguration(String host, String username, String password, String database) {
        return MySqlConnectionConfiguration.builder()
            .host(host)
            .username(username)
            .password(password)
            .database(database)
            .port(3306)
            .connectTimeout(Duration.ofSeconds(5))
            .useServerPrepareStatement()
            .build();
    }
}
