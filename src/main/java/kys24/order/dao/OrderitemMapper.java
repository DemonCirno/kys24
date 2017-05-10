package kys24.order.dao;

import kys24.order.model.Orderitem;

public interface OrderitemMapper {
    int deleteByPrimaryKey(Integer orderitemId);

    int insert(Orderitem record);

    int insertSelective(Orderitem record);

    Orderitem selectByPrimaryKey(Integer orderitemId);

    int updateByPrimaryKeySelective(Orderitem record);

    int updateByPrimaryKey(Orderitem record);
}