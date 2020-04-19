package org.codingsquid.r2dbc.mysql.delete;

import org.codingsquid.r2dbc.entity.User;
import org.codingsquid.r2dbc.mysql.R2dbcConnectionHelper;
import org.junit.jupiter.api.Test;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import reactor.test.StepVerifier;

/**
 * @author hubert.squid
 * @since 2020.04.19
 */
public class DeleteSpec {

    @Test
    void deleteBy() {
        DatabaseClient client = R2dbcConnectionHelper.getClient();

        client.delete()
            .from(User.class)
            .matching(CriteriaDefinition.from(Criteria.where("name").is("codingsquid")))
            .then()
            .as(StepVerifier::create)
            .verifyComplete();
    }
}
