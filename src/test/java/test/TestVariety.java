package test;

import com.alibaba.fastjson.JSON;
import kys24.goods.dao.VarietyMapper;
import kys24.goods.entity.Variety;
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

    private VarietyMapper varietyMapper;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setVarietyMapper(VarietyMapper varietyMapper) {
        this.varietyMapper = varietyMapper;
    }

    @Test
    public void test_1(){
      List<Variety> list = varietyMapper.queryAllVariety();
     System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void test_2(){
        Variety v = new Variety();
        v.setVarietyName("鸡爪");
        for(int i=0;i<100;i++){
            varietyMapper.insertVariety(v);
        }
    }

    @Test
    public void test_3(){
        varietyMapper.deleteVariety(23);
    }

    @Test
    public void test_4(){
        Variety v = new Variety();
        v.setVarietyId(100);
        v.setType("hello");
        varietyMapper.updateVariety(v);
    }
}
