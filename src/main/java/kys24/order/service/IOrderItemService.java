package kys24.order.service;

import kys24.order.model.OrderItem;

import java.util.List;

/**
 * Created by cirno on 2017/5/21.
 */
public interface IOrderItemService {

    /**
     * 插入订单项
     * @param orderItem
     * @return
     */
    int addOrderItem(OrderItem orderItem);

    /**
     * 根据ID删除订单项
     * @param orderitemid
     * @return
     */
    int deleteOrderItem(Integer orderitemid);

    /**
     * 根据订单ID查找订单项
     * @param orderid
     * @return
     */
    List<OrderItem> queryOrderItemsById(String orderid);

    /**
     * 根据商品id查询商品销量
     * @param commodityid
     * @return
     */
    Object querySumCount(Integer commodityid);

}
