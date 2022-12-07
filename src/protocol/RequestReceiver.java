package protocol;

import persistence.MenuDTO;
import persistence.StoreDTO;
import persistence.UserDTO;

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

    public String receiveUserIDResult_SignUpReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        String user_id = bodyReader.readUTF();
        if(typeCheck && codeCheck) {
            return user_id;
        }
        return "";
    }

    public String receiveUserIDResult_LogInReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_FAIL) ? true : false;

        String user_id = bodyReader.readUTF();
        if(typeCheck && codeCheck) {
            return user_id;
        }
        return "";
    }

    public UserDTO receiveUserPWResult_LogInReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        if(typeCheck && codeCheck) {
            return UserDTO.read(bodyReader);
        }
        return null;
    }

    public UserDTO receiveSignUpResult(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        if(typeCheck && codeCheck) {
            return UserDTO.read(bodyReader);
        }
        return null;
    }

    public boolean receiveUserIDReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_USER_ID) ? true : false;

        if(typeCheck&&codeCheck)
            return true;
        else
            return false;
    }
    public boolean receiveStoreApplyReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_STORE_INFO) ? true : false;

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

    public String receiveUserIDResult_SignUpReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        String user_id = bodyReader.readUTF();
        if(typeCheck && codeCheck) {
            return user_id;
        }

        return "";
    }

    public String receiveUserIDResult_LogInReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_FAIL) ? true : false;

        String user_id = bodyReader.readUTF();
        if(typeCheck && codeCheck) {
            return user_id;
        }

        return "";
    }

    public boolean receiveUserPWResult_LogInReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);


        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        if(typeCheck && codeCheck)
            return true;
        else
            return false;
    }

    public UserDTO receiveSignUpResult(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        if(typeCheck && codeCheck) {
            return UserDTO.read(bodyReader);
        }

        return null;
    }

    public boolean receiveMenuInfoReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_MENU_INFO) ? true : false;

        if(typeCheck && codeCheck)
            return true;
        else
            return false;
    }

    public MenuDTO receiveMenuAddResult(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        if(typeCheck && codeCheck) {
            return MenuDTO.read(bodyReader);
        }

        return null;
    }


    public MenuDTO receiveMenuOptionAddResult(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        if(typeCheck && codeCheck) {
            return MenuDTO.read(inputStream);
        }
        else
            return null;
    }

    public boolean receiveStoreTimeReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_STORE_TIME) ? true : false;

        if(typeCheck&&codeCheck)
            return true;
        else
            return false;
    }

    public StoreDTO receiveStoreTimeUpdateResult(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        if(typeCheck && codeCheck) {
            return StoreDTO.read(inputStream);
        }
        else
            return null;
    }

    public boolean receiveMenuOptionReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_MENU_OPTION) ? true : false;

        if(typeCheck&&codeCheck) {
            return true;
        }
        else {
            return false;
        }
    }
}
