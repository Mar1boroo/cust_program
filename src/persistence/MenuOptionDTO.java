package persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class MenuOptionDTO implements Serializable {
    private int menuOption_id;
    private int menu_id;
    private int option_id;

    public int getMenuOption_id() {
        return menuOption_id;
    }

    public void setMenuOption_id(int menuOption_id) {
        this.menuOption_id = menuOption_id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getOption_id() {
        return option_id;
    }

    public void setOption_id(int option_id) {
        this.option_id = option_id;
    }
}
