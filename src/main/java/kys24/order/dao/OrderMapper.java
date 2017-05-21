package kys24.order.dao;

import kys24.order.model.Order;
import kys24.user.utils.Page;

import java.util.HashMap;
import java.util.List;

public interface OrderMapper {

    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> queryAllOrder(Page page);

    int countAllOrder();

    List<Order> queryOrderByuserId(HashMap map);

    int countByuserId(Integer userid);
}