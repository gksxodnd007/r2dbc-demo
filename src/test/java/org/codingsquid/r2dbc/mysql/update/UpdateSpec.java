package org.codingsquid.r2dbc.mysql.update;

import org.codingsquid.r2dbc.entity.User;
import org.codingsquid.r2dbc.mysql.R2dbcConnectionHelper;
import org.junit.jupiter.api.Test;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import org.springframework.data.relational.core.query.Update;
import reactor.test.StepVerifier;

/**
 * @author hubert.squid
 * @since 2020.04.19
 */
public class UpdateSpec {

    @Test
    void updateById() {
        DatabaseClient client = R2dbcConnectionHelper.getClient();

        client.update()
            .table(User.class) // class정보로 update시에는 @Id가 mapping되어있는 필드를 조건으로 update한다.
            .using(new User(5L, "codingsquid", 26, "hello world"))
            .fetch()
            .rowsUpdated()
            .as(StepVerifier::create)
            .expectNext(1)
            .verifyComplete();
    }

    @Test
    void updatedByName() {
        DatabaseClient client = R2dbcConnectionHelper.getClient();

        client.update()
            .table("users")
            .using(Update.update("age", 26))
            .matching(CriteriaDefinition.from(Criteria.where("name").is("codingsquid")))
            .then()
            .as(StepVerifier::create)
            .verifyComplete();
    }
}
