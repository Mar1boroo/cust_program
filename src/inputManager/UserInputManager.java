package inputManager;

import persistence.UserDTO;

import java.util.Scanner;

public class UserInputManager {
    private Scanner sc;

    public UserInputManager()
    {
        sc = new Scanner(System.in);
    }
    public UserDTO getAddUserInfo()
    {
        UserDTO addUserDTO = new UserDTO();

        String user_pw = inputUser_pw(sc);
        String user_name = inputUser_name(sc);
        String user_address = inputUser_address(sc);
        String user_phone = inputUser_phone(sc);
        int user_category = inputUser_category(sc);

        addUserDTO.setUser_pw(user_pw);
        addUserDTO.setUser_name(user_name);
        addUserDTO.setUser_address(user_address);
        addUserDTO.setUser_phone(user_phone);
        addUserDTO.setUser_category(user_category);

        return addUserDTO;
    }

    private String inputUser_pw(Scanner sc)
    {
        String input = null;

        while(true)
        {
            System.out.print("비밀번호를 입력하세요. : ");
            input = sc.nextLine();

            if(input.length() >= 3)
                return input;
            else
                System.out.println("입력 형식에 맞지 않습니다(최소 세 글자). ");

        }
    }

    private String inputUser_name(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.print("이름을 입력하세요. : ");
            input = sc.nextLine();

            if(input.length() > 0)
            {
                return input;
            }
            else
                System.out.println("입력 형식에 맞지 않습니다(최소 한 글자). ");
        }

    }

    private String inputUser_address(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.print("주소를 입력하세요. : ");
            input = sc.nextLine();

            if(input.length() > 0)
            {
                return input;
            }
            else
                System.out.println("입력 형식에 맞지 않습니다(최소 한 글자). ");
        }
    }

    private String inputUser_phone(Scanner sc)
    {
        String input = null;
        while(true)
        {
            System.out.print("전화번호를 입력하세요. : ");
            input = sc.nextLine();

            if (isdigit(input))
                return input;
            else
                System.out.println("입력 형식에 맞지 않습니다. ");
        }

    }

    private int inputUser_category(Scanner sc)
    {
        int input;
        while(true)
        {
            System.out.print("계정 유형를 입력하세요.(0:관리자, 1:점주, 2:개인) : ");
            input = sc.nextInt();

            if (0 <= input && input <= 2)
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
