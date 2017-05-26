package kys24.order.service.impl;

import kys24.goods.dao.CommodityDao;
import kys24.goods.entity.Commodity;
import kys24.order.dao.OrderItemMapper;
import kys24.order.dao.OrderMapper;
import kys24.order.model.Order;
import kys24.order.model.OrderItem;
import kys24.order.service.IOrderService;
import kys24.user.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by cirno
 * on 2017/5/21.
 */
@Service
public class OrderServiceImpl implements IOrderService {

    /**
     * 处理订单的业务逻辑
     */
    private final OrderMapper orderMapper;
    /**
     * 处理订单项的业务逻辑
     */
    private final OrderItemMapper orderItemMapper;
    /**
     * 处理商品的业务逻辑
     */
    private final CommodityDao commodityDao;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public OrderServiceImpl(OrderMapper orderMapper,OrderItemMapper orderItemMapper,CommodityDao commodityDao){
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.commodityDao = commodityDao;
    }

    /**
     *          添加订单【事务开启】
     */
    @Override
    @Transactional
    public void addOrder(Order order, HashMap<String,Integer> cart) {

         //         1.添加订单信息

        orderMapper.insert(order);

         //         2.添加订单项信息

        //获取entry实体对象，通过实体获取键和值
        Set<Map.Entry<String, Integer>> set = cart.entrySet();
        for(Map.Entry<String,Integer> entry:set){
            Integer id = Integer.parseInt(entry.getKey());
            Commodity c = commodityDao.queryCommodityByID(id);
            if(c!=null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(order.getOrderId());
                orderItem.setCommodityId(c.getCommodityId());
                orderItem.setCommodityPrice(c.getCommodityPrice());
                orderItem.setCount(entry.getValue());
                orderItemMapper.insert(orderItem);

                 //        3.更新商品数量


                int count = c.getCommodityLeavenum()-entry.getValue();
                c.setCommodityLeavenum(count);
                commodityDao.updateByPrimaryKeySelective(c);
            }
        }
    }

    /**
     *              删除订单【事务开启】
     */
    @Override
    @Transactional
    public void deleteOrder(String orderId) {
        orderMapper.deleteByPrimaryKey(orderId);
        List<OrderItem> orderItems = orderItemMapper.queryAllById(orderId);
        for(OrderItem orderItem:orderItems){
            Integer id = orderItem.getCommodityId();
            Commodity c = commodityDao.queryCommodityByID(id);
            if(c!=null){
                Integer count = c.getCommodityLeavenum()+orderItem.getCount();
                c.setCommodityLeavenum(count);
                commodityDao.updateByPrimaryKeySelective(c);
            }
            orderItemMapper.deleteByPrimaryKey(orderItem.getOrderitemId());
        }
    }

    /**
     * 修改订单信息（无法修改订单项）【那当然是原谅它啦】
     */
    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }
    /**
     * 根据orderID查询订单
     */
    @Override
    public Order queryOrderById(String orderid) {
        return orderMapper.selectByPrimaryKey(orderid);
    }


    //后台方法

    /**
     * 分页查询所有订单
     */
    @Override
    public List<Order> queryAllOrder(Page page) {
        return orderMapper.queryAllOrder(page);
    }

    /**
     * 个人中心
     */
    @Override
    public List<Order> queryOrderByuserId(Integer userid, Page page) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("beginpage",page.getBeginIndex());
        map.put("everypage",page.getEveryPage());
        return orderMapper.queryOrderByuserId(map);
    }

    /**
     * all分页辅助
     */
    @Override
    public int count() {
        return orderMapper.countAllOrder();
    }

    /**
     * byuser分页辅助
     */
    @Override
    public int countByuserId(Integer userid) {
        return orderMapper.countByuserId(userid);
    }
}