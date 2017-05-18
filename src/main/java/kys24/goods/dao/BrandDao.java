package kys24.goods.dao;

import kys24.goods.entity.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandDao {



    /**
     * 插入一条品牌信息
     * @param record
     */
    void insertBrand(Brand record);

    /**
     * 删除品牌信息
     * @param brandid
     */
    void deleteBrand(Integer brandid);

    /**
     * 更新品牌信息
     * @param record
     */
    void updateBrand(Brand record);

    /**
     * 查找所有品牌信息
     * @return
     */
    List<Brand> queryBrandList();

    /**
     *
     * @param brandId
     * @param brandPicture
     */
    void uploadPicture(@Param("brandId") int brandId, @Param("brandPicture") String brandPicture);
}