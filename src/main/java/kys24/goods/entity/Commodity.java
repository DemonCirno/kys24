package kys24.goods.entity;

import java.util.Date;

/**
 * @author Duolaimon
 *         17-4-29 上午10:29
 */
@SuppressWarnings("unused")
public class Commodity {
    private int commodityId;        //商品id
    private String commodityName;   //商品名称
    private int commodityBrand;     //商品品牌id
    private int commodityVariety;    //种类id
    private String commodityDepict; //商品标签
    private Float commodityPrice;   //商品单价
    private int commodityAmount;    //库存
    private int commodityLeavenum;  //商品剩余数量
    private Float commodityStandard;//商品规格
    private int operator;           //修改者id
    private long createTime;        //创建时间
    private long updateTime;        //更新时间
    private String commodityPicture;//商品图片地址

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityID) {
        this.commodityId = commodityID;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getCommodityBrand() {
        return commodityBrand;
    }

    public void setCommodityBrand(int commodityBrand) {
        this.commodityBrand = commodityBrand;
    }

    public int getCommodityVariety() {
        return commodityVariety;
    }

    public void setCommodityVariety(int commodityVariety) {
        this.commodityVariety = commodityVariety;
    }

    public String getCommodityDepict() {
        return commodityDepict;
    }

    public void setCommodityDepict(String commodityDepict) {
        this.commodityDepict = commodityDepict;
    }

    public Float getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Float commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public int getCommodityAmount() {
        return commodityAmount;
    }

    public void setCommodityAmount(int commodityAmount) {
        this.commodityAmount = commodityAmount;
    }

    public int getCommodityLeavenum() {
        return commodityLeavenum;
    }

    public void setCommodityLeavenum(int commodityLeavenum) {
        this.commodityLeavenum = commodityLeavenum;
    }

    public Float getCommodityStandard() {
        return commodityStandard;
    }

    public void setCommodityStandard(Float commodityStandard) {
        this.commodityStandard = commodityStandard;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return new Date(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime.getTime();
    }

    public Date getUpdateTime() {
        return new Date(updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime.getTime();
    }

    public String getCommodityPicture() {
        return commodityPicture;
    }

    public void setCommodityPicture(String commodityPicture) {
        this.commodityPicture = commodityPicture;
    }
}
