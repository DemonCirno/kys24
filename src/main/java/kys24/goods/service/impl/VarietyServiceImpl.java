package kys24.goods.service.impl;

import kys24.goods.controller.ShopController;
import kys24.goods.dao.VarietyDao;
import kys24.goods.entity.Variety;
import kys24.goods.enums.ResultEnum;
import kys24.goods.exception.ResultException;
import kys24.goods.service.VarietyService;
import kys24.goods.utils.StateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author Duolaimon
 *         17-4-29 下午3:39
 */
@Service
public class VarietyServiceImpl implements VarietyService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
    /**
     * 所有分类信息
     */
    private List<Variety> varietyList;

    private final VarietyDao varietyDao;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public VarietyServiceImpl(VarietyDao varietyDao) {
        this.varietyDao = varietyDao;
        varietyList = varietyDao.queryAllVariety();
    }

    /**
     * 重新从数据库中获取品牌列表
     */
    private void reGetBrandList() {
        varietyList = varietyDao.queryAllVariety();
        StateUtils.VARIETY_IS_UPDATED = false;
    }

    @Override
    public List<Variety> getAllVarietyList() {
        if (StateUtils.VARIETY_IS_UPDATED) {
            reGetBrandList();
        }
        return varietyList;
    }

    /* *******************************************************************************
                    对分类的后台处理
     ******************************************************************************* */

    /**
     * 获得一个种类对象容器
     * @param varietyId   种类id
     * @return  包含对象的容器
     */
    private Optional<Variety> hasVariety(int varietyId) {
        if (StateUtils.VARIETY_IS_UPDATED) {
            reGetBrandList();
        }
        return varietyList.stream()
                .filter(c -> c.getVarietyId() == varietyId)
                .findAny();
    }

    /**
     * 增加一条种类记录
     * @param variety 种类信息
     */
    @Override
    public void addVariety(Variety variety) {
        varietyDao.insertVariety(variety);
        StateUtils.VARIETY_IS_UPDATED = true;
    }

    /**
     * 修改一条种类信息
     * @param variety 种类信息
     */
    @Override
    public void setVariety(Variety variety) throws Exception{
        if (!hasVariety(variety.getVarietyId()).isPresent())
            throw new ResultException(ResultEnum.UPDATE_NOT_EXIST_ID);
        varietyDao.updateVariety(variety);
        StateUtils.VARIETY_IS_UPDATED = true;
    }

    /**
     * 根据指定id删除一条种类信息
     * @param varietyId   种类id
     * @return  被删除的种类信息
     */
    @Override
    public Variety removeVariety(int varietyId) throws Exception{
        Optional<Variety> optional = hasVariety(varietyId);
        Variety variety;
        if (!optional.isPresent()) {
            throw new ResultException(ResultEnum.DELETE_NOT_EXIST_ID);
        }else {
            variety = optional.get();
        }
        varietyDao.deleteVariety(varietyId);
        StateUtils.VARIETY_IS_UPDATED = true;
        return variety;
    }
}