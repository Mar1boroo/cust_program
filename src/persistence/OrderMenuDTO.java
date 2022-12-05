package persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class OrderMenuDTO implements MySerializableClass {
    private String orderMenu_id;
    private String menu_name;
    private String order_num;

    public OrderMenuDTO(String orderMenu_id, String order_num, String menu_name)
    {
        this.orderMenu_id = orderMenu_id;
        this.order_num = order_num;
        this.menu_name = menu_name;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getOrderMenu_id() {
        return orderMenu_id;
    }

    public void setOrderMenu_id(String orderMenu_id) {
        this.orderMenu_id = orderMenu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(orderMenu_id);
        dos.writeUTF(menu_name);
        dos.writeUTF(order_num);
        return buf.toByteArray();
    }
}
