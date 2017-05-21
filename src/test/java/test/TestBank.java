package test;

import kys24.order.dao.BankMapper;
import kys24.order.model.Bank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cirno on 2017/5/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml" })
public class TestBank {
    private BankMapper bankmapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setBankmapper(BankMapper bankmapper) {
        this.bankmapper = bankmapper;
    }

    @Test
    public void test_1(){
        Bank b = new Bank();
        b.setCardid(123456789);
        b.setName("杨子墨");
        int count = bankmapper.insert(b);
        System.out.println(count);
    }

    @Test
    public void test_2(){
        Bank b = new Bank();
        b.setCardid(123456789);
        b.setName("魏垚");
        bankmapper.insert(b);
        bankmapper.selectByPrimaryKey(1000);
    }

    @Test
    @Transactional
    public void test_3(){
        Bank b = new Bank();
        b.setCardid(123456789);
        b.setName("魏垚");
        bankmapper.insert(b);
        bankmapper.selectByPrimaryKey(1000);
    }
}