package control;

import protocol.BodyMaker;
import protocol.Header;
import protocol.ResponseReceiver;
import protocol.RequestSender;
import persistence.UserDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Controller {

    public static final int SIGN_UP = 1;
    public static final int LOG_IN = 2;
    public static final int INQUIRE_AND_PW_CHANGE = 3;
    public static final int INQUIRE_STORE = 4;
    public static final int ORDER = 5;
    public static final int ORDER_CANCLE = 6;
    public static final int INQUIRE_ORDER = 7;
    public static final int WRITE_REVIEW = 8;
    public static final int QUIT = 9;



    public boolean handleCommand(int command, Scanner s, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        ResponseReceiver resReceiver = new ResponseReceiver();
        RequestSender reqSender = new RequestSender();

        switch(command) {

            case SIGN_UP:
                System.out.print("사용할 id :"); String user_id = s.next();
                System.out.print("사용할 pw :"); String user_pw = s.next();
                System.out.print("이름 : "); String user_name = s.next();
                System.out.print("주소 : "); String user_address = s.next();
                System.out.print("전화번호 : "); String phoen = s.next();
                String name = s.next();
                UserDTO u = new UserDTO(user_id , user_pw , user_name , user_address, phoen , 1 );
                BodyMaker bodyMaker = new BodyMaker();
                bodyMaker.add(u);

                byte[] body = bodyMaker.getBody();

                Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_DTO,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);

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

            case QUIT:
                /*reqSender.sendQuitReq(outputStream);*/
                return false;

        }

        return true;
    }




}
