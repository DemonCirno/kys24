package test;

import kys24.goods.dao.CommodityDao;
import kys24.goods.entity.Commodity;
import kys24.goods.service.CommodityService;
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

    private CommodityDao commodityDao;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setCommodityDao(CommodityDao commodityDao) {
        this.commodityDao = commodityDao;
    }

    @Test
    public void test_1(){
      List<Commodity> list = commodityDao.queryCommodityList();
      for(Commodity c:list){
          System.out.println(c.getCommodityName());
      }
    }

    @Test
    public void test_2(){
        commodityDao.uploadPicture(3,"wetryuidfghjkhgfdghjkhgfdsfghj");
    }

    @Test
    public void test_3(){
        commodityDao.deleteCommodity(4);
    }

    @Test
    public void test_4(){
        Commodity c = commodityDao.queryCommodityByID(100);
        System.out.println(c.getCommodityName());
    }

  /*  private CommodityService commodityService;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setCommodityService(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @Test
    public void test1(){
        Commodity c = commodityService.getCommodityInfoById(100);
        System.out.println(c.getCommodityName());
    }

    @Test
    public void test2(){
        List<Commodity> list = commodityService.getCommodityList();
        for(Commodity c:list){
            System.out.println(c.getCommodityName());
        }
    }*/
}
