package test;

import kys24.order.dao.OrderMapper;
import kys24.order.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cirno on 2017/5/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml" })
public class TestOrder {

    private OrderMapper ordermapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setOrdermapper(OrderMapper ordermapper) {
        this.ordermapper = ordermapper;
    }

    @Test
    public void test_1(){
        Order order = new Order();
        order.setOrderId("qwhjnbhgfcdtgyhjnbvggfdsa");
        int count = ordermapper.insert(order);
    }

    @Test
    public void test_2(){
        int count = ordermapper.deleteByPrimaryKey("qwhjnbhgfcdtgyhjnbvggfdsa");
        System.out.println(count);
    }
}
