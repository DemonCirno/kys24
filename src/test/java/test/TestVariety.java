package test;

import com.alibaba.fastjson.JSON;
import kys24.goods.dao.VarietyDao;
import kys24.goods.entity.Variety;
import kys24.goods.service.VarietyService;
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
public class TestVariety {

  /*  private VarietyDao varietyDao;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setVarietyDao(VarietyDao varietyDao) {
        this.varietyDao = varietyDao;
    }

    @Test
    public void test_1(){
      List<Variety> list = varietyDao.queryAllVariety();
     System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void test_2(){
        Variety v = new Variety();
        v.setVarietyName("鸡爪");
        for(int i=0;i<100;i++){
            varietyDao.insertVariety(v);
        }
    }

    @Test
    public void test_3(){
        varietyDao.deleteVariety(23);
    }

    @Test
    public void test_4(){
        Variety v = new Variety();
        v.setVarietyId(100);
        v.setType("hello");
        varietyDao.updateVariety(v);
    }*/

    private VarietyService varietyService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setVarietyService(VarietyService varietyService) {
        this.varietyService = varietyService;
    }

    @Test
    public void test1() {
        List list = varietyService.getAllVarietyList();
        System.out.println(JSON.toJSONString(list));
    }
}
