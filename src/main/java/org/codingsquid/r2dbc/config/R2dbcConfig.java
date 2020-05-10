package org.codingsquid.r2dbc.config;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.ReactiveTransactionManager;

import java.time.Duration;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
@EnableR2dbcRepositories(basePackages = "org.codingsquid.r2dbc.repository")
@Configuration
public class R2dbcConfig extends AbstractR2dbcConfiguration {

    @Override
    @Primary
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = MySqlConnectionFactory.from(configuration("localhost", "root", "1234", "r2dbc"));
        ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration.builder(connectionFactory)
            .acquireRetry(3)
            .initialSize(20)
            .validationQuery("SELECT 1")
            .maxSize(20)
            .build();

        return new ConnectionPool(poolConfiguration);
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    public static MySqlConnectionConfiguration configuration(String host, String username, String password, String database) {
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
}
