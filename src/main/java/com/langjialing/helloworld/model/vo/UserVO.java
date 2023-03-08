package com.langjialing.helloworld.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 郎家岭伯爵
 * @time 2023/3/2 9:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserVO {
    private String name;
    private Integer age;
    private String address;
}
