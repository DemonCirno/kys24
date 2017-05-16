package test;

import kys24.goods.dao.CommodityMapper;
import kys24.goods.entity.Commodity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by cirno on 2017/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml" })
public class Testgoods {

    private CommodityMapper commodityMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setCommodityMapper(CommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    @Test
    public void test_1(){
      List<Commodity> list = commodityMapper.queryCommodityList();
      for(Commodity c:list){
          System.out.println(c.getCommodityName());
      }
    }

    @Test
    public void test_2(){
        commodityMapper.uploadPicture(3,"wetryuidfghjkhgfdghjkhgfdsfghj");
    }

    @Test
    public void test_3(){
        commodityMapper.deleteCommodity(4);
    }
}
