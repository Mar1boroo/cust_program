package inputManager;

import persistence.dto.MenuDTO;

import java.util.Scanner;

public class MenuInputManager {
    private Scanner sc;

    public MenuInputManager(Scanner scanner)
    {
        sc = scanner;
    }

    public MenuDTO getAddMenuInfo()
    {
        MenuDTO addMenuInfo = new MenuDTO();

        String menu_name = inputMenu_name_add(sc);
        while(menu_name == "")
            menu_name = inputMenu_name_add(sc);

        long menu_price = inputMenu_price(sc);
        while(menu_price == -1)
            menu_price = inputMenu_price(sc);

        int menu_quantity = inputMenu_quantity(sc);
        while(menu_quantity == -1)
            menu_quantity = inputMenu_quantity(sc);

        String menu_category = inputMenu_category(sc);
        while(menu_category == "")
            menu_category = inputMenu_category(sc);

        System.out.println("========================================");


        addMenuDTO.setStore_id(checked_Store_Id);
        addMenuDTO.setMenu_name(menu_name);
        addMenuDTO.setMenu_price(menu_price);
        addMenuDTO.setMenu_quantity(menu_quantity);
        addMenuDTO.setMenu_category(menu_category);

        menu_id = myMenuDAO.menuAdd(addMenuDTO);

        addMenuOption(menu_id, store_id);

    }
    private String inputMenu_name_add(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.print("메뉴 이름을 입력해주세요.: ");
            input = sc.nextLine();

            if(nameDupCheck(input))
                return input;
        }
    }

    private String inputMenu_name_mod(Scanner sc)
    {
        String input;

        System.out.print("메뉴 이름을 입력해주세요.: ");
        input = sc.nextLine();

        return input;
    }

    private long inputMenu_price(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.print("메뉴 가격을 입력해주세요.: ");
            input = sc.nextLine();

            if(input.equals(""))
                return -1;

            if(isdigit(input))
            {
                return Long.parseLong(input);
            }

            else
                System.out.println("입력 형식에 맞지 않습니다. ");
        }

    }

    private int inputMenu_quantity(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.print("메뉴 재고를 입력해주세요.: ");
            input = sc.nextLine();

            if(input.equals(""))
                return -1;

            if(isdigit(input))
            {
                return Integer.parseInt(input);
            }

            else
                System.out.println("입력 형식에 맞지 않습니다. ");
        }
    }

    private String inputMenu_category(Scanner sc)
    {
        String input;
        System.out.print("메뉴 카테고리를 입력해주세요.: ");
        input = sc.nextLine();
        return input;
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

}
