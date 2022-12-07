package StokeeperFunctionControll;

import persistence.Review_omDTO;
import protocol.BodyMaker;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReviewController {

    public void handleReview(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        //시작 신호 보내기
        Header startHeader = new Header(
                Header.TYPE_START,
                Header.CODE_REVIEW_LOOKUP,
                0);
        outputStream.write(startHeader.getBytes());

        //서버에서 보낸 review List( review comment == 0 ) 받기
        Header list_header = Header.readHeader(inputStream);
        byte[] body = new byte[list_header.length];
        int listSize = inputStream.readInt();
        List<Review_omDTO> list  = new ArrayList<Review_omDTO>();

        for(int i = 0 ; i <listSize ; i ++)
        {
            int review_id = inputStream.readInt(); int order_id = inputStream.readInt();
            int store_id = inputStream.readInt(); String menu_name = inputStream.readUTF();
            String review_content = inputStream.readUTF(); int review_rate = inputStream.readInt() ;
            int review_comment = inputStream.readInt();

            Review_omDTO review_omDTO = new Review_omDTO(review_id ,order_id, store_id , menu_name, review_content , review_rate , review_comment);
            list.add(review_omDTO);
        }

        // review List( review comment > 0 ) 요청
        Header rqHeader = new Header(
                Header.TYPE_REQ,
                Header.CODE_REVIEW_REPLY,
                0);
        outputStream.write(rqHeader.getBytes());


        // review list ( review comment > 0 ) 받기
        Header reply_header = Header.readHeader(inputStream);
        System.out.println(reply_header.type);
        System.out.println(reply_header.code);
        System.out.println(reply_header.length);

        int reply_List_Size = inputStream.readInt();
        System.out.println("reply list size :" + reply_List_Size);
        List<Review_omDTO> reply_List  = new ArrayList<Review_omDTO>();

        for(int i = 0 ; i <reply_List_Size ; i ++)
        {
            int review_id = inputStream.readInt();
            int order_id = inputStream.readInt();
            int store_id = inputStream.readInt(); String menu_name = inputStream.readUTF();
            String review_content = inputStream.readUTF(); int review_rate = inputStream.readInt() ;
            int review_comment = inputStream.readInt();

            Review_omDTO review_omDTO = new Review_omDTO(review_id,order_id, store_id , menu_name, review_content , review_rate,review_comment);
            reply_List.add(review_omDTO);
        }
        System.out.println(reply_List.size());

        List<Integer> checkReply = new ArrayList<Integer>();
        System.out.println("<리뷰 목록>");
        for(int i = 0 ; i < listSize ; i ++)
        {
            Review_omDTO review = list.get(i);
            System.out.println("("+(i+1)+") "  + review.getMenu_name() + " | " + review.getReview_content() + " | 평점 :" + review.getReview_rate() );
            for(int j = 0 ; j< reply_List_Size ; j ++)
            {
                Review_omDTO reply = reply_List.get(j);
                if(reply.getReview_comment()  == review.getReview_id())
                {
                    System.out.println("ㄴ " + reply.getReview_content());
                    checkReply.add(i);
                    break;
                }
            }
        }


        System.out.println("답글을 작성할 리뷰의 번호를 선택하시오 : ");
        int select_review = sc.nextInt();
        sc.nextLine();
        boolean isReplyed = false;
        for(int i = 0 ; i < checkReply.size() ; i ++)
        {
            if(checkReply.get(i) == select_review-1)
            {
                isReplyed = true;
                break;
            }
        }
        if(isReplyed== true)
            System.out.println("이미 답글을 작성한 리뷰입니다.");
        else
        {
            int originNumber = list.get(select_review-1).getReview_id();
            System.out.println("답글 작성 : ");
            String replyContent = sc.nextLine();
            Review_omDTO reply =list.get(select_review-1);
            reply.setReview_content(replyContent);
            reply.setReview_comment(reply.getReview_id());
            // 답글 보내기
            BodyMaker bodyMaker_reply = new BodyMaker();
            bodyMaker_reply.add(reply);
            byte[] reply_Body = bodyMaker_reply.getBody();
            Header reply_Header = new Header(
                    Header.TYPE_ANS,
                    Header.CODE_REPLY_CONTENTS,
                    reply_Body.length);
            outputStream.write(reply_Header.getBytes());
            outputStream.write(reply_Body);


            //결과 받기
            Header result_header = Header.readHeader(inputStream);
            if(result_header.code == Header.CODE_SUCCESS)
                System.out.println("답글을 작성하는데 성공하였습니다.");
            else
                System.out.println("답글을 작성하는데 실패하였습니다.");

        }
    }
}

