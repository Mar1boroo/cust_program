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
public class StoreReviewDTO implements MySerializableClass {
    private int store_id;
    private String store_name;
    private String store_phone;
    private String store_address;
    private String store_time;
    private String store_info;
    private int review_rate;
    private String review_content;

    public StoreReviewDTO(int store_id, String store_name, String store_phone, String store_address,
                          String store_time, String store_info, int review_rate, String review_content)
    {
        this.store_id = store_id;
        this.store_name = store_name;
        this.store_phone = store_phone;
        this.store_address = store_address;
        this.store_time = store_time;
        this.store_info = store_info;
        this.review_rate = review_rate;
        this.review_content = review_content;
    }

    public static StoreReviewDTO read(DataInputStream bodyReader) throws IOException
    {
        int store_id = bodyReader.readInt();
        String store_name = bodyReader.readUTF();
        String store_phone = bodyReader.readUTF();
        String store_address = bodyReader.readUTF();
        String store_time = bodyReader.readUTF();
        String store_info = bodyReader.readUTF();
        int review_rate = bodyReader.readInt();
        String review_content = bodyReader.readUTF();

        return new StoreReviewDTO(store_id, store_name, store_phone, store_address, store_time, store_info, review_rate, review_content);
    }

    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeInt(store_id);
        dos.writeUTF(store_name);
        dos.writeUTF(store_phone);
        dos.writeUTF(store_address);
        dos.writeUTF(store_time);
        dos.writeUTF(store_info);
        dos.writeInt(review_rate);
        dos.writeUTF(review_content);
        return buf.toByteArray();
    }
}
