package StokeeperFunctionControll;

import persistence.StoreDTO;
import protocol.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class StoreController {
    private ResponseSender responseSender;
    private ResponseReceiver responseReceiver;
    private RequestSender requestSender;
    private RequestReceiver requestReceiver;

    public StoreController()
    {
        this.responseSender = new ResponseSender();
        this.responseReceiver = new ResponseReceiver();
        this.requestSender = new RequestSender();
        this.requestReceiver = new RequestReceiver();
    }
    public void handleStoreTimeUpdate(Scanner sc, int store_id, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        //시작 신호 보내기
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(store_id);

        byte[] body = bodyMaker.getBody();
        Header startHeader = new Header(
                Header.TYPE_START,
                Header.CODE_UPDATE_STORE_TIME,
                body.length);
        outputStream.write(startHeader.getBytes());
        outputStream.write(body);

        if(requestReceiver.receiveStoreTimeReq(inputStream)) {
            StoreDTO nowStore = StoreDTO.read(inputStream);
            System.out.println(nowStore.getStore_name() + " | ");

            responseSender.sendStoreTimeAns(store_id, outputStream);
        }
        StoreDTO updateStore = new StoreDTO();

        if((updateStore = requestReceiver.receiveStoreTimeUpdateResult(inputStream)) != null) {
            System.out.println("운영시간 변경 완료.");
            System.out.println(updateStore.getStore_name() + " | 현재 운영시간 : " + updateStore.getStore_time());
        }
    }

    public void handleStoreApply(Scanner sc, String user_id, DataInputStream inputStream, DataOutputStream outputStream) {
    }
}
