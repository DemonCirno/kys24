package kys24.goods.dao;

import kys24.goods.entity.Commodity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author Duolaimon
 *         17-4-29 上午10:32
 */
public interface CommodityDao {
    /**
     * 查找所有商品的所有信息
     * @return  商品信息列表
     */
    List<Commodity> queryCommodityList();

    /**
     * 插入一条商品记录
     * @param commodity 商品信息
     */
    void insertCommodity(Commodity commodity);

    /**
     * 更新商品信息
     * @param newCommodity 更新后的商品信息
     */
    void updateCommodity(Commodity newCommodity);

    /**
     * 删除指定商品
     * @param commodityId 商品id
     */
    void deleteCommodity(@Param("commodityId") int commodityId);

    /**
     * 上传商品图片
     * @param commodityId       商品id
     * @param commodityPicture  商品图片路径
     */
    void uploadPicture(@Param("commodityId") int commodityId, @Param("commodityPicture") String commodityPicture);
}
