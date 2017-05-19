package kys24.goods.dto;


import kys24.goods.entity.Commodity;

/**
 * @author Duolaimon
 *         17-4-29 下午1:11
 */
@SuppressWarnings("unused")
public class CommodityMainInfo {

    private Integer commodityID;
    private Integer commodityBrand;
    private Integer commodityVariety;
    private String commodityName;
    private Float commodityPrice;
    private String commodityPicture;

    public CommodityMainInfo() {
    }

    public CommodityMainInfo(Commodity c) {
        this.commodityID = c.getCommodityId();
        this.commodityBrand = c.getCommodityBrand();
        this.commodityVariety = c.getCommodityVariety();
        this.commodityName = c.getCommodityName();
        this.commodityPrice = c.getCommodityPrice();
        this.commodityPicture = c.getCommodityPicture();
    }

    public int getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Float getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Float commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getCommodityPicture() {
        return commodityPicture;
    }

    public void setCommodityPicture(String commodityPicture) {
        this.commodityPicture = commodityPicture;
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
}