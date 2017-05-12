package kys24.goods.service.impl;

import kys24.goods.dao.BrandDao;
import kys24.goods.entity.Brand;
import kys24.goods.enums.ResultEnum;
import kys24.goods.exception.ResultException;
import kys24.goods.service.BrandService;
import kys24.goods.utils.StateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author Duolaimon
 *         17-4-29 下午12:35
 */
@Service
public class BrandServiceImpl implements BrandService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);
    /**
     * 所有品牌的信息
     */
    private List<Brand> brandList;
    /**
     * 品牌数据控制层
     */
    private BrandDao brandDao;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public BrandServiceImpl(BrandDao brandDao) {
        this.brandDao = brandDao;
        brandList = brandDao.queryBrandList();
    }

    /**
     * 重新从数据库中获取品牌列表
     */
    private void reGetBrandList() {
        brandList = brandDao.queryBrandList();
        StateUtils.BRAND_IS_UPDATED = false;
    }

    /**
     * 获得品牌列表
     * @return  品牌列表
     */
    @Override
    public List<Brand> getBrandList() {
//        logger.info("getBrandList = {}",brandList);
        if (StateUtils.BRAND_IS_UPDATED)
            reGetBrandList();
        return brandList;
    }

    /* *******************************************************************************
                    对品牌的后台处理
     ******************************************************************************* */

    /**
     * 获得一个品牌对象容器
     * @param brandId   品牌id
     * @return  包含对象的容器
     */
    private Optional<Brand> hasBrand(int brandId) {
        if (StateUtils.BRAND_IS_UPDATED) {
            reGetBrandList();
        }
        return brandList.stream()
                .filter(c -> c.getBrandId() == brandId)
                .findAny();
    }

    /**
     * 增加一条品牌记录
     * @param brand 品牌信息
     */
    @Override
    public void addBrand(Brand brand) {
        brandDao.insertBrand(brand);
        StateUtils.BRAND_IS_UPDATED = true;
    }

    /**
     * 修改一条品牌信息
     * @param brand 品牌信息
     */
    @Override
    public void setBrand(Brand brand) throws Exception{
        if (!hasBrand(brand.getBrandId()).isPresent())
            throw new ResultException(ResultEnum.UPDATE_NOT_EXIST_ID);
        brandDao.updateBrand(brand);
        StateUtils.BRAND_IS_UPDATED = true;
    }

    /**
     * 根据指定id删除一条品牌信息
     * @param brandId   品牌id
     * @return  被删除的品牌信息
     */
    @Override
    public Brand removeBrand(int brandId) throws Exception{
        Optional<Brand> optional = hasBrand(brandId);
        Brand brand;
        if (!optional.isPresent()) {
            throw new ResultException(ResultEnum.DELETE_NOT_EXIST_ID);
        }else {
            brand = optional.get();
        }
        brandDao.deleteBrand(brandId);
        StateUtils.BRAND_IS_UPDATED = true;
        return brand;
    }
}