package control;

import protocol.BodyMaker;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class LoginController {

    public void handleLogin(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        //시작 신호 보내기
        Header start_Header = new Header(
                Header.TYPE_START,
                Header.CODE_LOG_IN,
                0);
        outputStream.write(start_Header.getBytes());
        System.out.println("시작신호 보내기");

        Header id_header = Header.readHeader(inputStream);
        byte[] body = new byte[id_header.length];
        inputStream.read(body);

        System.out.println("user_id 요청 받기");

        System.out.println("id 요청 받고 id 보내기");
        System.out.print("id :"); String user_id = sc.next();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(user_id);

        byte[] id_body = bodyMaker.getBody();
        Header id_Header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_ID,
                id_body.length);
        outputStream.write(id_Header.getBytes());
        outputStream.write(id_body);



        Header id_result_header = Header.readHeader(inputStream);
        byte[] id_result_body = new byte[id_result_header.length];
        inputStream.read(id_result_body);
        System.out.println("user_id 결과 받기");
/*
        id_result_header.type = Header.CODE_
*/

        byte[] id_Body = bodyMaker.getBody();
        Header userHeader = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_ID,
                id_Body.length);
        outputStream.write(userHeader.getBytes());
        outputStream.write(id_Body);
    }
}
