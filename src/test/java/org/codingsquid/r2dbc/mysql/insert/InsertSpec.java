package org.codingsquid.r2dbc.mysql.insert;

import org.codingsquid.r2dbc.entity.User;
import org.codingsquid.r2dbc.mysql.R2dbcConnectionHelper;
import org.junit.jupiter.api.Test;
import org.springframework.data.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

/**
 * @author hubert.squid
 * @since 2020.04.19
 */
public class InsertSpec {

    @Test
    void insertOne() {
        DatabaseClient client = R2dbcConnectionHelper.getClient();

        client.insert()
            .into(User.class)
            .using(new User(null, "codingsquid", 27, "software engineer"))
            .then()
            .as(StepVerifier::create)
            .verifyComplete();
    }
}
