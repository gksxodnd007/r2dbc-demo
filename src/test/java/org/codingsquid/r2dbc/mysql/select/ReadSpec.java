package org.codingsquid.r2dbc.mysql.select;

import org.codingsquid.r2dbc.entity.User;
import org.codingsquid.r2dbc.mysql.R2dbcConnectionHelper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import reactor.test.StepVerifier;

/**
 * @author hubert.squid
 * @since 2020.04.18
 */
public class ReadSpec {

    private static final Logger logger = LoggerFactory.getLogger(ReadSpec.class);

    @Test
    void readOne() {
        DatabaseClient client = R2dbcConnectionHelper.getClient();

        client.select()
            .from(User.class)
            .fetch()
            .first()
            .doOnNext(e -> logger.info(">>> name:" + e.getName() + " description: " + e.getDescription()))
            .as(StepVerifier::create)
            .expectNextCount(1)
            .verifyComplete();
    }

    @Test
    void readBy() {
        DatabaseClient client = R2dbcConnectionHelper.getClient();

        client.select()
            .from(User.class)
            .matching(CriteriaDefinition.from(Criteria.where("name").is("hubert.squid")))
            .fetch()
            .first()
            .doOnNext(e -> logger.info(">>> name:" + e.getName() + " description: " + e.getDescription()))
            .as(StepVerifier::create)
            .expectNext(new User(1L, "hubert.squid", 27, "software engineer"))
            .verifyComplete();
    }

    @Test
    void readMany() {
        DatabaseClient client = R2dbcConnectionHelper.getClient();

        client.select()
            .from(User.class)
            .fetch()
            .all()
            .doOnNext(e -> logger.info(">>> name:" + e.getName() + " description: " + e.getDescription()))
            .as(StepVerifier::create)
            .expectNextCount(4)
            .verifyComplete();
    }

    @Test
    void readPaging() {

    }
}
