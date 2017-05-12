package kys24.order.dao;

import kys24.order.model.OrderItem;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer orderitemId);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer orderitemId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}