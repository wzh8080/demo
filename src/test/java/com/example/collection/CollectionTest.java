package com.example.collection;

import org.junit.jupiter.api.Test;

import java.util.HashMap;


public class CollectionTest {
    @Test
    public void t1(){
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        System.out.println(map);
        // 删除 key="1" 的元素
        map.remove("1");
        System.out.println(map);
        System.out.println(map.size());
        map.clear();
    }
}
