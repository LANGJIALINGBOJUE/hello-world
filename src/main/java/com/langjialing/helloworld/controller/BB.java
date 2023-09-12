package com.langjialing.helloworld.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 郎家岭伯爵
 * @time 2023/7/5 14:14
 */
@RestController
public class BB {
    @GetMapping("/b")
    public void b(){
        String dataBody = "";
        JSONObject outData = new JSONObject();
        outData.put("traceNo", "1200906673859,1200906673859,1200906762859,1296504358899,1296504367699");

        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = sdf3.format(new Date());

        // 获取当前时间的前一年
        Calendar calendar6 = Calendar.getInstance();
        calendar6.add(Calendar.YEAR, -1);
        String beginTime = sdf3.format(calendar6.getTime());
        outData.put("beginTime", beginTime);
        outData.put("endTime", endTime);


        String aString = outData.toString();
        try {
            dataBody = URLEncoder.encode(aString, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] strs = new String[] { aString, "90403E24D57409BC" };
        String url = "http://211.156.197.242:8080/querypush-gjcx/mailTrackGjcx/mailTrackGjcxPhone";
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
                System.out.println("-------------url:\t" + url + "\ndataBody:" + dataBody
                        + "\n报文返回:\t" + resEntityStr);
                JSONObject jsonObject = JSONObject.parseObject(resEntityStr);
                Collections.reverse(jsonObject.getJSONObject("waybillTrace").getJSONArray("msgList"));

                try{
                    JSONArray jsonArray = jsonObject.getJSONObject("waybillTrace").getJSONArray("msgList");
                    Collections.reverse(jsonArray);

                    String endOpTime = jsonArray.getJSONObject(0).get("lastOpTime").toString();
                    String beginOpTime = jsonArray.getJSONObject(jsonArray.size()-1).get("lastOpTime").toString();

                    // 计算endOptime和beginOpTime的时间差

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date1 = sdf.parse(endOpTime);
                    Date date2 = sdf.parse(beginOpTime);
                    long time1 = date1.getTime();
                    long time2 = date2.getTime();
                    long diff = time1 - time2;
                    // 计算出相差天数和小时数
                    long day = diff / (24 * 60 * 60 * 1000);
                    long hour = (diff / (60 * 60 * 1000) - day * 24);
                    String s = "耗时" + day + "天" + hour + "小时";
                    System.out.println(s);

                } catch (Exception e) {
                    e.printStackTrace();
                }

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
