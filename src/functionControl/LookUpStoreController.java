package functionControl;

import persistence.ReviewDTO;
import persistence.StoreDTO;
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

        List<StoreDTO> storeList = new ResponseReceiver().receiveStoreList(inputStream);
        List<ReviewDTO> reviewList = new ResponseReceiver().receiveReviewList(inputStream);
        ArrayList<Integer> storeIDs = getStoreIds(storeList);
        printStore(storeList, reviewList, storeIDs);
        //서버에게서 가게 리스트를 받아와서 출력함
        System.out.println();
    }

    public ArrayList<Integer> getStoreIds (List<StoreDTO> storeList)
    {
        ArrayList<Integer> storeIds = new ArrayList<>();
        for(int i = 0; i < storeList.size(); i++)
        {
            if(storeIds.isEmpty())
                storeIds.add(storeList.get(i).getStore_id());
            else
            {
                boolean isSame = false;
                for(int j = 0; j < storeIds.size(); j++)
                {
                    if(storeIds.get(j) == storeList.get(i).getStore_id())
                    {
                        isSame = true;
                        break;
                    }
                }
                if(!isSame)
                    storeIds.add(storeList.get(i).getStore_id());
            }
        }
        return storeIds;
    }

    public int getStoreRate(List<ReviewDTO> reviewList, int store_id)
    {
        int rate = 0;
        int cnt = 0;
        for (ReviewDTO dto : reviewList)
        {
            if(store_id == dto.getStore_id())
            {
                rate = rate + dto.getReview_rate();
                cnt++;
            }
        }
        if(cnt == 0)
            return 0;
        return rate / cnt;
    }

    public void printStore(List<StoreDTO> storeList, List<ReviewDTO> reviewList, ArrayList<Integer> storeIds)
    {
        System.out.println("-------------------------가게 리스트--------------------------");

        if(storeIds.size() == 0)
            System.out.println("조회 가능한 가게가 없습니다.");

        for(int i = 0; i < storeIds.size(); i++)
        {
            int reviewCnt = 0;
            System.out.println((i + 1) + ". " + storeList.get(i).getStore_name() + "  \"" + storeList.get(i).getStore_info() + "\""
                    + " | 별점: " + getStoreRate(reviewList, storeIds.get(i)) + " |"
                    + "\n | 영업시간 : " + storeList.get(i).getStore_time() + " | 주소 : " + storeList.get(i).getStore_address()
                    + " | 매장 전화번호 : " + storeList.get(i).getStore_phone() + " |");

            System.out.println("------------리뷰------------");
            for (ReviewDTO dto : reviewList)
            {
                if (dto.getStore_id() == storeIds.get(i))
                {
                    System.out.println((reviewCnt + 1) + ". " + dto.getReview_content() + " (별점: " + dto.getReview_rate() + ")");
                    reviewCnt++;
                }
            }
            if(reviewCnt == 0)
                System.out.println("해당 가게에 대한 리뷰가 존재하지 않습니다.");
            System.out.println("---------------------------");
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------");
    }
}
