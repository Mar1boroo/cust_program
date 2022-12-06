package inputManager;

import persistence.MenuDTO;

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
        String menu_name = inputMenu_name(sc);
        long menu_price = inputMenu_price(sc);
        int menu_quantity = inputMenu_quantity(sc);
        String menu_category = inputMenu_category(sc);

        addMenuInfo.setMenu_name(menu_name);
        addMenuInfo.setMenu_price(menu_price);
        addMenuInfo.setMenu_quantity(menu_quantity);
        addMenuInfo.setMenu_category(menu_category);


        return addMenuInfo;
    }
    private String inputMenu_name(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.print("메뉴 이름을 입력해주세요.: ");
            input = sc.nextLine();

            if(!input.equals(""))
                return input;

            else
                System.out.println("입력 형식에 맞지 않습니다. ");
        }
    }

    private long inputMenu_price(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.print("메뉴 가격을 입력해주세요.: ");
            input = sc.nextLine();

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
        while(true) {
            System.out.print("메뉴 카테고리를 입력해주세요.: ");
            input = sc.nextLine();

            if (input.equals(""))
                return input;

            else
                System.out.println("입력 형식에 맞지 않습니다. ");
        }
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
