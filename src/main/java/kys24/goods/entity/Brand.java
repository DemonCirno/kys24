package kys24.goods.entity;

import java.util.Date;

public class Brand {
    private Integer brandid;

    private Integer varietyid;

    private String brandname;

    private Date createTime;

    private Date updateTime;

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public Integer getVarietyid() {
        return varietyid;
    }

    public void setVarietyid(Integer varietyid) {
        this.varietyid = varietyid;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname == null ? null : brandname.trim();
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
}