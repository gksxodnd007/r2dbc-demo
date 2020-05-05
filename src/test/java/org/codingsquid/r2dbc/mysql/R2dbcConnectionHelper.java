package org.codingsquid.r2dbc.mysql;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.data.r2dbc.core.DatabaseClient;

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
            .useClientPrepareStatement()
            .build();
    }

    public static MySqlConnectionConfiguration getConfiguration() {
        return getConfiguration("localhost", "root", "1234", "r2dbc");
    }

    public static ConnectionFactory getMysqlConnectionFactory() {
        return MySqlConnectionFactory.from(getConfiguration());
    }

    public static DatabaseClient getClient() {
        return getClient(getMysqlConnectionFactory());
    }

    public static DatabaseClient getClient(ConnectionFactory factory) {
        return DatabaseClient.create(factory);
    }
}
