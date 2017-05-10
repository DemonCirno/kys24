package kys24.user.dao;

import kys24.user.model.User;
import java.util.List;
import java.util.Map;

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
    List<User> selectBycreatetime(Map<String, String> map);

    //根据地址查询用户
    List<User> selectByorderaddress(String orderAddress);

    //查找所有用户
    List<User> selectAllUser();


}