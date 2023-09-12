package com.langjialing.helloworld.controller;

/**
 * @author 郎家岭伯爵
 * @time 2023/7/5 10:25
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
public class AA {

    @GetMapping("/a")
    public void a() {
        String dataBody = "";
        JSONObject outData = new JSONObject();
        // outData.put("containNo", "430001621000000032450003000730");// 轨迹
        // outData.put("containNo", "CNWUHAUSJFKAAUN30020001100023");// 明细
        // outData.put("containNo", "430001621000000032450003000730");// 明细
        // outData.put("dispatchDate", "2023-01-09 19:49:30");
        outData.put("traceNo", "1200906673859");
        String aString = outData.toString();
        try {
            dataBody = URLEncoder.encode(aString, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] strs = new String[] { aString, "90403E24D57409BC" };
        String url = "http://211.156.197.242:8080/querypush-gjcx/mailTrackGjcx/mailTrackGjcxTrace";
        StringBuffer sBuffer = new StringBuffer();
        for (String str : strs) {
            sBuffer.append(str);
        }

        String md5 = DigestUtils.md5Hex(sBuffer.toString());
        // 把md5进行base64编码
        Base64.Encoder encoder = Base64.getEncoder();
        md5 = encoder.encodeToString(md5.getBytes());
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            HttpPost post = new HttpPost(url);
            HttpResponse response = null;
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("sendID", "CRM_APP"));
            nvps.add(new BasicNameValuePair("msgKind", "JDPT"));
            nvps.add(new BasicNameValuePair("serialNo", "100000000001"));
            nvps.add(new BasicNameValuePair("sendDate", "20171111151500"));
            nvps.add(new BasicNameValuePair("receiveID", "JDPT"));
            nvps.add(new BasicNameValuePair("batchNo", "999"));
            nvps.add(new BasicNameValuePair("dataType", "1"));
            nvps.add(new BasicNameValuePair("dataDigest", md5));
            nvps.add(new BasicNameValuePair("msgBody", dataBody));
            // nvps.add(new BasicNameValuePair("traceNo", "1105593040899"));
            post.setEntity(new UrlEncodedFormEntity(nvps));
            try {
                response = client.execute(post);
                System.out.println("响应结果是：" + JSON.toJSONString(response));
            }
            catch (ClientProtocolException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("响应结果是：" + response.getStatusLine().getStatusCode());
            System.out.println("md5:" + md5);
            if (response.getStatusLine().getStatusCode() == 200) {
                String resEntityStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                JSONObject jsonObject = JSONObject.parseObject(resEntityStr);
                System.out.println("jsonObject=====>" + jsonObject);
                System.out.println("-------------url:\t" + url + "\ndataBody:" + dataBody
                        + "\n报文返回:\t" + resEntityStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                client.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return;

    }
}
