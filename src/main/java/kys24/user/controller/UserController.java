package kys24.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kys24.user.model.User;
import kys24.user.service.IUserService;
import kys24.user.utils.MD5Util;
import kys24.user.utils.Page;
import kys24.user.utils.PageUtil;
import kys24.user.utils.YUUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

/**
 * @author wlian
 */
@CrossOrigin
@RestController
@RequestMapping("/userController")
public class UserController {

	/**
	 *分页显示每页显示数据条数
	 */
	private final Integer EVERYPAGE = 10;

	/**
	 *用于处理用户的业务逻辑
	 */
	private final IUserService userService;

	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	public UserController(IUserService userService){
		this.userService = userService;
	}

	/**
	 * cookie设置
	 *
	 */

	/**
	 * 用户登录
	 * @param httpSession
	 * @param userPhone
	 * @param userPassword
	 * @return
	 */
	@RequestMapping(path="/login",method = RequestMethod.POST)
	public Map<String,Integer> login(HttpSession httpSession, String userPhone, String userPassword){
		Map<String,Integer> map = new HashMap<>();
		User user = userService.findByuserPhone(userPhone);
		//用户手机号码不存在
		if(user==null){
			map.put("info", 0);
		}else{
			if(MD5Util.md5(userPassword).equals(user.getUserPassword())){
				//将用户信息存入session
				httpSession.setAttribute("user",user);
				//密码正确
				map.put("info",1);
			}else{
				//密码错误
				map.put("info",-1);
			}
		}
		return map;
	}

	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(path="/register",method = RequestMethod.POST)
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

	/**
	 * 发送短信验证码
	 * @param userPhone
	 * @return
	 */
	@RequestMapping(path="/message/{userPhone}",method = RequestMethod.GET)
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


	/*
	 *后台用户处理
	 */


	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(path="/users/{userId}",method = RequestMethod.DELETE)
	public Map<String,Integer> delete(@PathVariable("userId") Integer userId){
		Map<String,Integer> map = new HashMap<>();
		if(userService.delectByid(userId)){
			map.put("upte", 1);
		}else{
			map.put("upte", 0);
		}
		return map;
	}

	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="update",method = RequestMethod.POST)
	public Map<String,Integer> update(User user){
		Map<String,Integer> map = new HashMap<>();
		if(userService.updateUser(user)){
			map.put("upte", 1);
		}else{
			map.put("upte", 0);
		}
		return map;
	}

	/**
	 * 忘记密码修改密码
	 * @param userPhone
	 * @param newPassword
	 * @return
	 */
	@RequestMapping(path="/update_1",method = RequestMethod.POST)
	public Map<String,Integer> updateBypassword(String userPhone,String newPassword){
		Map<String,Integer> map = new HashMap<>();
		User user = userService.findByuserPhone(userPhone);
		if(user!=null){
			if(userService.updateBypassword(userPhone, newPassword)){
				map.put("upte", 1);
			}else{
				map.put("upte", 0);
			}
		}else{
			map.put("upte",-1);
		}
		return map;
	}

	/**
	 * 修改密码
	 * @param userId
	 * @param Password
	 * @return
	 */
	@RequestMapping(path="/update_2",method = RequestMethod.POST)
	public String updateByword(Integer userId,String Password,String newPassword){
		Map<String,Object> map = new HashMap<>();
		String str = "";
		User user = userService.selectById(userId);
		if(user!=null){
			String oldPassword = user.getUserPassword();
			if (MD5Util.md5(Password).equals(oldPassword)) {
				if (userService.updateByps(userId, newPassword)) {
					//map.put("upte", 1);
					str="1";
				} else {
					//map.put("upte", -1);
					str="-1";
				}
			} else {
				//map.put("upte", 0);
				str="0";
			}

		}else {
			//map.put("upte",2);
			str="2";
		}
		return str;
	}

	/**
	 * 根据ID查找用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(path="/users/{userId}",method = RequestMethod.GET)
	public Map selectById(@PathVariable("userId") Integer userId){
		Map<String,Object> map = new HashMap<>();
		User user = userService.selectById(userId);
		if(user==null){
			map.put("select",0);
		}else{
			map.put("select",user);
		}
		return map;
	}

	/**
	 * 根据收货地址模糊搜索用户（分页）
	 * @param orderAddress
	 * @param currentPage
	 * @return
	 */
	@RequestMapping(path="/users/address",method = RequestMethod.POST)
	public List selectByaddress(String orderAddress,Integer currentPage){
		List result = new ArrayList();
		Page page = PageUtil.createPage(EVERYPAGE, userService.findByAddress(orderAddress),currentPage);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderAddress",orderAddress);
		map.put("beginIndex",page.getCurrentPage());
		map.put("everyPage",page.getEveryPage());
		List<User> list = userService.findByOrderAddress(map);
		result.add(list);
		result.add(page);
		return result;
	}

	/**
	 * 根据注册时间查找用户（分页）
	 * @param date1
	 * @param date2
	 * @param currentPage
	 * @return
	 */
	@RequestMapping(path="/users/time",method = RequestMethod.POST)
	public List selectBytime(String date1,String date2,Integer currentPage){
		List result = new ArrayList();
		Map<String,String> timemap = new HashMap<String,String>();
		timemap.put("start", date1);
		timemap.put("end", date2);
		Page page = PageUtil.createPage(EVERYPAGE,userService.findByTime(timemap),currentPage);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start",date1);
		map.put("end",date2);
		map.put("beginIndex",page.getCurrentPage());
		map.put("everyPage",page.getEveryPage());
		List<User> list = userService.findByCreateTime(map);
		result.add(list);
		result.add(page);
		return result;
	}

	/**
	 * 查找所有用户（分页）
	 * @param currentPage
	 * @return
	 */
	@RequestMapping(path="/users/page/{currentPage}",method = RequestMethod.GET)
	public List selectAllUser(@PathVariable("currentPage") Integer currentPage){
		List result = new ArrayList();
		Page page = PageUtil.createPage(EVERYPAGE,userService.selectUsernum(),currentPage);
		List<User> list = userService.selectAllUser(page);
		result.add(list);
		result.add(page);
		return result;
	}
}