package com.langjialing.helloworld.mapper;

import com.langjialing.helloworld.model.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 郎家岭伯爵
 * @time 2023/3/13 16:05
 */
@Mapper
public interface UserMapper {
    List<UserEntity> getUserList(UserEntity userEntity);

    void insertUser(UserEntity user);

    void updateUsers(List<UserEntity> users);

    void updateUsersInfo(@Param("usersInfo")List<Map> usersInfo, @Param("age") Integer age);
}
