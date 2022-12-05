package protocol;


import persistence.StoreDTO;

import java.io.DataInputStream;
import java.io.IOException;

public interface MySerializableClass {
    public byte[] getBytes() throws IOException;

    public Object read();
}
