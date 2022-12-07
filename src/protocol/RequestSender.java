package protocol;

import persistence.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class RequestSender {

    public void insertReviewReq(ReviewDTO review, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(review);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_REQ, Header.CODE_INSERT_REVIEW, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void updateUserInfo(UserDTO user, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(user);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_REQ, Header.CODE_UPDATE_USER_INFO, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

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

    public void updateMenuQuantity(int menu_id, long order_price, String order_num, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(menu_id);
        bodyMaker.addLongBytes(order_price);
        bodyMaker.addStringBytes(order_num);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_REQ, Header.CODE_UPDATE_MENU_QUANTITY, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void updateOrderToCancel(int order_id, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(order_id);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_REQ, Header.CODE_CANCEL_ORDER, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

}