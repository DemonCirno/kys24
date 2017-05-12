package kys24.goods.entity;

import java.util.Date;

/**
 * @author Duolaimon
 *         17-4-29 上午10:31
 */
@SuppressWarnings("unused")
public class Brand {
    private int brandId;        //品牌编号
    private int varietyId;      //商品种类编号
    private String brandName;   //品牌名字
    private long createTime;    //创建时间
    private long updateTime;    //更新时间

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getVarietyId() {
        return varietyId;
    }

    public void setVarietyId(int varietyId) {
        this.varietyId = varietyId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
}
