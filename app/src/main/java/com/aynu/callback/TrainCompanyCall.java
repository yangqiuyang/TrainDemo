package com.aynu.callback;

import com.aynu.mydata.TrainCompany;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */

public interface TrainCompanyCall {
    void getTrainData(List<TrainCompany> companies);
}
