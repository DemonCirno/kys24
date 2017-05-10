package kys24.user.service;

import kys24.user.model.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
	//删除用户
	public boolean delectByid(Integer userId);
	//用户登录
	public User findByuserPhone(String userPhone);

	//用户注册
	public boolean addUser(User user);

	//修改用户
	public boolean updateUser(User user);

	//用户查询
	public User selectById(Integer userId);

	//根据用户注册时间查询
	public List<User> findByCreateTime(Map map);

	//根据用户收货地址来查
	public List<User> findByOrderAddress(String orderAddress);

	//忘记密码修改密码
	public boolean updateBypassword(String userPhone, String newPassword);

	//个人中心修改密码
	public boolean updateByps(Integer userId, String newPassword);

	//查找所有用户
	public List<User> selectAllUser();

}
