import MenuControl.MainController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 3000);
        //192.168.227.206

        System.out.println("연결되었습니다.");

        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);

        boolean isContinue = true;
        MainController mainController = new MainController(socket);

        while(isContinue) {
            int command;
            System.out.println();
            System.out.println("==================메인 메뉴=================");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 종료");
            System.out.println("==========================================");
            System.out.print("메뉴를 선택하세요 : ");
            command = sc.nextInt();

            System.out.println();
            isContinue = mainController.handleMainCommand(command, sc, is, os);
        }
    } // end of main
}
