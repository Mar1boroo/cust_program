package MenuControl;

import StokeeperFunctionControll.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ManagerController {

    public static final int STORE_ADD_APPLY = 1;
    public static final int MENU_ADD_APPLY = 2;
    public static final int STORE_TIME_UPDATE = 3;
    public static final int ORDER_ACCEPT_OR_REFUSE = 4;
    public static final int REVIEW_LOOKUP_REPLY = 5;
    public static final int STATISTICS = 6;
    public static final int QUIT = 7;

    MenuController menuController = new MenuController();
    StoreController storeController = new StoreController();
    OrderAcceptController orderAcceptController = new OrderAcceptController();
    ReviewController reviewController = new ReviewController();
    StatisticsController statisticsController = new StatisticsController();

    public boolean handleCommand(int command, Scanner sc, DataInputStream inputStream, DataOutputStream outputStream, String user_id) throws IOException
    {
        int store_id = 1;//임시

        switch(command) {
            case STORE_ADD_APPLY:
                storeController.handleStoreApply(sc, user_id, inputStream, outputStream);
                break;

            case MENU_ADD_APPLY:
                menuController.handleMenuApply(sc,store_id, inputStream, outputStream);
                break;

            case STORE_TIME_UPDATE:
                storeController.handleStoreTimeUpdate(sc, store_id, inputStream, outputStream);
                break;


            case ORDER_ACCEPT_OR_REFUSE:
                orderAcceptController.handleOrderAccept(sc,inputStream,outputStream);
                break;

            case REVIEW_LOOKUP_REPLY:
                reviewController.handleReview(sc , inputStream , outputStream);
                break;

            case STATISTICS:
                statisticsController.handleStatistics( sc, inputStream , outputStream);
                break;

            case QUIT:
                /*reqSender.sendQuitReq(outputStream);*/
                return false;

        }
        return true;
    }

}
