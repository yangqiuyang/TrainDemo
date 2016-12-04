package com.aynu.asynctask;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.aynu.mydata.TrainCompany;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/30.
 */
public class NetHttpUtils {
    public static String selectTrainComp(Context context, String content) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body=new FormBody.Builder()
                .add("sel",content)
                .build();
        Request request = new Request.Builder()
                .url("http://taopeixun.applinzi.com/sel.php")
                .post(body)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                    return response.body().string();
            }

        } catch (IOException e) {
            e.printStackTrace();


    }
        return null;
    }

    public static List<TrainCompany> getCompanyData(String contents) {
        List<TrainCompany> company=new ArrayList<>();
        try {
            JSONArray contetnsArray=new JSONArray(contents);
            for (int i = 0; i < contetnsArray.length(); i++) {
                JSONObject contentJson = contetnsArray.optJSONObject(i);
                TrainCompany trainCom=new TrainCompany();
                trainCom.setGs(contentJson.optString("gs"));
                trainCom.setId(contentJson.optString("id"));
                trainCom.setPhone(contentJson.optString("phone"));
                trainCom.setJianjie(contentJson.optString("jianjie"));
                trainCom.setSite(contentJson.optString("site"));
                company.add(trainCom);
            }
            return company;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
