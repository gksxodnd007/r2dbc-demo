package org.codingsquid.r2dbc.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.codingsquid.r2dbc.repository.projection.ItemWithUser;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
@Getter
@Setter
@AllArgsConstructor
public class ItemInfoDTO {

    private String userName;
    private String itemName;

    public static ItemInfoDTO from(ItemWithUser itemWithUser) {
        return new ItemInfoDTO(itemWithUser.getUserName(), itemWithUser.getItemName());
    }
}
