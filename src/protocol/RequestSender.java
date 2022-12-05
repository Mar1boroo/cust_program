package protocol;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class RequestSender {
/*
    public void sendUserIDReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_ID,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendUserPWReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_PW,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendUserNameReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_NAME,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendUserAddressReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_ADDRESS,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendUserPhoneReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_PHONE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendUserTypeReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_TYPE,
                0
        );

        outputStream.write(header.getBytes());
    }
*/

    public void sendUserInfoReq(DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_INFO,  //유저 정보 코드
                0
        );

        outputStream.write(header.getBytes());
    }
/*
    public void sendStoreIDReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_ID,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendStoreNameReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_NAME,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreAddressReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_ADDRESS,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStorePhoneReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_PHONE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreCategoryReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_CATEGORY,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreTimeReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_TIME,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreOpenReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_OPEN,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreCloseReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_CLOSE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreIntroduceReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_INTRODUCE,
                0
        );

        outputStream.write(header.getBytes());
    }
*/

    public void sendStoreInfoReq(DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_INFO,//가게 정보 전송 요청
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreStateReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_STATE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreDiscountRateReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_DISCOUNT_RATE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreTotalSalesReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_TOTAL_SALES,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreCountSalesReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_COUNT_OF_SALE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendMenuListReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_LIST,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendMenuIDReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_ID,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendMenuNameReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_NAME,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendMenuPriceReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_PRICE,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendMenuStockReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_STOCK,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendMenuCategoryReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_CATEGORY,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendOptionListReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_OPTION_LIST,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOptionIDReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_OPTION_ID,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOptionPriceReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_OPTION_PRICE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOrderListReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_ORDER_LIST,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOrderIDReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_ORDER_ID,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOrderStateReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_ORDER_STATE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOrderMenuListReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_ORDER_MENU_LIST,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOrderPriceReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_ORDER_PRICE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendReviewContentReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_REVIEW_CONTENT,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendReviewListReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_REVIEW_LIST,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendReviewIDReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_REVIEW_ID,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendReviewAnserReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_REVIEW_ANS,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendUserIDCheck(String user_id, DataOutputStream outputStream) throws IOException {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(user_id);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_REVIEW_ANS,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }
}