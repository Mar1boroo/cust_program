package persistence;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OrderDTO implements Serializable {
    private int order_id;
    private String user_id;
    private int store_id;
    private long order_price;
    private String order_state;
    private LocalDateTime order_orderTime;
    private String order_num;

    private List<OrderMenuDTO> orderMenuList;
    private List<OrderOptionDTO> orderOptionList;

    public OrderDTO(String user_id, int store_id, long order_price, LocalDateTime order_orderTime, String order_num) {
        this.user_id = user_id;
        this.store_id = store_id;
        this.order_price = order_price;
        this.order_state = "접수 대기";
        this.order_orderTime = order_orderTime;
        this.order_num = order_num;
    }

    public OrderDTO(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public long getOrder_price() {
        return order_price;
    }

    public String getOrder_state() {
        return order_state;
    }

    public LocalDateTime getOrder_orderTime() {
        return order_orderTime;
    }

    public List<OrderMenuDTO> getOrderMenuList() {
        return orderMenuList;
    }

    public List<OrderOptionDTO> getOrderOptionList() {
        return orderOptionList;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public void setOrder_price(long order_price) {
        this.order_price = order_price;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public void setOrder_orderTime(LocalDateTime order_orderTime) {
        this.order_orderTime = order_orderTime;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }
}
