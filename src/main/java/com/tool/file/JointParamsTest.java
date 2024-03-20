package com.tool.file;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 56465
 */
public class JointParamsTest {
    static String[] tempOSFXml = {"<field name=\"", "\" label=\"", "\"  type=\"STRING\" />"};
    static String[] tempJavaRsp = {"params.put(\"", "\", \"", "\");\t// ","1"};
    @Test
    public void osfXml(){
        String str = "businesstype\t业务品种\n" +
                "customername\t客户名称\n" +
                "businesssum\t申请金额（元）\n" +
                "bankmanager\t银行客户经理\n" +
                "managername\t业务经理\n" +
                "currency\t币种\n" +
                "busino\t业务号\n" +
                "flownode\t业务流程节点\n";
        List<String> result = convertStringWithTemplate(str, tempOSFXml);
        // 输出结果以验证
        result.forEach(System.out::println);
    }
    @Test
    public void rsp(){
        String str = "businesstype\t业务品种\t业务品种\n" +
                "customername\t客户名称\t客户名称\n" +
                "businesssum\t申请金额（元）\t申请金额（元）\n" +
                "bankmanager\t银行客户经理\t银行客户经理\n" +
                "managername\t业务经理\t业务经理\n" +
                "currency\t币种\t币种\n" +
                "busino\t业务号\t业务号\n" +
                "flownode\t业务流程节点\t业务流程节点\n";
        List<String> result = convertStringWithTemplate(str, tempJavaRsp);
        // 输出结果以验证
        result.forEach(System.out::println);
    }

    public static List<String> convertStringWithTemplate(String input, String[] template) {
        // 按行分割字符串
        String[] lines = input.split("\n");
        // 转换成List<String[]>
        List<String[]> keyValuePairs = new ArrayList<>();
        for (String line : lines) {
            String[] pair = line.trim().split("\t");
            if (pair.length == 2) {
                keyValuePairs.add(pair);
            }
        }

        // 使用模板和键值对生成新的字符串
        return keyValuePairs.stream()
                .map(pair -> template[0] + pair[0] + template[1] + pair[1] + template[2])
                .collect(Collectors.toList());
    }
}