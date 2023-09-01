package com.langjialing.helloworld.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * @author 郎家岭伯爵
 * @time 2023/3/13 16:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserEntity {

    private Integer id;

    @NotEmpty(message = "用户名不能为空")
    private String userName;

    private String password;

    private long age = 25;
}
