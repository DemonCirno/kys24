package kys24.user.service.impl;

import java.util.List;
import java.util.Map;
import kys24.user.dao.UserMapper;
import kys24.user.model.User;
import kys24.user.service.IUserService;
import kys24.user.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    private UserMapper usermapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setUsermapping(UserMapper usermapping) {
        this.usermapping = usermapping;
    }

    @Override
    public User findByuserPhone(String userPhone){
        return usermapping.selectByuserPhone(userPhone);
    }

    @Override
    public boolean delectByid(Integer userId){
        return usermapping.deleteByPrimaryKey(userId)==0?false:true;
    }
    @Override
    public boolean addUser(User user) {
        user.setUserPassword(MD5Util.md5(user.getUserPassword()));
        int count = usermapping.insertSelective(user);
        return count==0?false:true;
    }

    @Override
    public boolean updateUser(User user) {
        user.setUserPassword(MD5Util.md5(user.getUserPassword()));
        int count = usermapping.updateByPrimaryKey(user);
        return count!=0?true:false;
    }
    @Override
    public User selectById(Integer userId){
        return usermapping.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> findByOrderAddress(String orderAddress) {
        return usermapping.selectByorderaddress(orderAddress);
    }

    @Override
    public List<User> findByCreateTime(Map map) {
        return usermapping.selectBycreatetime(map);
    }
    @Override
    public boolean updateBypassword(String userPhone,String newPassword){
        User user = usermapping.selectByuserPhone(userPhone);
        user.setUserPassword(MD5Util.md5(newPassword));
        return usermapping.updateByPrimaryKeySelective(user)!=0?true:false;
    }

    @Override
    public boolean updateByps(Integer userId,String newPassword){
        User user = usermapping.selectByPrimaryKey(userId);
        user.setUserPassword(MD5Util.md5(newPassword));
        //user.setUserPassword(newPassword);
        return usermapping.updateByPrimaryKeySelective(user)!=0?true:false;
    }

    @Override
    public List<User> selectAllUser() {
        return usermapping.selectAllUser();
    }
}
