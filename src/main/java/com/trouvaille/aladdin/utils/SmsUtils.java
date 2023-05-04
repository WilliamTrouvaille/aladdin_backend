/**
 * @projectName: reggieTakeOut
 * @package: com.trouvaille.reggietakeout.utils
 * @className: SmsUtils
 * @author: Eric
 * @description: 手机验证码
 * @date: 2022/7/22 15:57
 * @version: 1.0
 */
package com.trouvaille.aladdin.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SmsUtils {
    public static final String BaseUrl = "http://api01.monyun.cn:7901/sms/v2/std/";
    
    
    @SuppressWarnings ("unchecked")
    public static void singleSend (String ctel , String code) {
        JSONObject jsobj1 = new JSONObject();
        // 需要替换自己的发送账号的apikey
        jsobj1.put("apikey" , "3217aad418c3708d1e66060a1ce66f4b");
        // 需要替换自己的测试手机号码
        jsobj1.put("mobile" , ctel);
        
        jsobj1.put("content" ,
                "%d1%e9%d6%a4%c2%eb%a3%ba"
                        + code + "%a3%ac%b4%f2%cb%c0%b6%bc%b2%bb%d2%aa%b8%e6%cb%df%b1%f0%c8%cb%c5" +
                        "%b6%a3%a1");
        String URL = BaseUrl + "single_send";
        post(jsobj1 , URL);
        
    }
    
    public static String post (JSONObject json , String URL) {
        
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);
        
        post.setHeader("Content-Type" , "application/json");
        post.addHeader("Authorization" , "Basic YWRtaW46");
        String result = "";
        
        try {
            
            StringEntity s = new StringEntity(json.toString() , "utf-8");
            s.setContentEncoding(
                    new BasicHeader(HTTP.CONTENT_TYPE , "application/json"));
            post.setEntity(s);
            
            // 发送请求
            HttpResponse httpResponse = client.execute(post);
            
            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inStream , StandardCharsets.UTF_8));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                strber.append(line + "\n");
            }
            inStream.close();
            
            result = strber.toString();
            
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                System.out.println("请求服务器成功，做相应处理");
            } else {
                System.out.println("请求服务端失败");
            }
            
        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }
        
        return result;
    }
}
