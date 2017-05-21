package test;

import com.alibaba.fastjson.JSON;
import kys24.order.dao.OrderItemMapper;
import kys24.order.model.OrderItem;
import kys24.order.service.IOrderItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by cirno on 2017/5/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml" })
public class TestOrderItems {
/*
   private OrderItemMapper orderItemMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setOrderItemMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    @Test
    public void test_1(){
        for(int i=0;i<10;i++){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId("dingdan");
            orderItem.setCommodityPrice((float) 23);
            orderItem.setCommodityId(23);
            orderItem.setCount(2);
            orderItemMapper.insert(orderItem);
        }
    }

    @Test
    public void test_2(){
        List<OrderItem> list = orderItemMapper.queryAllById("dingdan");
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void test_3(){
        int count = orderItemMapper.countById("dingdan");
        System.out.println(count);
    }*/

    private IOrderItemService orderItemService;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setOrderItemService(IOrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Test
    public void test_1(){
        List<OrderItem> list = orderItemService.queryOrderItemsById("dingdan");
        System.out.println(JSON.toJSONString(list));
    }
}
