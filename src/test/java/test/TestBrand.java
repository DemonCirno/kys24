package test;

import kys24.goods.dao.BrandDao;
import kys24.goods.entity.Brand;
import kys24.goods.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by cirno on 2017/5/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml" })
public class TestBrand {
    private BrandDao brandao;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setBrandao(BrandDao brandao) {
        this.brandao = brandao;
    }
    @Test
    public void test_1(){
        List<Brand> list = brandao.queryBrandList();
        for(Brand b:list){
            System.out.println(b.getBrandName());
        }
    }
}
