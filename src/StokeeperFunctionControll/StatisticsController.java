package StokeeperFunctionControll;

import persistence.StatisticalInfoDTO;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StatisticsController {

    public void handleStatistics(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        //시작 신호 보내기
        Header startHeader = new Header(
                Header.TYPE_START,
                Header.CODE_STATISTICS,
                0);
        outputStream.write(startHeader.getBytes());

        // statisticalinfo DTO LIST 받기
        Header list_header = Header.readHeader(inputStream);
        int listSize = inputStream.readInt();
        List<StatisticalInfoDTO> list  = new ArrayList<StatisticalInfoDTO>();
        for(int i = 0 ; i <listSize ; i ++)
        {
            int store_id = inputStream.readInt(); String store_name = inputStream.readUTF();
            String menu_name = inputStream.readUTF(); long sum_order_price = inputStream.readLong();
            int count_order = inputStream.readInt() ;

            StatisticalInfoDTO statisticalInfoDTO = new StatisticalInfoDTO(store_id , store_name , menu_name , sum_order_price , count_order);
            list.add(statisticalInfoDTO);
        }

        System.out.println("<메뉴 별 통계>");
        for(int i = 0 ; i < list.size() ; i ++)
        {
            StatisticalInfoDTO statisticInfo = list.get(i);
            System.out.println(statisticInfo.getMenu_name()+" | 총 매출 :"+statisticInfo.getSum_order_price()+
                    " | 주문 수 : " + statisticInfo.getCount_order());
        }




    }
}

