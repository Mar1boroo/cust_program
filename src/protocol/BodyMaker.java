package protocol;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class BodyMaker {

    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    DataOutputStream dos;

    public BodyMaker() {
        this.dos = new DataOutputStream(this.buf);
    }

    public int getSize() {return this.buf.size();}

    public void add(MySerializableClass object) throws IOException {
        this.dos.write(object.getBytes());
    }

    public void add(List<MySerializableClass> list) throws IOException {

        this.dos.writeInt(list.size());
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            MySerializableClass object = (MySerializableClass)var2.next();
            this.dos.write(object.getBytes());
        }

    }

    public void addStrList(List<String> list) throws IOException {
        this.dos.writeInt(list.size());
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            String str = (String) var2.next();
            this.dos.write(str.getBytes());
        }

    }

    public void addIntBytes(int integer) throws IOException {
        this.dos.writeInt(integer);
    }

    public void addStringBytes(String str) throws IOException {
        this.dos.writeUTF(str);
    }

    public void addBooleanBytes(boolean bool) throws IOException {
        this.dos.writeBoolean(bool);
    }

    public void addLongBytes(long longData) throws IOException {
        this.dos.writeLong(longData);
    }


    public byte[] getBody() {
        return this.buf.toByteArray();
    }

}
