package kys24.goods.controller;

import kys24.goods.dto.CommodityMainInfo;
import kys24.goods.dto.PageResult;
import kys24.goods.dto.Pages;
import kys24.goods.dto.SearchResult;
import kys24.goods.entity.Brand;
import kys24.goods.entity.Commodity;
import kys24.goods.entity.Variety;
import kys24.goods.service.BrandService;
import kys24.goods.service.CommodityService;
import kys24.goods.service.VarietyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 产品和商城展示
 *
 * @author Duolaimon
 *         17-4-28 下午8:25
 */
@RequestMapping("/shop")
@RestController
@SuppressWarnings("unused")
public class ShopController {
    private final Logger logger = LoggerFactory.getLogger(ShopController.class);

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
    public ShopController(CommodityService commodityService, BrandService brandService, VarietyService varietyService) {
        this.commodityService = commodityService;
        this.brandService = brandService;
        this.varietyService = varietyService;
    }

    /**
     * 反馈所有商品信息
     *
     * @return 所有商品所有信息列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageResult<Commodity> showAllProducesInfo(@RequestParam(defaultValue = "15") int pageSize,
                                                     @RequestParam(defaultValue = "0") int pageNumber) {
        logger.info("/shop?pageSize={}&pageNumber={}[GET]:(*^__^*) return all commodity information", pageSize, pageNumber);
        if (pageNumber != 0) {
            return Pages.getPageHandle(pageSize, commodityService.getCommodityList(), pageNumber);
        } else {
            return Pages.getPageResultHandle(pageSize, commodityService.getCommodityList());
        }
    }

    /**
     * 返回所有品牌信息
     *
     * @return 所有品牌的所有信息
     */
    @RequestMapping(value = "/brands", method = RequestMethod.GET)
    public PageResult<Brand> getAllBrandInfo(@RequestParam(defaultValue = "15") int pageSize,
                                             @RequestParam(defaultValue = "0") int pageNumber) {
        logger.info("/shop/brands?pageSize={}&pageNumber={}[GET]:(*^__^*) return all brand information", pageSize, pageNumber);
        if (pageNumber != 0) {
            return Pages.getPageHandle(pageSize, brandService.getBrandList(), pageNumber);
        } else {
            return Pages.getPageResultHandle(pageSize, brandService.getBrandList());
        }
    }

    /**
     * 返回所有商品种类信息
     *
     * @return 所有商品种类所有信息
     */
    @RequestMapping(value = "/varieties", method = RequestMethod.GET)
    public PageResult<Variety> getAllVarietyInfo(@RequestParam(defaultValue = "15") int pageSize,
                                                 @RequestParam(defaultValue = "0") int pageNumber) {
        logger.info("/shop/varieties?pageSize={}&pageNumber={}[GET]:(*^__^*) return all variety information", pageSize, pageNumber);
        if (pageNumber != 0) {
            return Pages.getPageHandle(pageSize, varietyService.getAllVarietyList(), pageNumber);
        } else {
            return Pages.getPageResultHandle(pageSize, varietyService.getAllVarietyList());
        }
    }


    /**
     * 展示商城页面商品
     *
     * @param pageSize 每页存放商品数量
     * @return 商城页面的商品部分信息
     */
    @RequestMapping(value = "/commodities", method = RequestMethod.GET)
    public PageResult<CommodityMainInfo> showMainPageProducesInfo(@RequestParam(defaultValue = "15") int pageSize,
                                                                  @RequestParam(defaultValue = "0") int pageNumber) {
        logger.info("/shop/commodities?pageSize={}&pageNumber={}[GET]:(*^__^*) show commodity information within the shop page ", pageSize, pageNumber);
        if (pageNumber != 0) {
            return Pages.getPageHandle(pageSize, commodityService.getCommodityInfoList(), pageNumber);
        } else {
            return Pages.getPageResultHandle(pageSize, commodityService.getCommodityInfoList());
        }
    }


    /**
     * 展示指定品牌号的商品
     *
     * @param brandId 品牌id
     * @return 如果id存在, 返回商城页面指定品牌id的商品部分信息
     * 否则返回空
     */
    @GetMapping(value = "/brands/{brandId}")
    public PageResult<CommodityMainInfo> showMainPageProducesInfoByBrandId(@PathVariable int brandId,
                                                                           @RequestParam(defaultValue = "15") int pageSize,
                                                                           @RequestParam(defaultValue = "0") int pageNumber) {
        logger.info("/shop/brands/{}?pageSize={}&pageNumber={}[GET]:(*^__^*) return the commodities information with specified brandId", brandId, pageSize, pageNumber);
        if (pageNumber != 0) {

            return Pages.getPageHandle(pageSize,
                    commodityService.getCommodityInfoListByBrandId(brandId), pageNumber);
        } else {
            return Pages.getPageResultHandle(pageSize, commodityService.getCommodityInfoListByBrandId(brandId));
        }
    }

    /**
     * 展示指定种类的商品
     *
     * @param varietyId 种类id
     * @return 如果id存在, 返回商城页面指定种类id的商品部分信息
     * 否则返回空
     */
    @RequestMapping(value = "/varieties/{varietyId}", method = RequestMethod.GET)
    public PageResult<CommodityMainInfo> showMainPageProducesInfoByVarietyId(@PathVariable int varietyId,
                                                                             @RequestParam(defaultValue = "15") int pageSize,
                                                                             @RequestParam(defaultValue = "0") int pageNumber) {
        logger.info("/shop/varieties/{}?pageSize={}&pageNumber={}[GET]:(*^__^*) return the commodities information with the specified varietyId", varietyId, pageSize, pageNumber);
        if (pageNumber != 0) {
            return Pages.getPageHandle(pageSize, commodityService.getCommodityInfoListByVarietyId(varietyId), pageNumber);
        } else {
            return Pages.getPageResultHandle(pageSize, commodityService.getCommodityInfoListByVarietyId(varietyId));
        }
    }

    /**
     * 展示指定商品的商品页
     *
     * @param commodityId 商品id
     * @return 如果id存在, 返回商品展示单页的商品信息
     * 否则返回空
     */
    @RequestMapping(value = "/commodities/{commodityId}", method = RequestMethod.GET)
    public Commodity showProducePageByCommodityId(@PathVariable int commodityId) {
        logger.info("/shop/commodities/{}[GET]:(*^__^*) return commodity with the specified commodityId", commodityId);
        return commodityService.getCommodityInfoById(commodityId);
    }

    /**
     * 根据接收的关键字查找相关商品
     *
     * @param searchKey 商品关键字
     * @return 如果关键字存在, 返回包含指定关键字的商品信息
     * 如果关键字为空,返回所有商品信息
     * 否则返回空
     */

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public SearchResult<CommodityMainInfo> showMainPageBySearch(@RequestParam("searchKey") String searchKey) {
        logger.info("/shop/search ---searchKey={}[POST]:(*^__^*) search commodity information by the specified [searchKey]", searchKey);
        return new SearchResult<>(commodityService.getCommodityMainInfoListBySearch(searchKey));
    }
}
