package kys24.user.service;

import kys24.user.model.User;
import kys24.user.utils.Page;

import java.util.List;
import java.util.Map;

public interface IUserService {
	//删除用户
	 boolean delectByid(Integer userId);
	//用户登录
	User findByuserPhone(String userPhone);

	//用户注册
	boolean addUser(User user);

	//修改用户
	boolean updateUser(User user);

	//用户查询
	User selectById(Integer userId);

	//根据用户注册时间查询
	List<User> findByCreateTime(Map<String, Object> map);
	Integer findByTime(Map map);

	//根据用户收货地址来查
	List<User> findByOrderAddress(Map<String, Object> map);
	Integer findByAddress(String orderAddress);

	//忘记密码修改密码
	boolean updateBypassword(String userPhone, String newPassword);

	//个人中心修改密码
	boolean updateByps(Integer userId, String newPassword);

	//查找所有用户总数
	Integer selectUsernum();

	//按分页查找用户
	List<User> selectAllUser(Page page);

}
