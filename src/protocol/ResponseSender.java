package protocol;

import inputManager.StoreInputManager;
import inputManager.UserInputManager;
import persistence.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResponseSender {

    public void sendUserIDAns(Scanner s, DataOutputStream outputStream) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("아이디를 입력하세요 : ");
        String id = sc.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(id);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_ID,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
        System.out.println("아이디 입력 보냄");
    }

    public void sendUserPWAns(Scanner s,String user_id, DataOutputStream outputStream) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("비밀번호를 입력하세요 : ");
        String pw = sc.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(user_id);
        bodyMaker.addStringBytes(pw);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_PW,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }
/*
    public void sendUserNameAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("이름을 입력하세요 : ");
        String name = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(name);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_NAME,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendUserAddressAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("주소을 입력하세요 : ");
        String address = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(address);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_ADDRESS,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendUserPhoneAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("전화번호를 입력하세요 : ");
        String phone = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(phone);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_PHONE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendUserTypeAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("계정 유형을 입력하세요(0:관리자, 1:점주, 2:개인) : ");
        int userType = s.nextInt();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(userType);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_TYPE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    */
    public void sendUserInfoAns(Scanner s, String user_id,DataOutputStream outputStream) throws IOException {
        UserInputManager addUserInfoManager = new UserInputManager();
        UserDTO addUserInfo = addUserInfoManager.getAddUserInfo();
        addUserInfo.setUser_id(user_id);

        switch(addUserInfo.getUser_category())
        {
            case 2:
                addUserInfo.setUser_state(true);
                break;
            default:
                break;
        }

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(addUserInfo);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_INFO,//임시 코드
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

/*
    public void sendStoreNameAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 이름을 입력하세요 : ");
        String store_name = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(store_name);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_NAME,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreIDAns(int store_id, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(store_id);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_ID,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreAddressAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 주소을 입력하세요 : ");
        String address = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(address);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_ADDRESS,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStorePhoneAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 전화번호를 입력하세요 : ");
        String phone = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(phone);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_PHONE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreCategoryAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 카테고리를 입력하세요. : ");
        String storeCatogory = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(storeCatogory);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_CATEGORY,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreStateAns(boolean store_state, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addBooleanBytes(store_state);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_STATE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreOpenAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 오픈시간을 입력하세요. : ");
        String storeOpen = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(storeOpen);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_OPEN,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreCloseAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 마감시간을 입력하세요. : ");
        String storeClose = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(storeClose);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_CLOSE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreIntroduceAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 한 줄 소개를 입력하세요. : ");
        String storeIntroduce = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(storeIntroduce);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_INTRODUCE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

 */

    public void sendStoreInfoAns(Scanner s, DataOutputStream outputStream) throws IOException { //가게 정보 입력 후 전송
        StoreInputManager addStoreInfoManager = new StoreInputManager(s);
        StoreDTO addStoreInfo = addStoreInfoManager.getAddStoreInfo();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(addStoreInfo);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_INFO,//임시 코드 가게 정보 전송
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }
/*
    public void sendStoreSaleCountAns(StatisticalInfoDTO statisticalInfo, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(statisticalInfo);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_COUNT_OF_SALE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreTotalSalesAns(int totalSales, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(totalSales);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_TOTAL_SALES,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }
*/
    public void sendStoreTimeAns(String time, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(time);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_TIME,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }
/*
    public void sendMenuIDAns(int menu_id, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(menu_id);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_MENU_ID,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendMenuNameAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("메뉴 이름을 입력하세요. : ");
        String menuName = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(menuName);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_MENU_NAME,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendMenuPriceAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("메뉴 가격을 입력하세요. : ");
        String menuPrice = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(menuPrice);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_MENU_PRICE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendMenuStockAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("메뉴 제고를 입력하세요. : ");
        int menuStock = s.nextInt();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(menuStock);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_MENU_STOCK,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendMenuCategoryAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("메뉴 카테고리를 입력하세요. : ");
        String menuCategory = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(menuCategory);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_MENU_CATEGORY,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

 */

    public void sendMenuInfoAns(DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_MENU_ID,
                0
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendMenuListAns(List<MenuDTO> menuList, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();

        for(MenuDTO menuDTO : menuList)
        {
            bodyMaker.add(menuDTO); // 이런식으로 하는데 일단 회의해야할 듯
        }

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_MENU_LIST,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendOptionIDAns(int option_id, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(option_id);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_OPTION_ID,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendOptionPriceAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("옵션 가격을 입력하세요. : ");
        long option_price = s.nextLong();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addLongBytes(option_price);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_OPTION_PRICE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendOptionListAns(List<OptionDTO> OptionList, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();

        for(OptionDTO optionDTO : OptionList)
        {
            bodyMaker.add(optionDTO); // 이런식으로 하는데 일단 회의해야할 듯
        }

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_OPTION_LIST,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendOrderIDAns(int order_id, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(order_id);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_ORDER_ID,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendOrderStateAns(String orderState, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(orderState);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_ORDER_STATE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendOrderPriceAns(long orderPrice, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addLongBytes(orderPrice);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_ORDER_PRICE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendOrderListAns(List<OrderDTO> OrderList, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();

        for(OrderDTO orderDTO : OrderList)
        {
            bodyMaker.add(orderDTO); // 이런식으로 하는데 일단 회의해야할 듯
        }


        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_ORDER_LIST,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }
    public void sendOrderMenuListAns(List<OrderMenuDTO> orderMenuList, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();

        for(OrderMenuDTO orderMenuDTO : orderMenuList)
        {
            bodyMaker.add(orderMenuDTO); // 이런식으로 하는데 일단 회의해야할 듯
        }

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_ORDER_MENU_LIST,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendReviewIDAns(int reviewID, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(reviewID);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_REVIEW_ID,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendReviewContentAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("리뷰에 대한 댓글을 입력하세요. : ");
        String content = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(content);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_REVIEW_CONTENT,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }
}
