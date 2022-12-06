package control;

import persistence.UserDTO;
import protocol.BodyMaker;
import protocol.Header;
import protocol.RequestSender;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class EditInformationController {

    public void handleEdit(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream, String user_id) throws IOException
    {
        sc.nextLine();
        while(true)
        {
            System.out.print("본인확인용 비밀번호를 입력해주세요: ");
            String pwd = sc.nextLine();
            sendPwdForStart(pwd, user_id, outputStream);

            if(Header.readHeader(inputStream).code != Header.CODE_SUCCESS)
                System.out.println("비밀번호가 일치하지 않습니다.");
            else
                break;
            System.out.println();
        }
        //변경할 정보 입력
        System.out.println();
        String user_name = inputUserName(sc);
        String user_address = inputUserAddress(sc);
        String user_phone = inputUserPhone(sc);
        String user_pwd = inputUserPWD(sc);

        UserDTO userDTO = new UserDTO(user_id, user_pwd, user_name, user_address, user_phone, 2);
        new RequestSender().updateUserInfo(userDTO, outputStream);
        //서버에 업데이트 요청
        System.out.println("개인정보 및 비밀번호 수정이 완료되었습니다.");
        System.out.println();
    }

   //개인정보 수정을 시작한다는 것을 서버에게 알리는 메소드
    public void sendPwdForStart(String pwd, String user_id, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(pwd);
        bodyMaker.addStringBytes(user_id);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_START, Header.CODE_INFO_AND_PW_FIX, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    //변경 비밀번호 입력 및 형식 검사
    private String inputUserPWD(Scanner sc)
    {
        String input = null;
        while(true)
        {
            System.out.print("변경할 비밀번호를 입력하세요.(변경하지 않는다면 엔터 입력): ");
            input = sc.nextLine();

            if(input.equals(""))
                return input;
            if(input.length() >= 3)
                return input;
            else
                System.out.println("비밀번호 입력 형식에 맞지 않습니다.(최소 세 글자)");
        }
    }

    //변경 이름 입력 및 형식 검사
    private String inputUserName(Scanner sc)
    {
        String input = null;
        while(true)
        {
            System.out.print("변경할 이름을 입력하세요.(변경하지 않는다면 엔터 입력): ");
            input = sc.nextLine();

            if(input.equals("") || input.length() > 0)
                return input;
            else
                System.out.println("이름 입력 형식에 맞지 않습니다. ");
        }
    }

    //변경 주소 입력 및 형식 검사
    private String inputUserAddress(Scanner sc)
    {
        String input = null;
        while(true)
        {
            System.out.print("변경할 주소를 입력하세요.(변경하지 않는다면 엔터 입력): ");
            input = sc.nextLine();

            if(input.equals("") || input.length() > 0)
                return input;
            else
                System.out.println("주소 입력 형식에 맞지 않습니다. ");
        }
    }

    //변경 전화번호 입력 및 형식 검사
    private String inputUserPhone(Scanner sc)
    {
        String input = null;
        while(true)
        {
            System.out.print("변경할 전화번호를 입력하세요.(숫자만 입력, 변경하지 않는다면 엔터 입력): ");
            input = sc.nextLine();

            if (input.equals("") || isdigit(input))
                return input;
            else
                System.out.println("전화번호 입력 형식에 맞지 않습니다. ");
        }
    }

    //전화번호 형식 검사
    private boolean isdigit(String input)
    {
        char tmp;
        for(int i = 0; i<input.length(); i++)
        {
            tmp = input.charAt(i);
            if(!('0' <= tmp && tmp <= '9'))
                return false;
        }
        return true;
    }
}
