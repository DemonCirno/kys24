package kys24.order.service.impl;

import kys24.order.dao.OrderItemMapper;
import kys24.order.model.OrderItem;
import kys24.order.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cirno on 2017/5/21.
 */
@Service
public class OrderItemServiceImpl implements IOrderItemService{

    private OrderItemMapper orderItemMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setOrderItemMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    /**
     * 添加订单项
     * @param orderItem
     * @return
     */
    @Override
    public int addOrderItem(OrderItem orderItem) {
        return orderItemMapper.insert(orderItem);
    }

    /**
     * 根据订单项ID删除订单
     * @param orderitemid
     * @return
     */
    @Override
    public int deleteOrderItem(Integer orderitemid) {
        return orderItemMapper.deleteByPrimaryKey(orderitemid);
    }

    /**
     * 根据orderID查询所有订单项
     * @param orderid
     * @return
     */
    @Override
    public List<OrderItem> queryOrderItemsById(String orderid) {
        return orderItemMapper.queryAllById(orderid);
    }

    /**
     * 根据商品id查询销量
     * @param commodityid
     * @return
     */
    @Override
    public Object querySumCount(Integer commodityid) {
        return orderItemMapper.querySumCount(commodityid);
    }
}