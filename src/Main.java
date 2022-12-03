import control.Controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 3000);

        System.out.println("connected.");

        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        Scanner s = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("=============고객 프로그램==============");
        System.out.println("======================================");

        boolean isContinue = true;

        Controller controller = new Controller();

        while(isContinue) {

            int command;

            System.out.println("1. 로그인");
//            System.out.println("2. Name 으로 Player 조회하기");
//            System.out.println("3. Player 전체 조회하기");
//            System.out.println("4. Team 전체 조회하기");
            System.out.println("5. 종료");
            System.out.println("======================================");
            System.out.print("메뉴를 선택하세요 : ");
            command = s.nextInt();

            isContinue = controller.handleCommand(command, s, is, os);

            System.out.println("======================================");


        } // end of while


    } // end of main
}
