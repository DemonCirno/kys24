package test;

import kys24.goods.dao.BrandMapper;
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
public class TestBrand {

    private BrandMapper brandMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setBrandMapper(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    @Test
    public void test_1(){
        brandMapper.selectByPrimaryKey(1);
    }
}