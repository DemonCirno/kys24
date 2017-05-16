package kys24.goods.dao;

import kys24.goods.entity.Brand;

import java.util.List;

public interface BrandMapper {



    /**
     * 插入一条品牌信息
     * @param record
     */
    void insert(Brand record);

    /**
     * 删除品牌信息
     * @param brandid
     */
    void deleteByPrimaryKey(Integer brandid);

    /**
     * 更新品牌信息
     * @param record
     */
    void updateByPrimaryKey(Brand record);

    /**
     * 查找所有品牌信息
     * @return
     */
    List<Brand> queryBrandList();
}