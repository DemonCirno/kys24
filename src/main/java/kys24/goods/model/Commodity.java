package kys24.goods.model;

import java.util.Date;

public class Commodity {
    private Integer commodityId;

    private String commodityName;

    private Integer commodityBrand;

    private Integer commodityVariety;

    private String commodityDepict;

    private Float commodityPrice;

    private Integer commodityAmount;

    private Integer commodityLeavenum;

    private Float commodityStandard;

    private Integer operator;

    private Date createTime;

    private Date updateTime;

    private String commodityPicture;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public Integer getCommodityBrand() {
        return commodityBrand;
    }

    public void setCommodityBrand(Integer commodityBrand) {
        this.commodityBrand = commodityBrand;
    }

    public Integer getCommodityVariety() {
        return commodityVariety;
    }

    public void setCommodityVariety(Integer commodityVariety) {
        this.commodityVariety = commodityVariety;
    }

    public String getCommodityDepict() {
        return commodityDepict;
    }

    public void setCommodityDepict(String commodityDepict) {
        this.commodityDepict = commodityDepict == null ? null : commodityDepict.trim();
    }

    public Float getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Float commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Integer getCommodityAmount() {
        return commodityAmount;
    }

    public void setCommodityAmount(Integer commodityAmount) {
        this.commodityAmount = commodityAmount;
    }

    public Integer getCommodityLeavenum() {
        return commodityLeavenum;
    }

    public void setCommodityLeavenum(Integer commodityLeavenum) {
        this.commodityLeavenum = commodityLeavenum;
    }

    public Float getCommodityStandard() {
        return commodityStandard;
    }

    public void setCommodityStandard(Float commodityStandard) {
        this.commodityStandard = commodityStandard;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCommodityPicture() {
        return commodityPicture;
    }

    public void setCommodityPicture(String commodityPicture) {
        this.commodityPicture = commodityPicture == null ? null : commodityPicture.trim();
    }
}