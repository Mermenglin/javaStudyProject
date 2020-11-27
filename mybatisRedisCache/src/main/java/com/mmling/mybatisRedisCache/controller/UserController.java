package com.mmling.mybatisRedisCache.controller;

import com.mmling.mybatisRedisCache.VO.ResultVO;
import com.mmling.mybatisRedisCache.service.LikedService;
import com.mmling.mybatisRedisCache.service.RedisService;
import com.mmling.mybatisRedisCache.util.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    LikedService likedService;

    @Autowired
    RedisService redisService;

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/like")
    public ResultVO like(@RequestParam("likedUserId") String likedUserId,
                         @RequestParam("likedPostId") String likedPostId) {
        //先把数据存到Redis里,再定时存回数据库
        redisService.saveLiked2Redis(likedUserId, likedPostId);
        redisService.incrementLikedCount(likedUserId);
        return ResultVOUtils.success();
    }

    @PostMapping("/unlike")
    public ResultVO unlike(@RequestParam("likedUserId") String likedUserId,
                           @RequestParam("likedPostId") String likedPostId) {
        //取消点赞,先存到Redis里面，再定时写到数据库里
        redisService.unlikeFromRedis(likedUserId, likedPostId);
        redisService.decrementLikedCount(likedUserId);
        return ResultVOUtils.success();
    }

}
