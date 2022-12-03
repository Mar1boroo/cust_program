package persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class MenuDTO implements Serializable {
    private int menu_id;
    private String menu_name;
    private int store_id;
    private long menu_price;
    private int menu_quantity;
    private String menu_category;

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public long getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(Long menu_price) {
        this.menu_price = menu_price;
    }

    public int getMenu_quantity() {
        return menu_quantity;
    }

    public void setMenu_quantity(int menu_quantity) {
        this.menu_quantity = menu_quantity;
    }

    public String getMenu_category() {
        return menu_category;
    }

    public void setMenu_category(String menu_category) {
        this.menu_category = menu_category;
    }
}
