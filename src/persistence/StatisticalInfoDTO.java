package persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
@ToString

public class StatisticalInfoDTO implements MySerializableClass {
    private int store_id;
    private String store_name;
    private String menu_name;
    private long sum_order_price;
    private int count_order;

    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeInt(store_id);
        dos.writeUTF(store_name);
        dos.writeUTF(menu_name);
        dos.writeLong(sum_order_price);
        dos.writeInt(count_order);
        return buf.toByteArray();
    }
}
