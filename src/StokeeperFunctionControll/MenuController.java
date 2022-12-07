package StokeeperFunctionControll;

import persistence.MenuDTO;
import persistence.OptionDTO;
import protocol.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    private ResponseSender responseSender;
    private ResponseReceiver responseReceiver;
    private RequestSender requestSender;
    private RequestReceiver requestReceiver;

    public MenuController()
    {
        this.responseSender = new ResponseSender();
        this.responseReceiver = new ResponseReceiver();
        this.requestSender = new RequestSender();
        this.requestReceiver = new RequestReceiver();
    }
    public void handleMenuApply(Scanner scc,int store_id, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        Scanner sc = new Scanner(System.in);
        //시작 신호 보내기
        Header menuApplyStartHeader = new Header(
                Header.TYPE_START,
                Header.CODE_INSERT_MENU,
                0);
        outputStream.write(menuApplyStartHeader.getBytes());

        MenuDTO addMenuDTO = new MenuDTO();

        if(requestReceiver.receiveMenuInfoReq(inputStream)) //menu_info요청 받기
        {
            responseSender.sendAddMenuInfoAns(store_id, outputStream);//menu_info보내기
        }

        if((addMenuDTO = requestReceiver.receiveMenuAddResult(inputStream)) != null) {
            //메뉴 옵션 추가 시작 신호 보내기
            BodyMaker bodyMaker = new BodyMaker();
            bodyMaker.addIntBytes(store_id);
            byte[] body = bodyMaker.getBody();
            Header menuOptionAddStartHeader = new Header(
                    Header.TYPE_START,
                    Header.CODE_INSERT_MENU_OPTION,
                    body.length);
            outputStream.write(menuOptionAddStartHeader.getBytes());
            outputStream.write(body);

            if(requestReceiver.receiveMenuOptionReq(inputStream)) //menu_option요청 받기
            {
                List<OptionDTO> optionList = new ArrayList<OptionDTO>();
                int optSize = inputStream.readInt();
                for(int i = 0; i < optSize; i++)
                {
                    optionList.add(OptionDTO.read(inputStream));
                }

                printMenuAllOption(optionList);

                List<OptionDTO> selectOptions = new ArrayList<OptionDTO>();
                int selectOptionNum;

                System.out.println("해당 메뉴에 추가할 옵션 번호를 입력하세요. (종료 : 0): ");
                while(true)
                {
                    selectOptionNum = sc.nextInt();
                    if(selectOptionNum == 0)
                    {
                        break;
                    }
                    selectOptions.add(optionList.get(selectOptionNum -1));
                }

                responseSender.sendAddMenuOptionAns(selectOptions, addMenuDTO.getMenu_id(), outputStream);

                List<OptionDTO> addedMenuOptions = new ArrayList<OptionDTO>();
                MenuDTO addedMenuWithOption = new MenuDTO();

                if((addedMenuWithOption = requestReceiver.receiveMenuOptionAddResult(inputStream)) != null)
                {
                    System.out.println("이제 출력할거임");
                    int addedMenuOptionNum = inputStream.readInt();
                    for(int i = 0; i< addedMenuOptionNum; i++)
                    {
                        addedMenuOptions.add(OptionDTO.read(inputStream));
                    }

                    System.out.println("메뉴가 추가 되었습니다.");
                    printOneMenu(addedMenuWithOption);
                    printMenuAllOption(addedMenuOptions);

                }

            }

        }
        else
            System.out.println("메뉴 추가 실패.");
    }


    public void printOneMenu(MenuDTO menuDTO)
    {
        System.out.println("================ 메뉴 확인===============");

        System.out.println(" | 카테고리 : " + menuDTO.getMenu_category() + " | " + menuDTO.getMenu_name() + " | " + menuDTO.getMenu_price() + "원" + " | 재고 : " + menuDTO.getMenu_quantity());

        System.out.println("=======================================");

    }

    public List<OptionDTO> printMenuAllOption(List<OptionDTO> dtos)
    {
        System.out.println("=================옵션 목록===================");
        if(dtos.size() == 0)
            System.out.println("해당 메뉴에 옵션이 없습니다.");

        else
        {
            int i = 0;
            for(OptionDTO dto: dtos) {
                System.out.println((i + 1) + ". " + dto.getOption_name() + " | " + dto.getOption_price() + "원");
                i++;
            }
        }
        System.out.println("============================================");
        return dtos;
    }
}
