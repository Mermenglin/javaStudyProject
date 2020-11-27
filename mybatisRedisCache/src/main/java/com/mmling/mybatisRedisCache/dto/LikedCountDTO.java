package com.mmling.mybatisRedisCache.dto;

import lombok.Data;

@Data
public class LikedCountDTO {

    private String key;

    private Integer value;

    public LikedCountDTO(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
}
