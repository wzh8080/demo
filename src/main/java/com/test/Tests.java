package com.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

public class Tests {
    /** 三元表达式必须加括号 */
    @Test
    public void test01() {
        ArrayList<Map<String, String>> pathList = null;
        //if(pathList == null){
        //    System.out.println(1);
        //}else{
        //    System.out.println(pathList.size());
        //}
        System.out.println("pathList size() === " + (pathList == null?"pathList size() is null":pathList.size()+""));
        // 会报错：空指针，因为将 == 左边看作了整体（"pathList size() === " + pathList）
        System.out.println("pathList size() === " + pathList == null?"pathList size() is null":pathList.size()+"");
    }
}
