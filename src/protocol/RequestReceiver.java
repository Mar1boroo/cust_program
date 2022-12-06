package protocol;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class RequestReceiver {

    public boolean receiveUserIDReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_USER_ID) ? true : false;

        if(typeCheck&&codeCheck)
            return true;
        else
            return false;
    }
    public String receiveUserInfoReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_USER_DTO) ? true : false;

        if(typeCheck && codeCheck)
            return bodyReader.readUTF();

        return "";
    }

    public boolean receiveResultReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        return typeCheck && codeCheck;
    }

    public String receiveUserIDResultReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        if(typeCheck && codeCheck)
            return bodyReader.readUTF();

        return "";
    }
}
