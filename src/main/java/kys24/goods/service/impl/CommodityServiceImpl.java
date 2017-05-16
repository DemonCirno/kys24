package kys24.goods.service.impl;

import kys24.goods.dao.CommodityDao;
import kys24.goods.dto.CommodityMainInfo;
import kys24.goods.entity.Commodity;
import kys24.goods.enums.ResultEnum;
import kys24.goods.exception.ResultException;
import kys24.goods.service.CommodityService;
import kys24.goods.service.StorageProperties;
import kys24.goods.utils.StateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Duolaimon
 *         17-4-29 上午11:00
 */
@Service
public class CommodityServiceImpl implements CommodityService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);
    /**
     * 商品图片保存路径
     */
    private final Path rootLocation;
    /**
     * 所有商品的完整信息
     */
    private List<Commodity> commodityList;
    /**
     * 商城页面的商品部分信息
     */
    private List<CommodityMainInfo> commodityMainInfoList;
    /**
     * 商品的数据控制层
     */
    private final CommodityDao commodityDao;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CommodityServiceImpl(CommodityDao commodityDao, StorageProperties properties) {
        this.commodityDao = commodityDao;
        commodityList = commodityDao.queryCommodityList();
        this.rootLocation = Paths.get(properties.getLocation());
    }

    /**
     * 重新获取商品数据列表
     */
    private void reGetCommodityList() {
        commodityList = commodityDao.queryCommodityList();
        commodityMainInfoList = commodityList.stream().map(c ->
                new CommodityMainInfo(c.getCommodityId(), c.getCommodityBrand(),
                        c.getCommodityVariety(), c.getCommodityName(),
                        c.getCommodityPrice(), c.getCommodityPicture())
        ).collect(Collectors.toList());
        StateUtils.COMMODITY_IS_UPDATED = false;
    }

    /**
     * 获得一个商品对象容器
     *
     * @param commodityId 商品id
     * @return 包含对象的容器
     */
    private Optional<Commodity> hasCommodity(int commodityId) {
        if (StateUtils.COMMODITY_IS_UPDATED) {
            reGetCommodityList();
        }
        return commodityList.stream()
                .filter(c -> c.getCommodityId() == commodityId)
                .findAny();
    }

    /**
     * 获得所有商品的完整信息
     *
     * @return 所有商品完整信息
     */
    @Override
    public List<Commodity> getCommodityList() {
        if (StateUtils.COMMODITY_IS_UPDATED) {
            reGetCommodityList();
        }
        logger.info("showProduceList : ...");
        return commodityList;
    }

    /**
     * 使用流运算处理{@link Commodity}列表,获得{@link CommodityMainInfo}列表
     *
     * @return 商城页面的商品展示信息
     */
    @Override
    public List<CommodityMainInfo> getCommodityInfoList() {
        if (StateUtils.COMMODITY_IS_UPDATED) {
            reGetCommodityList();
        }
        return commodityMainInfoList;
    }

    /**
     * 使用流运算处理所有商城页面商品信息,获得指定品牌商品信息
     *
     * @param brandId 品牌id
     * @return 商城页面指定品牌商品展示信息
     */
    @Override
    public List<CommodityMainInfo> getCommodityInfoListByBrandId(int brandId) {
        if (StateUtils.COMMODITY_IS_UPDATED) {
            reGetCommodityList();
        }
        return commodityMainInfoList.stream()
                .filter(c -> c.getCommodityBrand() == brandId)
                .collect(Collectors.toList());
    }

    /**
     * 使用流运算处理所有商城页面商品信息,获得指定种类商品信息
     *
     * @param varietyId 种类id
     * @return 商城页面指定种类商品展示信息
     */
    @Override
    public List<CommodityMainInfo> getCommodityInfoListByVarietyId(int varietyId) {
        if (StateUtils.COMMODITY_IS_UPDATED) {
            reGetCommodityList();
        }
        return commodityMainInfoList.stream()
                .filter(c -> c.getCommodityVariety() == varietyId)
                .collect(Collectors.toList());
    }

    /**
     * 使用流运算处理所有商城页面商品信息,获得指定商品信息
     *
     * @param commodityId 商品id
     * @return 商品展示页面商品信息
     */
    @Override
    public Commodity getCommodityInfoById(int commodityId) {
        Optional<Commodity> optional = hasCommodity(commodityId);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ResultException(ResultEnum.SELECT_NOT_EXIST_ID);
        }
    }

    /**
     * 使用流运算处理所有商城页面商品信息，获得包含指定关键字的商品信息
     *
     * @param searchKey 指定关键字
     * @return 指定关键字的商品信息
     */
    public List<CommodityMainInfo> getCommodityMainInfoListBySearch(String searchKey) {
        if (StateUtils.COMMODITY_IS_UPDATED) {
            reGetCommodityList();
        }
        return commodityMainInfoList.stream()
                .filter(c -> c.getCommodityName().contains(searchKey))
                .collect(Collectors.toList());
    }



    /* *******************************************************************************
                对商品的后台处理
     ******************************************************************************* */

    /**
     * 增加商品
     *
     * @param commodity 商品信息
     */
    @Override
    public void addCommodity(Commodity commodity) {
        commodityDao.insertCommodity(commodity);
        StateUtils.COMMODITY_IS_UPDATED = true;
    }

    /**
     * 更新商品信息
     *
     * @param commodity 商品信息
     */
    @Override
    public void setCommodity(Commodity commodity) throws Exception {
        if (!hasCommodity(commodity.getCommodityId()).isPresent())
            throw new ResultException(ResultEnum.UPDATE_NOT_EXIST_ID);
        commodityDao.updateCommodity(commodity);
        StateUtils.COMMODITY_IS_UPDATED = true;
    }

    /**
     * 删除指定id号的商品
     * @param commodityId   商品id
     * @return  删除的商品信息
     */
    @Override
    public Commodity removeCommodity(int commodityId) throws Exception {
        Optional<Commodity> optional = hasCommodity(commodityId);
        Commodity commodity;
        if (!optional.isPresent()) {
            throw new ResultException(ResultEnum.DELETE_NOT_EXIST_ID);
        } else {
            commodity = optional.get();
        }
        commodityDao.deleteCommodity(commodityId);
        StateUtils.COMMODITY_IS_UPDATED = true;
        return commodity;
    }

    /**
     * 上传商品的图片
     *
     * @param commodityId 商品id
     * @param file        商品图片
     * @return 商品图片地址
     */
    @Override
    public String storePicture(int commodityId, MultipartFile file) {
        String picturePath;
        try {
            if (!Files.isDirectory(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
            logger.info("文件地址 = {}", rootLocation);
            if (file.isEmpty()) {
                throw new ResultException(ResultEnum.UPLOAD_EMPTY_FILE);
            }
            String fileName = file.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            String pictureName = "4d3c2b1a_" + commodityId + fileType;// 保存在本地的图片名字
            Optional<Commodity> optional = hasCommodity(commodityId);
            /*判断是否存在目标商品*/
            if (optional.isPresent()) {
                picturePath = "/image/" + pictureName;
                StateUtils.COMMODITY_IS_UPDATED = true;
                commodityDao.uploadPicture(commodityId, picturePath);
            } else {
                throw new ResultException(ResultEnum.UPDATE_NOT_EXIST_ID);
            }

            Files.copy(file.getInputStream(), this.rootLocation.resolve(pictureName));
        } catch (ResultException e) {
            logger.info("ResultException:{}",e.getMessage());
            throw new ResultException(ResultEnum.UPDATE_NOT_EXIST_ID);
        } catch (MultipartException e) {
            logger.info("MultipartException:{}", e.getMessage());
            throw new ResultException(ResultEnum.FILE_TOO_LARGE);
        } catch (Exception e) {
            logger.info("Exception:{}", e.getMessage());
            throw new ResultException(ResultEnum.OTHERS_EXCEPTION);
        }
        return picturePath;
    }
}