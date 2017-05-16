package kys24.goods.dao;

import kys24.goods.entity.Variety;
import java.util.List;

public interface VarietyDao {

    /**
     *插入一条种类信息
     * @param record
     */
    void insertVariety(Variety record);

    /**
     *根据ID删除一条种类信息
     * @param varietyId
     */
    void deleteVariety(Integer varietyId);

    /**
     *更新一条种类信息
     * @param record
     */
    void updateVariety(Variety record);

    /**
     * 查询所有种类信息
     * @return
     */
    List<Variety> queryAllVariety();
}