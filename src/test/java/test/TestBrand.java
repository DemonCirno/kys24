package test;

import com.alibaba.fastjson.JSON;
import kys24.goods.dao.BrandMapper;
import kys24.goods.entity.Brand;
import netscape.javascript.JSObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


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
        Brand b = new Brand();
        b.setBrandid(1);
        b.setBrandname("华晨");
        brandMapper.updateBrand(b);
    }

    @Test
    public void test_2(){
        List<Brand> list = brandMapper.queryBrandList();
        for(Brand b:list){
            System.out.println(JSON.toJSONString(list));
        }
    }
}