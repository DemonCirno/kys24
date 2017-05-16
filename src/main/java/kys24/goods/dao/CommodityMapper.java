package kys24.goods.dao;

import kys24.goods.entity.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapper {

    /**
     * 插入一条商品信息
     * @param record 商品信息
     */
    void insertCommodity(Commodity record);

    /**
     * 删除指定商品
     * @param commodityId 商品ID
     */
    void deleteCommodity(Integer commodityId);

    /**
     * 更新商品信息
     * @param record 更新后的商品信息
     */
    void updateCommodity(Commodity record);

    /**
     *上传商品图片
     * @param commodityId 商品ID
     * @param commodityPicture 商品图片路径
     */
    void uploadPicture(@Param("commodityId")int commodityId, @Param("commodityPicture") String commodityPicture);

    /**
     * 查询所有商品的所有信息
     * @return
     */
    List<Commodity> queryCommodityList();
}