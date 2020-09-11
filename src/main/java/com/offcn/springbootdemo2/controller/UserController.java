package com.offcn.springbootdemo2.controller;

import com.offcn.springbootdemo2.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private List<User> userList = Collections.synchronizedList(new ArrayList<User>());

    /***
     * 获取全部用户信息
     *@return
     */
    @GetMapping("/")
    public List<User> getUserList(){
        return userList;
    }
    /***
     * 新增用户
     *@paramuser
     *@return
     */
    @PostMapping("/")
    public String createUser(User user){
        userList.add(user);
        return"success";
    }
    /***
     * 获取指定 id 用户信息
     *@paramid
     *@return
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id){
        for(User user:userList){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }
/**
 * 更新指定 id 用户信息
 *@paramid
 *@paramuser
 *@return
 */
@PutMapping("/{id}")
public String updateUser(@PathVariable("id") Long id,User user){
    for(User user2:userList){
        if(user2.getId()==id){
            user2.setName(user.getName());
            user2.setAge(user.getAge());
        }
    }
    return"success";
}
    /***
     * 删除指定 id 用户
     *@paramid
     *@return
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userList.remove(getUser(id));
        return"success";
    }
}
