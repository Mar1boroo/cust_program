package control;

import persistence.MenuDTO;
import persistence.OptionDTO;
import persistence.StoreDTO;
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

        List<StoreDTO> storeList = responseReceiver.receiveStoreList(inputStream);

        long order_price = 0;
        int selectMenuNum = -1;
        int store_id;
        List<String> optionNames = new ArrayList<>();

        boolean continueFlag = false;
        //가게 선택하는 것입니다......
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
        List<MenuDTO> menuList = responseReceiver.receiveMenuList(inputStream);
        ArrayList<String> menuCategory = getMenuCategory(menuList);

        int menuCnt = 0;
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
            int menu_id = selectMenu(menuList, selectMenuNum); //메뉴아이디 가져오기

            if(menu_id != -1) //옵션...
            {
                order_price = order_price + menuList.get(selectMenuNum - 1).getMenu_price();
                System.out.println();

                requestSender.menuOptionListReq(menu_id, outputStream);
                List<OptionDTO> optionList = responseReceiver.receiveOptionList(inputStream);

                if(optionList.size() != 0)
                {
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

                String order_num = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss-")) + user_id; // 주문번호 생성
                if(menuCnt == 0)

            }
        }



        return -1;
    }

    public void printStoreList(List<StoreDTO> storeList)
    {
        int i = 0;
        System.out.println("-------------------------------------------------------");
        for (StoreDTO dto : storeList) {
            System.out.println((i + 1) + ". " + dto.getStore_name() + "  \"" + dto.getStore_info() + " \"" +
                    "\n | 영업시간 : " + dto.getStore_time() + " \t| " + ((dto.getStore_state()) ? "(영업중)" : "(영업종료)") +
                    "\n | 주소 : " + dto.getStore_address() + "\t| 매장 전화번호 : " + dto.getStore_phone());
            System.out.println("-------------------------------------------------------");
            i++;
        }
    }

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

    public void printStoreMenu(List<MenuDTO> menuList, ArrayList<String> menuCategory)
    {
        System.out.println("================메뉴 목록================");
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

    public List<String> getOptionName(List<OptionDTO> optionList, List<Integer> options)
    {
        List<String> optionName = new ArrayList<>();
        for(int i = 0; i < options.size(); i++)
            optionName.add(optionList.get(options.get(i) - 1).getOption_name());
        return optionName;
    }

    public long getOptionPrice(List<OptionDTO> optionList, List<Integer> options)
    {
        long result = 0;
        for(int i = 0; i < options.size(); i++)
            result = result + optionList.get(options.get(i) - 1).getOption_price();
        return result;
    }
}


