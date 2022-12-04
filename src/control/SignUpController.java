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

import static control.Controller.SIGN_UP;

public class SignUpController {

    public void handleSignUp( Scanner s, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        switch(header.type)
        {

            case Header.TYPE_REQ:
                System.out.print("사용할 id :");
                String user_id = s.next();
                System.out.print("사용할 pw :");
                String user_pw = s.next();
                System.out.print("이름 : ");
                String user_name = s.next();
                System.out.print("주소 : ");
                String user_address = s.next();
                System.out.print("전화번호 : ");
                String phoen = s.next();

                UserDTO u = new UserDTO(user_id, user_pw, user_name, user_address, phoen, 1);
                BodyMaker bodyMaker = new BodyMaker();
                bodyMaker.add(u);

                byte[] outBody = bodyMaker.getBody();

                Header outHeader = new Header(
                        Header.TYPE_ANS,
                        Header.CODE_USER_DTO,
                        outBody.length);
                outputStream.write(outHeader.getBytes());
                outputStream.write(body);
                break;

            case Header.TYPE_RES:
                if(header.code == 0x01)
                    System.out.println("Sign Up Seccess!");
                else
                    System.out.println("Sign Up fail.");

                break;
        }


    }

}
