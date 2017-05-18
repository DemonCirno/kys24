package kys24.goods.service.impl;

import kys24.goods.dao.BrandDao;
import kys24.goods.entity.Brand;
import kys24.goods.enums.ResultEnum;
import kys24.goods.exception.ResultException;
import kys24.goods.service.BrandService;
import kys24.goods.service.StorageProperties;
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
     * 商品图片保存路径
     */
    private final Path rootLocation;
    private boolean BRAND_IS_UPDATED = true;

    /**
     * 所有品牌的信息
     */
    private List<Brand> brandList;
    /**
     * 品牌数据控制层
     */
    private final BrandDao brandDao;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public BrandServiceImpl(BrandDao brandDao, StorageProperties properties) {
        this.brandDao = brandDao;
        brandList = brandDao.queryBrandList();
        rootLocation = Paths.get(properties.getLocation());
    }

    /**
     * 重新从数据库中获取品牌列表
     */
    private void reGetBrandList() {
        brandList = brandDao.queryBrandList();
        BRAND_IS_UPDATED = false;
    }

    /**
     * 获得品牌列表
     * @return  品牌列表
     */
    @Override
    public List<Brand> getBrandList() {
//        logger.info("getBrandList = {}",brandList);
        if (BRAND_IS_UPDATED)
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
        if (BRAND_IS_UPDATED) {
            reGetBrandList();
        }
        return brandList.stream()
                .filter(c -> c.getBrandid() == brandId)
                .findAny();
    }

    /**
     * 增加一条品牌记录
     * @param brand 品牌信息
     */
    @Override
    public void addBrand(Brand brand) {
        brandDao.insertBrand(brand);
        BRAND_IS_UPDATED = true;
    }

    /**
     * 修改一条品牌信息
     * @param brand 品牌信息
     */
    @Override
    public void setBrand(Brand brand) throws Exception{
        if (!hasBrand(brand.getBrandid()).isPresent())
            throw new ResultException(ResultEnum.UPDATE_NOT_EXIST_ID);
        brandDao.updateBrand(brand);
        BRAND_IS_UPDATED = true;
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
        BRAND_IS_UPDATED = true;
        return brand;
    }

    @Override
    public String storePicture(int brandId, MultipartFile file) {
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
            String pictureName = "4d3c2b1aB_" + brandId + fileType;// 保存在本地的图片名字
            Optional<Brand> optional = hasBrand(brandId);
            /*判断是否存在目标商品*/
            if (optional.isPresent()) {
                picturePath = "/image/" + pictureName;
                brandDao.uploadPicture(brandId, picturePath);
                BRAND_IS_UPDATED = true;
            } else {
                throw new ResultException(ResultEnum.UPDATE_NOT_EXIST_ID);
            }

            Files.copy(file.getInputStream(), this.rootLocation.resolve(pictureName));
        } catch (ResultException e) {
            logger.info("ResultException:{}", e.getMessage());
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
