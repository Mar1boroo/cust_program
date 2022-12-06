package inputManager;

import persistence.StoreDTO;

import java.util.Scanner;

public class StoreInputManager {
    private Scanner sc;

    public StoreInputManager(Scanner scanner)
    {
        sc = scanner;
    }

    public StoreDTO getAddStoreInfo()
    {
        StoreDTO addStoreDTO = new StoreDTO();

        String store_name = inputStore_Name(sc);
        String store_address = inputStore_Address(sc);
        String store_phone = inputStore_Phone(sc);
        int store_category = inputStore_Category(sc);
        String store_time = inputStore_Time(sc);
        String store_info = inputStore_info(sc);

        addStoreDTO.setStore_name(store_name);
        addStoreDTO.setStore_address(store_address);
        addStoreDTO.setStore_phone(store_phone);
        addStoreDTO.setStore_category(store_category);
        addStoreDTO.setStore_time(store_time);
        addStoreDTO.setStore_info(store_info);

        return addStoreDTO;
    }

    private String inputStore_Phone(Scanner sc)
    {
        String input = new String();

        while(true)
        {
            System.out.print("가게 전화번호를 입력해주세요.(형식 : 01012345678) : ");
            input = sc.nextLine();
            System.out.println();
            if(isdigit(input))
                return input;
            else
                System.out.println("입력 형식에 맞지 않습니다.");
        }
    }

    private String inputStore_Name(Scanner sc)
    {
        String input;
        while(true)
        {
            System.out.print("가게 이름를 입력해주세요 : ");
            input = sc.nextLine();

            if (!input.equals(""))
                return input;
        }
    }

    private String inputStore_Address(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.print("가게 주소를 입력해주세요 : ");
            input = sc.nextLine();

            if (!input.equals(""))
                return input;
        }
    }
    private int inputStore_Category(Scanner sc)
    {
        String input;
        while(true)
        {
            System.out.print("가게 카테고리를 입력해주세요. : ");
            input = sc.nextLine();

            if (isdigit(input))
                return Integer.parseInt(input);
            else
                System.out.print("입력 값이 형식에 맞지 않습니다.");
        }
    }
    private String inputStore_Time(Scanner sc)
    {
        String store_time = "",open_input, close_input;
        while(true)
        {
            System.out.print("가게 오픈시간을 입력해주세요(00:00): ");
            open_input = sc.nextLine();

            if (isTime(open_input)) {
                while(true)
                {
                    System.out.print("가게 마감시간을 입력해주세요(00:00): ");
                    close_input = sc.nextLine();
                    if(isTime(close_input)) {
                        store_time += open_input + "~" + close_input;
                        return store_time;
                    }
                    else {
                        System.out.println("입력 값이 형식에 맞지 않습니다. (00:00) ");
                        break;
                    }

                }

            }
            else
                System.out.println("입력 값이 형식에 맞지 않습니다. (00:00) ");

        }
    }
    private String inputStore_info(Scanner sc)
    {
        System.out.print("가게 한 줄 소개를 입력해주세요 : ");
        return sc.nextLine();
    }

    private boolean isdigit(String input)
    {
        char tmp;

        for(int i = 0; i<input.length(); i++)
        {
            tmp = input.charAt(i);
            if(!('0' <= tmp && tmp <= '9'))
                return false;
        }

        return true;
    }

    private boolean isTime(String input)
    {
        String[] temp = input.split(":");
        for(int i = 0; i < temp.length; i++)
        {
            if(!(isdigit(temp[i]) && temp[i].length() == 2))
                return false;
        }
        if(0 <= Integer.parseInt(temp[0]) && Integer.parseInt(temp[0]) <= 24)
            if(0 <= Integer.parseInt(temp[1]) && Integer.parseInt(temp[1]) < 60)
                return true;

        return false;
    }
}
