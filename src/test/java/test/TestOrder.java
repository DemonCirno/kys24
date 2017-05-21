package test;

import com.alibaba.fastjson.JSON;
import kys24.order.dao.OrderMapper;
import kys24.order.model.Order;
import kys24.order.service.IOrderService;
import kys24.user.utils.Page;
import kys24.user.utils.PageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by cirno on 2017/5/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml" })
public class TestOrder {

    /*private OrderMapper ordermapper;

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
    }*/

    private IOrderService orderService;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Test
    public void test_1(){
        Order order = orderService.queryOrderById("dingdan");
        System.out.println(JSON.toJSONString(order));
    }

    @Test
    public void test_2(){
        int count = orderService.count();
        System.out.println(count);
    }

    @Test
    public void test_3(){
        Page page = PageUtil.createPage(2,orderService.count(),1);
        List list = orderService.queryAllOrder(page);
        list.add(page);
        System.out.println(JSON.toJSONString(list));
    }
}
