package MenuControl;

import functionControl.*;
import protocol.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CustomerController {

    public static final int EDIT_INFORMATION = 1;
    public static final int INQUIRE_STORE = 2;
    public static final int ORDER = 3;
    public static final int INQUIRE_ORDER = 4;
    public static final int ORDER_CANCLE = 5;
    public static final int WRITE_REVIEW = 6;
    public static final int QUIT = 7;

    OrderController orderController = new OrderController();
    EditInformationController editInformationController = new EditInformationController();
    LookUpStoreController lookUpStoreController = new LookUpStoreController();
    LookUpOrderController lookUpOrderController = new LookUpOrderController();
    OrderCancelController orderCancelController = new OrderCancelController();
    WriteReviewController writeReviewController = new WriteReviewController();

    public boolean handleCommand(int command, Scanner sc, DataInputStream inputStream, DataOutputStream outputStream, String user_id) throws IOException {

        switch(command)
        {
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

            case WRITE_REVIEW:
                writeReviewController.handleWrite(sc, inputStream, outputStream, user_id);
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
}
