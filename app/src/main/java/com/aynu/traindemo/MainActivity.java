package com.aynu.traindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aynu.adapters.CompanyAdapter;
import com.aynu.asynctask.MyAsyncTask;
import com.aynu.callback.TrainCompanyCall;
import com.aynu.mydata.TrainCompany;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.select_train)
    private TextView selectTrain;
    @ViewInject(R.id.content_select_train)
    private EditText contentSelect;
    @ViewInject(R.id.company_list)
    private ListView companyList;
    @ViewInject(R.id.insert_train)
    TextView insertTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initActionBar();
        initListenter();
        //分支
        initView();
    }
    CompanyAdapter adapter;
    private void initView() {
        adapter=new CompanyAdapter(this);
        companyList.setAdapter(adapter);
    }

    /////监听事件
    private void initListenter() {
        selectTrain.setOnClickListener(this);//查询
        insertTrain.setOnClickListener(this);//插入
    }

    private void initActionBar() {
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }
    /////////////////适配器数据
    private void initListView(List<TrainCompany> trainCompany) {
        adapter.setCompanies(trainCompany);
    }
    String content;//查询的内容
    private List<TrainCompany> trainCompany;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_train:
                content= contentSelect.getText().toString().trim();
                if (!content.equals("")){
                  new MyAsyncTask(MainActivity.this,content,new TrainCompanyCall(){

                      @Override
                      public void getTrainData(List<TrainCompany> companies) {
                          trainCompany=companies;
                          initListView(trainCompany);
                      }
                  }).execute();
                }
                else {
                    Toast.makeText(MainActivity.this,"请输入公司信息",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.insert_train:
                Intent intent=new Intent(this,InsertAcitivity.class);
                startActivity(intent);
                break;
        }
    }



    }
