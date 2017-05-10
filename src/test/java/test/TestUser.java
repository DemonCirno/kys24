package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kys24.user.model.User;
import kys24.user.service.IUserService;
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
		user.setUserId(20);
		user.setUserName("王玉");
		user.setUserPassword("123456789");
		boolean count = userservice.addUser(user);
		if(count){
			System.out.println("注册成功");
		}
	}
	@Test
	public void test2(){
		String userPhone = "15204696480";
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
		Map<String,String> map = new HashMap<String,String>();
		String beginTime = "2017-05-01";
		String endTime = "2017-05-04";
		map.put("start",beginTime );
		map.put("end",endTime );
		List<User> list = userservice.findByCreateTime(map);
		System.out.println("user有："+list);
	}

	@Test
	public void test7(){
		List<User> list;
		String orderAddress = "哈尔滨市";
		list = userservice.findByOrderAddress(orderAddress);
		System.out.println("用户有"+list);
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
		List<User> list = userservice.selectAllUser();
		for(User user:list){
			System.out.println(user.getUserName());
		}
	}
}