package kys24.order.dto;

/**
 * Created by cirno on 2017/5/20.
 */
public class CartItems {

    //商品名
    private String commodityname;

    //商品单价
    private float price;

    //商品数量
    private Integer num;

   /* public CartItems(){}

    public CartItems(String commodityname,float price,Integer num){
        this.commodityname = commodityname;
        this.price = price;
        this.num = num;
    }*/

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
