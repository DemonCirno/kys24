package kys24.goods.dao;


import kys24.goods.entity.Variety;

import java.util.List;

/**
 * @author Duolaimon
 *         17-4-29 下午3:36
 */
public interface VarietyDao {
    /**
     * 查询所有种类信息
     * @return  种类信息列表
     */
    List<Variety> queryAllVariety();

    /**
     * 插入一条种类信息
     * @param variety   种类信息
     */
    void insertVariety(Variety variety);

    /**
     * 更新一条种类信息
     * @param variety   种类信息
     */
    void updateVariety(Variety variety);

    /**
     * 删除一条种类信息
     * @param varietyId 种类id
     */
    void deleteVariety(int varietyId);
}
