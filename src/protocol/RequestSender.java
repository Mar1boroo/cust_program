package protocol;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class RequestSender {

    public void sendCustomerID(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print(" ID : ");
        int id = s.nextInt();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(id);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_CUSTOMER_ID,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);


    }

    public void sendCustomerPW(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print(" PW : ");
        int pw = s.nextInt();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(pw);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_CUSTOMER_PW,
                body.length
        );
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }


//    public void sendFindPlayerByNameReq(Scanner s, DataOutputStream outputStream) throws IOException {
//
//        System.out.print("조회할 Player의 name을 입력하세요 : ");
//        String name = s.next();
//
//        BodyMaker bodyMaker = new BodyMaker();
//        bodyMaker.addStringBytes(name);
//
//        byte[] body = bodyMaker.getBody();
//
//        Header header = new Header(
//                Header.TYPE_REQ,
//                Header.UNUSED,
//                body.length
//        );
//
//        outputStream.write(header.getBytes());
//        outputStream.write(body);
//
//    }
//
//
//
//    public void sendFindAllPlayerReq(DataOutputStream outputStream) throws IOException {
//
//        Header header = new Header(
//                Header.TYPE_REQ,
//                Header.ACTION_READ,
//                Header.TARGET_PLAYER,
//                Header.OPTION_PLAYER_ALL,
//                Header.UNUSED,
//                0
//        );
//
//        outputStream.write(header.getBytes());
//
//    }
//
//    public void sendFindAllTeamReq(DataOutputStream outputStream) throws IOException {
//
//        Header header = new Header(
//                Header.TYPE_REQ,
//                Header.ACTION_READ,
//                Header.TARGET_TEAM,
//                Header.FIND_TEAM_ALL,
//                Header.UNUSED,
//                0
//        );
//
//        outputStream.write(header.getBytes());
//
//    }

}
