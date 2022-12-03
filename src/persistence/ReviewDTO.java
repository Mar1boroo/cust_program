package persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReviewDTO implements Serializable {
    private int review_id;
    private int store_id;
    private String user_id;
    private int order_id;
    private int review_rate;
    private String review_content;
    private LocalDateTime review_time;
    private String order_num;

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

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

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getReview_rate() {
        return review_rate;
    }

    public void setReview_rate(int review_rate) {
        this.review_rate = review_rate;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public LocalDateTime getReview_time() {
        return review_time;
    }

    public void setReview_time(LocalDateTime review_time) {
        this.review_time = review_time;
    }

    public String getOrder_num() {return this.order_num;}
    public void setOrder_num(String order_num) {this.order_num = order_num;}

    public ReviewDTO() {}

    public ReviewDTO(int store_id , String user_id , int order_id,int review_rate ,String review_content, LocalDateTime review_time,String order_num)
    {
        this.store_id=store_id;
        this.user_id=user_id;
        this.order_id=order_id;
        this.review_rate=review_rate;
        this.review_content=review_content;
        this.review_time=review_time;
        this.order_num=order_num;
    }

}