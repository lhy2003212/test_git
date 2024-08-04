package com.example.mapper;

import com.example.entity.Attention;

import java.util.List;

public interface AttentionMappper {
    Attention selectUserAttention(Attention attention);

    void insert(Attention attention);

    void deleteById(Integer id);

    int selectByFidCount(Integer id);

    List<Attention> getByUserAttention(Integer id);

    List<Attention> getByUserFans(Integer fid);
}
