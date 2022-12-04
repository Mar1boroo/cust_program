package control;

import protocol.BodyMaker;
import protocol.Header;
import protocol.ResponseReceiver;
import protocol.RequestSender;
import persistence.UserDTO;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class LoginController {

    public void handleLogin(Scanner s, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        switch (header.type) {

            case Header.TYPE_REQ:
                break;


//            case LOG_IN:
//                reqSender.sendFindPlayerByNameReq(s, outputStream);
//                resReceiver.receiveOnePlayer(inputStream);
//                break;
//
//            case INQUIRE_AND_PW_CHANGE:
//                reqSender.sendFindAllPlayerReq(outputStream);
//                resReceiver.receivePlayerList(inputStream);
//                break;
//
//            case INQUIRE_STORE:
//                reqSender.sendFindAllTeamReq(outputStream);
//                resReceiver.receiveTeamList(inputStream);
//                break;

            case Header.TYPE_RES:
                //
                break;
        }
    }
}
