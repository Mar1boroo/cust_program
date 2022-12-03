package persistence;

import lombok.*;
//import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor

//@Alias("orderOptionList")
public class OrderOptionDTO implements Serializable {
    private String orderMenu_id;
    private String option_name;

    public OrderOptionDTO(String orderMenu_id, String option_name)
    {
        this.orderMenu_id = orderMenu_id;
        this.option_name = option_name;
    }

    public String getOrderMenu_id() {
        return orderMenu_id;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOrderMenu_id(String orderMenu_id) {
        this.orderMenu_id = orderMenu_id;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }
}
