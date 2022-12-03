package protocol;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class BodyMaker {

    ByteArrayOutputStream buf;
    DataOutputStream dos;

    public BodyMaker() {
        buf = new ByteArrayOutputStream();
        dos = new DataOutputStream(buf);
    }

    public int getSize() {return buf.size();}

    public void add(MySerializableClass object) throws IOException {
        dos.write(object.getBytes());
    }

    public void add(List<MySerializableClass> list) throws IOException {

        dos.writeInt(list.size());
        for(MySerializableClass object : list) dos.write(object.getBytes());

    }

    public void addIntBytes(int integer) throws IOException {
        dos.writeInt(integer);
    }

    public void addStringBytes(String str) throws IOException {
        dos.writeUTF(str);
    }


    public byte[] getBody() {
        return buf.toByteArray();
    }

}
