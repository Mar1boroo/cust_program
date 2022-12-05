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

    SignUpController signUpController;

    public boolean handleCommand(int command, Scanner sc, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        ResponseReceiver resReceiver = new ResponseReceiver();
        RequestSender reqSender = new RequestSender();

        switch(command)
        {
            case SIGN_UP:
                //시작 신호 보내기
                Header startHeader = new Header(
                        Header.TYPE_START,
                        Header.CODE_SIGN_UP,
                        0);
                outputStream.write(startHeader.getBytes());
                System.out.println("시작신호 보내기");

                Header header = Header.readHeader(inputStream);
                byte[] body = new byte[header.length];
                inputStream.read(body);
                DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
                System.out.println("user_dto 요청 받기");

                System.out.println("요청 받고 dto 보내기");
                //user_dto 보내기
                System.out.print("사용할 id :"); String user_id = sc.next();
                System.out.print("사용할 pw :");String user_pw = sc.next();
                System.out.print("이름 : ");String user_name = sc.next();
                System.out.print("주소 : ");String user_address = sc.next();
                System.out.print("전화번호 : ");String phone = sc.next();
                System.out.print("고객 유형 ( 1: 점주 , 2 : 고객 ) :"); int user_category = sc.nextInt();

                UserDTO u = new UserDTO(user_id, user_pw, user_name, user_address, phone, user_category);
                BodyMaker bodyMaker = new BodyMaker();
                bodyMaker.add(u);

                byte[] userBody = bodyMaker.getBody();
                Header userHeader = new Header(
                        Header.TYPE_ANS,
                        Header.CODE_USER_DTO,
                        userBody.length);
                outputStream.write(userHeader.getBytes());
                outputStream.write(userBody);
                break;

            case LOG_IN:
                //
                break;

            case INQUIRE_AND_PW_CHANGE:
                //
                break;

            case INQUIRE_STORE:
                //
                break;

            case QUIT:
                /*reqSender.sendQuitReq(outputStream);*/
                return false;

        }

        return true;
    }




}
