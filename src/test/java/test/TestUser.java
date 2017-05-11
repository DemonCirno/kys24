package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kys24.user.model.User;
import kys24.user.service.IUserService;
import kys24.user.utils.Page;
import kys24.user.utils.PageUtil;
import kys24.user.utils.YUUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })

public class TestUser {
	private IUserService userservice;

	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	public void setUserservice(IUserService userservice) {
		this.userservice = userservice;
	}
	@Test
	public void test1(){
		User user = new User();
		user.setUserId(166);
		user.setUserName("王玉");
		user.setUserPassword("123456789");
		boolean count = userservice.addUser(user);
		if(count){
			System.out.println("注册成功");
		}
	}
	@Test
	public void test2(){
		String userPhone = "13988455255";
		User user = userservice.findByuserPhone(userPhone);
		System.out.println(user.getUserPassword());
	}
	@Test
	public void test3(){
		User user = new User();
		user.setUserId(4);
		user.setUserName("白白");
		user.setUserPassword("123456789qwyu");
		user.setToken("个人");
		user.setUserAddress("哈尔滨");
		boolean count = userservice.updateUser(user);
		if(count){
			System.out.println("修改成功");
		}
	}
	@Test
	public void test4(){
		String str = YUUtils.getRandom();
		System.out.println(str);
	}
	@Test
	public void test5() throws Exception{
		String phone ="15204696480";
		String str = YUUtils.getMessageStatus(phone);
		System.out.println(str);
	}

	@Test
	public void test6(){
		Map<String,Object> map = new HashMap<String,Object>();
		String beginTime = "2017-05-01";
		String endTime = "2017-05-04";
		map.put("start",beginTime );
		map.put("end",endTime );
		List<User> list = userservice.findByCreateTime(map);
		System.out.println("user有："+list);
	}


	@Test
	public void test8(){
		String userPhone = "13988455255";
		String password = "123456789";
		boolean b = userservice.updateBypassword(userPhone, password);
		System.out.println(b);
	}

	@Test
	public void test9(){
		Page page = PageUtil.createPage(2, userservice.findByAddress("哈尔滨"), 1);
		String str = "哈尔滨";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderAddress","哈尔滨");
		map.put("beginIndex",page.getCurrentPage());
		map.put("everyPage",page.getEveryPage());
		List<User> list = userservice.findByOrderAddress(map);
		System.out.print(list);
	}
	@Test
	public void test10(){
		Map map1 = new HashMap();
		String beginTime = "2017-05-01";
		String endTime = "2017-05-04";
		map1.put("start",beginTime );
		map1.put("end",endTime );
		Page page = PageUtil.createPage(2,userservice.findByTime(map1), 1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start",beginTime );
		map.put("end",endTime );
		map.put("beginIndex",page.getCurrentPage());
		map.put("everyPage",page.getEveryPage());
		List<User> list = userservice.findByCreateTime(map);
		System.out.println("user有："+list);
	}
}