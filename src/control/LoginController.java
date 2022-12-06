package control;

import protocol.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class LoginController {

    public void handleLogin(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        ResponseSender responseSender = new ResponseSender();
        ResponseReceiver responseReceiver = new ResponseReceiver();
        RequestSender requestSender = new RequestSender();
        RequestReceiver requestReceiver = new RequestReceiver();
        //시작 신호 보내기
        Header start_Header = new Header( Header.TYPE_START, Header.CODE_LOG_IN, 0);
        outputStream.write(start_Header.getBytes());

        if(requestReceiver.receiveUserIDReq(inputStream)) //id 요청 받기
            responseSender.sendUserIDAns(sc, outputStream); //id 요청 받고 id 보내기




        Header id_result_header = Header.readHeader(inputStream);
        byte[] id_result_body = new byte[id_result_header.length];
        inputStream.read(id_result_body);
        System.out.println("user_id 결과 받기");
/*
        id_result_header.type = Header.CODE_


        byte[] id_Body = bodyMaker.getBody();
        Header userHeader = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_ID,
                id_Body.length);
        outputStream.write(userHeader.getBytes());
        outputStream.write(id_Body);*/
    }
}
