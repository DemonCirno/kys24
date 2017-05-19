package kys24.goods.controller;

import kys24.goods.dto.BackStageResult;
import kys24.goods.dto.PageResult;
import kys24.goods.dto.Pages;
import kys24.goods.entity.Brand;
import kys24.goods.entity.Commodity;
import kys24.goods.entity.Variety;
import kys24.goods.enums.ResultEnum;
import kys24.goods.exception.ResultException;
import kys24.goods.service.BrandService;
import kys24.goods.service.CommodityService;
import kys24.goods.service.VarietyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author Duolaimon
 *         17-5-1 下午7:04
 */
@RestController
@RequestMapping("/back")
@SuppressWarnings("unused")
public class BackStageController {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(BackStageController.class);

    /**
     * 用于处理商品的业务逻辑
     */
    private final CommodityService commodityService;
    /**
     * 用于处理品牌的业务逻辑
     */
    private final BrandService brandService;
    /**
     * 用于处理商品种类的业务逻辑
     */
    private final VarietyService varietyService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public BackStageController(CommodityService commodityService, BrandService brandService, VarietyService varietyService) {
        this.commodityService = commodityService;
        this.brandService = brandService;
        this.varietyService = varietyService;
    }

    /**
     * 反馈所有商品信息
     *
     * @return 所有商品所有信息列表
     */
    @RequestMapping(value = "/commodities", method = RequestMethod.GET)
    public PageResult<Commodity> showAllProducesInfo(@RequestParam(defaultValue = "15") int pageSize,
                                                     @RequestParam(defaultValue = "0") int pageNumber) {
        logger.info("/back/commodities?pageSize={}:[GET]:(*^__^*) return all the commodities information", pageSize);
        if (pageNumber != 0) {
            return Pages.getPageHandle(pageSize, commodityService.getCommodityList(), pageNumber);
        } else {
            return Pages.getPageResultHandle(pageSize, commodityService.getCommodityList());
        }
    }

    /**
     * 增加一条商品
     *
     * @param commodity 商品信息
     * @return 操作结果
     */
    @RequestMapping(value = "/commodities", method = RequestMethod.POST)
    public BackStageResult<Commodity> addCommodity(@RequestBody Commodity commodity) {
        logger.info("/back/commodities:[POST]:(*^__^*) add a new commodity");
        try {
            commodityService.addCommodity(commodity);
        } catch (DuplicateKeyException e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(ResultEnum.DUPLICATE_INSERT, null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(ResultEnum.OTHERS_EXCEPTION, null);
        }
        commodity.setCreateTime(new Date());
        commodity.setUpdateTime(new Date());
        return new BackStageResult<>(ResultEnum.SUCCESS, commodity);
    }

    /**
     * 修改更新一条商品
     *
     * @param commodity 商品信息
     * @return 操作结果
     */
    @RequestMapping(value = "/commodities", method = RequestMethod.PUT)
    public BackStageResult<Commodity> setCommodity(@RequestBody Commodity commodity) {
        logger.info("/back/commodities[PUT]:(*^__^*) alter commodity ");
        try {
            commodityService.setCommodity(commodity);
        } catch (ResultException e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(e, commodity);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(ResultEnum.OTHERS_EXCEPTION, null);
        }
        return new BackStageResult<>(ResultEnum.SUCCESS, commodity);
    }

    /**
     * 根据商品id删除商品
     *
     * @param commodityId 商品id
     * @return 被删除的商品信息
     */
    @RequestMapping(value = "/commodities/{commodityId}", method = RequestMethod.DELETE)
    public BackStageResult<Commodity> removeCommodity(@PathVariable int commodityId) {
        logger.info("/back/commodities/{}[DELETE]:(*^__^*) delete commodity", commodityId);
        Commodity commodity;
        try {
            commodity = commodityService.removeCommodity(commodityId);
        } catch (ResultException e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(e, null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(ResultEnum.OTHERS_EXCEPTION, null);
        }
        return new BackStageResult<>(ResultEnum.SUCCESS, commodity);
    }

    /**
     * 上传商品图片至指定路径
     * {@link kys24.goods.service.StorageProperties}类设置了路径位置
     *
//     * @param file        上传的图片
//     * @param commodityId 商品id
     * @return 商品图片上传结果
     */
    @PostMapping("/pictures/{commodityId}")
    public BackStageResult<String> uploadCommodityPicture(@RequestBody MultipartFile file,
                                                          @PathVariable("commodityId") int commodityId) {
        logger.info("/back/pictures[POST]:(*^__^*) upload commodity picture");
        String picturePath;
        try {
            picturePath = commodityService.storePicture(commodityId, file);
        } catch (ResultException e) {
            return new BackStageResult<>(e, null);
        }
        return new BackStageResult<>(ResultEnum.SUCCESS, picturePath);
    }


    /*
        后台品牌处理
     */

    /**
     * 返回所有品牌信息
     *
     * @return 所有品牌的所有信息
     */
    @RequestMapping(value = "/brands", method = RequestMethod.GET)
    public PageResult<Brand> getAllBrandInfo(@RequestParam(defaultValue = "15") int pageSize,
                                             @RequestParam(defaultValue = "0") int pageNumber) {
        logger.info("/back/brands?pageSize={}[GET]:(*^__^*) return all the brands information", pageSize);
        if (pageNumber != 0) {
            return Pages.getPageHandle(pageSize, brandService.getBrandList(), pageNumber);
        } else {
            return Pages.getPageResultHandle(pageSize, brandService.getBrandList());
        }
    }

    /**
     * 添加品牌
     *
     * @param brand 品牌信息
     * @return 添加品牌结果
     */
    @RequestMapping(value = "/brands", method = RequestMethod.POST)
    public BackStageResult<Brand> addBrand(@RequestBody Brand brand) {
        logger.info("/back/brands[POST]:(*^__^*) add a new brand ");
        try {
            brandService.addBrand(brand);
        } catch (DuplicateKeyException e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(ResultEnum.DUPLICATE_INSERT, null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(ResultEnum.OTHERS_EXCEPTION, null);
        }
        brand.setCreateTime(new Date());
        brand.setUpdateTime(new Date());
        return new BackStageResult<>(ResultEnum.SUCCESS, brand);
    }

    /**
     * 修改品牌信息
     *
     * @param brand 品牌信息
     * @return 修改品牌结果
     */
    @PutMapping("/brands")
    public BackStageResult<Brand> setBrand(@RequestBody Brand brand) {
        logger.info("/back/brands[PUT]:(*^__^*) alter brand");
        try {
            brandService.setBrand(brand);
        } catch (ResultException e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(e, brand);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(ResultEnum.OTHERS_EXCEPTION, null);
        }
        return new BackStageResult<>(ResultEnum.SUCCESS, brand);
    }

    /**
     * 根据指定id删除品牌信息
     *
     * @param brandId 品牌id
     * @return 删除品牌结果
     */
    @DeleteMapping("/brands/{brandId}")
    public BackStageResult<Brand> removeBrand(@PathVariable("brandId") int brandId) {
        logger.info("/back/brands/{}[DELETE]:(*^__^*) delete brand", brandId);
        Brand brand;
        try {
            brand = brandService.removeBrand(brandId);
        } catch (ResultException e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(e, null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(ResultEnum.OTHERS_EXCEPTION, null);
        }
        return new BackStageResult<>(ResultEnum.SUCCESS, brand);
    }

    @PostMapping("/pictures/brand/{brandId}")
    public BackStageResult<String> uploadBrandPicture(@RequestParam("file") MultipartFile file,
                                                      @PathVariable("brandId") int brandId) {
        logger.info("/back/pictures/brand[POST]:(*^__^*) upload brand picture");
        String picturePath;
        try {
            picturePath = brandService.storePicture(brandId, file);
        } catch (ResultException e) {
            return new BackStageResult<>(e, null);
        }
        return new BackStageResult<>(ResultEnum.SUCCESS, picturePath);
    }
    /*
       后台种类处理
     */

    /**
     * 返回所有商品种类信息的页面
     *
     * @return 所有商品种类所有信息
     */
    @RequestMapping(value = "/varieties", method = RequestMethod.GET)
    public PageResult<Variety> getAllVarietyInfo(@RequestParam(defaultValue = "15") int pageSize,
                                                 @RequestParam(defaultValue = "0") int pageNumber) {
        logger.info("/back/varieties?pageSize={}[GET]:(*^__^*) return all the varieties information", pageSize);
        if (pageNumber != 0) {
            return Pages.getPageHandle(pageSize, varietyService.getAllVarietyList(), pageNumber);
        } else {
            return Pages.getPageResultHandle(pageSize, varietyService.getAllVarietyList());
        }
    }

    /**
     * 添加种类
     *
     * @param variety 种类信息
     * @return 添加种类结果
     */
    @RequestMapping(value = "/varieties", method = RequestMethod.POST)
    public BackStageResult<Variety> addVariety(@RequestBody Variety variety) {
        logger.info("/back/varieties[POST]:(*^__^*) add a new variety");
        try {
            varietyService.addVariety(variety);
        } catch (DuplicateKeyException e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(ResultEnum.DUPLICATE_INSERT, null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(ResultEnum.OTHERS_EXCEPTION, null);
        }
        variety.setCreateTime(new Date());
        variety.setUpdateTime(new Date());
        return new BackStageResult<>(ResultEnum.SUCCESS, variety);
    }

    /**
     * 修改种类
     *
     * @param variety 种类信息
     * @return 修改种类结果
     */
    @PutMapping("/varieties")
    public BackStageResult<Variety> setVariety(@RequestBody Variety variety) {
        logger.info("/back/varieties[PUT]:(*^__^*) alter variety");
        try {
            varietyService.setVariety(variety);
        } catch (ResultException e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(e, null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(ResultEnum.OTHERS_EXCEPTION, null);
        }
        return new BackStageResult<>(ResultEnum.SUCCESS, variety);
    }

    /**
     * 根据指定id删除种类
     *
     * @param varietyId 种类id
     * @return 删除种类结果
     */
    @DeleteMapping("/varieties/{varietyId}")
    public BackStageResult<Variety> removeVariety(@PathVariable("varietyId") int varietyId) {
        logger.info("/back/varieties/{}[DELETE]:(*^__^*) delete variety", varietyId);
        Variety variety;
        try {
            variety = varietyService.removeVariety(varietyId);
        } catch (ResultException e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(e, null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new BackStageResult<>(ResultEnum.OTHERS_EXCEPTION, null);
        }
        return new BackStageResult<>(ResultEnum.SUCCESS, variety);
    }
}
