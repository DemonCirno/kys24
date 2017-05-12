package kys24.goods.entity;

import java.util.Date;

/**
 * @author Duolaimon
 *         17-4-29 下午3:34
 */
public class Variety {
    private int varietyId;
    private String varietyName;
    private int parentId;
    private String type;
    private long createTime;
    private long updateTime;

    public int getVarietyId() {
        return varietyId;
    }

    public void setVarietyId(int varietyId) {
        this.varietyId = varietyId;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
