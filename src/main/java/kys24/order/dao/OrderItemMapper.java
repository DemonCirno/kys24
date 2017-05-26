package kys24.order.dao;

import kys24.order.model.OrderItem;
import java.util.List;

public interface OrderItemMapper {

    int deleteByPrimaryKey(Integer orderitemId);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer orderitemId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItem> queryAllById(String orderid);

    int countById(String orderid);

    Object querySumCount(Integer commodityid);

}