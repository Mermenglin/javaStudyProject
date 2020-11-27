package com.mmling.mybatisRedisCache.service.impl;

import com.mmling.mybatisRedisCache.dto.LikedCountDTO;
import com.mmling.mybatisRedisCache.entity.UserLike;
import com.mmling.mybatisRedisCache.enums.LikedStatusEnum;
import com.mmling.mybatisRedisCache.service.LikedService;
import com.mmling.mybatisRedisCache.service.RedisService;
import com.mmling.mybatisRedisCache.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class LikedServiceImpl implements LikedService {

    @Autowired
    RedisService redisService;

    @Autowired
    StudentService userService;

    /**
     * 保存点赞记录
     *
     * @param userLike
     * @return
     */
    @Override
    public UserLike save(UserLike userLike) {
//        return likeRepository.save(userLike);
        return null;
    }

    /**
     * 批量保存或修改
     *
     * @param list
     */
    @Override
    public List<UserLike> saveAll(List<UserLike> list) {
//        return likeRepository.saveAll(list);
        return null;
    }

    /**
     * 根据被点赞人的id查询点赞列表（即查询都谁给这个人点赞过）
     *
     * @param likedUserId 被点赞人的id
     * @param pageable
     * @return
     */
    @Override
    public Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable) {
//        return likeRepository.findByLikedUserIdAndStatus(likedUserId, LikedStatusEnum.LIKE.getCode(), pageable);
        return null;
    }

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     *
     * @param likedPostId
     * @param pageable
     * @return
     */
    @Override
    public Page<UserLike> getLikedListByLikedPostId(String likedPostId, Pageable pageable) {
//        return likeRepository.findByLikedPostIdAndStatus(likedPostId, LikedStatusEnum.LIKE.getCode(), pageable);
        return null;
    }

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     *
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    @Override
    public UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
//        return likeRepository.findByLikedUserIdAndLikedPostId(likedUserId, likedPostId);
        return null;
    }

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    @Override
    @Transactional
    public void transLikedFromRedis2DB() {
        List<UserLike> list = redisService.getLikedDataFromRedis();
        for (UserLike like : list) {
            UserLike ul = getByLikedUserIdAndLikedPostId(like.getLikedUserId(), like.getLikedPostId());
            if (ul == null){
                //没有记录，直接存入
                save(like);
            }else{
                //有记录，需要更新
                ul.setStatus(like.getStatus());
                save(ul);
            }
        }
    }

    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    @Override
    @Transactional
    public void transLikedCountFromRedis2DB() {
        List<LikedCountDTO> list = redisService.getLikedCountFromRedis();
        for (LikedCountDTO dto : list) {
//            UserInfo user = userService.findById(dto.getId());
//            //点赞数量属于无关紧要的操作，出错无需抛异常
//            if (user != null){
//                Integer likeNum = user.getLikeNum() + dto.getCount();
//                user.setLikeNum(likeNum);
//                //更新点赞数量
//                userService.updateInfo(user);
//            }
        }
    }
}
