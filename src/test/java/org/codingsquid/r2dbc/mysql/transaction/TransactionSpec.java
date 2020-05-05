package org.codingsquid.r2dbc.mysql.transaction;

import io.r2dbc.spi.ConnectionFactory;
import org.codingsquid.r2dbc.entity.Item;
import org.codingsquid.r2dbc.entity.User;
import org.codingsquid.r2dbc.mysql.R2dbcConnectionHelper;
import org.junit.jupiter.api.Test;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author hubert.squid
 * @since 2020.04.19
 */
public class TransactionSpec {

    @Test
    void whenSuccess() {
        ConnectionFactory factory = R2dbcConnectionHelper.getMysqlConnectionFactory();
        ReactiveTransactionManager tm = new R2dbcTransactionManager(factory);
        TransactionalOperator operator = TransactionalOperator.create(tm);

        DatabaseClient client = DatabaseClient.create(factory);

        operator.execute(tx -> {
            Mono<Void> insertUser = client.insert()
                .into(User.class)
                .using(new User(null, "codingsquid", 27, "Those who love spring"))
                .then();

            Mono<Void> insertItem = client.select()
                .from(User.class)
                .matching(CriteriaDefinition.from(Criteria.where("name").is("codingsquid")))
                .fetch()
                .one()
                .flatMap(user -> client.insert()
                    .into(Item.class)
                    .using(new Item(null, user.getId(), "macbook", 3200000))
                    .then())
                .then();

            return insertUser.then(insertItem);
        })
            .as(StepVerifier::create)
            .verifyComplete();
    }

    @Test
    void whenFail() {
        ConnectionFactory factory = R2dbcConnectionHelper.getMysqlConnectionFactory();
        ReactiveTransactionManager tm = new R2dbcTransactionManager(factory);
        TransactionalOperator operator = TransactionalOperator.create(tm);

        DatabaseClient client = DatabaseClient.create(factory);
        client.insert()
            .into(User.class)
            .using(new User(null, "codingsquid", 27, "Those who love spring"))
            .fetch()
            .one()
            .flatMap(result -> client.insert()
                .into(Item.class)
                .using(new Item(null, Long.parseLong(result.get("LAST_INSERT_ID").toString()), "macbook", 3200000))
                .then()
            )
            .then(Mono.error(RuntimeException::new))
            .as(operator::transactional)
            .as(StepVerifier::create)
            .expectError()
            .verify();
    }
}
