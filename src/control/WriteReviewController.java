package control;

import persistence.*;
import protocol.BodyMaker;
import protocol.Header;
import protocol.RequestSender;
import protocol.ResponseReceiver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class WriteReviewController {
    public void handleWrite(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream, String user_id) throws IOException
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
            System.out.print("리뷰를 작성할 주문의 번호를 선택하세요.: ");
            selectNum = sc.nextInt();
            selectNum = selectOrder(orderDTOList, selectNum);
        }
        int order_id = orderDTOList.get(selectNum - 1).getOrder_id();
        String order_num = orderDTOList.get(selectNum - 1).getOrder_num();
        int store_id = orderDTOList.get(selectNum - 1).getStore_id();
        System.out.println();
        System.out.print("해당 주문에 대한 별점을 입력하세요.(0 ~ 5사이 숫자만 입력): ");
        int review_rate = sc.nextInt();
        sc.nextLine();
        System.out.print("해당 주문에 대한 리뷰를 입력하세요.: ");
        String review_content = sc.nextLine();

        ReviewDTO reviewDTO = new ReviewDTO(store_id, user_id, order_id, review_rate, review_content, LocalDateTime.now(), order_num);
        requestSender.insertReviewReq(reviewDTO, outputStream);
        System.out.println("리뷰 작성이 완료되었습니다.");
        System.out.println();
    }

    public void sendUserIdForStart(String user_id, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(user_id);
        byte[] body = bodyMaker.getBody();

        Header header = new Header(Header.TYPE_START, Header.CODE_WRITE_REVIEW, body.length);
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
