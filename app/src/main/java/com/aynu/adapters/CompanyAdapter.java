package com.aynu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aynu.mydata.TrainCompany;
import com.aynu.traindemo.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public class CompanyAdapter  extends BaseAdapter{
    Context context;
    private List<TrainCompany> company;
    public CompanyAdapter(Context context) {
        this.context=context;
    }
    public void setCompanies(List<TrainCompany> company){
        this.company=company;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return company!=null?company.size():0;
    }

    @Override
    public Object getItem(int position) {
        return company.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.company_data,parent,false);
            x.view().inject(holder,convertView);
            convertView.setTag(holder);
        }
        else {
          holder= (ViewHolder) convertView.getTag();
        }
        TrainCompany trainCompany = company.get(position);
        holder.gs.setText(trainCompany.getGs());
        holder.phone.setText(trainCompany.getPhone());
        holder.jianjie.setText(trainCompany.getJianjie());
        holder.site.setText(trainCompany.getSite());
        return convertView;
    }
    static class ViewHolder{
        @ViewInject(R.id.gs)
        TextView  gs;
        @ViewInject(R.id.phone)
        TextView phone;
        @ViewInject(R.id.site)
        TextView site;//地址
        @ViewInject(R.id.jianjie)
        TextView  jianjie;//简介
    }
}
