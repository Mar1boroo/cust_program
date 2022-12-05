package protocol;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class RequestReceiver {

    public boolean receiveUserIDReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_USER_ID) ? true : false;

        return typeCheck && codeCheck;
    }
}
