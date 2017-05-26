package kys24.order.service;

import kys24.order.model.Order;
import kys24.user.utils.Page;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cirno
 * on 2017/5/21.
 */
public interface IOrderService {

    /**
     * 根据ID查询订单
     */
    Order queryOrderById(String orderid);

    /**
     * 查询所有订单(分页)
     */
    List<Order> queryAllOrder(Page page);

    /**
     * 根据用户id查询订单（分页）
     */
    List<Order> queryOrderByuserId(Integer userid,Page page);

    /**
     * 查询所有订单数量
     */
    int count();

    /**
     * 根据用户ID查询订单数量
     */
    int countByuserId(Integer userid);

    /**
     * 添加订单
     */
    void addOrder(Order order, HashMap<String,Integer> cart);

    /**
     * 根据订单ID删除订单
     */
    void deleteOrder(String orderId);

    /**
     * 更改订单状态
     */
    int updateOrder(Order order);
}
