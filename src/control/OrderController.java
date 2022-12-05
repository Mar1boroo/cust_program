package control;

import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class OrderController {
    public void handleOrder(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream, String user_id) throws IOException
    {
        Header start_Header = new Header( Header.TYPE_START, Header.CODE_FOOD_ORDER, 0);
        outputStream.write(start_Header.getBytes());

        Header header = Header.readHeader(inputStream);
    }
}
