package control;

import protocol.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Controller {

    public static final int SIGN_UP = 1;
    public static final int LOG_IN = 2;
    public static final int EDIT_INFORMATION = 3;
    public static final int INQUIRE_STORE = 4;
    public static final int ORDER = 5;
    public static final int ORDER_CANCLE = 6;
    public static final int INQUIRE_ORDER = 7;
    public static final int WRITE_REVIEW = 8;
    public static final int QUIT = 9;

    SignUpController signUpController = new SignUpController();
    LoginController loginController = new LoginController();
    OrderController orderController = new OrderController();
    EditInformationController editInformationController = new EditInformationController();
    LookUpStoreController lookUpStoreController = new LookUpStoreController();
    LookUpOrderController lookUpOrderController = new LookUpOrderController();
    OrderCancelController orderCancelController = new OrderCancelController();

    String user_id = "user1";

    public boolean handleCommand(int command, Scanner sc, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        ResponseReceiver resReceiver = new ResponseReceiver();
        RequestSender reqSender = new RequestSender();

        switch(command)
        {
            case SIGN_UP:
                signUpController.handleSignUp(sc,inputStream, outputStream);
                break;

            case LOG_IN:
                loginController.handleLogin(sc,inputStream, outputStream);
                break;

            case EDIT_INFORMATION:
                editInformationController.handleEdit(sc, inputStream, outputStream, user_id);
                break;

            case INQUIRE_STORE:
                lookUpStoreController.handleLookUp(inputStream, outputStream);
                break;

            case ORDER:
                orderController.handleOrder(sc, inputStream, outputStream, user_id);
                break;

            case ORDER_CANCLE:
                orderCancelController.handleCancel(sc, inputStream, outputStream, user_id);
                break;

            case INQUIRE_ORDER:
                lookUpOrderController.handleLookUpOrder(inputStream, outputStream, user_id);
                break;

            case QUIT:
                Header quitHeader = new Header(
                        Header.TYPE_QUIT,
                        (byte) 0,
                        0);
                outputStream.write(quitHeader.getBytes());
                System.out.println("종료합니다.");
                return false;

        }

        return true;
    }
}
