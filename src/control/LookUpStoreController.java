package control;

import persistence.StoreReviewDTO;
import protocol.Header;
import protocol.ResponseReceiver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LookUpStoreController {
    public void handleLookUp(DataInputStream inputStream, DataOutputStream outputStream) throws IOException
    {
        Header start_Header = new Header(Header.TYPE_START, Header.CODE_STORE_LOOKUP, 0);
        outputStream.write(start_Header.getBytes());
        //가게 조회 하는것을 서버에게 알림.

        List<StoreReviewDTO> storeReviewList = new ResponseReceiver().receiveStoreReviewList(inputStream);
        ArrayList<Integer> storeIDs = getStoreIds(storeReviewList);
        printStore(storeReviewList, storeIDs);
        //서버에게서 가게 리스트를 받아와서 출력함
        System.out.println();
    }

    public ArrayList<Integer> getStoreIds (List<StoreReviewDTO> storeReviewList)
    {
        ArrayList<Integer> storeIds = new ArrayList<>();
        for(int i = 0; i < storeReviewList.size(); i++)
        {
            if(storeIds.isEmpty())
                storeIds.add(storeReviewList.get(i).getStore_id());
            else
            {
                boolean isSame = false;
                for(int j = 0; j < storeIds.size(); j++)
                {
                    if(storeIds.get(j) == storeReviewList.get(i).getStore_id())
                    {
                        isSame = true;
                        break;
                    }
                }
                if(!isSame)
                    storeIds.add(storeReviewList.get(i).getStore_id());
            }
        }
        return storeIds;
    }

    public int getStoreRate(List<StoreReviewDTO> storeReviewList, int store_id)
    {
        int rate = 0;
        int cnt = 0;
        for (StoreReviewDTO dto : storeReviewList)
        {
            if(store_id == dto.getStore_id())
            {
                rate = rate + dto.getReview_rate();
                cnt++;
            }
        }
        return rate / cnt;
    }

    public void printStore(List<StoreReviewDTO> storeReviewList, ArrayList<Integer> storeIds)
    {
        System.out.println("-------------------------가게 리스트--------------------------");
        for(int i = 0; i < storeIds.size(); i++)
        {
            int reviewCnt = 0;
            System.out.println((i + 1) + ". " + storeReviewList.get(i).getStore_name() + "  \"" + storeReviewList.get(i).getStore_info() + "\""
                    + " | 별점: " + getStoreRate(storeReviewList, storeIds.get(i)) + " |"
                    + "\n | 영업시간 : " + storeReviewList.get(i).getStore_time() + " | 주소 : " + storeReviewList.get(i).getStore_address()
                    + " | 매장 전화번호 : " + storeReviewList.get(i).getStore_phone() + " |");

            System.out.println("------------리뷰------------");
            for (StoreReviewDTO dto : storeReviewList)
            {
                if (dto.getStore_id() == storeIds.get(i))
                {
                    System.out.println((reviewCnt + 1) + ". " + dto.getReview_content() + " (별점: " + dto.getReview_rate() + ")");
                    reviewCnt++;
                }
            }
            System.out.println("---------------------------");
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------");
    }
}
