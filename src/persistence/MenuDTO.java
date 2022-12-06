package persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
@ToString
public class MenuDTO implements MySerializableClass {
    private int menu_id;
    private String menu_name;
    private int store_id;
    private long menu_price;
    private int menu_quantity;
    private String menu_category;
    private boolean menu_state;

    public MenuDTO() {}

    public MenuDTO(int menu_id, String menu_name, int store_id, long menu_price, int menu_quantity, String menu_category, boolean menu_state)
    {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.store_id = store_id;
        this.menu_price = menu_price;
        this.menu_quantity = menu_quantity;
        this.menu_category = menu_category;
        this.menu_state = menu_state;
    }

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

    public boolean getMenu_state() {return menu_state;}

    public static MenuDTO read(DataInputStream bodyReader) throws IOException
    {
        int menu_id = bodyReader.readInt();
        String menu_name = bodyReader.readUTF();
        int store_id = bodyReader.readInt();
        long menu_price = bodyReader.readLong();
        int menu_quantity = bodyReader.readInt();
        String menu_category = bodyReader.readUTF();
        boolean menu_state = bodyReader.readBoolean();

        return new MenuDTO(menu_id, menu_name, store_id, menu_price, menu_quantity, menu_category,menu_state);
    }

    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeInt(menu_id);
        dos.writeUTF(menu_name);
        dos.writeInt(store_id);
        dos.writeLong(menu_price);
        dos.writeInt(menu_quantity);
        dos.writeUTF(menu_category);
        dos.writeBoolean(menu_state);
        return buf.toByteArray();
    }
}
