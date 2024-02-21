package com.test.httpdemo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RequestDemo {
    public static void main(String[] args) throws IOException {
        String url = "";
        String res = "{\"name\":\"张三\",\"age\":18}";
        RequestDemo requestDemo = new RequestDemo();
        requestDemo.execute(url, res);
    }
    public void execute(String url,String res) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type","application/json;charset=utf-8");
        httpPost.setEntity(new StringEntity(res, "UTF-8"));

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if(entity!=null){
            String entityStr= EntityUtils.toString(entity,"utf-8");
            System.out.println("返回报文: \n\r"+entityStr);
        }

    }
}
