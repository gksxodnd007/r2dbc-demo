package org.codingsquid.r2dbc.repository;

import lombok.RequiredArgsConstructor;
import org.codingsquid.r2dbc.repository.projection.ItemWithUser;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author hubert.squid
 * @since 2020.05.11
 */
@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemCustomRepository {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<ItemWithUser> findByUserId(long userId) {
        return databaseClient.execute("SELECT u.name as userName, i.name as itemName FROM items i INNER JOIN users u on i.user_id = u.id WHERE i.user_id = :userId")
            .bind("userId", userId)
//            .as(ItemWithUser.class) field 값이 null로 채워지는 이슈 있음
            .fetch()
            .all()
            .map(resultMap -> new ItemWithUser((String) resultMap.get("userName"), (String) resultMap.get("itemName")));
    }
}
