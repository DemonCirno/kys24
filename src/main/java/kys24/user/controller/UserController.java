package kys24.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kys24.user.model.User;
import kys24.user.service.IUserService;
import kys24.user.utils.Page;
import kys24.user.utils.PageUtil;
import kys24.user.utils.YUUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author wlian
 */
@CrossOrigin
@Controller
@RequestMapping("/userController")
public class UserController {
	private IUserService userService;

	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	//用户登录
	@RequestMapping(path="/login",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Integer> login(String userPhone,String userPassword){
		Map<String,Integer> map = new HashMap<>();
		User user = userService.findByuserPhone(userPhone);
		if(user==null){
			map.put("info", 0);
		}else if(user.getUserPassword().equals(userPassword)){
			map.put("info", 1);
		}else map.put("info", -1);
		return map;
	}
	//用户注册
	@RequestMapping(path="/register",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Integer> register(User user){
		Map<String,Integer> map = new HashMap<>();
		String phone = user.getUserPhone();
		User newuser  = userService.findByuserPhone(phone);
		if(newuser==null){
			if(userService.addUser(user)){
				map.put("info", 1);
			}else map.put("info", 0);
		}else{
			map.put("info", -1);
		}
		return map;
	}
	//删除用户
	@RequestMapping(path="/users/{userId}",method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String,Integer> delete(@PathVariable("userId") Integer userId){
		Map<String,Integer> map = new HashMap<>();
		if(userService.delectByid(userId)){
			map.put("upte", 1);
		}else{
			map.put("upte", 0);
		}
		return map;
	}
	//发短信
	@RequestMapping(path="/message/{userPhone}",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> message(@PathVariable("userPhone") String userPhone){
		Map<String,String> map = new HashMap<>();
		String newnum = YUUtils.getMessageStatus(userPhone);
		if(newnum.equals("0")){
			map.put("rg", "0");
		}else{
			map.put("rg",newnum);
		}
		return map;
	}

	//个人中心修改用户
	@RequestMapping(path="/update",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Integer> update(User user){
		Map<String,Integer> map = new HashMap<>();
		if(userService.updateUser(user)){
			map.put("upte", 1);
		}else{
			map.put("upte", 0);
		}
		return map;
	}
	//忘记密码修改用户
	@RequestMapping(path="/update_1",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Integer> updateBypassword(String userPhone,String newPassword){
		Map<String,Integer> map = new HashMap<>();
		if(userService.updateBypassword(userPhone, newPassword)){
			map.put("upte", 1);
		}else{
			map.put("upte", 0);
		}
		return map;
	}

	//个人中心修改密码
	@RequestMapping(path="/update_2",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Integer> updateByword(Integer userId,String Password){
		Map<String,Integer> map = new HashMap<>();
		User user = userService.selectById(userId);
		String oldPassword = user.getUserPassword();
		if(Password==oldPassword){
			map.put("upte",1);
		}else{
			map.put("upte", 0);
		}
		return map;
	}

	//根据id查找用户
	@RequestMapping(path="/users/{userId}",method = RequestMethod.GET)
	@ResponseBody
	public User selectById(@PathVariable("userId") Integer userId){
		User user = userService.selectById(userId);
		return user;
	}
	//根据收货地址查询用户
	@RequestMapping(path="/users/address",method = RequestMethod.POST)
	@ResponseBody
	public List<User> selectByaddress(String orderAddress,Integer currentPage){
		Page page = PageUtil.createPage(10, userService.findByAddress(orderAddress),currentPage);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderAddress",orderAddress);
		map.put("beginIndex",page.getCurrentPage());
		map.put("everyPage",page.getEveryPage());
		List<User> list = userService.findByOrderAddress(map);
		return list;
	}

	//根据时间来查找用户
	@RequestMapping(path="/users/time",method = RequestMethod.POST)
	@ResponseBody
	public List<User> selectBytime(String date1,String date2,Integer currentPage){
		Map<String,String> timemap = new HashMap<String,String>();
		timemap.put("start", date1);
		timemap.put("end", date2);
		Page page = PageUtil.createPage(10,userService.findByTime(timemap),currentPage);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start",date1);
		map.put("end",date2);
		map.put("beginIndex",page.getCurrentPage());
		map.put("everyPage",page.getEveryPage());
		List<User> list = userService.findByCreateTime(map);
		return list;
	}

	//查找所有用户(分页显示)
	@RequestMapping(path="/users/page/{currentPage}",method = RequestMethod.GET)
	@ResponseBody
	public List<User> selectAllUser(@PathVariable("currentPage") Integer currentPage){
		Page page = PageUtil.createPage(10,userService.selectUsernum(),currentPage);
		List<User> list = userService.selectAllUser(page);
		return list;
	}

}