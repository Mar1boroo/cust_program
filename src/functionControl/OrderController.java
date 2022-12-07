package functionControl;

import persistence.*;
import protocol.Header;
import protocol.RequestSender;
import protocol.ResponseReceiver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {

    public int handleOrder(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream, String user_id) throws IOException
    {
        ResponseReceiver responseReceiver = new ResponseReceiver();
        RequestSender requestSender = new RequestSender();

        Header start_Header = new Header(Header.TYPE_START, Header.CODE_FOOD_ORDER, 0);
        outputStream.write(start_Header.getBytes());
        //서버에게 주문을 시작하는 것을 알림

        List<StoreDTO> storeList = responseReceiver.receiveStoreList(inputStream);
        //서버에게서 가게 리스트를 받아옴

        long order_price = 0;
        int selectMenuNum = -1;
        int store_id;
        List<String> optionNames = new ArrayList<>();
        boolean continueFlag = false;

        //가게선택. 해당 가게의 영업시간이 아니거나 가게를 잘못 선택하면 가게를 재선택하도록 함.
        do {
            printStoreList(storeList);
            System.out.print("주문할 가게의 번호를 선택하세요.(주문종료: 0): ");
            int selectStoreNum = sc.nextInt();

            if (selectStoreNum == 0) {
                System.out.println("주문이 종료되었습니다.");
                return -1;
            }
            store_id = selectStore(storeList, selectStoreNum);

            if (store_id != -1)
            {
                continueFlag = isStoreOpen(storeList.get(selectStoreNum - 1));
                if (!continueFlag)
                    System.out.println("해당 가게의 영업 시간이 아닙니다. 가게를 다시 선택해주세요.");
            }
            System.out.println();
        } while (continueFlag == false);

        requestSender.storeMenuListReq(store_id, outputStream);
        //서버에 가게의 메뉴 목록 요청

        List<MenuDTO> menuList = responseReceiver.receiveMenuList(inputStream);
        ArrayList<String> menuCategory = getMenuCategory(menuList);
        //서버에게서 가게의 메뉴를 받아오고, 카테고리별 출력을 위해 메뉴 카테고리를 ArrayList에 담아줌

        //메뉴 선택. 메뉴를 잘못 선택하거나 수량이 없으면 메뉴를 다시 선택하도록 함.
        int menuCnt = 0;
        String order_num = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss-")) + user_id;
        while(selectMenuNum != 0)
        {
            printStoreMenu(menuList, menuCategory);
            System.out.print("주문할 메뉴의 번호를 선택하세요.(1개 선택, 주문종료: 0): ");
            selectMenuNum = sc.nextInt();

            if(selectMenuNum == 0)
            {
                System.out.println("주문이 종료되었습니다.");
                return -1;
            }
            int menu_id = selectMenu(menuList, selectMenuNum);

            if(menu_id != -1)
            {
                order_price = order_price + menuList.get(selectMenuNum - 1).getMenu_price();
                requestSender.menuOptionListReq(menu_id, outputStream);
                List<OptionDTO> optionList = responseReceiver.receiveOptionList(inputStream);
                //서버에서 해당 메뉴의 옵션 목록을 받아오기 위해 메뉴 아이디를 보내고 옵션 목록을 요청

                //옵션이 있을 시 옵션 선택
                if(optionList.size() != 0)
                {
                    System.out.println();
                    printMenuOption(optionList);
                    System.out.print("메뉴의 옵션을 선택하세요(여러개 선택가능, 띄어쓰기로 구분, 옵션선택종료: 0): ");
                    List<Integer> options = new ArrayList<>();
                    int selectOptionNum = -1;
                    do {
                        selectOptionNum = sc.nextInt();
                        if (selectOptionNum != 0)
                            options.add(selectOptionNum);
                    } while (selectOptionNum != 0);
                    optionNames = getOptionName(optionList, options);
                    order_price = order_price + getOptionPrice(optionList, options);
                }

                OrderDTO order = new OrderDTO(user_id, store_id, order_price, LocalDateTime.now(), order_num);
                //주문 구분을 위한 주문번호 생성, 주문 객체 생성

                if(menuCnt == 0)
                    requestSender.insertOrderReq(order, outputStream);
                //첫 메뉴 주문시에만 데이터베이스 주문 테이블에 주문을 넣는 것을 요청

                requestSender.updateMenuQuantity(menu_id, order_price, order_num, outputStream);
                //메뉴 수량 조정
                String menu_name = menuList.get(selectMenuNum - 1).getMenu_name();
                String orderMenu_id = order_num + "-" + menuCnt;

                OrderMenuDTO orderMenuDTO = new OrderMenuDTO(orderMenu_id, order_num, menu_name);
                requestSender.insertOrderMenuReq(orderMenuDTO, outputStream);
                //데이터베이스의 주문메뉴 테이블에 메뉴를 넣는 것을 요청

                OrderOptionDTO orderOptionDTO;
                for(int j = 0; j < optionNames.size(); j++)
                {
                    orderOptionDTO = new OrderOptionDTO(orderMenu_id, optionNames.get(j));
                    requestSender.insertOrderOptionReq(orderOptionDTO, outputStream);
                }
                //데이터베이스의 주문옵션 테이블에 옵션을 넣는 것을 요청

                System.out.println(menu_name + "에 대한 주문이 완료되었습니다.");
                System.out.println(); menuCnt++;
            }
        }
        return -1;
    }

    //가게 리스트 출력 메소드
    public void printStoreList(List<StoreDTO> storeList)
    {
        int i = 0;
        if(storeList.size() == 0)
            System.out.println("주문 가능한 가게가 없습니다.");
        else
            System.out.println("-------------------------------------------------------");

        for (StoreDTO dto : storeList)
        {
            System.out.println((i + 1) + ". " + storeList.get(i).getStore_name() + "  \"" + storeList.get(i).getStore_info() + "\""
                    + "\n | 영업시간 : " + storeList.get(i).getStore_time() + " | 주소 : " + storeList.get(i).getStore_address()
                    + " | 매장 전화번호 : " + storeList.get(i).getStore_phone() + " |");
            System.out.println("-------------------------------------------------------");
            i++;
        }
    }

    //가게 선택 메소드. 오류가 있다면 -1을 반환하고 잘 선택했다면 해당 가게의 아이디를 반환해준다.
    public int selectStore(List<StoreDTO> storeList, int selectNum)
    {
        if(storeList.size() < selectNum || selectNum < 1)
        {
            System.out.println("잘못된 가게번호입니다.");
            return -1;
        }
        else
            return storeList.get(selectNum - 1).getStore_id();
    }

    //가게가 영업중인지 판단하는 메소드
    public boolean isStoreOpen(StoreDTO store)
    {
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmm"));
        String storeTime = store.getStore_time();

        int open = Integer.parseInt(storeTime.substring(0, 2) + storeTime.substring(3, 5));
        int close = Integer.parseInt(storeTime.substring(6, 8) + storeTime.substring(9));
        int now = Integer.parseInt(nowTime);

        if(open < close && (open <= now && now < close))
            return true;
        else if(open > close && (open <= now || now < close))
            return true;
        else
            return false;
    }

    //메뉴 카테고리를 ArrayList에 저장하는 메소드
    public ArrayList<String> getMenuCategory(List<MenuDTO> menuList)
    {
        ArrayList<String> category = new ArrayList<>();
        for(int i = 0; i < menuList.size(); i++)
        {
            if(category.isEmpty())
                category.add(menuList.get(i).getMenu_category());
            else
            {
                boolean isSame = false;
                for(int j = 0; j < category.size(); j++)
                {
                    if(category.get(j).equals(menuList.get(i).getMenu_category()))
                    {
                        isSame = true;
                        break;
                    }
                }
                if(!isSame)
                    category.add(menuList.get(i).getMenu_category());
            }
        }
        return category;
    }

    //메뉴를 카테고리별로 출력하는 메소드
    public void printStoreMenu(List<MenuDTO> menuList, ArrayList<String> menuCategory)
    {
        System.out.println("================메뉴 목록================\n");
        for(int i = 0, j = 0; i < menuCategory.size(); i++)
        {
            System.out.println(menuCategory.get(i));
            System.out.println("---------------------------------------");

            for(MenuDTO dto: menuList)
            {
                if(dto.getMenu_category().equals(menuCategory.get(i)))
                {
                    System.out.println((j + 1) + ". " + dto.getMenu_name() + " | " + dto.getMenu_price() + "원");
                    j++;
                }
            }
            System.out.println("---------------------------------------\n");
        }
        System.out.println("========================================");
    }

    //메뉴를 선택하는 메소드. 메뉴를 잘 선택했다면 해당 메뉴의 아이디, 선택에 오류가 있다면 -1을 반환한다.
    public int selectMenu(List<MenuDTO> menuList, int selectMenuNum)
    {
        if(menuList.size() < selectMenuNum || selectMenuNum < 1)
        {
            System.out.println("잘못된 메뉴번호 선택입니다.");
            System.out.println();
            return -1;
        }

        if(menuList.get(selectMenuNum - 1).getMenu_quantity() == 0)
        {
            System.out.println("선택하신 메뉴의 수량이 소진되어 주문이 불가합니다.");
            System.out.println();
            return -1;
        }
        return menuList.get(selectMenuNum - 1).getMenu_id();
    }

    //메뉴의 옵션을 출력해주는 메소드
    public void printMenuOption(List<OptionDTO> optionList)
    {
        System.out.println("=================옵션 목록===================");
        if(optionList.size() == 0)
            System.out.println("해당 메뉴에 옵션이 없습니다.");

        else
        {
            int i = 0;
            for(OptionDTO dto: optionList) {
                System.out.println((i + 1) + ". " + dto.getOption_name() + " | " + dto.getOption_price() + "원");
                i++;
            }
        }
        System.out.println("============================================");
    }

    //선택한 옵션의 이름을 저장하는 메소드
    public List<String> getOptionName(List<OptionDTO> optionList, List<Integer> options)
    {
        List<String> optionName = new ArrayList<>();
        for(int i = 0; i < options.size(); i++)
            optionName.add(optionList.get(options.get(i) - 1).getOption_name());
        return optionName;
    }

    //옵션의 총 가격을 구해 반환해주는 메소드
    public long getOptionPrice(List<OptionDTO> optionList, List<Integer> options)
    {
        long result = 0;
        for(int i = 0; i < options.size(); i++)
            result = result + optionList.get(options.get(i) - 1).getOption_price();
        return result;
    }
}


