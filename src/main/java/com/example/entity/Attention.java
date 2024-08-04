package com.example.entity;

import lombok.Data;

@Data
public class Attention {
    private Integer id;
    //关联id
    private Integer fid;
    //关注人ID
    private Integer userId;
}
