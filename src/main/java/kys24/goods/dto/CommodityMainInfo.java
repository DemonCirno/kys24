package kys24.goods.dto;

/**
 * @author Duolaimon
 *         17-4-29 下午1:11
 */
@SuppressWarnings("unused")
public class CommodityMainInfo {
    private int commodityID;
    private int commodityBrand;
    private int commodityVariety;
    private String commodityName;
    private float commodityPrice;
    private String commodityPicture;

    public CommodityMainInfo() {
    }

    public CommodityMainInfo(int commodityID, int commodityBrand,
                             int commodityVariety, String commodityName,
                             float commodityPrice, String commodityPicture) {
        this.commodityID = commodityID;
        this.commodityBrand = commodityBrand;
        this.commodityVariety = commodityVariety;
        this.commodityName = commodityName;
        this.commodityPrice = commodityPrice;
        this.commodityPicture = commodityPicture;
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

    public float getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(float commodityPrice) {
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
