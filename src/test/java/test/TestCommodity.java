package test;

import kys24.goods.service.ICommodityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cirno on 2017/5/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml" })
public class TestCommodity {

    private ICommodityService commodityService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setCommodityService(ICommodityService commodityService) {
        this.commodityService = commodityService;
    }
    @Test
    public void test_1(){
        commodityService.selectById(1);
    }
}