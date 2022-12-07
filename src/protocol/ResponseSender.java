package protocol;

import inputManager.StoreInputManager;
import inputManager.UserInputManager;
import persistence.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResponseSender {

    public void sendUserIDAns(Scanner s, DataOutputStream outputStream) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("아이디를 입력하세요 : ");
        String id = sc.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(id);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_ID,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
        System.out.println("아이디 입력 보냄");
    }

    public void sendUserPWAns(Scanner s,String user_id, DataOutputStream outputStream) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("비밀번호를 입력하세요 : ");
        String pw = sc.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(user_id);
        bodyMaker.addStringBytes(pw);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_PW,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendUserInfoAns(Scanner s, String user_id,DataOutputStream outputStream) throws IOException {
        UserInputManager addUserInfoManager = new UserInputManager();
        UserDTO addUserInfo = addUserInfoManager.getAddUserInfo();
        addUserInfo.setUser_id(user_id);

        switch(addUserInfo.getUser_category())
        {
            case 2:
                addUserInfo.setUser_state(true);
                break;
            default:
                break;
        }

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(addUserInfo);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_INFO,//임시 코드
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }
}
