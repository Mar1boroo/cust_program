package protocol;

import persistence.OrderDTO;
import persistence.OrderMenuDTO;
import persistence.OrderOptionDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class RequestSender {

    public void storeMenuListReq(int store_id, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(store_id);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_REQ, Header.CODE_MENU_LIST, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void menuOptionListReq(int menu_id, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(menu_id);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_REQ, Header.CODE_OPTION_LIST, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void insertOrderReq(OrderDTO order, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(order);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_REQ, Header.CODE_INSERT_ORDER, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void insertOrderMenuReq(OrderMenuDTO orderMenu, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(orderMenu);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_REQ, Header.CODE_INSERT_ORDER_MENU, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void insertOrderOptionReq(OrderOptionDTO orderOption, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(orderOption);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_REQ, Header.CODE_INSERT_ORDER_OPTION, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void updateMenuQuantity(int menu_id, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(menu_id);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_REQ, Header.CODE_UPDATE_MENU_QUANTITY, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendUserInfoReq(DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_INFO,  //유저 정보 코드
                0
        );

        outputStream.write(header.getBytes());
    }

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