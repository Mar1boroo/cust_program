package protocol;

import persistence.*;

import persistence.StoreDTO;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResponseReceiver {

    public List<MenuDTO> receiveMenuList(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<MenuDTO> menuDTOs = new ArrayList<>();
        int size = bodyReader.readInt();

        for(int i = 0; i < size; i++)
            menuDTOs.add(MenuDTO.read(bodyReader));

        return menuDTOs;
    }

    public List<OptionDTO> receiveOptionList(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<OptionDTO> optionDTOs = new ArrayList<>();
        int size = bodyReader.readInt();

        for(int i = 0; i < size; i++)
            optionDTOs.add(OptionDTO.read(bodyReader));

        return optionDTOs;
    }

    public List<StoreDTO> receiveStoreList(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<StoreDTO> storeDTOs = new ArrayList<>();
        int size = bodyReader.readInt();

        for(int i = 0; i < size; i++)
            storeDTOs.add(StoreDTO.read(bodyReader));

        return storeDTOs;
    }

    public List<ReviewDTO> receiveReviewList(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<ReviewDTO> ReviewDTOS = new ArrayList<>();
        int size = bodyReader.readInt();

        for(int i = 0; i < size; i++)
            ReviewDTOS.add(ReviewDTO.read(bodyReader));

        return ReviewDTOS;
    }

    public List<OrderDTO> receiveOrderList(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<OrderDTO> orderDTOS = new ArrayList<>();
        int size = bodyReader.readInt();

        for(int i = 0; i < size; i++)
            orderDTOS.add(OrderDTO.read(bodyReader));

        return orderDTOS;
    }

    public List<OrderMenuDTO> receiveOrderMenuList(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<OrderMenuDTO> orderMenuDTOS = new ArrayList<>();
        int size = bodyReader.readInt();

        for(int i = 0; i < size; i++)
            orderMenuDTOS.add(OrderMenuDTO.read(bodyReader));

        return orderMenuDTOS;
    }

    public List<OrderOptionDTO> receiveOrderOptionList(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<OrderOptionDTO> orderOptionDTOS = new ArrayList<>();
        int size = bodyReader.readInt();

        for(int i = 0; i < size; i++)
            orderOptionDTOS.add(OrderOptionDTO.read(bodyReader));

        return orderOptionDTOS;
    }
}
