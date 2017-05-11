package kys24.user.dao;

import kys24.user.model.User;
import java.util.List;
import java.util.Map;
import kys24.user.utils.Page;

public interface UserMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //根据userPhone查找用户
    User selectByuserPhone(String userPhone);

    //根据时间查询用户
    List<User> selectBycreatetime(Map<String, Object> map);
    Integer selectBytime(Map<String, Object> map);

    //根据地址查询用户
    List<User> selectByorderaddress(Map<String, Object> map);
    Integer selectByaddress(String orderAddress);


    //按分页查找所有用户
    List<User> selectAllUser(Page page);

    //查找用户总数
    Integer selectUsernum();


}