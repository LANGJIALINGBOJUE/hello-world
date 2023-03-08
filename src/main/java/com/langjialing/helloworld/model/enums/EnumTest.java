package com.langjialing.helloworld.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 郎家岭伯爵
 * @date 2023/3/2 9:06
 */
@Getter
@AllArgsConstructor
public enum EnumTest {
    ENUM_TEST_1("code1","name1",new ArrayList<String>(){{
        add("string1-1");
        add("string1-2");
        add("string1-3");
    }}),
    ENUM_TEST_2("code2","name2",new ArrayList<String>(){{
        add("string2-1");
        add("string2-2");
        add("string2-3");
    }}),
    ENUM_TEST_3("code3","name3",new ArrayList<String>(){{
        add("string3-1");
        add("string3-2");
        add("string3-3");
    }}),;

    private String code;
    private String name;
    private List<String> strings;

    public static HashMap<String, String> getCodeAndNameByStrings(String s){
        for (EnumTest e:EnumTest.values()){
            if (e.strings.contains(s)){
                HashMap<String, String> resultMap = new HashMap<>();
                resultMap.put("code", e.code);
                resultMap.put("name", e.name);
                return  resultMap;
            }
        }
        return null;
    }
}

