package kys24.goods.service.impl;

import kys24.goods.dao.CommodityMapper;
import kys24.goods.model.Commodity;
import kys24.goods.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cirno on 2017/5/13.
 */
@Service
public class CommodityServiceImpl implements ICommodityService {

   private CommodityMapper commodityMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
   public void setCommodityMapper(CommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    @Override
    public Commodity selectById(Integer id) {
        return commodityMapper.selectByPrimaryKey(id);
    }
}
