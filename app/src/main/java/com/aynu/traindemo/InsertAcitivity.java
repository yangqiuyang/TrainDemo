package com.aynu.traindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@ContentView(R.layout.activity_insert_acitivity)
public class InsertAcitivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.gsInsert)
    private EditText gsInsert;
    @ViewInject(R.id.phoneInsert)
    private EditText phoneInsert;
    @ViewInject(R.id.siteInsert)
    private EditText siteInsert;
    @ViewInject(R.id.jianjieInsert)
    private EditText jianjieInsert;
    @ViewInject(R.id.sure_insert)
    private TextView sureInsert;
    @ViewInject(R.id.cancle_insert)
    private TextView cancleInsert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initActionBar();
        initListener();
    }

    private void initListener() {
        sureInsert.setOnClickListener(this);
        cancleInsert.setOnClickListener(this);
    }

    private void initActionBar() {
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }

    String gs;
    String phone;
    String site;
    String jianjie;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_insert:
                 gs=gsInsert.getText().toString().trim();
               phone=phoneInsert.getText().toString().trim();
                site=siteInsert.getText().toString().trim();
                jianjie=jianjieInsert.getText().toString().trim();
                if(gs.equals("")){
                    Toast.makeText(this,"公司名不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            insertCompany(gs,phone,site,jianjie);
                        }
                    }).start();
                }
                break;
            case R.id.cancle_insert:
                this.finish();
                break;
        }
    }
    public void insertCompany(String gs, String phone, String site, String jianjie){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body=new FormBody.Builder()
                .add("gs",gs)
                .add("phone",phone)
                .add("jianjie",jianjie)
                .add("site",site)
                .build();
        Request request = new Request.Builder()
                .url("http://taopeixun.applinzi.com/add.php")
                .post(body)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(InsertAcitivity.this,"数据上传成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
