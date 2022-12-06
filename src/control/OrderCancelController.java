package control;

import persistence.OrderDTO;
import persistence.OrderMenuDTO;
import persistence.OrderOptionDTO;
import persistence.StoreDTO;
import protocol.BodyMaker;
import protocol.Header;
import protocol.RequestSender;
import protocol.ResponseReceiver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class OrderCancelController {
    public void handleCancel(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream, String user_id) throws IOException
    {
        ResponseReceiver responseReceiver = new ResponseReceiver();
        RequestSender requestSender = new RequestSender();

        sendUserIdForStart(user_id, outputStream);
        List<OrderDTO> orderDTOList = responseReceiver.receiveOrderList(inputStream);
        List<OrderMenuDTO> orderMenuDTOList = responseReceiver.receiveOrderMenuList(inputStream);
        List<OrderOptionDTO> orderOptionDTOList = responseReceiver.receiveOrderOptionList(inputStream);
        List<StoreDTO> storeDTOList = responseReceiver.receiveStoreList(inputStream);

        int selectNum = 0;
        System.out.println();
        new LookUpOrderController().printOrder(orderDTOList, orderMenuDTOList, orderOptionDTOList, storeDTOList);

        while(selectNum == 0)
        {
            System.out.print("취소할 주문의 번호를 선택하세요.: ");
            selectNum = sc.nextInt();
            selectNum = selectOrder(orderDTOList, selectNum);
        }

        int order_id = orderDTOList.get(selectNum - 1).getOrder_id();
        System.out.println(order_id);
        requestSender.updateOrderToCancel(order_id, outputStream);
        System.out.println("주문 취소가 완료되었습니다.");
        System.out.println();
    }

    public void sendUserIdForStart(String user_id, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(user_id);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_START, Header.CODE_ORDER_CANCEL, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public int selectOrder(List<OrderDTO> orderDTOList, int selectNum)
    {
        if(orderDTOList.size() < selectNum || selectNum < 1)
        {
            System.out.println("잘못된 주문 번호입니다.");
            return 0;
        }
        else
            return selectNum;
    }
}
