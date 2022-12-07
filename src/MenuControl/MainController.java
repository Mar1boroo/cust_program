package MenuControl;

import functionControl.LoginController;
import functionControl.SignUpController;
import persistence.UserDTO;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MainController {
    Scanner sc;
    DataInputStream is;
    DataOutputStream os;

    public MainController(Socket socket) throws IOException {
        sc = new Scanner(System.in);
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
    }

    public static final int SIGN_UP = 1;
    public static final int LOG_IN = 2;
    public static final int QUIT = 3;

    SignUpController signUpController = new SignUpController();
    LoginController loginController = new LoginController();

    public boolean handleMainCommand(int command, Scanner sc, DataInputStream inputStream, DataOutputStream outputStream) throws IOException
    {
        switch(command)
        {
            case SIGN_UP:
                signUpController.handleSignUp(sc,inputStream, outputStream);
                break;

            case LOG_IN:
                UserDTO user = loginController.handleLogin(sc,inputStream, outputStream);
                String user_id = user.getUser_id();
                int user_category = user.getUser_category();
                System.out.println();

                if(user_category == 0)
                    managerCommand(user_id);
                else if(user_category == 1)
                    storeKeeperCommand(user_id);
                else if(user_category == 2)
                    customerCommand(user_id);
                break;

            case QUIT:
                Header quitHeader = new Header(
                        Header.TYPE_QUIT,
                        (byte) 0,
                        0);
                outputStream.write(quitHeader.getBytes());
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
                return false;
        }
        return true;
    }

    private void customerCommand(String user_id) throws IOException
    {
        boolean isContinue = true;
        while(isContinue)
        {
            int command;
            System.out.println("==================고객 메뉴=================");
            System.out.println("1. 개인 정보 및 비밀번호 수정");
            System.out.println("2. 음식점 조회");
            System.out.println("3. 음식 주문");
            System.out.println("4. 주문 내역 조회");
            System.out.println("5. 주문 취소");
            System.out.println("6. 리뷰와 별점 등록");
            System.out.println("7. 종료");
            System.out.println("==========================================");
            System.out.print("메뉴를 선택하세요 : ");
            command = sc.nextInt();

            System.out.println();
            isContinue = new CustomerController().handleCommand(command, sc, is, os, user_id);
        }
    }

    private void managerCommand(String user_id) throws IOException
    {
        boolean isContinue = true;
        while(isContinue) {
            int command;
            System.out.println("=============점주 프로그램==============");
            System.out.println("3. 매장 추가");
            System.out.println("4. 메뉴 추가");
            System.out.println("5. 영업시간 변경");
            System.out.println("6. 주문 접수 승인/거절");
            System.out.println("7. 리뷰 조회/답글");
            System.out.println("8. 통계정보 (메뉴별 주문건수, 매출)");
            System.out.println("9. 종료");
            System.out.println("======================================");
            System.out.print("메뉴를 선택하세요 : ");
            command = sc.nextInt();

            isContinue = new ManagerController().handleCommand(command, sc, is, os, user_id);
            System.out.println("======================================");
        }
    }

    private void storeKeeperCommand(String user_id)
    {

    }
}
