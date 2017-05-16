package kys24.goods.dao;

import kys24.goods.entity.Variety;
import java.util.List;

public interface VarietyMapper {

    /**
     *插入一条种类信息
     * @param record
     */
    void insert(Variety record);

    /**
     *根据ID删除一条种类信息
     * @param varietyId
     */
    void deleteByPrimaryKey(Integer varietyId);

    /**
     *更新一条种类信息
     * @param record
     */
    void updateByPrimaryKey(Variety record);

    /**
     * 查询所有种类信息
     * @return
     */
    List<Variety> queryAllVariety();
}