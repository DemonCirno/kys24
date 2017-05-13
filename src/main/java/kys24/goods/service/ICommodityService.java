package kys24.goods.service;

import kys24.goods.model.Commodity;

/**
 * Created by cirno on 2017/5/13.
 */
public interface ICommodityService {

    Commodity selectById(Integer id);
}
