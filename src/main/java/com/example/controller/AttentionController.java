package com.example.controller;

import com.example.common.Result;
import com.example.entity.Attention;
import com.example.entity.Likes;
import com.example.entity.User;
import com.example.service.AttentionService;
import com.example.service.LikesService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/attention")
public class AttentionController {

    @Resource
    AttentionService attentionService;

    // 关注和取消
    @PostMapping("/set")
    public Result set(@RequestBody Attention attention) {
        attentionService.set(attention);
        return Result.success();
    }

//    获取当前用户关注列表
    @GetMapping("/getByUserAttention")
    public Result getByUserAttention(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<User> list = attentionService.getByUserAttention(pageNum, pageSize);
        return Result.success(list);
    }

//    获取当前用户粉丝列表
    @GetMapping("/getByUserFans")
    public Result getByUserFans(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<User> list = attentionService.getByUserFans(pageNum, pageSize);
        return Result.success(list);
    }

}
