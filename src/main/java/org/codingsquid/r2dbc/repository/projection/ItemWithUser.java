package org.codingsquid.r2dbc.repository.projection;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
public class ItemWithUser {

    private final String userName;
    private final String itemName;

    public ItemWithUser(String userName, String itemName) {
        this.userName = userName;
        this.itemName = itemName;
    }

    public String getUserName() {
        return userName;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return "ItemWithUser{" +
            "userName='" + userName + '\'' +
            ", itemName='" + itemName + '\'' +
            '}';
    }
}
