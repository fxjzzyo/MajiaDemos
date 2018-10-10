package com.example.v_fanlulin.majiademo2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.v_fanlulin.majiademo2.R;
import com.example.v_fanlulin.majiademo2.javabean.Info;

import java.util.List;

/**
 * Created by v_fanlulin on 2018/10/9.
 */

public class MyListAdapter extends BaseAdapter {
    private List<Info> infos;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MyListAdapter(Context context, List<Info> infos) {
        this.mContext = context;
        this.infos = infos;
        this.mLayoutInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.list_item_layout, null);

            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvSalary = convertView.findViewById(R.id.tv_salary);
            holder.tvType = convertView.findViewById(R.id.tv_type);
            holder.ivPic = convertView.findViewById(R.id.iv_pic);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Info info = infos.get(position);


        holder.tvTitle.setText(info.getTitle());
        holder.tvSalary.setText(info.getSalary());
        holder.tvType.setText(info.getType());
        holder.ivPic.setImageResource(info.getPicture());
        return convertView;
    }

    class ViewHolder{
        TextView tvTitle,tvSalary,tvType;
        ImageView ivPic;
    }

}
