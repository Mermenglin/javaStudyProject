create table `user_like`
(
    `id`            int         not null auto_increment,
    `liked_user_id` varchar(32) not null comment '被点赞的用户id',
    `liked_post_id` varchar(32) not null comment '点赞的用户id',
    `status`        tinyint(1)           default '1' comment '点赞状态，0取消，1点赞',
    `create_time`   timestamp   not null default current_timestamp comment '创建时间',
    `update_time`   timestamp   not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`id`),
    INDEX `liked_user_id` (`liked_user_id`),
    INDEX `liked_post_id` (`liked_post_id`)
) comment '用户点赞表';