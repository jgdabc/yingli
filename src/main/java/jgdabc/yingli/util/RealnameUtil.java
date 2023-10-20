package jgdabc.yingli.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RealnameUtil {
    public static boolean invokeIdCardApi(String requestUrl,String appCode,String name,String idCard) {

        Map<String, String> params = new HashMap<>();
        params.put("idcard", idCard);
        params.put("name", name);
        Boolean auResult = false;
        try {
          String  result = postForm(appCode, requestUrl, params);
            if (StrUtil.isNotEmpty(result)) {
                JSONObject jsonObject = JSONUtil.parseObj(result);
                JSONObject jsonResult = jsonObject.getJSONObject("result");
                if ("1".equals(jsonResult.getStr("res"))) {
                    auResult  =  true;

                }
            }
        } catch (IOException e) {
            auResult  =  false;
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return  auResult;


    }


    public static String postForm(String appCode, String url, Map<String, String> params) throws IOException, IOException {

        OkHttpClient client = new OkHttpClient.Builder().build();
        FormBody.Builder formbuilder = new FormBody.Builder();
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            formbuilder.add(key, params.get(key));
        }
        FormBody body = formbuilder.build();
        Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE " + appCode).post(body).build();
        Response response = client.newCall(request).execute();
        System.out.println("返回状态码" + response.code() + ",message:" + response.message());

        String result    =  null;
        if (response.code()==200){
            result = response.body().string();
        }

        return result;
    }


}
