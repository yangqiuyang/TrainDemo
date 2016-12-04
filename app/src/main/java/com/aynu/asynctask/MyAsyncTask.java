package com.aynu.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.aynu.callback.TrainCompanyCall;
import com.aynu.mydata.TrainCompany;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public class MyAsyncTask extends AsyncTask<Void,Void,List<TrainCompany>>{
    Context context;
    String content;
    TrainCompanyCall trainCompanyCall;
    List<TrainCompany> companyData = null;
    public MyAsyncTask(Context context,String content,TrainCompanyCall trainCompanyCall) {
        this.context=context;
        this.content=content;
        this.trainCompanyCall=trainCompanyCall;
    }
    @Override
    protected List<TrainCompany> doInBackground(Void... params) {

            String contents = NetHttpUtils.selectTrainComp(context, content);
            if(contents!=null){
              companyData = NetHttpUtils.getCompanyData(contents);
            }
            return companyData;
    }

    @Override
    protected void onPostExecute(List<TrainCompany> companies) {
        super.onPostExecute(companies);
        if(companies!=null) {
            trainCompanyCall.getTrainData(companies);
        }
        else {
            Toast.makeText(context,"无此公司信息",Toast.LENGTH_SHORT).show();
        }
    }
}
