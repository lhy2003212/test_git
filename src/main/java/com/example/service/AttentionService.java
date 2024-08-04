package com.example.service;

import com.example.common.enums.RoleEnum;
import com.example.entity.*;
import com.example.mapper.AttentionMappper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttentionService {

    @Resource
    AttentionMappper attentionMappper;
    @Resource
    UserService userService;

    public void set(Attention attention) {
        Account currentUser = TokenUtils.getCurrentUser();
        attention.setUserId(currentUser.getId());
        Attention dblAttention = attentionMappper.selectUserAttention(attention);
        if (dblAttention == null) {
            attentionMappper.insert(attention);
        } else {
            attentionMappper.deleteById(dblAttention.getId());
        }
    }

    /**
     * 查询当前用户是否关注fid代表的用户
     */
    public Attention selectUserAttention(Integer fid) {
        Account currentUser = TokenUtils.getCurrentUser();
        Attention attention = new Attention();
        attention.setUserId(currentUser.getId());
        attention.setFid(fid);
        return attentionMappper.selectUserAttention(attention);
    }
//通过fid统计关注人数
    public int selectByFidCount(Integer fid) {
        return attentionMappper.selectByFidCount(fid);
    }


    public PageInfo<User> getByUserAttention(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Account currentUser = TokenUtils.getCurrentUser();
        Integer id = currentUser.getId();
        List<Attention> listAttention= attentionMappper.getByUserAttention(id);
        List<User> list = new ArrayList();
        for (Attention item : listAttention) {
            User user = userService.selectById(item.getFid());
            list.add(user);
        };
        return PageInfo.of(list);
    }

    public PageInfo<User> getByUserFans(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Account currentUser = TokenUtils.getCurrentUser();
        Integer fid = currentUser.getId();
        List<Attention> listAttention= attentionMappper.getByUserFans(fid);
        List<User> list = new ArrayList();
        for (Attention item : listAttention) {
            User user = userService.selectById(item.getUserId());
            list.add(user);
        };
        return PageInfo.of(list);
    }
}
