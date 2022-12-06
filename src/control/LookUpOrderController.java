package control;

import persistence.OrderDTO;
import persistence.OrderMenuDTO;
import persistence.OrderOptionDTO;
import persistence.StoreDTO;
import protocol.BodyMaker;
import protocol.Header;
import protocol.ResponseReceiver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class LookUpOrderController {
    public void handleLookUpOrder(DataInputStream inputStream, DataOutputStream outputStream, String user_id) throws IOException {
        ResponseReceiver responseReceiver = new ResponseReceiver();

        sendUserIdForStart(user_id, outputStream);
        List<OrderDTO> orderDTOList = responseReceiver.receiveOrderList(inputStream);
        List<OrderMenuDTO> orderMenuDTOList = responseReceiver.receiveOrderMenuList(inputStream);
        List<OrderOptionDTO> orderOptionDTOList = responseReceiver.receiveOrderOptionList(inputStream);
        List<StoreDTO> storeDTOList = responseReceiver.receiveStoreList(inputStream);

        System.out.println();
        printOrder(orderDTOList, orderMenuDTOList, orderOptionDTOList, storeDTOList);
        System.out.println();
    }

    public void sendUserIdForStart(String user_id, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(user_id);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_START, Header.CODE_ORDER_LIST_LOOKUP, body.length);
        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void printOrder(List<OrderDTO> orderDTOList, List<OrderMenuDTO> orderMenuDTOList,
                           List<OrderOptionDTO> orderOptionDTOList, List<StoreDTO> storeDTOList)
    {
        int cnt = 0;
        System.out.println("=======================주문 내역=======================");
        for(OrderDTO odtos : orderDTOList)
        {
            System.out.print((cnt + 1) + ". 가게명: " + findStoreName(odtos.getStore_id(), storeDTOList));
            System.out.println(" | 총 주문금액: " + odtos.getOrder_price());
            subPrint(odtos.getOrder_num(), orderMenuDTOList, orderOptionDTOList);
            System.out.println();
            cnt++;
        }
        System.out.println("======================================================");
    }

    public String findStoreName(int store_id, List<StoreDTO> storeDTOList)
    {
        for(StoreDTO dtos : storeDTOList)
        {
            if(dtos.getStore_id() == store_id)
                return dtos.getStore_name();
        }
        return null;
    }

    public void subPrint(String order_num, List<OrderMenuDTO> orderMenuDTOList, List<OrderOptionDTO> orderOptionDTOList)
    {
        int menuCnt = 1;
        for(OrderMenuDTO mdtos : orderMenuDTOList)
        {
            if(order_num.equals(mdtos.getOrder_num()))
            {
                System.out.print("\t메뉴 " + menuCnt + ") " + mdtos.getMenu_name() + " (");
                menuCnt++;

                int optionCnt = 0;
                for(OrderOptionDTO odtos : orderOptionDTOList)
                {
                    if(mdtos.getOrderMenu_id().equals(odtos.getOrderMenu_id()))
                    {
                        if(optionCnt != 0)
                            System.out.print(", ");
                        System.out.print(odtos.getOption_name());
                        optionCnt++;
                    }
                }
                if(optionCnt == 0)
                    System.out.print("옵션 없음");
                System.out.println(")");
            }
        }
    }


}
