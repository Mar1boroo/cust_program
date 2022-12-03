package persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class OptionDTO implements Serializable {
    private int option_id;
    private long option_price;
    private String option_name;
    private int store_id;

    public int getOption_id() {
        return option_id;
    }

    public void setOption_id(int option_id) {
        this.option_id = option_id;
    }

    public long getOption_price() {
        return option_price;
    }

    public void setOption_price(long option_price) {
        this.option_price = option_price;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }
}
