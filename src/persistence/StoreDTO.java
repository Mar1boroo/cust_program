package persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class StoreDTO implements Serializable {
    private int store_id;
    private String user_id; //점주 누군지 확인용
    private String store_name;
    private String store_phone;
    private String store_address;
    private boolean store_state;
    private int store_category;
    private int store_rate;
    private String store_time;
    private String store_info;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_phone() {
        return store_phone;
    }

    public void setStore_phone(String store_phone) {
        this.store_phone = store_phone;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public boolean getStore_state() {
        return store_state;
    }

    public void setStore_state(boolean store_state) {
        this.store_state = store_state;
    }

    public int getStore_category() {
        return store_category;
    }

    public void setStore_category(int store_category) {
        this.store_category = store_category;
    }

    public int getStore_rate() {
        return store_rate;
    }

    public void setStore_rate(int store_rate) {
        this.store_rate = store_rate;
    }

    public String getStore_time() {
        return store_time;
    }

    public void setStore_time(String store_time) {
        this.store_time = store_time;
    }

    public String getStore_info() {
        return store_info;
    }

    public void setStore_info(String store_info) {
        this.store_info = store_info;
    }
}
