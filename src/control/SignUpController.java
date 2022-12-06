package control;

import protocol.*;
import persistence.UserDTO;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class SignUpController {
    private ResponseSender responseSender;
    private ResponseReceiver responseReceiver = new ResponseReceiver();
    private RequestSender requestSender = new RequestSender();
    private RequestReceiver requestReceiver = new RequestReceiver();

    public SignUpController()
    {
        this.responseSender = new ResponseSender();
        this.responseReceiver = new ResponseReceiver();
        this.requestSender = new RequestSender();
        this.requestReceiver = new RequestReceiver();
    }

    public void handleSignUp(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        UserDTO signUpUser = new UserDTO();
        String user_id;

        //시작 신호 보내기
        Header startHeader = new Header(
                Header.TYPE_START,
                Header.CODE_SIGN_UP,
                0);
        outputStream.write(startHeader.getBytes());

        if(requestReceiver.receiveUserIDReq(inputStream)) //user_id 요청 받기
        {
            responseSender.sendUserIDAns(sc, outputStream);//user_id 보내기
        }

        while(true)
        {
            if((user_id = requestReceiver.receiveUserIDResult_SignUpReq(inputStream)) != "")
                break;

            System.out.println("아이디가 중복됩니다.");
            responseSender.sendUserIDAns(sc, outputStream);//user_id 보내기
        }
        responseSender.sendUserInfoAns(sc,user_id, outputStream);

        if((signUpUser = requestReceiver.receiveSignUpResult(inputStream)) != null)
            System.out.println(signUpUser.getUser_name() + "(" + signUpUser.getUser_id() + ")님 회원가입 되셨습니다.");
        else
            System.out.println("회원가입 실패.");


/*
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

        UserDTO u;
        if (user_category==2)
             u = new UserDTO(user_id, user_pw, user_name, user_address, phone, user_category, true);
         else
             u = new UserDTO(user_id,user_pw,user_name,user_address,phone,user_category);

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(u);

        byte[] userBody = bodyMaker.getBody();
        Header userHeader = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_DTO,
                userBody.length);
        outputStream.write(userHeader.getBytes());
        outputStream.write(userBody);

*/




    }

}
